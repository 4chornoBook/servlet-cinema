package com.chornobuk.web.model.builder;

import com.chornobuk.web.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQueryBuilder extends QueryBuilder<User> {

	@Override
	public User getObject(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setLogin(rs.getString("login"));
		user.setName(rs.getString("name"));
		user.setSurname(rs.getString("surname"));
		user.setPassword(rs.getString("password"));
		user.setSalt(rs.getString("salt"));
		user.setRoleId(rs.getInt("role_id"));
		return user;
	}
}
