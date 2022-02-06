package com.chornobuk.web.model.entity;

public class MovieGenre {
	private long movieId;
	private long genreId;

	public MovieGenre(long movieId, long genreId) {
		this.movieId = movieId;
		this.genreId = genreId;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public long getGenreId() {
		return genreId;
	}

	public void setGenreId(long genreId) {
		this.genreId = genreId;
	}
}
