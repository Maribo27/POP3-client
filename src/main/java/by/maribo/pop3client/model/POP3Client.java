package by.maribo.pop3client.model;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

import static by.maribo.pop3client.controller.StringConstants.NEW_LINE;

public class POP3Client {
	private static final POP3Client instance = new POP3Client();

	private static final String ERR = "-ERR";

	private SSLSocket socket;
	private BufferedReader inputStream;
	private BufferedWriter outputStream;
	private String response;

	private POP3Client(){}

	public static POP3Client getInstance() {
		return instance;
	}

	public void connect(String serverHost, int serverPort) throws ConnectionException {
		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		socket = null;
		try {
			socket = (SSLSocket) factory.createSocket(serverHost, serverPort);
			socket.startHandshake();
			inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			createResponse();
		} catch (IOException e) {
			throw new ConnectionException("Error occurred while connecting");
		}
	}

	private boolean isConnected() {
		return socket != null && socket.isConnected();
	}

	public void disconnect() throws ConnectionException {
		if (!isConnected()) {
			throw new ConnectionException("Not connected to a host");
		}

		try {
			socket.close();
		} catch (IOException e) {
			throw new ConnectionException("Error occurred while closing socket");
		}
		inputStream = null;
		outputStream = null;
	}

	public void createResponse() throws ConnectionException {
		response = null;
		try {
			StringBuilder result = new StringBuilder();
			String data = inputStream.readLine();
			result.append(data);
			result.append(NEW_LINE);
			response = result.toString();
			if (response.startsWith(ERR)) {
				throw new ConnectionException("Server has returned an error: " + response.replaceFirst(ERR, ""));
			}
		} catch (IOException e) {
			throw new ConnectionException("Error occurred while reading response");
		}
	}

	public String getResponse() {
		return response;
	}

	public void sendCommand(String command) throws ConnectionException {
		try {
			inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outputStream.write(command);
			outputStream.flush();
			createResponse();
		} catch (IOException e) {
			throw new ConnectionException("Error occurred while sending command");
		}
	}
}