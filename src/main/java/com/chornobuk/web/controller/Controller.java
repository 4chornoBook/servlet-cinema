package com.chornobuk.web.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chornobuk.web.controller.command.ICommand;
import com.chornobuk.web.controller.command.CommandFactory;
import com.chornobuk.web.model.dao.MovieDao;
import com.chornobuk.web.model.entity.Genre;
import com.chornobuk.web.model.entity.Movie;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

public class Controller extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}

	private void doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String action = req.getParameter("action");
		CommandFactory commandFactory = CommandFactory.getInstance();
		ICommand command = commandFactory.getCommand(action);
		String actionPage = command.execute(req, resp);
		if (!actionPage.equals("redirect")) {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(actionPage);
			requestDispatcher.forward(req, resp);
		}
	}
}
