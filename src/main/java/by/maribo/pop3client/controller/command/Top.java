package by.maribo.pop3client.controller.command;

import by.maribo.pop3client.controller.Command;
import by.maribo.pop3client.controller.CommandType;
import by.maribo.pop3client.util.InvalidInputException;
import by.maribo.pop3client.controller.POP3ClientException;
import by.maribo.pop3client.model.ConnectionException;
import by.maribo.pop3client.model.POP3Client;

import static by.maribo.pop3client.controller.StringConstants.END_OF_RESPONSE;
import static by.maribo.pop3client.util.CommandCreator.createCommand;
import static by.maribo.pop3client.util.ResponseCreator.readResponseLine;
import static by.maribo.pop3client.util.Validator.validateDigitParameters;

public class Top implements Command {
	@Override
	public String execute(String parameters, POP3Client client) throws POP3ClientException {
		try {
			validateDigitParameters(parameters);
			String command = createCommand(CommandType.TOP, parameters);
			client.sendCommand(command);
			String response;
			StringBuilder result = new StringBuilder();
			while (!(response = readResponseLine(client)).equals(END_OF_RESPONSE)){
				result.append(response);
			}
			return result.toString();
		} catch (InvalidInputException | ConnectionException e) {
			throw new POP3ClientException(e.getMessage());
		}
	}
}