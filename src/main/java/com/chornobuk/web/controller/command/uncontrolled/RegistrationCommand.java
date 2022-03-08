package com.chornobuk.web.controller.command.uncontrolled;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.controller.command.ICommand;
import com.chornobuk.web.model.HashingAlgorithm;
import com.chornobuk.web.model.dao.UserDao;
import com.chornobuk.web.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String errorTag = "is-invalid";
		String forward = Path.REGISTRATION_PAGE;

		String login = req.getParameter("login");
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		String password = req.getParameter("password");

		if (login == null || login.length() < 5 || login.length() > 50) {
			req.setAttribute("loginError", errorTag);
		} else if (name == null || name.isEmpty() || name.length() > 300) {
			req.setAttribute("nameError", errorTag);
		} else if (surname == null || surname.isEmpty() || surname.length() > 300) {
			req.setAttribute("surnameError", errorTag);
		} else if (password == null || password.length() < 5 || password.length() > 64) {
			req.setAttribute("passwordError", errorTag);
		} else {
			UserDao userDao = new UserDao();
			User user = userDao.getUserByLogin(login);
			if (user == null) {
				forward = Path.INDEX_PAGE;
				user = new User();
				user.setLogin(login);
				user.setName(name);
				user.setSurname(surname);
				user.setSalt(HashingAlgorithm.getSalt());
				user.setRoleId(2);
				user.setPassword(HashingAlgorithm.cryptPassword(password, user.getSalt()));
				userDao.add(user);
			} else {
				req.setAttribute("loginError", errorTag);
			}
		}
		return forward;
	}
}
