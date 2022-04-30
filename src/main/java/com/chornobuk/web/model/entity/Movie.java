package com.chornobuk.web.model.entity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Movie extends Entity{
	private String name;
	private LocalDate releaseDate;
	private String description;
	private String imageURL;
	private int lengthInMinutes;
	private List<Genre> genres;

	public Movie(long id, String name, LocalDate releaseDate, String description, String imageURL, int lengthInMinutes, LinkedList<Genre> genres) {
		super(id);
		this.name = name;
		this.releaseDate = releaseDate;
		this.description = description;
		this.imageURL = imageURL;
		this.lengthInMinutes = lengthInMinutes;
		this.genres = genres;
	}

	public Movie(long id) {
		super(id);
	}

	public Movie() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public int getLengthInMinutes() {
		return lengthInMinutes;
	}

	public void setLengthInMinutes(int lengthInMinutes) {
		this.lengthInMinutes = lengthInMinutes;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
}
