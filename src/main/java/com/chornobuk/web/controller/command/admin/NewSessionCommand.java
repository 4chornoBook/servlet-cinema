package com.chornobuk.web.controller.command.admin;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.controller.command.ICommand;
import com.chornobuk.web.model.dao.MovieDao;
import com.chornobuk.web.model.entity.Movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;

public class NewSessionCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		MovieDao movieDao = new MovieDao();
		LinkedList<Movie> movies = new LinkedList<>(movieDao.getAll());
		req.getServletContext().setAttribute("movies", movies);
		return Path.ADD_NEW_SESSION_PAGE;
	}
}
