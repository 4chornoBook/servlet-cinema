package com.chornobuk.web.controller.command;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.model.dao.UserDao;
import com.chornobuk.web.model.entity.User;
import com.chornobuk.web.model.entity.UserRole;
import com.chornobuk.web.model.HashingAlgorithm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String errorTag = "is-invalid";
		String forward = Path.LOGIN_PAGE;
		String login = req.getParameter("login");
		String password = req.getParameter("password");

		if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
			req.setAttribute("userLoginError", errorTag);
			return forward;
		} else {
			UserDao userDao = new UserDao();
			User user = userDao.getUserByLogin(login);
			if (user == null || user.getPassword().compareTo(HashingAlgorithm.cryptPassword(password, user.getSalt())) != 0) {
				req.setAttribute("userLoginError", errorTag);
				return forward;
			} else {
				UserRole userRole = UserRole.getUserRole(user);
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
				session.setAttribute("role", userRole);
				forward = Path.INDEX_PAGE;
			}
		}
		return forward;
	}
}
