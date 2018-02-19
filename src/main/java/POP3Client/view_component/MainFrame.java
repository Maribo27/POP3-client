package POP3Client.view_component;

import POP3Client.controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
	private JFrame mainFrame = new JFrame("POP3 Client");
	private ResultPanel resultPanel;

	public MainFrame(ClientController controller) {
		mainFrame.setLayout(new FlowLayout());
		mainFrame.setPreferredSize(new Dimension(1000,600));
		mainFrame.setBackground(Color.DARK_GRAY);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		ConnectionPanel connectionPanel = new ConnectionPanel(controller);
		mainFrame.add(connectionPanel.getConnectionPanel());
		LogInPanel logInPanel = new LogInPanel(controller);
		mainFrame.add(logInPanel.getLogInPanel());
		CommandPanel commandPanel = new CommandPanel();
		mainFrame.add(commandPanel.getCommandPanel());
		resultPanel = new ResultPanel();
		mainFrame.add(resultPanel.getResultPanel());
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
	}

	public void writeResult(String result) {
		resultPanel.writeResult(result);
	}

	public void clearResults() {
		resultPanel.clearResults();
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void show() {
		mainFrame.setVisible(true);
	}
}
