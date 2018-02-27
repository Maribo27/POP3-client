package by.maribo.pop3client.util;

import by.maribo.pop3client.model.ConnectionException;
import by.maribo.pop3client.model.POP3Client;

import static by.maribo.pop3client.controller.StringConstants.END_OF_RESPONSE;

public class ResponseCreator {

	public static String readResponseLine(POP3Client client) throws ConnectionException {
		client.createResponse();
		return client.getResponse();
	}

	public static String getAllResponseLines(POP3Client client, String response) throws ConnectionException {
		StringBuilder result = new StringBuilder();
		result.append(response);
		result.append(" ");
		while (!(response = readResponseLine(client)).equals(END_OF_RESPONSE)) {
			result.append(response);
		}
		return result.toString();
	}
}
