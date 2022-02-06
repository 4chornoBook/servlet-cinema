package com.chornobuk.web.model.dao;

import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.Genre;
import com.chornobuk.web.model.entity.MovieGenre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class GenreDao implements IDao<Genre>{
	private final static String GET_ALL_GENRES = "select * from genre";
	@Override
	public Genre get(long id) {
//		don't use it
		return null;
	}

	@Override
	public List<Genre> getAll() {
		LinkedList<Genre> genres = new LinkedList<>();
		try{
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(GET_ALL_GENRES);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				genres.add(getValues(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genres;
	}

	@Override
	public void add(Genre movieGenre) {
//		don't add genres
	}

	@Override
	public void update(Genre movieGenre) {
//		don't update genres
	}

	@Override
	public void delete(Genre movieGenre) {
// 		don't delete genres
	}

	private Genre getValues(ResultSet rs) throws SQLException {
		long movieId = rs.getLong(1);
		String name = rs.getString(2);
		return new Genre(movieId, name);
	}
}
