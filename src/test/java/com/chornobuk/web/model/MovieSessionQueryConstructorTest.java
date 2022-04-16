package com.chornobuk.web.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovieSessionQueryConstructorTest {

	@Test
	public void defaultQuery() {
		MovieSessionQueryConstructor movieSessionQueryConstructor = new MovieSessionQueryConstructor();
		String query = "select movie_session.*, movie.name,"
				+ " (select (100 - count(ticket.id)) from ticket where ticket.movie_session_id = movie_session.id) as available_tickets"
				+ " from movie_session inner join movie on movie_session.movie_id = movie.id where"
				+ " movie_session.session_date + movie_session.beginning_time >= now() group by movie_session.id, movie.id" +
				" limit ? offset ?";
		assertEquals(query, movieSessionQueryConstructor.getQuery());
	}

	@Test
	public void queryWithFilter() {
		MovieSessionQueryConstructor movieSessionQueryConstructor = new MovieSessionQueryConstructor();
		movieSessionQueryConstructor.setFilmFilter("Форест Гамп");
		String query = "select movie_session.*, movie.name,"
				+ " (select (100 - count(ticket.id)) from ticket where ticket.movie_session_id = movie_session.id) as available_tickets"
				+ " from movie_session inner join movie on movie_session.movie_id = movie.id where"
				+ " movie_session.session_date + movie_session.beginning_time >= now() and movie.name ='Форест Гамп' group by movie_session.id, movie.id" +
				" limit ? offset ?";

		assertEquals(query, movieSessionQueryConstructor.getQuery());
	}


	@Test
	public void getOrderByWithoutSorting() {
		MovieSessionQueryConstructor movieSessionQueryConstructor = new MovieSessionQueryConstructor();
		String orderBy = "";
		assertEquals(orderBy, movieSessionQueryConstructor.getOrderBy());
	}

	@Test
	public void getOrderByWithAllDescendingSorting() {
		MovieSessionQueryConstructor movieSessionQueryConstructor = new MovieSessionQueryConstructor();
		movieSessionQueryConstructor.setSortingByDate("ascending");
		movieSessionQueryConstructor.setSortingByTime("ascending");
		movieSessionQueryConstructor.setSortingByTickets("ascending");
		movieSessionQueryConstructor.setSortingByMovieName("ascending");

		String orderBy = "order by movie_session.session_date ASC, movie_session.beginning_time ASC, available_tickets ASC, movie.name ASC";
		assertEquals(orderBy, movieSessionQueryConstructor.getOrderBy());
	}

	@Test
	public void getOrderByWithWrongSorting() {
		MovieSessionQueryConstructor movieSessionQueryConstructor = new MovieSessionQueryConstructor();
		movieSessionQueryConstructor.setSortingByDate("d");
		movieSessionQueryConstructor.setSortingByTime("");
		movieSessionQueryConstructor.setSortingByTickets("dkdkd");
		movieSessionQueryConstructor.setSortingByMovieName(";lksajfd");

		String orderBy = "order by movie_session.session_date DESC, movie_session.beginning_time DESC, available_tickets DESC, movie.name DESC";
		assertEquals(orderBy, movieSessionQueryConstructor.getOrderBy());
	}

	@Test
	public void getOrderByWithMixedSorting() {
		MovieSessionQueryConstructor movieSessionQueryConstructor = new MovieSessionQueryConstructor();
		movieSessionQueryConstructor.setSortingByDate("ascending");
		movieSessionQueryConstructor.setSortingByTime("descending");
		movieSessionQueryConstructor.setSortingByTickets("ascending");
		movieSessionQueryConstructor.setSortingByMovieName("descending");

		String orderBy = "order by movie_session.session_date ASC, movie_session.beginning_time DESC, available_tickets ASC, movie.name DESC";
		assertEquals(orderBy, movieSessionQueryConstructor.getOrderBy());
	}

	@Test
	public void gerOrderByWithOneSortingField() {
		MovieSessionQueryConstructor movieSessionQueryConstructor = new MovieSessionQueryConstructor();
		movieSessionQueryConstructor.setSortingByDate("ascending");

		String orderBy = "order by movie_session.session_date ASC";
		assertEquals(orderBy, movieSessionQueryConstructor.getOrderBy());
	}

	@Test
	public void getOrderByWithTwoSortingFields() {
		MovieSessionQueryConstructor movieSessionQueryConstructor = new MovieSessionQueryConstructor();
		movieSessionQueryConstructor.setSortingByDate("descending");
		movieSessionQueryConstructor.setSortingByMovieName("descending");

		String orderBy = "order by movie_session.session_date DESC, movie.name DESC";
		assertEquals(orderBy, movieSessionQueryConstructor.getOrderBy());
	}

	@Test
	public void getOrderByWithThreeSortingFields() {
		MovieSessionQueryConstructor movieSessionQueryConstructor = new MovieSessionQueryConstructor();
		movieSessionQueryConstructor.setSortingByTime("ascending");
		movieSessionQueryConstructor.setSortingByTickets("ascending");
		movieSessionQueryConstructor.setSortingByMovieName("ascending");

		String orderBy = "order by movie_session.beginning_time ASC, available_tickets ASC, movie.name ASC";
		assertEquals(orderBy, movieSessionQueryConstructor.getOrderBy());
	}

	@Test
	public void getWhereWithoutFilmFilter() {
		MovieSessionQueryConstructor movieSessionQueryConstructor = new MovieSessionQueryConstructor();
		String where = "where movie_session.session_date + movie_session.beginning_time >= now()";
		assertEquals(where, movieSessionQueryConstructor.getWhere());
	}

	@Test
	public void getWhereWithFilmFilter() {
		MovieSessionQueryConstructor movieSessionQueryConstructor = new MovieSessionQueryConstructor();
		movieSessionQueryConstructor.setFilmFilter("Зелена книга");
		String where = "where movie_session.session_date + movie_session.beginning_time >= now() and movie.name =\'Зелена книга\'";
		assertEquals(where, movieSessionQueryConstructor.getWhere());
	}
}