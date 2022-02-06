package com.chornobuk.web.model.dao;

import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.MovieSession;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class MovieSessionDao implements IDao<MovieSession> {
	public static final String GET_SESSION_BY_ID = "select * from movie_session where session_id = ?";
	public static final String INSERT_SESSION = "insert into movie_session values (default,?,?,?,?)";

	@Override
	public MovieSession get(long id) {
		MovieSession movieSession = null;
		try{
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(GET_SESSION_BY_ID);
			ps.setLong(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				movieSession = getValues(rs);
		}catch (SQLException e) {
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
			if(rs.next())
				movieSession.setId(rs.getLong(1));
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(MovieSession movieSession) {
// don't need now
	}

	@Override
	public void delete(MovieSession movieSession) {
// don't need now
	}

	private void setParams(PreparedStatement ps, MovieSession movieSession) throws SQLException{
		ps.setLong(1, movieSession.getMovieId());
		ps.setObject(2,movieSession.getBeginningDate());
		ps.setObject(3,movieSession.getEndingDate());
		ps.setInt(4,movieSession.getAvailablePlaces());
	}

	private MovieSession getValues(ResultSet rs) throws SQLException {
		MovieSession movieSession = new MovieSession();
		movieSession.setId(rs.getLong(1));
		movieSession.setMovieId(rs.getLong(2));
		movieSession.setBeginningDate(rs.getObject(3, LocalDateTime.class));
		movieSession.setEndingDate(rs.getObject(4,LocalDateTime.class));
		movieSession.setAvailablePlaces(rs.getInt(5));

		return movieSession;
	}
}
