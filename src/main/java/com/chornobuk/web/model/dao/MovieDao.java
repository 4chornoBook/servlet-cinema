package com.chornobuk.web.model.dao;

import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.Movie;
import com.chornobuk.web.model.entity.MovieSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MovieDao implements IDao<Movie> {
	private static final String GET_MOVIE_BY_ID = "select * from movie where id = ?";
	private static final String INSERT_MOVIE = "insert into movie values(default, ?,?,?,?,?,?)";

	@Override
	public Movie get(long id) {
		Movie movie = null;

		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(GET_MOVIE_BY_ID);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				movie = getValues(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movie;
	}

	@Override
	public List<Movie> getAll() {
		return null;
	}

	@Override
	public void add(Movie movie) {
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT_MOVIE);
			setParams(ps, movie);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		add all links on genres
	}

	@Override
	public void update(Movie movie) {

	}

	@Override
	public void delete(Movie movie) {

	}

	public void setParams(PreparedStatement ps, Movie movie) throws SQLException {
		ps.setString(1, movie.getName());
		ps.setObject(2, movie.getReleaseDate());
		ps.setString(3, movie.getDescription());
		ps.setString(4, movie.getImageURL());
		ps.setInt(5, movie.getTicketPrice());
		ps.setInt(6, movie.getLengthInMinutes());
	}

	public Movie getValues(ResultSet rs) throws SQLException {
		Movie movie = new Movie();
		movie.setId(rs.getLong(1));
		movie.setName(rs.getString(2));
		movie.setReleaseDate(rs.getObject(3, LocalDate.class));
		movie.setDescription(rs.getString(4));
		movie.setImageURL(rs.getString(5));
		movie.setTicketPrice(rs.getInt(6));
		movie.setLengthInMinutes(rs.getInt(7));

		return movie;
	}
}
