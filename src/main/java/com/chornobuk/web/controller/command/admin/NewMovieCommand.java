package com.chornobuk.web.controller.command.admin;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.controller.command.ICommand;
import com.chornobuk.web.model.dao.GenreDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewMovieCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		if (req.getSession().getAttribute("genres") == null) {
			GenreDao genreDao = new GenreDao();
			req.getSession().setAttribute("genres", genreDao.getAll());
		}
		return Path.ADD_NEW_MOVIE_PAGE;
	}
}
