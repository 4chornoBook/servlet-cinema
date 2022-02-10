package com.chornobuk.web.model;

import com.chornobuk.web.model.dao.GenreDao;
import com.chornobuk.web.model.entity.Genre;

import java.util.LinkedList;

public class GenreManager {
	private static GenreManager genreManager;
	private LinkedList<Genre> genres;

	private GenreManager() {
		GenreDao genreDao = new GenreDao();
		genres = new LinkedList<>(genreDao.getAll());
	}

	public static GenreManager getInstance() {
		if(genreManager == null)
			genreManager = new GenreManager();
		return genreManager;
	}

	public LinkedList<Genre> getGenres() {
		return genres;
	}
}
