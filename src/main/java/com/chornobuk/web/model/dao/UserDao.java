package com.chornobuk.web.model.dao;

import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.User;

import java.sql.*;
import java.util.List;

public class UserDao implements IDao<User> {
	private final static String GET_USER_BY_LOGIN = "select * from cinema_user where login = ?";
	private final static String INSERT_USER = "insert into cinema_user values(default,?,?,?,?,?,?);";
	DBManager connectionPool = DBManager.getInstance();

	@Override
	public User get(long id) {
//		don't need now
		User user = null;
		return user;
	}

	public User getUserByLogin(String login) {
		User user = null;
		try {
			Connection con = connectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(GET_USER_BY_LOGIN);
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = getUser(rs);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getAll() {
//		I don't need to get all users now
		return null;
	}

	@Override
	public void add(User user) {
		try {
			Connection con = connectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			setParams(ps, user);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next())
				user.setId(rs.getLong(1));

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(User user) {
//		I don't need to update user now
	}

	@Override
	public void delete(User user) {
//		I don't need to delete user
	}

	public User getUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(1));
		user.setLogin(rs.getString(2));
		user.setName(rs.getString(3));
		user.setSurname(rs.getString(4));
		user.setPassword(rs.getString(5));
		user.setSalt(rs.getString(6));
		user.setRoleId(rs.getInt(7));
		return user;
	}

	private void setParams(PreparedStatement ps, User user) throws SQLException {
		ps.setString(1, user.getLogin());
		ps.setString(2, user.getName());
		ps.setString(3, user.getSurname());
		ps.setString(4, user.getPassword());
		ps.setString(5, user.getSalt());
		ps.setLong(6, user.getRoleId());
	}
}
