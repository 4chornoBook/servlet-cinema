package com.chornobuk.web.controller.command;

import com.chornobuk.web.model.HashingAlgorithm;
import com.chornobuk.web.model.dao.UserDao;
import com.chornobuk.web.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String message;
		String forward = "registration.jsp";

		String login = req.getParameter("login");
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		String password = req.getParameter("password");

		if (login == null || login.length() < 5 || login.length() > 50) {
			message = "Enter login at least 5 symbols and not more than 50 symbols";
			return forward;
		}
//		check name
//		check surname
//		check password
		UserDao userDao = new UserDao();
		User user = userDao.getUserByLogin(login);
		if (user == null) {
			user = new User();
			user.setLogin(login);
			user.setName(name);
			user.setSurname(surname);
			user.setSalt(HashingAlgorithm.getSalt());
			user.setRoleId(2);
			user.setPassword(HashingAlgorithm.cryptPassword(password, user.getSalt()));
			userDao.add(user);
			System.out.println("registration salt:"+ user.getSalt());
			forward = "login.jsp";
			return forward;
		} else {
			message = "Choose another login";
			return forward;
		}
	}
}
