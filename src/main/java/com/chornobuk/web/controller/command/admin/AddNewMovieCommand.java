package com.chornobuk.web.controller.command.admin;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.controller.command.ICommand;
import com.chornobuk.web.model.entity.Genre;
import com.chornobuk.web.model.entity.Movie;
import com.chornobuk.web.model.repository.implementation.GenreRepository;
import com.chornobuk.web.model.repository.implementation.MovieRepository;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class AddNewMovieCommand implements ICommand {
	MovieRepository movieRepository = new MovieRepository();
	GenreRepository genreRepository = new GenreRepository();
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		Logger log = LogManager.getLogger(AddNewMovieCommand.class);
		String errorTag = "is-invalid";
		String name = req.getParameter("name");
		String forward = Path.ADD_NEW_MOVIE_PAGE;
		String releaseDateString = req.getParameter("releaseDate");
		LocalDate releaseDate = (releaseDateString == null || releaseDateString.isEmpty()) ? null : LocalDate.parse(releaseDateString);
		int lengthInMinutes;
		try {
			lengthInMinutes = Integer.parseInt(req.getParameter("length"));
		}
		catch (NumberFormatException e) {
			lengthInMinutes = -1;
		}
		String[] genresId = req.getParameterValues("genre");
		String imageURL = req.getParameter("imageURL");
		String description = req.getParameter("description");

		if (name == null || name.isEmpty() || name.length() > 200) {
			req.setAttribute("movieNameError", errorTag);
		}
		else if (releaseDate == null) {
			req.setAttribute("releaseDateError", errorTag);
		}
		else if (lengthInMinutes < 20 || lengthInMinutes > 600) {
			req.setAttribute("lengthError", errorTag);
		}
//		at least one genre
		else if (genresId == null || genresId.length == 0) {
			req.setAttribute("genresError", errorTag);
		}
		else if (imageURL == null || imageURL.isEmpty()) {
			req.setAttribute("imageURLError", errorTag);
		}
		else if (description == null || description.isEmpty()) {
			req.setAttribute("descriptionError", errorTag);
		} else {
			log.debug("fields are valid");
			forward = Path.REDIRECT_COMMAND;
			Movie movie = new Movie();

			LinkedList<Genre> genres = genreRepository.getAll()
					.stream()
					.filter(x -> Arrays.binarySearch(genresId, x.getName()) >= 0)
					.collect(Collectors.toCollection(LinkedList::new));

			movie.setName(name);
			movie.setGenres(genres);
			movie.setDescription(description);
			movie.setImageURL(imageURL);
			movie.setLengthInMinutes(lengthInMinutes);
			movie.setReleaseDate(releaseDate);
			movieRepository.add(movie);
			try {
				resp.sendRedirect(Path.INDEX_PAGE);
			} catch (IOException e) {
				log.error("redirect error", e);
			}
		}
		return forward;
	}
}
