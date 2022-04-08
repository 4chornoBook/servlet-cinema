package com.chornobuk.web.model.repository.implementation;

import com.chornobuk.web.model.builder.OrderQueryBuilder;
import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.Order;
import com.chornobuk.web.model.entity.Ticket;
import com.chornobuk.web.model.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {
	DBManager instance = DBManager.getInstance();
	OrderQueryBuilder orderQueryBuilder = new OrderQueryBuilder();
	TicketRepository ticketRepository = new TicketRepository();
	private static final String GET_BY_ID = "select * from tickets_order where id = ?";
	private static final String GET_NEXT_ID = "select max(id)+1 from tickets_order";
	private static final String GET_BY_USER = "select * from tickets_order where user_id= ?";
	private static final String INSERT = "insert into tickets_order values(?, ?, ?)";
	private static final String INSERT_TICKET = "insert into ticket values (default, ?, ?, ?)";
	public Order get(Order entity) {
		Order order =orderQueryBuilder.getValue(instance, GET_BY_ID, entity.getId());
		order.setTickets(ticketRepository.getByOrder(new Order(order.getId())));
		return order;
	}

	public void add(Order entity, Ticket[] tickets) {
		long id = orderQueryBuilder.getNextId(instance,GET_NEXT_ID);
		entity.setId(id);
		Map<String, Object[]> queryParametersMap = new HashMap<>();
		queryParametersMap.put(INSERT,new Object[]{
				id,
				entity.getUserId(),
				entity.getCreationDate()}
		);
		for(Ticket ticket : tickets) {
			queryParametersMap.put(INSERT_TICKET, new Object[]{
					ticket.getPlaceNumber(),
					ticket.getOrderId(),
					ticket.getSessionId()}
			);
		}
		orderQueryBuilder.executeTransaction(instance, queryParametersMap);
	}

	public List<Order> getByUser(User user) {
		List<Order> orderList = orderQueryBuilder.getValues(instance, GET_BY_USER, user.getId());
		for(Order order : orderList) {
			order.setTickets(ticketRepository.getByOrder(new Order(order.getId())));
		}
		return  orderList;
	}

}
