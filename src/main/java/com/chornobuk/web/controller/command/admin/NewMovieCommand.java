package com.chornobuk.web.controller.command.admin;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.controller.command.ICommand;
import com.chornobuk.web.model.repository.implementation.GenreRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewMovieCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		if (req.getSession().getAttribute("genres") == null) {
			GenreRepository genreRepository = new GenreRepository();
			req.getSession().setAttribute("genres", genreRepository.getAll());
		}
		return Path.ADD_NEW_MOVIE_PAGE;
	}
}
