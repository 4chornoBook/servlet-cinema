package com.chornobuk.web.model.builder;

import com.chornobuk.web.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrderQueryBuilder extends QueryBuilder<Order> {
	@Override
	public Order getObject(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setId(rs.getLong("id"));
		order.setUserId(rs.getLong("user_id"));
		order.setCreationDate(rs.getObject("creation_date", LocalDateTime.class));
		order.setTotalPrice(rs.getInt("total_price"));
		return order;
	}
}
