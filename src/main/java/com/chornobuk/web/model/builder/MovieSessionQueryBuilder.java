package com.chornobuk.web.model.builder;

import com.chornobuk.web.model.entity.Movie;
import com.chornobuk.web.model.entity.MovieSession;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class MovieSessionQueryBuilder extends QueryBuilder<MovieSession>{
	@Override
	public MovieSession getObject(ResultSet rs) throws SQLException {
		MovieSession movieSession = new MovieSession();
		movieSession.setId(rs.getLong("id"));
		movieSession.setMovieId(rs.getLong("movie_id"));
		movieSession.setMovieDate(rs.getObject("session_date",LocalDate.class));
		movieSession.setBeginningTime(rs.getObject("beginning_time", LocalTime.class));
		movieSession.setEndingTime(rs.getObject("ending_time",LocalTime.class));
		movieSession.setTicketPrice(rs.getInt("ticket_price"));
		return movieSession;
	}
}
