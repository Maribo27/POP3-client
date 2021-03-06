package by.maribo.pop3client.controller.command;

import by.maribo.pop3client.controller.Command;
import by.maribo.pop3client.controller.CommandType;
import by.maribo.pop3client.controller.POP3ClientException;
import by.maribo.pop3client.domain.HeaderBuilder;
import by.maribo.pop3client.domain.Message;
import by.maribo.pop3client.model.ConnectionException;
import by.maribo.pop3client.model.POP3Client;
import by.maribo.pop3client.util.InvalidInputException;

import static by.maribo.pop3client.controller.StringConstants.END_OF_RESPONSE;
import static by.maribo.pop3client.controller.StringConstants.NEW_LINE;
import static by.maribo.pop3client.util.CommandCreator.createCommand;
import static by.maribo.pop3client.util.ResponseCreator.readResponseLine;
import static by.maribo.pop3client.util.Validator.validateDigitParameter;

public class Retr implements Command {
	private static final String DIV_DIR_AUTO = "Content-Type: text/plain; charset=utf-8\\n";
	private static final String DIV = "Content-Type: .+; charset=utf-8\\n";
	private static final String LINE = "<div[^>]+>";
	private static final String END = "</div>";
	private int messageNumber;

	@Override
	public String execute(String parameters, POP3Client client) throws POP3ClientException {
		try {
			validateDigitParameter(parameters);
			messageNumber = Integer.parseInt(parameters);
			String command = createCommand(CommandType.RETR, parameters);
			client.sendCommand(command);
			return createMessage(client);
		} catch (InvalidInputException | ConnectionException e) {
			throw new POP3ClientException(e.getMessage());
		}
	}

	private String createMessage(POP3Client client) throws ConnectionException {
		String response;
		StringBuilder body = new StringBuilder();
		Message message = new Message();
		message.setNumber(messageNumber);

		HeaderBuilder builder = new HeaderBuilder();
		while (!(response = readResponseLine(client)).equals(END_OF_RESPONSE)) {
			int colonPosition = response.indexOf(":");
			if (colonPosition != -1) {
				String headerName = response.substring(0, colonPosition);
				String value = response.substring(colonPosition + 2, response.length());
				builder.addToHeader(headerName, value);
			}
			if (response.matches(DIV_DIR_AUTO) && fullInfo(builder)) {
				break;
			}
		}
		while (!(response = readResponseLine(client)).equals(DIV)) {
			if (response.matches(DIV)) {
				break;
			}
			response = response.replace("=\n", "");
			body.append(response);
		}
		while (!response.equals(END_OF_RESPONSE)) {
			response = readResponseLine(client);
		}
		String messageBody = body.toString();
		message.setHeader(builder.buildHeader());
		message.setBody(messageBody);
		return message.toString();
	}

	private boolean fullInfo(HeaderBuilder builder) {
		final Message.Header header = builder.buildHeader();
		return header.getSubject() != null && header.getFrom() != null && header.getDate() != null;
	}
}