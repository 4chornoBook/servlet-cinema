package com.chornobuk.web.model.dao;

import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.MovieSession;
import com.chornobuk.web.model.entity.Ticket;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TicketDao implements IDao<Ticket>{
	private final static String GET_TICKET_BY_ID = "select * from ticket where ticket_id = ?";
	private final static String INSERT_TICKET = "insert into ticket values (default ,?,?,?)";
	private final static String GET_TICKETS_BY_MOVIE = "select * from ticket where movie_session_id = ?";
	private final static String GET_TICKETS_BY_ORDER = "select * from ticket where order_id = ?";
	private final static String GET_TICKET_BY_SESSION_AND_PLACE_NUMBER = "select * from ticket, movie_session " +
			"where ticket.place_number = ? and ticket.movie_session_id = ?";
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
			con.close();
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
			PreparedStatement ps = con.prepareStatement(INSERT_TICKET, Statement.RETURN_GENERATED_KEYS);
			setParams(ps, ticket);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next())
				ticket.setId(rs.getLong(1));
			con.close();
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

	public List<Ticket> getTicketsBySession(MovieSession session) {
		List<Ticket> tickets = new LinkedList<>();
		try{
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(GET_TICKETS_BY_MOVIE);
			ps.setLong(1,session.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				tickets.add(getTicket(rs));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	public Ticket getTicketBySession(Ticket ticket, MovieSession session) {
		Ticket existedTicket = null;
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(GET_TICKET_BY_SESSION_AND_PLACE_NUMBER);
			ps.setInt(1,ticket.getPlaceNumber());
			ps.setLong(2,session.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				existedTicket.setId(rs.getLong(1));
				existedTicket.setPlaceNumber(rs.getInt(2));
				existedTicket.setOrderId(rs.getLong(3));
				existedTicket.setSessionId(rs.getLong(4));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existedTicket;
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
