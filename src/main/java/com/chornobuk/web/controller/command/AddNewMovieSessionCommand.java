package com.chornobuk.web.controller.command;

import com.chornobuk.web.model.dao.MovieDao;
import com.chornobuk.web.model.dao.MovieSessionDao;
import com.chornobuk.web.model.entity.MovieSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AddNewMovieSessionCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String forward = "WEB-INF/jsp/admin/newSession.jsp";
		long movieId = Long.parseLong(req.getParameter("movie"));
		String movieDateString = req.getParameter("movieDate");
		String beginningTimeS = req.getParameter("beginningTime");
//		String endingTimeS = req.getParameter("endingTime");
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime beginningTime = LocalTime.parse(beginningTimeS, dateTimeFormatter);
//		LocalTime endingTime = LocalTime.parse(endingTimeS, dateTimeFormatter);

//		get movie length
		MovieSessionDao movieSessionDao = new MovieSessionDao();
		MovieDao movieDao = new MovieDao();
		LocalDate movieDate = LocalDate.parse(movieDateString);

		MovieSession movieSession = new MovieSession();
		movieSession.setMovieId(movieId);
		movieSession.setMovieDate(movieDate);
		movieSession.setBeginningTime(beginningTime);


		movieSession.setAvailablePlaces(100);
		movieSession.setMovie(movieDao.get(movieSession.getMovieId()));
		int cleaningTime = 20;
//		add to beginning time length of movie rounded to the next 10 and cleaning time
		LocalTime endingTime = beginningTime.plusMinutes(movieSession.getMovie().getLengthInMinutes() +
				(10 - (movieSession.getMovie().getLengthInMinutes() % 10)) + cleaningTime);

		movieSession.setEndingTime(endingTime);
		movieSessionDao.add(movieSession);

		List<MovieSession> availableSessions = movieSessionDao.getAvailableSessions();
		req.getServletContext().setAttribute("availableSessions", availableSessions);
		forward = "WEB-INF/jsp/admin/admin.jsp";
		return forward;
	}
}
