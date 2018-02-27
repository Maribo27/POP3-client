package by.maribo.pop3client.controller.command;

import by.maribo.pop3client.controller.Command;
import by.maribo.pop3client.controller.CommandType;
import by.maribo.pop3client.util.InvalidInputException;
import by.maribo.pop3client.controller.POP3ClientException;
import by.maribo.pop3client.model.ConnectionException;
import by.maribo.pop3client.model.POP3Client;

import static by.maribo.pop3client.util.CommandCreator.createCommand;
import static by.maribo.pop3client.util.ResponseCreator.getAllResponseLines;
import static by.maribo.pop3client.util.Validator.validateDigitParameter;

public class List implements Command {
	@Override
	public String execute(String parameters, POP3Client client) throws POP3ClientException {
		try {
			String command;
			String response;
			if (parameters.isEmpty()) {
				command = createCommand(CommandType.LIST);
				client.sendCommand(command);
				response = client.getResponse();
				return getAllResponseLines(client, response);
			} else {
				validateDigitParameter(parameters);
				command = createCommand(CommandType.LIST, parameters);
				client.sendCommand(command);
				response = client.getResponse();
				return response;
			}
		} catch (InvalidInputException | ConnectionException e) {
			throw new POP3ClientException(e.getMessage());
		}
	}
}