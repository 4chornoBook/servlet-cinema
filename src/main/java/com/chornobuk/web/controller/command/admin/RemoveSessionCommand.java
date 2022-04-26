package com.chornobuk.web.controller.command.admin;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.controller.command.ICommand;
import com.chornobuk.web.model.entity.MovieSession;
import com.chornobuk.web.model.repository.implementation.MovieSessionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RemoveSessionCommand implements ICommand {
	MovieSessionRepository movieSessionRepository = new MovieSessionRepository();
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		Logger logger = LogManager.getLogger(RemoveSessionCommand.class);
		String forward = Path.REDIRECT_COMMAND;
		try {
			long id = Long.parseLong(req.getParameter("sessionId"));
			movieSessionRepository.delete(new MovieSession(id));
			List<MovieSession> availableSessions = movieSessionRepository.getAvailable();
			req.getSession().setAttribute("availableSessions", availableSessions);
		} catch (NumberFormatException e ) {
			logger.error("session id is in wrong format", e);
		}
		try {
			resp.sendRedirect(Path.INDEX_PAGE);
		} catch (IOException e) {
			logger.error("redirect error", e);
		}
		return forward;
	}
}
