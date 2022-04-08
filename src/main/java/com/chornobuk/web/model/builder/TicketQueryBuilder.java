package com.chornobuk.web.model.builder;

import com.chornobuk.web.model.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketQueryBuilder extends QueryBuilder<Ticket>{
	@Override
	public Ticket getObject(ResultSet rs) throws SQLException {
		Ticket ticket = new Ticket();
		ticket.setId(rs.getLong("id"));
		ticket.setPlaceNumber(rs.getInt("place_number"));
		ticket.setOrderId(rs.getLong("order_id"));
		ticket.setSessionId(rs.getLong("movie_session_id"));
		return ticket;
	}
}
