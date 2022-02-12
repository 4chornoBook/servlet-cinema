package com.chornobuk.web.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class MovieSession {
	private long id;
	private long movieId;
	private LocalDate movieDate;
	private LocalTime beginningTime;
	private LocalTime endingTime;
	private int availablePlaces;

	private Movie movie;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public int getAvailablePlaces() {
		return availablePlaces;
	}

	public void setAvailablePlaces(int availablePlaces) {
		this.availablePlaces = availablePlaces;
	}

	public LocalDate getMovieDate() {
		return movieDate;
	}

	public void setMovieDate(LocalDate movieDate) {
		this.movieDate = movieDate;
	}

	public LocalTime getBeginningTime() {
		return beginningTime;
	}

	public void setBeginningTime(LocalTime beginningTime) {
		this.beginningTime = beginningTime;
	}

	public LocalTime getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(LocalTime endingTime) {
		this.endingTime = endingTime;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}

