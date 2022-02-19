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
		String errorTag = "is-invalid";
		String forward = "WEB-INF/jsp/movie.jsp";//continue operation
		UserRole role = (UserRole) req.getSession().getAttribute("role");
		if (role != null) {
			MovieSessionDao sessionDao = new MovieSessionDao();
			MovieDao movieDao = new MovieDao();
			Long sessionId = Long.parseLong(req.getParameter("sessionId"));
			MovieSession session = sessionDao.get(sessionId);
			session.setMovie(movieDao.get(session.getMovieId()));
			String [] placesString = req.getParameterValues("numberPlace");
			if (placesString == null || placesString.length == 0) {
				req.setAttribute("noTicketsError",errorTag );
				System.out.println("error");
				System.out.println(sessionId);
				return forward;
			}
			int[] places = Arrays.stream(placesString)
					.mapToInt(Integer::parseInt).toArray();

			forward = "/WEB-INF/buyTickets.jsp";
			int totalPrice = req.getParameterValues("numberPlace").length * session.getMovie().getTicketPrice();

			req.getSession().removeAttribute("session");
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
