package com.chornobuk.web.controller.command.admin;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.controller.command.ICommand;
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
		String errorTag = "is-invalid";
		String name = req.getParameter("name");
		String forward = Path.ADD_NEW_MOVIE_PAGE;
		String releaseDateString = req.getParameter("releaseDate");
		LocalDate releaseDate = (releaseDateString == null || releaseDateString.isEmpty()) ? null : LocalDate.parse(releaseDateString);
		int lengthInMinutes = Integer.parseInt(req.getParameter("length"));
		String[] genresId = req.getParameterValues("genre");
		String imageURL = req.getParameter("imageURL");
		String description = req.getParameter("description");
		int ticketPrice = Integer.parseInt(req.getParameter("ticketPrice"));
//		movie name min 1 max 200
		if (name == null || name.isEmpty() || name.length() > 200) {
			req.setAttribute("movieNameError", errorTag);
		}
//		add release date not greater than now
//		|| releaseDate.isAfter(LocalDate.now()) this don't need because cinema
//		can add session for future movies
		else if (releaseDate == null) {
			req.setAttribute("releaseDateError", errorTag);
		}
//		length min 20 max 600
		else if (lengthInMinutes < 20 || lengthInMinutes > 600) {
			req.setAttribute("lengthError", errorTag);
		}
//		add minimal genre
		else if (genresId == null || genresId.length == 0) {
			req.setAttribute("genresError", errorTag);
		}
//		image url not null (optional check is it url)
		else if (imageURL == null || imageURL.isEmpty()) {
			req.setAttribute("imageURLError", errorTag);
		}
//		ticket price min 50
		else if (ticketPrice < 50) {
			req.setAttribute("ticketPriceError", errorTag);
		}
//		description not null
		else if (description == null || description.isEmpty()) {
			req.setAttribute("descriptionError", errorTag);
		} else {
			forward = Path.INDEX_PAGE;
			Movie movie = new Movie();
			MovieDao movieDao = new MovieDao();
			GenreDao genreDao = new GenreDao();
			LinkedList<Genre> genres = genreDao.getAll()
					.stream()
					.filter(x -> Arrays.binarySearch(genresId, x.getName()) >= 0)
					.collect(Collectors.toCollection(LinkedList::new));

			movie.setName(name);
			movie.setGenres(genres);
			movie.setDescription(description);
			movie.setTicketPrice(ticketPrice);
			movie.setImageURL(imageURL);
			movie.setLengthInMinutes(lengthInMinutes);
			movie.setReleaseDate(releaseDate);
			movieDao.add(movie);
		}
		return forward;
	}
}
