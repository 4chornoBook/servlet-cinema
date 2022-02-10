package com.chornobuk.web.controller.command;

import java.util.HashMap;

public class CommandFactory {
	private static CommandFactory commandFactory = new CommandFactory();
	private HashMap<String, ICommand> commands = new HashMap<>();

	private CommandFactory() {
		commands.put("login", new LoginCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("newMovieForm", new NewMovieFormCommand());
	}

	public static CommandFactory getInstance() {
		if (commandFactory != null)
			commandFactory = new CommandFactory();

		return commandFactory;
	}

	public ICommand getCommand(String command) {
		return commands.get(command);
	}
}
