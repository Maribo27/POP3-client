package by.maribo.pop3client.view_component;

import javax.swing.*;
import java.awt.*;

import static by.maribo.pop3client.view_component.ViewConstants.PANEL_WIDTH;

public class ResultPanel {

	private static final int HEIGHT = 370;
	private JScrollPane resultPanel;
	private JTextArea resultArea;

	ResultPanel() {
		resultArea = new JTextArea();
		resultPanel = new JScrollPane(resultArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		resultPanel.setPreferredSize(new Dimension(PANEL_WIDTH, HEIGHT));
		resultPanel.setMaximumSize(new Dimension(PANEL_WIDTH, HEIGHT));
		resultPanel.setMinimumSize(new Dimension(PANEL_WIDTH, HEIGHT));
		resultArea.setFont(new Font("Raleway", Font.PLAIN, 12));

		for (Component component : resultPanel.getComponents()) {
			component.setEnabled(false);
		}
	}

	public JScrollPane getResultPanel() {
		return resultPanel;
	}



	public void writeResult(String result) {
		resultArea.append(result);
	}
}
