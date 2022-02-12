package com.chornobuk.web.controller.command;

import com.chornobuk.web.model.dao.MovieDao;
import com.chornobuk.web.model.dao.MovieSessionDao;
import com.chornobuk.web.model.entity.MovieSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSessionCommand implements ICommand{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String forward = "WEB-INF/jsp/movie.jsp";
		long sessionId = Long.parseLong(req.getParameter("sessionId"));

		MovieSessionDao sessionDao = new MovieSessionDao();
		MovieDao movieDao = new MovieDao();
		MovieSession session = sessionDao.get(sessionId);
		session.setMovie(movieDao.get(session.getMovieId()));
		req.setAttribute("session", session);

		return forward;
	}
}
