package com.chornobuk.web.controller.command;

import com.chornobuk.web.model.dao.GenreDao;
import com.chornobuk.web.model.dao.MovieDao;
import com.chornobuk.web.model.entity.Genre;
import com.chornobuk.web.model.entity.Movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class AddNewMovieCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
//		todo add validation
		String forward = "WEB-INF/jsp/admin/admin.jsp";
		Movie movie = new Movie();
		MovieDao movieDao = new MovieDao();

		String name = req.getParameter("name");
		LocalDate releaseDate = LocalDate.parse(req.getParameter("releaseDate"));
		int lengthInMinutes = Integer.parseInt(req.getParameter("length"));

		String[] genresId = req.getParameterValues("genre");
		GenreDao genreDao = new GenreDao();
		LinkedList<Genre> genres = genreDao.getAll()
				.stream()
				.filter(x -> Arrays.binarySearch(genresId, x.getName()) >= 0)
				.collect(Collectors.toCollection(LinkedList::new));
		String imageURL = req.getParameter("imageURL");
		String description = req.getParameter("description");
		int ticketPrice = Integer.parseInt(req.getParameter("ticketPrice"));

//			after validation if everything is okay
//			I will add new movie
		movie.setName(name);
		movie.setGenres(genres);
		movie.setDescription(description);
		movie.setTicketPrice(ticketPrice);
		movie.setImageURL(imageURL);
		movie.setLengthInMinutes(lengthInMinutes);
		movie.setReleaseDate(releaseDate);
		movieDao.add(movie);
		return forward;
	}
}
