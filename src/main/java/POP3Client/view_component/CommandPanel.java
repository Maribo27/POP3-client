package POP3Client.view_component;

import POP3Client.controller.CommandType;

import javax.swing.*;
import java.awt.*;

public class CommandPanel {
	private JPanel commandPanel = new JPanel();

	CommandPanel() {
		commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.X_AXIS));
		commandPanel.setPreferredSize(new Dimension(1000, 50));
		commandPanel.setMaximumSize(new Dimension(1000, 50));
		commandPanel.setMinimumSize(new Dimension(1000, 50));
		JComboBox<String> comboBox = new JComboBox<>();
		for (CommandType command : CommandType.values()) {
			comboBox.addItem(command.toString());
		}
		commandPanel.add(comboBox);
		JLabel label = new JLabel("parameter");
		commandPanel.add(label);
		JTextField textField = new JTextField();
		commandPanel.add(textField);
		JButton button = new JButton("execute");
		commandPanel.add(button);
	}

	public JPanel getCommandPanel() {
		return commandPanel;
	}
}
