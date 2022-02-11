package com.chornobuk.web.model.dao;

import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.MovieSession;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MovieSessionDao implements IDao<MovieSession> {
	public static final String GET_SESSION_BY_ID = "select * from movie_session where session_id = ?";
	public static final String INSERT_SESSION = "insert into movie_session values (default,?,?,?,?,?)";
	public static final String DELETE_SESSION_BY_ID = "delete from movie_session where session_id = ?";
	public static final String DELETE_TICKETS_BY_SESSION = "delete from ticket where movie_session_id = ?";

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
			deleteTickets.setLong(1, movieSession.getId());
			deleteSession.setLong(1, movieSession.getId());
			deleteTickets.execute();
			deleteSession.execute();
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
