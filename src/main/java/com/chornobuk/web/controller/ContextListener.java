package com.chornobuk.web.controller;

import com.chornobuk.web.model.dao.GenreDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
	private static final Logger log = LogManager.getLogger(ContextListener.class);
	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		MovieSessionDao movieSessionDao = new MovieSessionDao();
//		ServletContextListener.super.contextInitialized(sce);
//		List<MovieSession> availableSessions = movieSessionDao.getAvailableSessions();
//		sce.getServletContext().setAttribute("availableSessions", availableSessions);
		GenreDao genreDao = new GenreDao();
		sce.getServletContext().setAttribute("genres", genreDao.getAll());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContextListener.super.contextDestroyed(sce);
	}

}
