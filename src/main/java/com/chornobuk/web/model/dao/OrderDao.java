package com.chornobuk.web.model.dao;

import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class OrderDao implements IDao<Order> {
	private final static String GET_ORDER_BY_ID = "select * from tickets_order where order_id = ?";
	private final static String INSERT_ORDER = "insert into tickets_order values(default,?,?,?)";
	private final static String INSERT_TICKET = "insert into ticket values(default, ?, ?, ?)";
	private final static String GET_ORDERS_BY_USER = "select * from tickets_order where user_id = ?";
	private final static String GET_PAID_ORDER_STATUS = "select * from order_status where status_name = ?";
	private final static String GET_ORDER_ID_PRICE_MAP_BY_USER = "select ticket.order_id, sum(ticket_price) from ticket" +
			" inner join tickets_order" +
			" on ticket.order_id = tickets_order.order_id" +
			" inner join movie_session" +
			" on ticket.movie_session_id = movie_session.session_id" +
			" inner join movie" +
			" on movie.movie_id = movie_session.movie_id" +
			" inner join cinema_user" +
			" on tickets_order.user_id = cinema_user.user_id" +
			" where cinema_user.user_id = ?" +
			" group by ticket.order_id ";

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
			OrderStatus orderStatus = getStatusByName("Оплачено");
			con.setAutoCommit(false);
//			set parameters for order
			insertOder.setLong(1, user.getId());
			insertOder.setLong(2, orderStatus.getId());
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

	private OrderStatus getStatusByName(String name) {
		OrderStatus orderStatus = new OrderStatus();
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement getOrderStatus= con.prepareStatement(GET_PAID_ORDER_STATUS);
			getOrderStatus.setString(1, name);
			ResultSet rs = getOrderStatus.executeQuery();
			if(rs.next()) {
				orderStatus.setId(rs.getLong(1));
				orderStatus.setName(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderStatus;
	}

	public Map<Long, Integer> getOrdersPricesByUser(User user) {
		Map<Long, Integer> map = new HashMap<>();
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(GET_ORDER_ID_PRICE_MAP_BY_USER);
			ps.setLong(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				long orderId = rs.getLong(1);
				int price = rs.getInt(2);
				map.put(orderId, price);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	public List<Order> getOrdersByUser(User user) {
		List<Order> orders = new LinkedList<>();
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(GET_ORDERS_BY_USER);
			ps.setLong(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order order = getOrder(rs);
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
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
