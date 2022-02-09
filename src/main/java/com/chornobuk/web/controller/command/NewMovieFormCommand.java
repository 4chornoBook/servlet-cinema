package com.chornobuk.web.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewMovieFormCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		return "WEB-INF/jsp/admin/newMovie.jsp";
	}
}
