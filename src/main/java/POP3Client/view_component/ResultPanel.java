package POP3Client.view_component;

import javax.swing.*;
import java.awt.*;

public class ResultPanel {

	private JScrollPane resultPanel;
	private JTextArea resultArea;

	ResultPanel() {
		resultArea = new JTextArea();
		resultPanel = new JScrollPane(resultArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		resultPanel.setPreferredSize(new Dimension(1000, 300));
		resultPanel.setMaximumSize(new Dimension(1000, 300));
		resultPanel.setMinimumSize(new Dimension(1000, 300));
	}

	public JScrollPane getResultPanel() {
		return resultPanel;
	}



	public void writeResult(String result) {
		resultArea.append(result);
	}

	public void clearResults() {
		resultArea.setText("");
	}

}
