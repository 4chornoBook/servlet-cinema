package com.chornobuk.web.model.repository.implementation;

import com.chornobuk.web.model.builder.UserQueryBuilder;
import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.User;
import com.chornobuk.web.model.repository.IRepository;

//todo test execute query without parameters
public class UserRepository implements IRepository<User> {
	private UserQueryBuilder queryBuilder = new UserQueryBuilder();
	private DBManager instance = DBManager.getInstance();
	private static final String GET_BY_LOGIN = "select * from cinema_user where login = ?";
	private static final String DELETE_BY_ID = "delete from cinema_user where id = ?";
	private static final String INSERT = "insert into cinema_user values (default, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "update cinema set login = ?, name = ?, surname = ?, password = ?, salt = ?, role_id = ? where id = ?";
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
		queryBuilder.executeQuery(instance, UPDATE,
				entity.getLogin(),
				entity.getName(),
				entity.getSurname(),
				entity.getPassword(),
				entity.getSalt(),
				entity.getRoleId(),
				entity.getId() // update data by id
		);
	}

	@Override
	public void add(User entity) {
		queryBuilder.insertNewEntity(instance, entity, INSERT,
				entity.getLogin(),
				entity.getName(),
				entity.getSurname(),
				entity.getPassword(),
				entity.getSalt(),
				entity.getRoleId()
		);
	}
}
