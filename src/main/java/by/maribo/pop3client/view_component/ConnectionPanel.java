package by.maribo.pop3client.view_component;

import by.maribo.pop3client.controller.ClientController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static by.maribo.pop3client.view_component.ViewConstants.PANEL_HEIGHT;
import static by.maribo.pop3client.view_component.ViewConstants.PANEL_WIDTH;

public class ConnectionPanel {
	private JPanel connectionPanel = new JPanel();
	private JButton connectButton = new JButton("connect");
	private JButton disconnectButton = new JButton("disconnect");


	ConnectionPanel(ClientController controller) {
		connectionPanel.setLayout(new GridBagLayout());
		connectionPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		connectionPanel.setMaximumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		connectionPanel.setMinimumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		connectionPanel.setBorder(new TitledBorder("connect"));

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.fill = GridBagConstraints.NONE;
		constraints.weighty   = 1.0;
		constraints.gridwidth = 1;
		constraints.gridx     = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.ipadx    = 30;
		constraints.ipady    = 45;
		constraints.insets = new Insets(0, 10, 5, 10);

		JLabel hostLabel = new JLabel("host");
		connectionPanel.add(hostLabel, constraints);

		constraints.gridwidth = 3;
		constraints.gridx     = 1;
		constraints.ipadx    = 250;
		constraints.insets = new Insets(0, 0, 5, 0);

		JTextField hostTextField = new JTextField("pop.gmail.com");
		hostTextField.setMaximumSize(new Dimension(500, 50));
		connectionPanel.add(hostTextField, constraints);

		constraints.gridwidth = 1;
		constraints.gridx     = 4;
		constraints.ipadx    = 30;
		constraints.insets = new Insets(0, 10, 5, 10);
		JLabel portLabel = new JLabel("port");
		connectionPanel.add(portLabel, constraints);

		constraints.ipadx    = 250;
		constraints.insets = new Insets(0, 0, 5, 0);
		constraints.gridwidth = 3;
		constraints.gridx     = 5;
		JTextField portTextField = new JTextField("995");
		portTextField.setMaximumSize(new Dimension(500, 50));
		connectionPanel.add(portTextField, constraints);

		constraints.ipadx    = 50;
		constraints.insets = new Insets(0, 5, 5, 5);
		constraints.gridwidth = 1;
		constraints.gridx     = 8;
		connectButton.addActionListener(e -> controller.connect(hostTextField.getText(), portTextField.getText()));
		connectButton.setEnabled(true);
		connectionPanel.add(connectButton, constraints);

		constraints.gridwidth = 1;
		constraints.gridx     = 9;
		disconnectButton.addActionListener(e -> controller.disconnect());
		disconnectButton.setEnabled(false);
		connectionPanel.add(disconnectButton, constraints);
	}

	public JPanel getConnectionPanel() {
		return connectionPanel;
	}

	public void changeButtonState(boolean state) {
		connectButton.setEnabled(!state);
		disconnectButton.setEnabled(state);
	}
}