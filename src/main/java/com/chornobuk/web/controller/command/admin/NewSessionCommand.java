package com.chornobuk.web.controller.command.admin;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.controller.command.ICommand;
import com.chornobuk.web.model.entity.Movie;
import com.chornobuk.web.model.repository.implementation.MovieRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;

public class NewSessionCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		MovieRepository movieRepository = new MovieRepository();
		LinkedList<Movie> movies = new LinkedList<>(movieRepository.getAll());
		req.getServletContext().setAttribute("movies", movies);
		return Path.ADD_NEW_SESSION_PAGE;
	}
}
