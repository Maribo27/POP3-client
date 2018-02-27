package by.maribo.pop3client.view_component;

import by.maribo.pop3client.controller.ClientController;
import by.maribo.pop3client.controller.CommandDirector;
import by.maribo.pop3client.controller.CommandType;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static by.maribo.pop3client.view_component.ViewConstants.PANEL_HEIGHT;
import static by.maribo.pop3client.view_component.ViewConstants.PANEL_WIDTH;

public class CommandPanel {
	private JPanel commandPanel = new JPanel();

	CommandPanel(ClientController controller) {
		commandPanel.setLayout(new GridBagLayout());
		commandPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		commandPanel.setMaximumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		commandPanel.setMinimumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		commandPanel.setBorder(new TitledBorder("choose command"));

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.fill = GridBagConstraints.NONE;
		constraints.weighty   = 1;
		constraints.gridwidth = 2;
		constraints.gridx     = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.ipadx    = 100;
		constraints.ipady    = 45;
		constraints.insets = new Insets(0, 5, 5, 10);
		JComboBox<String> comboBox = new JComboBox<>();
		for (CommandType command : CommandType.values()) {
			boolean common = CommandDirector.getInstance().isCommon(command);
			if (common) {
				continue;
			}
			comboBox.addItem(command.toString());
		}
		commandPanel.add(comboBox, constraints);

		constraints.gridwidth = 1;
		constraints.gridx     = 2;
		constraints.ipadx    = 30;
		constraints.insets = new Insets(0, 20, 5, 10);
		JLabel label = new JLabel("parameter");
		commandPanel.add(label, constraints);

		constraints.gridwidth = 5;
		constraints.gridx     = 3;
		constraints.ipadx    = 400;
		constraints.insets = new Insets(0, 0, 5, 0);
		JTextField textField = new JTextField();
		commandPanel.add(textField, constraints);

		constraints.ipadx    = 100;
		constraints.insets = new Insets(0, 20, 5, 5);
		constraints.gridwidth = 2;
		constraints.gridx     = 8;
		JButton button = new JButton("execute");
		button.addActionListener(e -> {
			Object selectedItem = comboBox.getSelectedItem();
			if (selectedItem != null) {
				CommandType command = CommandType.valueOf(selectedItem.toString());
				controller.execute(command, textField.getText());
			}
		});
		commandPanel.add(button, constraints);
		for (Component component : commandPanel.getComponents()) {
			component.setEnabled(false);
		}
	}

	public JPanel getCommandPanel() {
		return commandPanel;
	}

	public void changeState(boolean connected) {
		for (Component component : commandPanel.getComponents()) {
			component.setEnabled(connected);
		}
	}
}
