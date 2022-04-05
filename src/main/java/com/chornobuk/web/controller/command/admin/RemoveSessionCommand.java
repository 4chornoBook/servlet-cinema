package com.chornobuk.web.controller.command.admin;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.controller.command.ICommand;
import com.chornobuk.web.model.dao.MovieSessionDao;
import com.chornobuk.web.model.entity.MovieSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RemoveSessionCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		Logger logger = LogManager.getLogger(RemoveSessionCommand.class);
		String forward = Path.REDIRECT_COMMAND;
		MovieSession movieSession;
		MovieSessionDao movieSessionDao = new MovieSessionDao();
		long id = Long.parseLong( req.getParameter("sessionId"));
		movieSession = movieSessionDao.get(id);
		movieSessionDao.delete(movieSession);
		List<MovieSession> availableSessions = movieSessionDao.getAvailableSessions();
		req.getSession().setAttribute("availableSessions", availableSessions);

		try {
			resp.sendRedirect(Path.INDEX_PAGE);
		} catch (IOException e) {
			logger.error("redirect error", e);
		}
		return forward;
	}
}
