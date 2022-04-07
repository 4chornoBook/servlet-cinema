package com.chornobuk.web.model.builder;

import com.chornobuk.web.model.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreQueryBuilder extends QueryBuilder<Genre>{
	@Override
	public Genre getObject(ResultSet rs) throws SQLException {
		Genre genre = new Genre();
		genre.setId(rs.getLong("id"));
		genre.setName(rs.getString("name"));
		return genre;
	}
}
