package com.chornobuk.web.model.repository.implementation;

import com.chornobuk.web.model.builder.UserQueryBuilder;
import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity1.User;
import com.chornobuk.web.model.repository.IRepository;

//todo test execute query without parameters
public class UserRepository implements IRepository<User> {
	private UserQueryBuilder queryBuilder = new UserQueryBuilder();
	private DBManager instance = DBManager.getInstance();
	private static final String GET_BY_LOGIN = "select * from cinema_user where login = ?";
	private static final String DELETE_BY_ID = "delete from cinema_user where id = ?";
	private static final String UPDATE_BY_ID = "";
	private static final String ADD_NEW = "insert into cinema user values (?, ?, ?, ?, ?, ?, ?)";

	@Override
	public User get(User entity) {
		return queryBuilder.getValue(instance, GET_BY_LOGIN, entity.getLogin());
	}

	@Override
	public void delete(User entity) {
		queryBuilder.executeQuery(instance, DELETE_BY_ID);
	}

	@Override
	public void update(User entity) {

	}

	@Override
	public void add(User entity) {

	}

	public User getByLogin(String login) {
		User user  = new User();
		user.setLogin(login);
		return get(user);
	}
}
