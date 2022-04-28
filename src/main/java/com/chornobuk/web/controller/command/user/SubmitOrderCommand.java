package com.chornobuk.web.controller.command.user;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.controller.command.ICommand;
import com.chornobuk.web.model.entity.Movie;
import com.chornobuk.web.model.entity.MovieSession;
import com.chornobuk.web.model.entity.User;
import com.chornobuk.web.model.entity.UserRole;
import com.chornobuk.web.model.repository.implementation.MovieRepository;
import com.chornobuk.web.model.repository.implementation.MovieSessionRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class SubmitOrderCommand implements ICommand {
	MovieSessionRepository movieSessionRepository = new MovieSessionRepository();
	MovieRepository movieRepository = new MovieRepository();
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String errorTag = "is-invalid";
		String forward = Path.SESSION_PAGE;//continue operation
		User role = (User) req.getSession().getAttribute("user");
		if (role != null) {
			Long sessionId = Long.parseLong(req.getParameter("sessionId"));
			MovieSession session = movieSessionRepository.get(new MovieSession(sessionId));
			session.setMovie(movieRepository.get(new Movie(session.getMovieId())));
			String [] placesString = req.getParameterValues("numberPlace");
			if (placesString == null || placesString.length == 0) {
				req.setAttribute("noTicketsError",errorTag );
				return forward;
			}
			int[] places = Arrays.stream(placesString)
					.mapToInt(Integer::parseInt).toArray();

			forward = Path.BUY_TICKETS_PAGE;
			int totalPrice = req.getParameterValues("numberPlace").length * session.getTicketPrice();

			req.getSession().removeAttribute("session");
			req.getSession().setAttribute("orderCreatingTime", LocalDateTime.now());
			req.getSession().setAttribute("orderSession", session);
			req.getSession().setAttribute("orderPlaces", places);
			req.getSession().setAttribute("totalPrice", totalPrice);
		} else {
			forward = Path.REDIRECT_COMMAND;
			try {
				resp.sendRedirect(Path.LOGIN_PAGE);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return forward;
	}
}
