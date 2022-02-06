package com.chornobuk.web.model.dao;

import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDao implements IDao<Order> {
	private final static String GET_ORDER_BY_ID = "select * from tickets_order where order_id = ?";
	private final static String INSERT_ORDER = "insert into tickets_order values(default,?,?,?)";

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
			PreparedStatement ps = con.prepareStatement(INSERT_ORDER);
			setParams(ps, order);
			ps.execute();
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
