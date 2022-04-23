package com.chornobuk.web.controller.command.admin;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.controller.command.ICommand;
import com.chornobuk.web.model.MovieSessionQueryConstructor;
import com.chornobuk.web.model.entity.MovieSession;
import com.chornobuk.web.model.entity.Movie;
import com.chornobuk.web.model.repository.implementation.MovieRepository;
import com.chornobuk.web.model.repository.implementation.MovieSessionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class AddNewMovieSessionCommand implements ICommand {
	MovieSessionRepository movieSessionRepository = new MovieSessionRepository();
	MovieRepository movieRepository = new MovieRepository();
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		Logger log = LogManager.getLogger(AddNewMovieSessionCommand.class);
		String errorTag = "is-invalid";
		String forward = Path.ADD_NEW_SESSION_PAGE;
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		String movieDateString = req.getParameter("movieDate");
		String beginningTimeS = req.getParameter("beginningTime");
		String movieIdString = req.getParameter("movie");
		int ticketPrice;
		Long movieId;
		LocalDate movieDate;
		LocalTime beginningTime;
		try {
			movieId = (movieIdString == null || movieIdString.isEmpty()) ? null : Long.parseLong(movieIdString);
			ticketPrice = Integer.parseInt(req.getParameter("ticketPrice"));
		}catch (NumberFormatException e) {
			movieId = null;
			ticketPrice = -1;
		}
		try{
			movieDate = (movieDateString == null || movieDateString.isEmpty()) ? null : LocalDate.parse(movieDateString);
			beginningTime = (beginningTimeS == null || beginningTimeS.isEmpty()) ? null : LocalTime.parse(beginningTimeS, dateTimeFormatter);
		}catch (DateTimeParseException e) {
			movieDate = null;
			beginningTime = null;
		}
		if (movieId == null) {
			req.setAttribute("movieSelectionError", errorTag);
		} else if (movieDate == null || movieDate.isBefore(LocalDate.now())) {
			req.setAttribute("movieDateError", errorTag);
		} else if (beginningTime == null
				|| beginningTime.isBefore(LocalTime.of(9, 0))
				|| beginningTime.isAfter(LocalTime.of(22, 0))
				|| LocalDateTime.of(movieDate, beginningTime).isBefore(LocalDateTime.now())) {
			req.setAttribute("beginningTimeError", errorTag);
		}
		else if(ticketPrice < 50 || ticketPrice > 1000) {
			req.setAttribute("ticketPriceError", errorTag);
		}
		else {
			MovieSession movieSession = new MovieSession();
			movieSession.setMovieId(movieId);
			movieSession.setMovieDate(movieDate);
			movieSession.setBeginningTime(beginningTime);
			movieSession.setTicketPrice(ticketPrice);
			movieSession.setMovie(movieRepository.get(new Movie(movieSession.getMovieId())));
			int cleaningTime = 20;

//			add to beginning time length of movie rounded to the next 10 and add cleaning time
			LocalTime endingTime = beginningTime.plusMinutes(movieSession.getMovie().getLengthInMinutes() +
					(10 - (movieSession.getMovie().getLengthInMinutes() % 10)) + cleaningTime);
			movieSession.setEndingTime(endingTime);

			if (movieSessionRepository.getByTime(movieSession.getMovieDate(), movieSession.getBeginningTime(), movieSession.getEndingTime()).size() != 0) {
				req.setAttribute("slotNotAvailableError", errorTag);
			} else {
				forward = Path.REDIRECT_COMMAND;
				movieSessionRepository.add(movieSession);
				MovieSessionQueryConstructor constructor = (MovieSessionQueryConstructor) req.getSession().getAttribute("queryConstructor");
				int limit = (int) req.getSession().getAttribute("limit");
				int numberOfSessions = movieSessionRepository.getAvailable().size();
				int numberOfPages = numberOfSessions / limit;
				if (numberOfSessions % limit != 0)
					numberOfPages += 1;
				List<MovieSession> availableSessions = movieSessionRepository.getLimitedWithOffset(constructor.getQuery(), limit, 0);
				req.getSession().setAttribute("availableSessions", availableSessions);
				req.getSession().setAttribute("numberOfPages", numberOfPages);

				try {
					resp.sendRedirect(Path.INDEX_PAGE);
				} catch (IOException e) {
					log.error("redirect error", e);
				}
			}
		}
		return forward;
	}
}
