package com.chornobuk.web.controller.command;

import com.chornobuk.web.model.dao.MovieDao;
import com.chornobuk.web.model.dao.MovieSessionDao;
import com.chornobuk.web.model.dao.TicketDao;
import com.chornobuk.web.model.entity.MovieSession;
import com.chornobuk.web.model.entity.Ticket;
import com.chornobuk.web.model.entity.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class ShowSessionCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String forward = "WEB-INF/jsp/common/movie.jsp";
		long sessionId = Long.parseLong(req.getParameter("sessionId"));
		UserRole role = (UserRole) req.getSession().getAttribute("role");
		MovieSessionDao sessionDao = new MovieSessionDao();
		MovieDao movieDao = new MovieDao();
		MovieSession session = sessionDao.get(sessionId);
		TicketDao ticketDao = new TicketDao();
		LinkedList<Ticket> tickets = new LinkedList<>(ticketDao.getTicketsBySession(session));
		HashSet<Integer> ticketsNumbers = tickets.stream()
				.map(x->x.getPlaceNumber())
				.collect(Collectors.toCollection(HashSet::new));
		req.setAttribute("ticketsNumber", ticketsNumbers);
		session.setMovie(movieDao.get(session.getMovieId()));
		req.getSession().setAttribute("session", session);
		return forward;
	}
}
