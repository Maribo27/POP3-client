import by.maribo.pop3client.controller.ClientController;
import org.apache.log4j.Logger;

import javax.swing.*;

public class Main {
	private static final Logger logger = Logger.getLogger(ClientController.class);
	public static void main(String[] args) {
		try {
			String systemLookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(systemLookAndFeelClassName);
			logger.info("Style applied");
		} catch (UnsupportedLookAndFeelException e) {
			logger.error("Style is not supported on current OS");
		} catch (Exception e) {
			logger.error("Cannot apply style");
		}
		ClientController controller = new ClientController();
		controller.run();
	}
}
