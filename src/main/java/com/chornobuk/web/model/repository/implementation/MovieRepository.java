package com.chornobuk.web.model.repository.implementation;

import com.chornobuk.web.model.builder.MovieQueryBuilder;
import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.Genre;
import com.chornobuk.web.model.entity.Movie;
import com.chornobuk.web.model.repository.IRepository;

import java.util.*;

public class MovieRepository implements IRepository<Movie> {
	MovieQueryBuilder movieQueryBuilder = new MovieQueryBuilder();
	DBManager instance = DBManager.getInstance();
	private final static String GET_NEXT_ID = "select max(id)+1 from movie";
	private final static String GET_BY_ID = "select * from movie where id = ?";
	private final static String GET_ALL = "select * from movie";
	private final static String DELETE_BY_ID = "delete from movie where id ?";
	private final static String INSERT = "insert into movie values (?,?,?,?,?,?,?)";
	private final static String INSERT_GENRE = "insert into movie_genre values (?, ?)";
	private final static String DELETE_MOVIE_GENRES = "delete from movie_genre where movie_id = ?";
	private final static String UPDATE = "update movie set name = ?, release_date = ?, description = ?, image_url = ?, length_in_minutes = ? where id = ?";
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
		List<Map.Entry<String, Object[]>> queryParametersMap = new LinkedList<>();
		queryParametersMap.add(new AbstractMap.SimpleEntry<>(DELETE_BY_ID, new Object[]{entity.getId()}));
		queryParametersMap.add(new AbstractMap.SimpleEntry<>(DELETE_MOVIE_GENRES, new Object[]{entity.getId()}));
		movieQueryBuilder.executeTransaction(instance, queryParametersMap);
	}

	@Override
	public void update(Movie entity) {
		movieQueryBuilder.executeQuery(instance, UPDATE,
				entity.getName(),
				entity.getReleaseDate(),
				entity.getDescription(),
				entity.getImageURL(),
				entity.getLengthInMinutes(),
				entity.getId()
		);
	}

	@Override
	public void add(Movie entity) {
		long id = movieQueryBuilder.getNextId(instance, GET_NEXT_ID);
		entity.setId(id);
		List<Map.Entry<String, Object[]>> queryParametersMap = new LinkedList<>();
//		insert new movie
		queryParametersMap.add(new AbstractMap.SimpleEntry<>(INSERT, new Object[] {
				entity.getId(),
				entity.getName(),
				entity.getReleaseDate(),
				entity.getDescription(),
				entity.getImageURL(),
				entity.getLengthInMinutes()
		}));
//		insert movie's genres
		for(Genre g : entity.getGenres()) {
			queryParametersMap.add(new AbstractMap.SimpleEntry<>(INSERT_GENRE, new Object[]{entity.getId(), g.getId()}));
		}
		movieQueryBuilder.executeTransaction(instance, queryParametersMap);
	}

	public List<Movie> getAll() {
		return movieQueryBuilder.getValues(instance, GET_ALL);
	}

	public List<Movie> getAvailable() {
		return movieQueryBuilder.getValues(instance, GET_AVAILABLE);
	}
}
