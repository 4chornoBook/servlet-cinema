package com.chornobuk.web.model.entity;

import java.time.LocalDateTime;

public class MovieSession {
	private long id;
	private long movieId;
	private LocalDateTime beginningDate;
	private LocalDateTime endingDate;
	private int availablePlaces;

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

	public LocalDateTime getBeginningDate() {
		return beginningDate;
	}

	public void setBeginningDate(LocalDateTime beginningDate) {
		this.beginningDate = beginningDate;
	}

	public LocalDateTime getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(LocalDateTime endingDate) {
		this.endingDate = endingDate;
	}

	public int getAvailablePlaces() {
		return availablePlaces;
	}

	public void setAvailablePlaces(int availablePlaces) {
		this.availablePlaces = availablePlaces;
	}
}

