package com.chornobuk.web.controller.command.uncontrolled;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.controller.command.ICommand;
import com.chornobuk.web.model.entity.Movie;
import com.chornobuk.web.model.entity.MovieSession;
import com.chornobuk.web.model.entity.Ticket;
import com.chornobuk.web.model.entity.UserRole;
import com.chornobuk.web.model.repository.implementation.MovieRepository;
import com.chornobuk.web.model.repository.implementation.MovieSessionRepository;
import com.chornobuk.web.model.repository.implementation.TicketRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class ShowSessionCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String forward = Path.SESSION_PAGE;
		long sessionId = Long.parseLong(req.getParameter("sessionId"));
		UserRole role = (UserRole) req.getSession().getAttribute("role");
		MovieSessionRepository movieSessionRepository = new MovieSessionRepository();
		MovieRepository movieRepository = new MovieRepository();
		MovieSession session = movieSessionRepository.get(new MovieSession(sessionId));
		TicketRepository ticketRepository = new TicketRepository();
		LinkedList<Ticket> tickets = new LinkedList<>(ticketRepository.getBySession(session));
		HashSet<Integer> ticketsNumbers = tickets.stream()
				.map(x->x.getPlaceNumber())
				.collect(Collectors.toCollection(HashSet::new));
		req.setAttribute("ticketsNumber", ticketsNumbers);
		session.setMovie(movieRepository.get(new Movie(session.getMovieId())));
		req.getSession().setAttribute("session", session);
		return forward;
	}
}
