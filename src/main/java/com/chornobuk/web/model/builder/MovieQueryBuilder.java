package com.chornobuk.web.model.builder;

import com.chornobuk.web.model.entity.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MovieQueryBuilder extends QueryBuilder<Movie> {
	@Override
	public Movie getObject(ResultSet rs) throws SQLException {
		Movie movie = new Movie();
		movie.setId(rs.getLong("id"));
		movie.setName(rs.getString("name"));
		movie.setReleaseDate(rs.getObject("release_date", LocalDate.class));
		movie.setDescription(rs.getString("description"));
		movie.setImageURL(rs.getString("image_url"));
		movie.setTicketPrice(rs.getInt("ticket_price"));
		movie.setLengthInMinutes(rs.getInt("length_in_minutes"));

		return movie;
	}
}
