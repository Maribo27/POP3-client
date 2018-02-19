package POP3Client.controller;

import POP3Client.controller.command.User;

import java.util.HashMap;
import java.util.Map;

public class CommandDirector {
	private Map<CommandType, Command> commandMap = new HashMap<>();
	private static CommandDirector instance = new CommandDirector();

	private CommandDirector() {
		commandMap.put(CommandType.USER, new User());
	}

	public Command getCommand(String name) {
		CommandType commandName = CommandType.valueOf(name);
		return commandMap.get(commandName);
	}
	public static CommandDirector getInstance() {
		return instance;
	}
}
