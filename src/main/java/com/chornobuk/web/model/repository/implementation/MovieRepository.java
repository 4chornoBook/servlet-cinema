package com.chornobuk.web.model.repository.implementation;

import com.chornobuk.web.model.builder.MovieQueryBuilder;
import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.Movie;
import com.chornobuk.web.model.repository.IRepository;

import java.util.List;

public class MovieRepository implements IRepository<Movie> {
	MovieQueryBuilder movieQueryBuilder = new MovieQueryBuilder();
	DBManager instance = DBManager.getInstance();
	private final static String GET_NEXT_ID = "select max(id)+1 from movie";
	private final static String GET_BY_ID = "select * from movie where id = ?";
	private final static String GET_ALL = "select * from movie";
	private final static String DELETE_BY_ID = "delete from movie where id ?";
	private final static String ADD_NEW = "insert into movie values (?,?,?,?,?,?,?)";
	private final static String UPDATE = "update movie set name = ?, release_date = ?, description = ?, image_url = ?, ticket_price = ?, length_in_minutes = ? where id = ?";
	private final static String GET_AVAILABLE = "select movie.* from movie" +
			" inner join movie_session" +
			" on movie.id = movie_session.movie_id" +
			" where movie_session.session_date + movie_session.beginning_time >= now()" +
			" group by movie.id ";

	@Override
	public Movie get(Movie entity) {
		return movieQueryBuilder.getValue(instance, GET_BY_ID, entity.getId());
	}

	@Override
	public void delete(Movie entity) {
		movieQueryBuilder.executeQuery(instance, DELETE_BY_ID, entity.getId());
	}

	@Override
	public void update(Movie entity) {
		movieQueryBuilder.executeQuery(instance, UPDATE,
				entity.getName(),
				entity.getReleaseDate(),
				entity.getDescription(),
				entity.getImageURL(),
				entity.getTicketPrice(),
				entity.getLengthInMinutes(),
				entity.getId()
		);
	}

	@Override
	public void add(Movie entity) {
		long id = movieQueryBuilder.getNextId(instance, GET_NEXT_ID);
		entity.setId(id);
		movieQueryBuilder.executeQuery(instance, ADD_NEW,
				entity.getId(),
				entity.getName(),
				entity.getReleaseDate(),
				entity.getDescription(),
				entity.getImageURL(),
				entity.getTicketPrice(),
				entity.getLengthInMinutes()
		);
	}

	public List<Movie> getAll() {
		return movieQueryBuilder.getValues(instance, GET_ALL);
	}

	public List<Movie> getAvailable() {
		return movieQueryBuilder.getValues(instance, GET_AVAILABLE);
	}
}
