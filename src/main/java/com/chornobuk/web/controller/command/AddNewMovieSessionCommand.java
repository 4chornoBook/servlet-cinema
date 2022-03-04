package com.chornobuk.web.controller.command;

import com.chornobuk.web.model.MovieSessionQueryConstructor;
import com.chornobuk.web.model.dao.MovieDao;
import com.chornobuk.web.model.dao.MovieSessionDao;
import com.chornobuk.web.model.entity.MovieSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AddNewMovieSessionCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String errorTag = "is-invalid";
		String forward = "WEB-INF/jsp/admin/newSession.jsp";
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		String movieDateString = req.getParameter("movieDate");
		String beginningTimeS = req.getParameter("beginningTime");
		String movieIdString = req.getParameter("movie");
		Long movieId = (movieIdString == null || movieIdString.isEmpty()) ? null : Long.parseLong(movieIdString);
		LocalDate movieDate = (movieDateString == null || movieDateString.isEmpty()) ? null : LocalDate.parse(movieDateString);
		LocalTime beginningTime = (beginningTimeS == null || beginningTimeS.isEmpty()) ? null : LocalTime.parse(beginningTimeS, dateTimeFormatter);
		if (movieId == null) {
			req.setAttribute("movieSelectionError", errorTag);
		} else if (movieDate == null || movieDate.isBefore(LocalDate.now())) {
			req.setAttribute("movieDateError", errorTag);
		} else if (beginningTime == null
				|| beginningTime.isBefore(LocalTime.of(9, 0))
				|| beginningTime.isAfter(LocalTime.of(22,0))
				|| LocalDateTime.of(movieDate, beginningTime).isBefore(LocalDateTime.now())) {
			req.setAttribute("beginningTimeError", errorTag);
		} else {
			MovieSessionDao movieSessionDao = new MovieSessionDao();
			MovieDao movieDao = new MovieDao();

			MovieSession movieSession = new MovieSession();
			movieSession.setMovieId(movieId);
			movieSession.setMovieDate(movieDate);
			movieSession.setBeginningTime(beginningTime);
			movieSession.setAvailablePlaces(100);
			movieSession.setMovie(movieDao.get(movieSession.getMovieId()));
			int cleaningTime = 20;

//			add to beginning time length of movie rounded to the next 10 and cleaning time
			LocalTime endingTime = beginningTime.plusMinutes(movieSession.getMovie().getLengthInMinutes() +
					(10 - (movieSession.getMovie().getLengthInMinutes() % 10)) + cleaningTime);
			movieSession.setEndingTime(endingTime);

			if (!movieSessionDao.isSlotAvailable(movieSession)) {
				req.setAttribute("slotNotAvailableError", errorTag);
			} else {
				forward = "index.jsp";
				movieSessionDao.add(movieSession);
				MovieSessionQueryConstructor constructor = (MovieSessionQueryConstructor) req.getSession().getAttribute("queryConstructor");
				int limit = (int) req.getSession().getAttribute("limit");
				int numberOfSessions = movieSessionDao.getNumberOfAvailableSessions();
				int numberOfPages = numberOfSessions / limit;
				if (numberOfSessions % limit != 0)
					numberOfPages += 1;
				List<MovieSession> availableSessions = movieSessionDao.getSomeElementsByQuery(constructor.getQuery(), 0, limit);
				req.getSession().setAttribute("availableSessions", availableSessions);
				req.getSession().setAttribute("numberOfPages", numberOfPages);
			}
		}
		return forward;
	}
}
