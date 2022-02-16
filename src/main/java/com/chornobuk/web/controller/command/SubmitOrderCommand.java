package com.chornobuk.web.controller.command;

import com.chornobuk.web.model.dao.MovieDao;
import com.chornobuk.web.model.dao.MovieSessionDao;
import com.chornobuk.web.model.entity.MovieSession;
import com.chornobuk.web.model.entity.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class SubmitOrderCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String forward = "WEB-INF/jsp/movie.jsp";//continue operation
		UserRole role = (UserRole) req.getSession().getAttribute("role");
		if (role != null) {
			forward = "/WEB-INF/buyTickets.jsp";
			MovieSessionDao sessionDao = new MovieSessionDao();
			MovieDao movieDao = new MovieDao();
			Long sessionId = Long.parseLong(req.getParameter("sessionId"));
			MovieSession session = sessionDao.get(sessionId);
			session.setMovie(movieDao.get(session.getMovieId()));
			int[] places = Arrays.stream(req.getParameterValues("numberPlace"))
					.mapToInt(Integer::parseInt).toArray();
			int totalPrice = req.getParameterValues("numberPlace").length * session.getMovie().getTicketPrice();

			req.getSession().setAttribute("orderCreatingTime", LocalDateTime.now());
			req.getSession().setAttribute("orderSession", session);
			req.getSession().setAttribute("orderPlaces", places);
			req.getSession().setAttribute("totalPrice", totalPrice);
		} else {
			forward = "redirect";
			try {
				resp.sendRedirect("login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return forward;
	}
}
