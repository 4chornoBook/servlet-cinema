package com.chornobuk.web.model.repository.implementation;

import com.chornobuk.web.model.builder.GenreQueryBuilder;
import com.chornobuk.web.model.database.DBManager;
import com.chornobuk.web.model.entity.Genre;
import com.chornobuk.web.model.entity.Movie;
import com.chornobuk.web.model.repository.IRepository;

import java.util.List;

public class GenreRepository  {
	GenreQueryBuilder genreQueryBuilder = new GenreQueryBuilder();
	DBManager instance = DBManager.getInstance();
	private static final String GET_ALL = "select * from genre";
	private static final String GET_BY_MOVIE = " select genre.* from genre"+
		" inner join movie_genre on genre.id = movie_genre.genre_id"+
		" inner join movie on movie_genre.movie_id = movie.id"+
		" where movie.id = ?";

	public List<Genre> getALL() {
		return genreQueryBuilder.getValues(instance, GET_ALL);
	}
	public List<Genre> getByMovie(Movie movie) {
		return genreQueryBuilder.getValues(instance, GET_BY_MOVIE, movie.getId());
	}
}
