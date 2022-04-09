package com.chornobuk.web.controller.command.uncontrolled;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.controller.command.ICommand;
import com.chornobuk.web.model.MovieSessionQueryConstructor;
import com.chornobuk.web.model.entity.MovieSession;
import com.chornobuk.web.model.repository.implementation.MovieSessionRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SessionsSortingCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String forward = Path.INDEX_PAGE;
		String selected = "selected";

		MovieSessionQueryConstructor constructor = new MovieSessionQueryConstructor();
		String dateSort = req.getParameter("dateSort");
		String timeSort = req.getParameter("timeSort");
		String ticketSort = req.getParameter("ticketsSort");
		String movieNameSort = req.getParameter("movieNameSort");
		String movieNameFilter = req.getParameter("movieFilter");
		if (dateSort != null && !dateSort.isEmpty()) {
			constructor.setSortingByDate(dateSort);
			if (dateSort.equals("ascending")) {
				req.getSession().setAttribute("byDateAscending", selected);
				req.getSession().removeAttribute("byDateDescending");
				req.getSession().removeAttribute("byDateNone");
			} else {
				req.getSession().setAttribute("byDateDescending", selected);
				req.getSession().removeAttribute("byDateAscending");
				req.getSession().removeAttribute("byDateNone");
			}
		} else {
			req.getSession().setAttribute("byDateNone", selected);
			req.getSession().removeAttribute("byDateAscending");
			req.getSession().removeAttribute("byDateDescending");
		}
		if (timeSort != null && !timeSort.isEmpty()) {
			constructor.setSortingByTime(timeSort);
			if (timeSort.equals("ascending")) {
				req.getSession().setAttribute("byTimeAscending", selected);
				req.getSession().removeAttribute("byTimeDescending");
				req.getSession().removeAttribute("byTimeNone");
			} else {
				req.getSession().setAttribute("byTimeDescending", selected);
				req.getSession().removeAttribute("byTimeAscending");
				req.getSession().removeAttribute("byTimeNone");
			}
		} else {
			req.getSession().setAttribute("byTimeNone", selected);
			req.getSession().removeAttribute("byTimeAscending");
			req.getSession().removeAttribute("byTimeDescending");
		}
		if (ticketSort != null && !ticketSort.isEmpty()) {
			constructor.setSortingByTickets(ticketSort);
			if (ticketSort.equals("ascending")) {
				req.getSession().setAttribute("byTicketsAscending", selected);
				req.getSession().removeAttribute("byTicketsDescending");
				req.getSession().removeAttribute("byTicketsNone");
			} else {
				req.getSession().setAttribute("byTicketsDescending", selected);
				req.getSession().removeAttribute("byTicketsAscending");
				req.getSession().removeAttribute("byTicketsNone");
			}
		} else {
			req.getSession().setAttribute("byTicketsNone", selected);
			req.getSession().removeAttribute("byTicketsAscending");
			req.getSession().removeAttribute("byTicketsDescending");
		}
		if (movieNameSort != null && !movieNameSort.isEmpty()) {
			constructor.setSortingByMovieName(movieNameSort);
			if (movieNameSort.equals("ascending")) {
				req.getSession().setAttribute("byNameAscending", selected);
				req.getSession().removeAttribute("byNameDescending");
				req.getSession().removeAttribute("byNameNone");
			} else {
				req.getSession().setAttribute("byNameDescending", selected);
				req.getSession().removeAttribute("byNameAscending");
				req.getSession().removeAttribute("byNameNone");
			}
		} else {
			req.getSession().setAttribute("byNameNone", selected);
			req.getSession().removeAttribute("byNameAscending");
			req.getSession().removeAttribute("byNameDescending");
		}
		if (movieNameFilter != null && !movieNameFilter.isEmpty()) {
			constructor.setFilmFilter(movieNameFilter);
		}
		MovieSessionRepository movieSessionRepository = new MovieSessionRepository();
		int currentPage = 1;
		int limit = (int) req.getSession().getAttribute("limit");
		int numberOfSessions = movieSessionRepository.getLimitedWithOffset(constructor.getQuery(), 0, Integer.MAX_VALUE).size();
		int numberOfPages = numberOfSessions / limit;
		if (numberOfSessions % limit != 0)
			numberOfPages += 1;
		req.getSession().setAttribute("currentPage", currentPage);
		req.getSession().setAttribute("numberOfPages", numberOfPages);

		List<MovieSession> sessions = movieSessionRepository.getLimitedWithOffset(constructor.getQuery(), limit, 0);
		req.getSession().setAttribute("availableSessions", sessions);
		req.getSession().setAttribute("queryConstructor", constructor);
		return forward;
	}
}
