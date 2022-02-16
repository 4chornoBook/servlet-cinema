package com.chornobuk.web.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ICommand{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String forward = "login.jsp";
		HttpSession session = req.getSession();
		if(session != null) {
			session.invalidate();
		}
		return forward;
	}
}
