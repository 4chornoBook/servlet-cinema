package com.chornobuk.web.controller.command.common;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.controller.command.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		Logger logger = LogManager.getLogger(LogOutCommand.class);
		String forward = Path.REDIRECT_COMMAND;
		HttpSession session = req.getSession();
		if (session != null) {
			session.invalidate();
		}
		try {
			resp.sendRedirect(Path.LOGIN_PAGE);
		} catch (IOException e) {
			logger.error("redirect error", e);
		}
		return forward;
	}
}
