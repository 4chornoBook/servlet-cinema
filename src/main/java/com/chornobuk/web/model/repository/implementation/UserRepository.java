package com.chornobuk.web.model.repository.implementation;

import com.chornobuk.web.model.builder.UserQueryBuilder;
import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity1.User;
import com.chornobuk.web.model.repository.IRepository;

//todo test execute query without parameters
public class UserRepository implements IRepository<User> {
	private UserQueryBuilder queryBuilder = new UserQueryBuilder();
	private DBManager instance = DBManager.getInstance();
	private static final String GET_NEXT_ID = "select max(id)+1 from cinema_user";
	private static final String GET_BY_LOGIN = "select * from cinema_user where login = ?";
	private static final String DELETE_BY_ID = "delete from cinema_user where id = ?";
	private static final String UPDATE_BY_ID = "";
	private static final String ADD_NEW = "insert into cinema_user values (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_DATA_BY_ID = "update cinema set login = ?, name = ?, surname = ?, password = ?, salt = ?, role_id = ? where id = ?";
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
		queryBuilder.executeQuery(instance, UPDATE_DATA_BY_ID,
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
		long id = queryBuilder.getNextId(instance, GET_NEXT_ID);
		entity.setId(id);
		queryBuilder.executeQuery(instance, ADD_NEW,
				id,
				entity.getLogin(),
				entity.getName(),
				entity.getSurname(),
				entity.getPassword(),
				entity.getSalt(),
				entity.getRoleId()
		);
	}

	public User getByLogin(String login) {
		User user = new User();
		user.setLogin(login);
		return get(user);
	}
}
