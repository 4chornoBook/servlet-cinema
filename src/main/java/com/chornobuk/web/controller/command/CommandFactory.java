package com.chornobuk.web.controller.command;

import com.chornobuk.web.controller.command.admin.*;
import com.chornobuk.web.controller.command.common.LogoutCommand;
import com.chornobuk.web.controller.command.common.ShowProfileCommand;
import com.chornobuk.web.controller.command.uncontrolled.*;
import com.chornobuk.web.controller.command.user.BuyTicketsCommand;
import com.chornobuk.web.controller.command.user.SubmitOrderCommand;

import java.util.HashMap;

public class CommandFactory {
	private static CommandFactory commandFactory = new CommandFactory();
	private HashMap<String, ICommand> commands = new HashMap<>();

	private CommandFactory() {
		commands.put("login", new LoginCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("newMovieForm", new NewMovieCommand());
		commands.put("addNewMovie", new AddNewMovieCommand());
		commands.put("newMovieSession", new NewSessionCommand());
		commands.put("addNewMovieSession", new AddNewMovieSessionCommand());
		commands.put("removeMovieSession", new RemoveSessionCommand());
		commands.put("showMovieSession", new ShowSessionCommand());
		commands.put("submitOrder", new SubmitOrderCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("buyTickets",new BuyTicketsCommand());
		commands.put("showProfile", new ShowProfileCommand());
		commands.put("pagination", new SessionsPaginationCommand());
		commands.put("sessionsSorting", new SessionsSortingCommand());
		commands.put("lang", new ChangeLanguageCommand());
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
