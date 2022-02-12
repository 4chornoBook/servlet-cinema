package com.chornobuk.web.controller.command;

import com.chornobuk.web.model.dao.MovieSessionDao;
import com.chornobuk.web.model.entity.MovieSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveSessionCommand implements ICommand{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String forward = "WEB-INF/jsp/admin/admin.jsp";
		MovieSession movieSession;
		MovieSessionDao movieSessionDao = new MovieSessionDao();
		long id = Long.parseLong( req.getParameter("sessionId"));

		movieSession = movieSessionDao.get(id);
		movieSessionDao.delete(movieSession);
		System.out.println(movieSession.getId() +"deleted");
		return forward;
	}
}
