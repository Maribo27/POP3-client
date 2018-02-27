package by.maribo.pop3client.controller;

import by.maribo.pop3client.model.ConnectionException;
import by.maribo.pop3client.model.POP3Client;
import by.maribo.pop3client.util.InvalidInputException;
import by.maribo.pop3client.view_component.MainFrame;
import org.apache.log4j.Logger;

import static by.maribo.pop3client.controller.StringConstants.INVALID_INPUT_DATA;
import static by.maribo.pop3client.controller.StringConstants.NEW_LINE;
import static by.maribo.pop3client.util.Validator.validateCommandWithParameter;
import static by.maribo.pop3client.util.Validator.validateDigitParameter;

public class ClientController {
	private POP3Client pop3Client;
	private MainFrame mainFrame;

	private static final Logger logger = Logger.getLogger(ClientController.class);

	private boolean connected;
	private boolean authorized;

	public ClientController() {
		mainFrame = new MainFrame(this);
		pop3Client = POP3Client.getInstance();
	}

	public void connect(String host, String port) {
		try {
			mainFrame.writeResult("[CLIENT] : connect to host - " + host + ", port - " + port);
			validateCommandWithParameter(host);
			validateDigitParameter(port);
			int portNumber = Integer.parseInt(port);
			pop3Client.connect(host, portNumber);
			mainFrame.writeResult("\n[SERVER] : " + pop3Client.getResponse());
			logger.info("CONNECTED");
			updateClientStatus(true, authorized);
		} catch (ConnectionException e) {
			mainFrame.writeResult("\n[SERVER] : " + e.getMessage() + NEW_LINE);
			logger.error(e.getMessage());
		} catch (InvalidInputException e) {
			mainFrame.writeResult("\n[SERVER] : " + e.getMessage() + NEW_LINE);
			logger.error(INVALID_INPUT_DATA + e.getMessage());
		}
	}

	public void disconnect() {
		try {
			mainFrame.writeResult("[CLIENT] : disconnect");
			pop3Client.disconnect();
			mainFrame.writeResult("\n[SERVER] : " + pop3Client.getResponse());
			logger.info("DISCONNECTED");
			updateClientStatus(false, authorized);
		} catch (ConnectionException e) {
			mainFrame.writeResult("\n[SERVER] : " + e.getMessage() + NEW_LINE);
			logger.error(e.getMessage());
		}
	}

	public void authorize(String user, String password) {
		execute(CommandType.USER, user);
		boolean status = execute(CommandType.PASS, password);
		updateClientStatus(connected, status);
	}

	public void run() {
		mainFrame.show();
	}

	public void quit() {
		execute(CommandType.QUIT, "");
		updateClientStatus(connected, false);
	}

	public boolean execute(CommandType commandType, String text) {
		try {
			final CommandDirector director = CommandDirector.getInstance();
			Command command = director.getCommand(commandType.name());
			mainFrame.writeResult("[CLIENT] : " + commandType.name() + " " + text);
			String result = command.execute(text, pop3Client);
			mainFrame.writeResult("\n[SERVER] : " + result);
			return true;
		} catch (POP3ClientException e) {
			mainFrame.writeResult("\n[SERVER] : " + e.getMessage() + NEW_LINE);
			logger.error(e.getMessage());
		}
		return false;
	}

	private void updateClientStatus(boolean connected, boolean authorized) {
		this.connected = connected;
		this.authorized = authorized;
		mainFrame.changeState(connected, authorized);
	}
}