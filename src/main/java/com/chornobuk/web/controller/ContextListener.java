package com.chornobuk.web.controller;

import com.chornobuk.web.model.GenreManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		ServletContextListener.super.contextInitialized(sce);
		sce.getServletContext().setAttribute("genres", GenreManager.getInstance().getGenres());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
//		ServletContextListener.super.contextDestroyed(sce);
	}
}
