package com.chornobuk.web.controller.command;

import com.chornobuk.web.model.dao.MovieSessionDao;
import com.chornobuk.web.model.entity.MovieSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;

public class SessionsPaginationCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String forward = "index.jsp";
		int currentPage = (int) req.getSession().getAttribute("currentPage");
		int numberOfPages = (int) req.getSession().getAttribute("numberOfPages");
		int limit = (int) req.getSession().getAttribute("limit");
		int page = currentPage;
		LinkedList<MovieSession> sessions = null;
		MovieSessionDao movieSessionDao = new MovieSessionDao();

		if (req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page"));
			sessions = new LinkedList<>(movieSessionDao.getSomeElements((page - 1) * limit, limit));
			req.getSession().setAttribute("availableSessions", sessions);
			req.getSession().setAttribute("currentPage", page);

		} else if (req.getParameter("moveTo") != null) {
			String action = req.getParameter("moveTo");
			if (action.equals("nextPage")) {
				page += 1;
				if (page <= numberOfPages) {
					sessions = new LinkedList<>(movieSessionDao.getSomeElements((page - 1) * limit, limit));
				}
			} else {
				page -= 1;
				if (page > 0) {
					sessions = new LinkedList<>(movieSessionDao.getSomeElements((page - 1) * limit, limit));
				}
			}
		}
		if (sessions != null) {
			req.getSession().setAttribute("availableSessions", sessions);
			req.getSession().setAttribute("currentPage", page);
		}
		return forward;
	}
}
