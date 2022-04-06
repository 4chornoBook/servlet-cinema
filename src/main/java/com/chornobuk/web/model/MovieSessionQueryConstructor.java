package com.chornobuk.web.model;

import java.util.stream.Stream;

public class MovieSessionQueryConstructor {
	private static String queryBody = "select movie_session.*, movie.*,"
			+ " (select (100 - count(ticket.ticket_id)) from ticket where ticket.movie_session_id = movie_session.session_id) as available_tickets"
			+ " from movie_session"
			+ " inner join movie"
			+ " on movie_session.movie_id = movie.movie_id";

	private static String where = "movie_session.session_date + movie_session.beginning_time >= now()";
	private static String limit = "limit ? offset ?";

	private String sortingByTime = "";
	private String sortingByDate = "";
	private String sortingByMovieName = "";
	private String sortByTickets = "";
	private String filmFilter = "";
	private String groupBy = "movie_session.session_id, movie.movie_id";

	public String getQuery() {
		String delimiter = " ";
		String[] strings = Stream.of(queryBody, getWhere(), "group by", groupBy, getOrderBy(), limit)
				.filter(s -> !s.isEmpty()).toArray(String[]::new);
		return String.join(delimiter, strings);
	}

	public void setSortingByDate(String value) {
		sortingByDate = "movie_session.session_date";
		if (value.equals("ascending"))
			sortingByDate += " ASC";
		else
			sortingByDate += " DESC";
	}

	public void setSortingByTime(String value) {
		sortingByTime = "movie_session.beginning_time";
		if (value.equals("ascending"))
			sortingByTime += " ASC";
		else
			sortingByTime += " DESC";
	}

	public void setSortingByTickets(String value) {
		sortByTickets = "14";
		if (value.equals("ascending"))
			sortByTickets += " ASC";
		else
			sortByTickets += " DESC";
	}

	public void setSortingByMovieName(String value) {
		sortingByMovieName = "movie.name";
		if (value.equals("ascending"))
			sortingByMovieName += " ASC";
		else
			sortingByMovieName += " DESC";
	}

	public void setFilmFilter(String movieName) {
		filmFilter = "movie.name =" + '\'' + movieName + '\'';
	}

	public String getOrderBy() {
		String delimiter = ", ";
		String[] sortingElements = Stream.of(sortingByDate, sortingByTime, sortByTickets, sortingByMovieName)
				.filter(s -> !s.isEmpty()).toArray(String[]::new);
		if (sortingElements.length != 0)
			return "order by " + String.join(delimiter, sortingElements);
		else return "";
	}

	public String getWhere() {
		String delimiter = " ";

		if (!filmFilter.isEmpty())
			return String.join(delimiter, new String[]{"where", where, "and", filmFilter});

		return String.join(delimiter, new String[]{"where", where});
	}
}
