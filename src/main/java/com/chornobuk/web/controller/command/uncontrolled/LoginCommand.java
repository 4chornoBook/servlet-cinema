package com.chornobuk.web.controller.command.uncontrolled;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.controller.command.ICommand;
import com.chornobuk.web.model.entity.User;
import com.chornobuk.web.model.entity.UserRole;
import com.chornobuk.web.model.HashingAlgorithm;
import com.chornobuk.web.model.repository.implementation.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements ICommand {
	UserRepository userRepository = new UserRepository();
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		Logger log = LogManager.getLogger(LoginCommand.class);
		String errorTag = "is-invalid";
		String forward = Path.LOGIN_PAGE;
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
			req.setAttribute("userLoginError", errorTag);
			return forward;
		} else {
			User user = userRepository.getByLogin(login);
			if (user == null || user.getPassword().compareTo(HashingAlgorithm.cryptPassword(password, user.getSalt())) != 0) {
				req.setAttribute("userLoginError", errorTag);
				System.out.println("lsjdflskf ");
				return forward;
			} else {
				UserRole userRole = UserRole.getRole(user);
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
				session.setAttribute("role", userRole);
				forward = Path.REDIRECT_COMMAND;

				try {
					resp.sendRedirect(Path.INDEX_PAGE);
				} catch (IOException e) {
					log.error("redirect error", e);
				}
			}
		}
		return forward;
	}
}
