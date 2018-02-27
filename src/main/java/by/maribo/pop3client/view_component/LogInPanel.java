package by.maribo.pop3client.view_component;

import by.maribo.pop3client.controller.ClientController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static by.maribo.pop3client.view_component.ViewConstants.PANEL_HEIGHT;
import static by.maribo.pop3client.view_component.ViewConstants.PANEL_WIDTH;

public class LogInPanel {
	private JPanel logInPanel = new JPanel();
	private JButton logInButton;
	private JButton logOutButton;

	LogInPanel(ClientController controller) {
		logInPanel.setLayout(new GridBagLayout());
		logInPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		logInPanel.setMaximumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		logInPanel.setMinimumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		logInPanel.setBorder(new TitledBorder("authorize"));

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.fill = GridBagConstraints.NONE;
		constraints.weighty   = 1;
		constraints.gridwidth = 1;
		constraints.gridx     = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.ipadx    = 25;
		constraints.ipady    = 45;
		constraints.insets = new Insets(0, 0, 5, 10);

		JLabel userLabel = new JLabel("user");
		logInPanel.add(userLabel, constraints);

		constraints.gridwidth = 3;
		constraints.gridx     = 1;
		constraints.ipadx    = 250;
		constraints.insets = new Insets(0, 0, 5, 0);

		JTextField userTextField = new JTextField();
		userTextField.setMaximumSize(new Dimension(500, 50));
		logInPanel.add(userTextField, constraints);

		constraints.gridwidth = 1;
		constraints.gridx     = 4;
		constraints.ipadx    = 15;
		constraints.insets = new Insets(0, 10, 5, 0);
		JLabel passwordLabel = new JLabel("password");
		logInPanel.add(passwordLabel, constraints);

		constraints.ipadx    = 240;
		constraints.insets = new Insets(0, 0, 5, 0);
		constraints.gridwidth = 3;
		constraints.gridx     = 5;
		JPasswordField passwordTextField = new JPasswordField();
		passwordTextField.setMaximumSize(new Dimension(500, 50));
		logInPanel.add(passwordTextField, constraints);

		constraints.ipadx    = 70;
		constraints.insets = new Insets(0, 5, 5, 5);
		constraints.gridwidth = 1;
		constraints.gridx     = 8;
		logInButton = new JButton("log in");
		logInButton.addActionListener(e -> {
			final char[] password = passwordTextField.getPassword();
			controller.authorize(userTextField.getText(), String.valueOf(password));
		});
		logInPanel.add(logInButton, constraints);

		constraints.gridwidth = 1;
		constraints.gridx     = 9;
		logOutButton = new JButton("quit");
		logOutButton.addActionListener(e -> controller.quit());
		logInPanel.add(logOutButton, constraints);
		for (Component component : logInPanel.getComponents()) {
			component.setEnabled(false);
		}
	}

	public JPanel getLogInPanel() {
		return logInPanel;
	}

	public void changeState(boolean connected, boolean authorized) {
		for (Component component : logInPanel.getComponents()) {
			component.setEnabled(connected);
		}
		logInButton.setEnabled(connected && !authorized);
		logOutButton.setEnabled(connected && authorized);
	}
}