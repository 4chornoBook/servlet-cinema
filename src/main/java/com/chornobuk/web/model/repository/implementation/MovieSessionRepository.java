package com.chornobuk.web.model.repository.implementation;

import com.chornobuk.web.model.builder.MovieSessionQueryBuilder;
import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.Movie;
import com.chornobuk.web.model.entity.MovieSession;
import com.chornobuk.web.model.repository.IRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class MovieSessionRepository implements IRepository<MovieSession> {
	private DBManager instance = DBManager.getInstance();
	MovieRepository movieRepository = new MovieRepository();
	MovieSessionQueryBuilder movieSessionQueryBuilder = new MovieSessionQueryBuilder();
	private static final String GET_NEXT_ID = "select max(id)+1 from movie_session";
	private static final String GET_BY_ID = "select * from movie_session where id = ?";
	private static final String INSERT = "insert into movie_session values (default, ?, ?, ?, ?, ?)";
	private static final String DELETE_BY_ID = "delete from movie_session where id = ?";
	private static final String UPDATE = "update movie_session set movie_id = ?, session_date = ?, beginning_time = ?, ending_time = ?, ticket_price = ? where id = ?";
	private static final String GET_LIMITED_WITH_OFFSET = "select * from movie_session where movie_session.session_date + movie_session.beginning_time >= now() limit ? offset ?";
	private static final String DELETE_TICKETS_BY_SESSION = "delete from ticket where movie_session_id = ?";
	private static final String GET_AVAILABLE = "select movie_session.* from movie_session where session_date + beginning_time >= now()";
	private static final String DELETE_ORDERS_BY_SESSION = "delete from tickets_order" +
			" where exists" +
			" (select 1 from ticket" +
			" where ticket.order_id = tickets_order.id" +
			" and ticket.movie_session_id = ?)";
	private static final String GET_SESSIONS_BY_TIME = "select * from movie_session"+
	" where session_date = ?"+
	" and ( (? <= ending_time and ? > beginning_time ) or"+
		" (? < ending_time and ? >= beginning_time) )";


	@Override
	public MovieSession get(MovieSession entity) {
		MovieSession movieSession = movieSessionQueryBuilder.getValue(instance, GET_BY_ID, entity.getId());
		movieSession.setMovie(movieRepository.get(new Movie(movieSession.getMovieId())));
		return movieSession;
	}

	@Override
	public void delete(MovieSession entity) {
		List<Map.Entry<String, Object[]>> queryParamsMap = new LinkedList<>();
		queryParamsMap.add(new AbstractMap.SimpleEntry<>(DELETE_TICKETS_BY_SESSION, new Object[] {entity.getId()}));
		queryParamsMap.add(new AbstractMap.SimpleEntry<>(DELETE_ORDERS_BY_SESSION, new Object[]{entity.getId()}));
		queryParamsMap.add(new AbstractMap.SimpleEntry<>(DELETE_BY_ID, new Object[]{entity.getId()}));
		movieSessionQueryBuilder.executeTransaction(instance,queryParamsMap);
	}

	@Override
	public void update(MovieSession entity) {
		movieSessionQueryBuilder.executeQuery(instance, UPDATE,
				entity.getMovieId(),
				entity.getMovieDate(),
				entity.getBeginningTime(),
				entity.getEndingTime(),
				entity.getMovieId(),
				entity.getId()
		);
	}

	@Override
	public void add(MovieSession entity) {
		movieSessionQueryBuilder.insertNewEntity(instance, entity, INSERT,
				entity.getMovieId(),
				entity.getMovieDate(),
				entity.getBeginningTime(),
				entity.getEndingTime(),
				entity.getTicketPrice()
		);
	}

	public List<MovieSession> getLimitedWithOffset(String query, int limit, int offset) {
		List<MovieSession> sessions = movieSessionQueryBuilder.getValues(instance, query, limit, offset);
		for(MovieSession session : sessions) {
			session.setMovie(movieRepository.get(new Movie(session.getMovieId())));
		}
		return sessions;
	}

	public List<MovieSession> getByQuery(String query) {
		List<MovieSession> sessions = movieSessionQueryBuilder.getValues(instance, query);
		for(MovieSession session : sessions) {
			session.setMovie(movieRepository.get(new Movie(session.getMovieId())));
		}
		return sessions;
	}

	public List<MovieSession> getByTime(LocalDate date, LocalTime beginning, LocalTime ending) {
		return movieSessionQueryBuilder.getValues(instance, GET_SESSIONS_BY_TIME, date, ending, ending, beginning, beginning);
	}

	public List<MovieSession> getAvailable() {
		List<MovieSession> sessions = movieSessionQueryBuilder.getValues(instance, GET_AVAILABLE);
		for(MovieSession session : sessions) {
			session.setMovie(movieRepository.get(new Movie(session.getMovieId())));
		}
		return sessions;
	}
}
