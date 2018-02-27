package by.maribo.pop3client.util;

import by.maribo.pop3client.controller.CommandType;

import static by.maribo.pop3client.controller.StringConstants.NEW_LINE;

public class CommandCreator {
	public static String createCommand(CommandType command, String parameters){
		StringBuilder fullCommand = new StringBuilder(command.toString());
		if (!parameters.isEmpty()) {
			fullCommand.append(" ");
			fullCommand.append(parameters);
		}
		fullCommand.append(NEW_LINE);
		return fullCommand.toString();
	}

	public static String createCommand(CommandType command){
		return command.toString() + NEW_LINE;
	}

}