package by.maribo.pop3client.controller.command;

import by.maribo.pop3client.controller.Command;
import by.maribo.pop3client.controller.CommandType;
import by.maribo.pop3client.util.InvalidInputException;
import by.maribo.pop3client.controller.POP3ClientException;
import by.maribo.pop3client.model.ConnectionException;
import by.maribo.pop3client.model.POP3Client;

import static by.maribo.pop3client.util.CommandCreator.createCommand;
import static by.maribo.pop3client.util.Validator.validateCommandWithParameter;

public class Pass implements Command {
	@Override
	public String execute(String parameters, POP3Client client) throws POP3ClientException {
		try {
			validateCommandWithParameter(parameters);
			String command = createCommand(CommandType.PASS, parameters);
			client.sendCommand(command);
			return client.getResponse();
		} catch (InvalidInputException | ConnectionException e) {
			throw new POP3ClientException(e.getMessage());
		}
	}
}