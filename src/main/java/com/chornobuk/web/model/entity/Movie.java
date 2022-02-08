package com.chornobuk.web.model.entity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Set;

public class Movie {
	private long id;
	private String name;
	private LocalDate releaseDate;
	private String description;
	private String imageURL;
	private int ticketPrice;
	private int lengthInMinutes;
	private LinkedList<Genre> genres;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getLengthInMinutes() {
		return lengthInMinutes;
	}

	public void setLengthInMinutes(int lengthInMinutes) {
		this.lengthInMinutes = lengthInMinutes;
	}

	public LinkedList<Genre> getGenres() {
		return genres;
	}

	public void setGenres(LinkedList<Genre> genres) {
		this.genres = genres;
	}
}
