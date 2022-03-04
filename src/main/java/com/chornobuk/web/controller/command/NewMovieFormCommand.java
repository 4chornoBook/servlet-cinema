package com.chornobuk.web.controller.command;

import com.chornobuk.web.controller.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewMovieFormCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		return Path.ADD_NEW_MOVIE_PAGE;

	}
}
