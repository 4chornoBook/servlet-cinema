package com.chornobuk.web.controller.command;

import com.chornobuk.web.model.dao.OrderDao;
import com.chornobuk.web.model.entity.MovieSession;
import com.chornobuk.web.model.entity.Order;
import com.chornobuk.web.model.entity.Ticket;
import com.chornobuk.web.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class BuyTicketsCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String forward = "index.jsp";

//		take money from card
		int[] places = (int[]) req.getSession().getAttribute("orderPlaces");
		Ticket[] tickets = new Ticket[places.length];
		MovieSession session = (MovieSession) req.getSession().getAttribute("orderSession");
		User user = (User) req.getSession().getAttribute("user");
//		creating tickets
		for (int i = 0; i < tickets.length; i++) {
			tickets[i] = new Ticket();
			tickets[i].setPlaceNumber(places[i]);
			tickets[i].setSessionId(session.getId());
		}
//		create order
		Order order = new Order();
		order.setCreationDate((LocalDateTime) req.getSession().getAttribute("orderCreatingTime"));
		order.setUserId(user.getId());
//		put them in database using transaction
		OrderDao orderDao = new OrderDao();

//		remove order attributes from session
		req.getSession().removeAttribute("orderCreatingTime");
		req.getSession().removeAttribute("orderSession");
		req.getSession().removeAttribute("orderPlaces");
		req.getSession().removeAttribute("totalPrice");

		orderDao.addOrderWithTickets(order,user,session,tickets);
		return forward;
	}
}
