package com.chornobuk.web.controller.command;

import com.chornobuk.web.model.MovieSessionQueryConstructor;
import com.chornobuk.web.model.dao.MovieSessionDao;
import com.chornobuk.web.model.entity.MovieSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SessionsSortingCommand implements ICommand{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String forward = "index.jsp";
//		create query for getting

		MovieSessionQueryConstructor constructor = new MovieSessionQueryConstructor();
//		get all parameters
		String dateSort = req.getParameter("dateSort");
		String timeSort = req.getParameter("timeSort");
		String ticketSort = req.getParameter("ticketsSort");
		String movieNameSort = req.getParameter("movieNameSort");
//		check all parameter
		if(dateSort != null) {
			constructor.addSortingByDate(dateSort);
		}
		if(timeSort != null) {
			constructor.addSortingByTime(timeSort);
		}
		if(ticketSort != null) {
			constructor.addSortingByTickets(ticketSort);
		}
		if(movieNameSort != null) {
			constructor.addSortingByMovieName(movieNameSort);
		}
//		construct query
//		show query
		System.out.println(constructor.getQuery());
		MovieSessionDao movieSessionDao = new MovieSessionDao();
		req.getSession().setAttribute("currentPage", 1);
		int limit = (int) req.getSession().getAttribute("limit");
		List<MovieSession> sessions = movieSessionDao.getSomeElementsByQuery(constructor.getQuery(),0,limit);
		req.getSession().setAttribute("availableSessions", sessions);
		req.getSession().setAttribute("queryConstructor", constructor);
//		use query in database
//		set undate session movie table

		return forward;
	}
}
