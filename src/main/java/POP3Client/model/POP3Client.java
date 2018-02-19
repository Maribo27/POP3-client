package POP3Client.model;

import org.apache.log4j.Logger;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;

public class POP3Client {
	private static final Logger logger = Logger.getLogger(POP3Client.class);
	private static final POP3Client instance = new POP3Client();
	private SSLSocket socket;
	private BufferedReader inputStream;
	private BufferedWriter outputStream;
	private String response;

	private POP3Client(){}

	public static POP3Client getInstance() {
		return instance;
	}

	public POP3Client(String serverHost, int serverPort) throws IOException {
		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		socket = null;
		socket = (SSLSocket) factory.createSocket(serverHost, serverPort);
		socket.startHandshake();
		inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		response = inputStream.readLine();
		logger.info(response);
	}

	public String getResponse() {
		return response;
	}

	public void disconnect(){

	}
}