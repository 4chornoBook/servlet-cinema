package com.chornobuk.web.model.dao;

import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.Movie;
import com.chornobuk.web.model.entity.MovieGenre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MovieGenreDao implements IDao<MovieGenre> {
	private final static String GET_MOVIE_GENRES = "select * from movie_genre where movie_id = ?";
	private final static String ADD_MOVIE_GENRE = "insert into movie_genre values(?,?)";

	@Override
	public MovieGenre get(long id) {
		return null;
	}

	@Override
	public List<MovieGenre> getAll() {
		return null;
	}

	public Set<MovieGenre> getMovieGenres(Movie movie) {
		HashSet<MovieGenre> movieGenres = new HashSet<>();
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(GET_MOVIE_GENRES);
			ps.setLong(1, movie.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				movieGenres.add(getValues(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return movieGenres;
	}

	@Override
	public void add(MovieGenre movieGenre) {
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(ADD_MOVIE_GENRE);
			ps.setLong(1, movieGenre.getMovieId());
			ps.setLong(2, movieGenre.getGenreId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(MovieGenre movieGenre) {

	}

	@Override
	public void delete(MovieGenre movieGenre) {

	}

	public MovieGenre getValues(ResultSet rs) throws SQLException{
		long movieId = rs.getLong(1);
		long genreId = rs.getLong(2);
		return new MovieGenre(movieId,genreId);
	}
}
