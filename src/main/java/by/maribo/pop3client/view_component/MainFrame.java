package by.maribo.pop3client.view_component;

import by.maribo.pop3client.controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
	private JFrame mainFrame = new JFrame("POP3 Client");
	private ResultPanel resultPanel;
	private ConnectionPanel connectionPanel;
	private LogInPanel logInPanel;
	private CommandPanel commandPanel;


	public MainFrame(ClientController controller) {
		mainFrame.setLayout(new FlowLayout());
		mainFrame.setPreferredSize(new Dimension(1000,600));
		mainFrame.setBackground(Color.DARK_GRAY);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		connectionPanel = new ConnectionPanel(controller);
		mainFrame.add(connectionPanel.getConnectionPanel());
		logInPanel = new LogInPanel(controller);
		mainFrame.add(logInPanel.getLogInPanel());
		commandPanel = new CommandPanel(controller);
		mainFrame.add(commandPanel.getCommandPanel());
		resultPanel = new ResultPanel();
		mainFrame.add(resultPanel.getResultPanel());
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
	}

	public void writeResult(String result) {
		resultPanel.writeResult(result);
	}

	public void show() {
		mainFrame.setVisible(true);
	}

	public void changeState(boolean connected, boolean authorized) {
		connectionPanel.changeButtonState(connected);
		logInPanel.changeState(connected, authorized);
		commandPanel.changeState(authorized);
	}
}
