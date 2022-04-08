package com.chornobuk.web.controller.command.common;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.controller.command.ICommand;
import com.chornobuk.web.model.entity.Order;
import com.chornobuk.web.model.entity.User;
import com.chornobuk.web.model.entity.UserRole;
import com.chornobuk.web.model.repository.implementation.OrderRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowProfileCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String forward = Path.PROFILE_PAGE;
		OrderRepository orderRepository = new OrderRepository();

		UserRole role = (UserRole) req.getSession().getAttribute("role");

		if (role == UserRole.USER) {
			User user = (User) req.getSession().getAttribute("user");
//			todo use list of orders instead of difficult code
			List<Order> userOrders = orderRepository.getByUser(user);
			req.setAttribute("userOrders", userOrders);
//			get user's orders
//			get tickets by order, sum all
//				call to database
//			put in map values
		}
		return forward;
	}
}
