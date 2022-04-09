package com.chornobuk.web.model.repository.implementation;

import com.chornobuk.web.model.builder.TicketQueryBuilder;
import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.MovieSession;
import com.chornobuk.web.model.entity.Order;
import com.chornobuk.web.model.entity.Ticket;

import java.util.List;

public class TicketRepository  {
	private DBManager instance = DBManager.getInstance();
	TicketQueryBuilder ticketQueryBuilder = new TicketQueryBuilder();
	private static final String GET_NEXT_ID = "select max(id)+1 from ticket";
	private static final String GET_BY_ID = "select * from ticket";
	private static final String GET_BY_MOVIE = "select * from ticket where movie_session_id = ?";
	private static final String GET_BY_SESSION_AND_PLACE_NUMBER = "select * from ticket where place_number = ? and movie_session_id = ?";
	private static final String INSERT = "insert into ticket values (default, ?, ?, ?)";
	private static final String GET_BY_ORDER = "select * from ticket where order_id = ?";
	public Ticket get(Ticket entity) {
		return ticketQueryBuilder.getValue(instance, GET_BY_ID,entity.getId());
	}

	public List<Ticket> getBySession(MovieSession session) {
		return ticketQueryBuilder.getValues(instance,GET_BY_MOVIE, session.getId());
	}

	public Ticket getBySession(Ticket ticket, MovieSession session) {
		return ticketQueryBuilder.getValue(instance, GET_BY_SESSION_AND_PLACE_NUMBER, ticket.getPlaceNumber(), session.getId() );
	}

	public List<Ticket> getByOrder(Order order) {
		return ticketQueryBuilder.getValues(instance, GET_BY_ORDER, order.getId());
	}

	public void add(Ticket entity) {
		long id = ticketQueryBuilder.getNextId(instance,GET_NEXT_ID);
		entity.setId(id);
		ticketQueryBuilder.insertNewEntity(instance,entity, INSERT,
				entity.getPlaceNumber(),
				entity.getOrderId(),
				entity.getSessionId()
		);
	}

}
