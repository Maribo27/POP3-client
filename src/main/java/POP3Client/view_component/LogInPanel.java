package POP3Client.view_component;

import POP3Client.controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class LogInPanel {
	private JPanel logInPanel = new JPanel();

	LogInPanel(ClientController controller) {
		logInPanel.setLayout(new BoxLayout(logInPanel, BoxLayout.X_AXIS));
		logInPanel.setPreferredSize(new Dimension(1000, 50));
		logInPanel.setMaximumSize(new Dimension(1000, 50));
		logInPanel.setMinimumSize(new Dimension(1000, 50));
		JLabel userLabel = new JLabel("user");
		logInPanel.add(userLabel);

		JTextField userTextField = new JTextField();
		userTextField.setMaximumSize(new Dimension(500, 50));
		logInPanel.add(userTextField);

		JLabel passwordLabel = new JLabel("password");
		logInPanel.add(passwordLabel);

		JTextField passwordTextField = new JTextField();
		passwordTextField.setMaximumSize(new Dimension(500, 50));
		logInPanel.add(passwordTextField);

		JButton logInButton = new JButton("logIn");
		logInButton.addActionListener(e -> controller.logIn(userTextField.getText(), passwordTextField.getText()));
		logInPanel.add(logInButton);

		JButton logOutButton = new JButton("logOut");
		//logOutButton.addActionListener(e -> controller.logOut());
		logInPanel.add(logOutButton);
	}

	public JPanel getLogInPanel() {
		return logInPanel;
	}
}