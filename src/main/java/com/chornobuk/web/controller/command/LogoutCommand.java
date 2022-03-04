package com.chornobuk.web.controller.command;

import com.chornobuk.web.controller.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ICommand{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
//		todo add forward to index page
//		todo fix bug with showing sessions after logout
		String forward = Path.LOGIN_PAGE;
		HttpSession session = req.getSession();
		if(session != null) {
			session.invalidate();
		}
		return forward;
	}
}
