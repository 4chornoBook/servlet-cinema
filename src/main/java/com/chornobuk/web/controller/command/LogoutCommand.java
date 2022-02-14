package com.chornobuk.web.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements ICommand{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String forward = "redirect";
		req.getSession().removeAttribute("user");
		req.getSession().removeAttribute("role");
		try {
			resp.sendRedirect("index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return forward;
	}
}
