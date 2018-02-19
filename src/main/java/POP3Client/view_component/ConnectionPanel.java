package POP3Client.view_component;

import POP3Client.controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class ConnectionPanel {
	private JPanel connectionPanel = new JPanel();

	ConnectionPanel(ClientController controller) {
		connectionPanel.setLayout(new BoxLayout(connectionPanel, BoxLayout.X_AXIS));
		connectionPanel.setPreferredSize(new Dimension(1000, 50));
		connectionPanel.setMaximumSize(new Dimension(1000, 50));
		connectionPanel.setMinimumSize(new Dimension(1000, 50));

		JLabel hostLabel = new JLabel("host");
		connectionPanel.add(hostLabel);
		JTextField hostTextField = new JTextField();
		hostTextField.setMaximumSize(new Dimension(500, 50));
		connectionPanel.add(hostTextField);
		JLabel portLabel = new JLabel("port");
		connectionPanel.add(portLabel);
		JTextField portTextField = new JTextField();
		portTextField.setMaximumSize(new Dimension(500, 50));
		connectionPanel.add(portTextField);
		JButton connectButton = new JButton("connect");
		connectButton.addActionListener(e -> controller.connect(hostTextField.getText(), portTextField.getText()));
		connectionPanel.add(connectButton);
		JButton disconnectButton = new JButton("disconnect");
		disconnectButton.addActionListener(e -> controller.disconnect());
		connectionPanel.add(disconnectButton);
	}

	public JPanel getConnectionPanel() {
		return connectionPanel;
	}
}