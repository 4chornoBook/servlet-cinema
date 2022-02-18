package com.chornobuk.web.controller.command;

import com.chornobuk.web.model.MovieSessionQueryConstructor;
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
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime beginningTime = LocalTime.parse(beginningTimeS, dateTimeFormatter);
		MovieSessionQueryConstructor constructor = (MovieSessionQueryConstructor) req.getSession().getAttribute("queryConstructor");
		int limit = (int)req.getSession().getAttribute("limit");
		System.out.println(limit);
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

		int numberOfSessions = movieSessionDao.getNumberOfAvailableSessions();
		int numberOfPages = numberOfSessions / limit;
		if (numberOfSessions % limit != 0)
			numberOfPages += 1;
		List<MovieSession> availableSessions = movieSessionDao.getSomeElementsByQuery(constructor.getQuery(),0,limit);
		req.getSession().setAttribute("availableSessions", availableSessions);
		req.getSession().setAttribute("numberOfPages", numberOfPages);
		forward = "WEB-INF/jsp/admin/admin.jsp";
		return forward;
	}
}
