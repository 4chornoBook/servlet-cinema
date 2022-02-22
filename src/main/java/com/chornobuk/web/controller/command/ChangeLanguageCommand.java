package com.chornobuk.web.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguageCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String forward = "index.jsp";
		if (req.getParameter("uk") != null) {
			req.getSession().setAttribute("lang", "uk");
		} else if (req.getParameter("en") != null) {
			req.getSession().setAttribute("lang", "en");
		}
		return forward;
	}
}
