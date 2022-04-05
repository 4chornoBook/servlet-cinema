package com.chornobuk.web.model.dao;

import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.Movie;
import com.chornobuk.web.model.entity.MovieSession;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class MovieSessionDao implements IDao<MovieSession> {
	private static final String GET_SESSION_BY_ID = "select * from movie_session where id = ?";
	private static final String INSERT_SESSION = "insert into movie_session values (default,?,?,?,?,?)";
	private static final String DELETE_SESSION_BY_ID = "delete from movie_session where id = ?";
	private static final String DELETE_TICKETS_BY_SESSION = "delete from ticket where movie_session_id = ?";
	//	todo rewrite as a procedure
	private static final String GET_AVAILABLE_MOVIES = "select movie_session.*, movie.* from movie_session"
			+ " inner join movie"
			+ " on movie_session.movie_id = movie.id"
			+ " where movie_session.session_date + movie_session.beginning_time >= now()";
	private static final String GET_N_SESSIONS_FROM_POSITION = "select movie_session.*, movie.* from movie_session"
			+ " inner join movie"
			+ " on movie_session.movie_id = movie.id"
			+ " where movie_session.session_date + movie_session.beginning_time >= now()"
			+ " order by movie_session.session_date, movie_session.beginning_time"
			+ " limit ? offset ?";
	private static final String IS_TIME_SLOT_AVAILABLE = "select is_slot_available(?,?,?)";
	private static final String DELETE_ORDERS_BY_SESSION = "delete from tickets_order" +
			" where exists" +
			" (select 1 from ticket" +
			" where ticket.order_id = tickets_order.id" +
			" and ticket.movie_session_id = ?)";
	@Override
	public MovieSession get(long id) {
		MovieSession movieSession = null;
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(GET_SESSION_BY_ID);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				movieSession = getValues(rs);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movieSession;
	}

	@Override
	public List<MovieSession> getAll() {
//		don't need now
		return null;
	}

	@Override
	public void add(MovieSession movieSession) {
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT_SESSION, Statement.RETURN_GENERATED_KEYS);
			setParams(ps, movieSession);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next())
				movieSession.setId(rs.getLong(1));
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(MovieSession movieSession) {
// don't need now
	}

	@Override
	public void delete(MovieSession movieSession) {
//		method removes session and all tickets
		Connection con = DBManager.getInstance().getConnection();
		try {
			con.setAutoCommit(false);
			PreparedStatement deleteTickets = con.prepareStatement(DELETE_TICKETS_BY_SESSION);
			PreparedStatement deleteSession = con.prepareStatement(DELETE_SESSION_BY_ID);
			PreparedStatement deleteOrders = con.prepareStatement(DELETE_ORDERS_BY_SESSION);
			deleteTickets.setLong(1, movieSession.getId());
			deleteSession.setLong(1, movieSession.getId());
			deleteOrders.setLong(1,movieSession.getId());

			deleteTickets.execute();
			deleteSession.execute();
			deleteOrders.execute();
			con.commit();
			con.setAutoCommit(true);
			con.close();
		} catch (SQLException e) {
			if (con != null) {
				try {
					System.out.println("transaction is being rolled back");
					con.rollback();
				} catch (SQLException ex) {

				}
			}
			e.printStackTrace();
		}
	}

	public List<MovieSession> getAvailableSessions() {
		List<MovieSession> availableSession = new LinkedList<>();
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(GET_AVAILABLE_MOVIES);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MovieSession session = getValues(rs);
				Movie movie = new Movie();
				movie.setId(session.getMovieId());
				movie.setName(rs.getString(8));
				movie.setReleaseDate(rs.getObject(9, LocalDate.class));
				movie.setDescription(rs.getString(10));
				movie.setImageURL(rs.getString(11));
				movie.setTicketPrice(rs.getInt(12));
				movie.setLengthInMinutes(rs.getInt(13));
				session.setMovie(movie);
				availableSession.add(session);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return availableSession;
	}

	public List<MovieSession> getSomeElements(int offset, int limit) {
		List<MovieSession> sessions = new LinkedList<>();
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(GET_N_SESSIONS_FROM_POSITION);
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MovieSession session = getValues(rs);
				Movie movie = new Movie();
				movie.setId(session.getMovieId());
				movie.setName(rs.getString(8));
				movie.setReleaseDate(rs.getObject(9, LocalDate.class));
				movie.setDescription(rs.getString(10));
				movie.setImageURL(rs.getString(11));
				movie.setTicketPrice(rs.getInt(12));
				movie.setLengthInMinutes(rs.getInt(13));
				session.setMovie(movie);
				sessions.add(session);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sessions;
	}

	public List<MovieSession> getSomeElementsByQuery(String query, int offset, int limit) {
		List<MovieSession> sessions = new LinkedList<>();
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MovieSession session = getValues(rs);
				Movie movie = new Movie();
				movie.setId(session.getMovieId());
				movie.setName(rs.getString(8));
				movie.setReleaseDate(rs.getObject(9, LocalDate.class));
				movie.setDescription(rs.getString(10));
				movie.setImageURL(rs.getString(11));
				movie.setTicketPrice(rs.getInt(12));
				movie.setLengthInMinutes(rs.getInt(13));
				session.setMovie(movie);
				sessions.add(session);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sessions;
	}

	public int getNumberOfAvailableSessions() {
		return getAvailableSessions().size();
	}

	public boolean isSlotAvailable(MovieSession session) {
		boolean isFree = false;

		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(IS_TIME_SLOT_AVAILABLE);
			ps.setObject(1,session.getMovieDate());
			ps.setObject(2, session.getBeginningTime());
			ps.setObject(3,session.getEndingTime());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				isFree = rs.getBoolean(1);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isFree;
	}

	private void setParams(PreparedStatement ps, MovieSession movieSession) throws SQLException {
		ps.setLong(1, movieSession.getMovieId());
		ps.setInt(2, movieSession.getAvailablePlaces());
		ps.setObject(3, movieSession.getMovieDate());
		ps.setObject(4, movieSession.getBeginningTime());
		ps.setObject(5, movieSession.getEndingTime());
	}

	private MovieSession getValues(ResultSet rs) throws SQLException {
		MovieSession movieSession = new MovieSession();
		movieSession.setId(rs.getLong(1));
		movieSession.setMovieId(rs.getLong(2));
		movieSession.setAvailablePlaces(rs.getInt(3));
		movieSession.setMovieDate(rs.getObject(4, LocalDate.class));
		movieSession.setBeginningTime(rs.getObject(5, LocalTime.class));
		movieSession.setEndingTime(rs.getObject(6, LocalTime.class));
		return movieSession;
	}
}
