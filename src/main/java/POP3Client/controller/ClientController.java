package POP3Client.controller;

import POP3Client.view_component.MainFrame;
import POP3Client.model.POP3Client;
import org.apache.log4j.Logger;

import java.io.IOException;

public class ClientController {
	private POP3Client pop3Client;
	private MainFrame mainFrame;

	private static final Logger logger = Logger.getLogger(ClientController.class);

	private boolean connected;

	public ClientController() {
		mainFrame = new MainFrame(this);
		pop3Client = POP3Client.getInstance();
	}

	public void connect(String host, String port) {
		final boolean invalidInput = host == null || port == null || !port.matches("[1-9]\\d*");
		if (invalidInput) {
			logger.error("Invalid input data");
			return;
		}
		int portNumber = Integer.parseInt(port);
		try {
			pop3Client = new POP3Client(host, portNumber);
			connected = true;
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public void disconnect() {
		pop3Client.disconnect();
		connected = false;
	}

	public void logIn(String user, String password) {
	}

	public void run() {
		mainFrame.show();
	}
}
