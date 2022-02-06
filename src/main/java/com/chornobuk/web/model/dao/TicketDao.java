package com.chornobuk.web.model.dao;

import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TicketDao implements IDao<Ticket>{
	private final static String GET_TICKET_BY_ID = "select * from ticket where id = ?";
	private final static String INSERT_TICKET = "insert into ticket values (default ,?,?,?)";
	@Override
	public Ticket get(long id) {
		Ticket ticket = null;
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(GET_TICKET_BY_ID);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				ticket = getTicket(rs);

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return ticket;
	}

	@Override
	public List<Ticket> getAll() {
//		don't need now
		return null;
	}

	@Override
	public void add(Ticket ticket) {
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT_TICKET);
			setParams(ps, ticket);
			ps.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Ticket ticket) {
// 		don't need now
	}

	@Override
	public void delete(Ticket ticket) {
//		don't need now
	}

	private void setParams(PreparedStatement ps, Ticket ticket) throws SQLException{
		ps.setInt(1,ticket.getPlaceNumber());
		ps.setLong(2,ticket.getOrderId());
		ps.setLong(3,ticket.getSessionId());
	}

	private Ticket getTicket(ResultSet rs) throws SQLException{
		Ticket ticket = new Ticket();
		ticket.setId(rs.getLong(1));
		ticket.setPlaceNumber(rs.getInt(2));
		ticket.setOrderId(rs.getLong(3));
		ticket.setSessionId(rs.getLong(4));
		return ticket;
	}
}
