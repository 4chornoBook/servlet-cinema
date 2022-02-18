package com.chornobuk.web.model;

import com.chornobuk.web.model.dao.MovieSessionDao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MovieSessionQueryConstructor {
	private String QueryBody = "select movie_session.*, movie.*, "
			+ " (select (movie_session.available_places - count(ticket.ticket_id)) from ticket where ticket.movie_session_id = movie_session.session_id) as available_tickets "
			+ " from movie_session"
			+ " inner join movie"
			+ " on movie_session.movie_id = movie.movie_id";

	private static String where = " where movie_session.session_date + movie_session.beginning_time >= now()";
	private static String limit = " limit ? offset ?";

	private String sortingByTime = "";
	private String sortingByDate = "";
	private String sortingByMovieName = "";
	private String sortByTickets = "";
	private String filmFilter = "";
	private String groupBy = " movie_session.session_id, movie.movie_id";

	public String getQuery() {
		return QueryBody
				+ getWhere() + " "
				+ "group by "
				+ groupBy + " "
				+ getOrderBy() + " "
				+ limit;
	}

	public void addSortingByDate(String value) {
		sortingByDate = " movie_session.session_date";
		if (value.equals("ascending"))
			sortingByDate += " ASC";
		else
			sortingByDate += " DESC";
	}

	public void addSortingByTime(String value) {
		sortingByTime = " movie_session.beginning_time";
		if (value.equals("ascending"))
			sortingByTime += " ASC";
		else
			sortingByTime += " DESC";
	}

	public void addSortingByTickets(String value) {
		sortByTickets = " 14";
		if (value.equals("ascending"))
			sortByTickets += " ASC";
		else
			sortByTickets += " DESC";
	}

	public void addSortingByMovieName(String value) {
		sortingByMovieName = "  movie.name";
		if (value.equals("ascending"))
			sortingByMovieName += " ASC";
		else
			sortingByMovieName += " DESC";
	}

	public void addFilteringByMovie(String movieName) {
		filmFilter = " movie.movie_name = " + '\'' + movieName + '\'';
	}

	private String getOrderBy() {
		String delimeter = ",";
		List<String> query = Arrays.asList(sortingByDate, sortingByTime, sortByTickets, sortingByMovieName)
				.stream()
				.filter(x -> !x.isEmpty()).collect(Collectors.toList());
		return "order by " + String.join(delimeter, query);
	}

	private String getWhere() {
		if (!filmFilter.isEmpty())
			return where + "," + filmFilter;

		return where;
	}
//	default must be a query with sorting by creating time
//	create method which add ordering parameter
//	method reformat user
//	public void
}
