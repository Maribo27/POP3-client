package by.maribo.pop3client.controller;

import by.maribo.pop3client.controller.command.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandDirector {
	private Map<CommandType, Command> commandMap = new HashMap<>();
	private java.util.List<CommandType> commonCommands = new ArrayList<>();
	private static CommandDirector instance = new CommandDirector();

	private CommandDirector() {
		createCommands();
		createList();
	}

	private void createCommands() {
		commandMap.put(CommandType.USER, new User());
		commandMap.put(CommandType.PASS, new Pass());
		commandMap.put(CommandType.DELE, new Dele());
		commandMap.put(CommandType.QUIT, new Quit());
		commandMap.put(CommandType.UIDL, new Uidl());
		commandMap.put(CommandType.STAT, new Stat());
		commandMap.put(CommandType.LIST, new List());
		commandMap.put(CommandType.RETR, new Retr());
		commandMap.put(CommandType.NOOP, new Noop());
		commandMap.put(CommandType.RSET, new Rset());
		commandMap.put(CommandType.TOP, new Top());
	}

	private void createList() {
		commonCommands.add(CommandType.USER);
		commonCommands.add(CommandType.PASS);
		commonCommands.add(CommandType.QUIT);
	}

	public Command getCommand(String name) {
		CommandType commandName = CommandType.valueOf(name);
		return commandMap.get(commandName);
	}

	public boolean isCommon(CommandType commandType) {
		return commonCommands.contains(commandType);
	}

	public static CommandDirector getInstance() {
		return instance;
	}
}
