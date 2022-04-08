package com.chornobuk.web.model.repository.implementation;

import com.chornobuk.web.model.builder.TicketQueryBuilder;
import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.MovieSession;
import com.chornobuk.web.model.entity.Ticket;

public class TicketRepository  {
	private DBManager instance = DBManager.getInstance();
	TicketQueryBuilder ticketQueryBuilder = new TicketQueryBuilder();
	private static final String GET_NEXT_ID = "select max(id)+1 from ticket";
	private static final String GET_BY_ID = "select * from ticket";
	private static final String GET_BY_MOVIE = "select * from ticket where movie_session_id = ?";
	private static final String GET_BY_SESSION_AND_PLACE_NUMBER = "select * from ticket where place_number = ? and movie_session_id = ?";
	private static final String INSERT = "insert into ticket values (?, ?, ?, ?)";

	public Ticket get(Ticket entity) {
		return ticketQueryBuilder.getValue(instance, GET_BY_ID,entity.getId());
	}

	public Ticket getByMovie(Ticket entity) {
		return ticketQueryBuilder.getValue(instance,GET_BY_MOVIE, entity.getSessionId());
	}

	public Ticket getByTicketAndSession(Ticket ticket, MovieSession session) {
		return ticketQueryBuilder.getValue(instance, GET_BY_SESSION_AND_PLACE_NUMBER, ticket.getPlaceNumber(), session.getId() );
	}

	public void add(Ticket entity) {
		long id = ticketQueryBuilder.getNextId(instance,GET_NEXT_ID);
		ticketQueryBuilder.executeQuery(instance, INSERT,
				id,
				entity.getPlaceNumber(),
				entity.getOrderId(),
				entity.getSessionId()
		);
	}

}
