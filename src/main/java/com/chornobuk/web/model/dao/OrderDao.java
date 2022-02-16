package com.chornobuk.web.model.dao;

import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.MovieSession;
import com.chornobuk.web.model.entity.Order;
import com.chornobuk.web.model.entity.Ticket;
import com.chornobuk.web.model.entity.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Stack;

public class OrderDao implements IDao<Order> {
	private final static String GET_ORDER_BY_ID = "select * from tickets_order where order_id = ?";
	private final static String INSERT_ORDER = "insert into tickets_order values(default,?,?,?)";
	private final static String INSERT_TICKET = "insert into ticket values(default, ?, ?, ?)";

	@Override
	public Order get(long id) {
		Order order = null;
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(GET_ORDER_BY_ID);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				order = getOrder(rs);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public List<Order> getAll() {
		return null;
	}

	@Override
	public void add(Order order) {
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
			setParams(ps, order);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next())
				order.setId(rs.getLong(1));
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Order order) {
//		don't need now
	}

	@Override
	public void delete(Order order) {
//		don't need now
	}

	public void addOrderWithTickets(Order order, User user, MovieSession session, Ticket[] tickets) {
		Connection con = DBManager.getInstance().getConnection();
		try {
			PreparedStatement insertOder = con.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement insertTicket = con.prepareStatement(INSERT_TICKET, Statement.RETURN_GENERATED_KEYS);
			con.setAutoCommit(false);
//			set parameters for order
			insertOder.setLong(1, user.getId());
			insertOder.setLong(2, 2);
			insertOder.setObject(3, order.getCreationDate());
			insertOder.execute();
			ResultSet orderKey = insertOder.getGeneratedKeys();
			if (orderKey.next())
				order.setId(orderKey.getLong(1));
			for (int i = 0; i < tickets.length; i++) {
//			set parameters for ticket
				insertTicket.setInt(1, tickets[i].getPlaceNumber());
				insertTicket.setLong(2, order.getId());
				insertTicket.setLong(3, session.getId());
				insertTicket.execute();
				ResultSet ticketKey = insertTicket.getGeneratedKeys();
				if (ticketKey.next())
					tickets[i].setId(ticketKey.getLong(1));
			}
			con.commit();
//			commit transaction
			con.setAutoCommit(true);
			con.close();
		} catch (SQLException e) {
			if (con != null) {
				try {
					System.out.println("transaction is being rolled back");
					con.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				e.printStackTrace();
			}
		}
	}

	private void setParams(PreparedStatement ps, Order order) throws SQLException {
		ps.setLong(1, order.getUserId());
		ps.setLong(2, order.getStatusId());
		ps.setObject(3, order.getCreationDate());
	}

	private Order getOrder(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setId(rs.getLong(1));
		order.setUserId(rs.getLong(2));
		order.setStatusId(rs.getLong(3));
		order.setCreationDate(rs.getObject(4, LocalDateTime.class));

		return order;
	}
}
