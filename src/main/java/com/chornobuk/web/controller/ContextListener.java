package com.chornobuk.web.controller;

import com.chornobuk.web.model.dao.GenreDao;
import com.chornobuk.web.model.dao.MovieSessionDao;
import com.chornobuk.web.model.entity.MovieSession;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

public class ContextListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		MovieSessionDao movieSessionDao = new MovieSessionDao();
//		ServletContextListener.super.contextInitialized(sce);
		List<MovieSession> availableSessions = movieSessionDao.getAvailableSessions();
		sce.getServletContext().setAttribute("availableSessions", availableSessions);
//		todo replace this class with using dao
		GenreDao genreDao = new GenreDao();
		sce.getServletContext().setAttribute("genres", genreDao.getAll());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
//		ServletContextListener.super.contextDestroyed(sce);
	}
}
