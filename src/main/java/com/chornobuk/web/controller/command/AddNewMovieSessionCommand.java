package com.chornobuk.web.controller.command;

import com.chornobuk.web.model.dao.MovieSessionDao;
import com.chornobuk.web.model.entity.MovieSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AddNewMovieSessionCommand implements ICommand{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String forward = "WEB-INF/jsp/admin/newSession.jsp";
		long movieId = Long.parseLong(req.getParameter("movie"));
		String movieDateString = req.getParameter("movieDate");
		String beginningTimeS = req.getParameter("beginningTime");
		String endingTimeS = req.getParameter("endingTime");
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime beginningTime = LocalTime.parse(beginningTimeS, dateTimeFormatter);
		LocalTime endingTime = LocalTime.parse(endingTimeS, dateTimeFormatter);
		LocalDate movieDate = LocalDate.parse(movieDateString);

		MovieSessionDao movieSessionDao = new MovieSessionDao();
		MovieSession movieSession = new MovieSession();
		movieSession.setMovieId(movieId);
		movieSession.setMovieDate(movieDate);
		movieSession.setBeginningTime(beginningTime);
		movieSession.setEndingTime(endingTime);
		movieSession.setAvailablePlaces(100);
		movieSessionDao.add(movieSession);
		return "WEB-INF/jsp/admin/admin.jsp";
	}
}
