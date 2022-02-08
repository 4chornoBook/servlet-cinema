package com.chornobuk.web.model.dao;

import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.Genre;
import com.chornobuk.web.model.entity.Movie;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class MovieDao implements IDao<Movie> {
	private static final String GET_MOVIE_BY_ID = "select * from movie where movie_id = ?";
	private static final String INSERT_MOVIE = "insert into movie values(default, ?,?,?,?,?,?)";
	private static final String GET_MOVIE_GENRES = "select genre.genre_name from genre inner join movie_genre on genre.genre_id = movie_genre.genre_id inner join movie on movie_genre.movie_id = movie.movie_id where movie.movie_id = ?";
	private static final String INSERT_MOVIE_GENRE = "insert into movie_genre values(?, ?)";

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
		Connection con = DBManager.getInstance().getConnection();
		try {
			PreparedStatement addMovie = con.prepareStatement(INSERT_MOVIE, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement addMovieGenre = con.prepareStatement(INSERT_MOVIE_GENRE);
			con.setAutoCommit(false);
//			insert new movie
			setParams(addMovie, movie);
			addMovie.execute();
			ResultSet generatedId = addMovie.getGeneratedKeys();
			if (generatedId.next()) {
				movie.setId(generatedId.getLong(1));
			} else
				throw new SQLException("Key wasn't returned");
//			insert data for intermediate table
			for (Genre genre : movie.getGenres()) {
				addMovieGenre.setLong(1, movie.getId());
				addMovieGenre.setLong(2, genre.getId());
				addMovieGenre.execute();
			}
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			if (con != null) {
				try {
					System.out.println("transaction is being rolled back");
					con.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Movie movie) {

	}

	@Override
	public void delete(Movie movie) {

	}

	private LinkedList<Genre> getMovieGenres(Movie movie) {
		LinkedList<Genre> genres = new LinkedList<>();
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(GET_MOVIE_BY_ID);
			ps.setLong(1, movie.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				genres.add(new Genre(rs.getLong(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genres;
	}

	private void addMovieGenres(Movie movie) {
//		must be transaction. if Movie don't add there are no any movieGenres;
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
