package com.chornobuk.web.model.builder;

import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//todo query as final
public abstract class QueryBuilder<T extends Entity> {


//	method for execute query without results (delete, update, add)
//	method for execute query with one result (select)
//	method for execute query with many results (select)
//	get method for get id (create in every builder query for getting max id
//	method for calling function in database
//	method for calling a procedure

	public abstract T getObject(ResultSet rs) throws SQLException;

	public long getNextId(DBManager instance, String query) {
		long nextId = -1;
		Connection connection = instance.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				nextId = resultSet.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nextId;
	}

	public List<T> getValues(DBManager instance, String query, Object... args) {
		List<T> objects = new LinkedList<>();
		Connection connection = instance.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			setArgsForPrepareStatement(preparedStatement, args);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				objects.add(getObject(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		instance.closeConnection(connection);
		return objects;
	}

	public T getValue(DBManager instance, String query, Object... args) {
		T object = null;
		Connection connection = instance.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			setArgsForPrepareStatement(preparedStatement, args);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
				object = getObject(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		instance.closeConnection(connection);
		return object;
	}


	public final void execute(DBManager instance, String query) {
		executeQuery(instance, query);
	}

	public final void execute(DBManager instance, String query, Object... args) {
		executeQuery(instance, query, args);
	}

	public void executeQuery(DBManager instance, String query, Object... args) {
		Connection connection = instance.getConnection();
		try (PreparedStatement prepareStatement = connection.prepareStatement(query)) {
			setArgsForPrepareStatement(prepareStatement, args);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			instance.closeConnection(connection);
		}
	}

	public void executeTransaction(DBManager instance, List<Map.Entry<String,Object[]>> queries) {
		PreparedStatement [] statements = new PreparedStatement[queries.size()];
		Connection connection = instance.getConnection();
		try {
			connection.setAutoCommit(false);
			int i = 0;
			for(Map.Entry<String, Object[]> entry : queries){
				statements[i] = connection.prepareStatement(entry.getKey());
				setArgsForPrepareStatement(statements[i], entry.getValue());
				i++;
			}
			for (int j = 0; j < statements.length; j++) {
				statements[j].executeUpdate();
			}
			connection.commit();
			connection.setAutoCommit(true);
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		finally {
			instance.closeConnection(connection);
		}
	}

	private void setArgsForPrepareStatement(PreparedStatement ps, Object... args) throws SQLException {
		for (int i = 0; i < args.length; i++) {
			ps.setObject(i + 1, args[i]);
		}
	}
}
