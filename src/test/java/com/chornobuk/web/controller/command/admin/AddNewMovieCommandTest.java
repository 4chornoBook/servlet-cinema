package com.chornobuk.web.controller.command.admin;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.model.entity.Genre;
import com.chornobuk.web.model.entity.Movie;
import com.chornobuk.web.model.repository.implementation.GenreRepository;
import com.chornobuk.web.model.repository.implementation.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class AddNewMovieCommandTest {

	@Mock
	MovieRepository movieRepository;

	@Mock
	GenreRepository genreRepository;

	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;

	@InjectMocks
	AddNewMovieCommand command;

	Movie testMovie;

	@Before
	public void setUp() throws Exception {

		LinkedList<Genre> genres = new LinkedList<>();
		genres.add(new Genre(1, "testGenre"));
		genres.add(new Genre(2, "testGenre2"));
		testMovie.setGenres(genres);
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void successfulAdding () throws Exception{
		Mockito.when(request.getParameter("name")).thenReturn(testMovie.getName());
		Mockito.when(request.getParameter("releaseDate")).thenReturn(testMovie.getReleaseDate().toString());
		Mockito.when(request.getParameter("length")).thenReturn(testMovie.getLengthInMinutes()+"");
		Mockito.when(request.getParameter("imageURL")).thenReturn(testMovie.getImageURL());
		Mockito.when(request.getParameter("description")).thenReturn(testMovie.getDescription());
		Mockito.when(request.getParameterValues("genre")).thenReturn(testMovie.getGenres().stream().map(x->x.getId()+"").toArray(String[]::new));
		Mockito.doNothing().when(movieRepository).add(Mockito.isA(Movie.class));
		Mockito.when(genreRepository.getAll()).thenReturn(testMovie.getGenres());
		command.execute(request,response);
		Mockito.verify(response, Mockito.times(1)).sendRedirect(Path.INDEX_PAGE);
	}


	@Test
	public void failedAllFieldsAreNotValid() throws Exception{
		Mockito.when(request.getParameter("name")).thenReturn(null);
		Mockito.when(request.getParameter("releaseDate")).thenReturn("");
		Mockito.when(request.getParameter("length")).thenReturn(null);
		Mockito.when(request.getParameter("imageURL")).thenReturn("");
		Mockito.when(request.getParameter("description")).thenReturn("");
		Mockito.when(request.getParameterValues("genre")).thenReturn(new String[]{});
		Mockito.doNothing().when(movieRepository).add(Mockito.isA(Movie.class));
		Mockito.when(genreRepository.getAll()).thenReturn(testMovie.getGenres());
		String result = command.execute(request,response);
		assertEquals(Path.ADD_NEW_MOVIE_PAGE, result);
	}

	@Test
	public void failedNotValidName() throws Exception{
		Mockito.when(request.getParameter("name")).thenReturn(null);
		Mockito.when(request.getParameter("releaseDate")).thenReturn(testMovie.getReleaseDate().toString());
		Mockito.when(request.getParameter("length")).thenReturn(testMovie.getLengthInMinutes()+"");
		Mockito.when(request.getParameter("imageURL")).thenReturn(testMovie.getImageURL());
		Mockito.when(request.getParameter("description")).thenReturn(testMovie.getDescription());
		Mockito.when(request.getParameterValues("genre")).thenReturn(testMovie.getGenres().stream().map(x->x.getId()+"").toArray(String[]::new));
		Mockito.doNothing().when(movieRepository).add(Mockito.isA(Movie.class));
		Mockito.when(genreRepository.getAll()).thenReturn(testMovie.getGenres());
		String result = command.execute(request,response);
		assertEquals(Path.ADD_NEW_MOVIE_PAGE, result);
	}

	@Test
	public void failedNotValidReleaseDate() throws Exception{
		Mockito.when(request.getParameter("name")).thenReturn(testMovie.getName());
		Mockito.when(request.getParameter("releaseDate")).thenReturn(null);
		Mockito.when(request.getParameter("length")).thenReturn(testMovie.getLengthInMinutes()+"");
		Mockito.when(request.getParameter("imageURL")).thenReturn(testMovie.getImageURL());
		Mockito.when(request.getParameter("description")).thenReturn(testMovie.getDescription());
		Mockito.when(request.getParameterValues("genre")).thenReturn(testMovie.getGenres().stream().map(x->x.getId()+"").toArray(String[]::new));
		Mockito.doNothing().when(movieRepository).add(Mockito.isA(Movie.class));
		Mockito.when(genreRepository.getAll()).thenReturn(testMovie.getGenres());
		command.execute(request,response);
		String result = command.execute(request,response);
		assertEquals(Path.ADD_NEW_MOVIE_PAGE, result);
	}

	@Test
	public void failedNotValidLength() throws Exception{
		Mockito.when(request.getParameter("name")).thenReturn(testMovie.getName());
		Mockito.when(request.getParameter("releaseDate")).thenReturn(testMovie.getReleaseDate().toString());
		Mockito.when(request.getParameter("length")).thenReturn(null);
		Mockito.when(request.getParameter("imageURL")).thenReturn(testMovie.getImageURL());
		Mockito.when(request.getParameter("description")).thenReturn(testMovie.getDescription());
		Mockito.when(request.getParameterValues("genre")).thenReturn(testMovie.getGenres().stream().map(x->x.getId()+"").toArray(String[]::new));
		Mockito.doNothing().when(movieRepository).add(Mockito.isA(Movie.class));
		Mockito.when(genreRepository.getAll()).thenReturn(testMovie.getGenres());
		command.execute(request,response);
		String result = command.execute(request,response);
		assertEquals(Path.ADD_NEW_MOVIE_PAGE, result);
	}

	@Test
	public void failedNotValidImageUrl() throws Exception{
		Mockito.when(request.getParameter("name")).thenReturn(testMovie.getName());
		Mockito.when(request.getParameter("releaseDate")).thenReturn(testMovie.getReleaseDate().toString());
		Mockito.when(request.getParameter("length")).thenReturn(testMovie.getLengthInMinutes()+"");
		Mockito.when(request.getParameter("imageURL")).thenReturn(null);
		Mockito.when(request.getParameter("description")).thenReturn(testMovie.getDescription());
		Mockito.when(request.getParameterValues("genre")).thenReturn(testMovie.getGenres().stream().map(x->x.getId()+"").toArray(String[]::new));
		Mockito.doNothing().when(movieRepository).add(Mockito.isA(Movie.class));
		Mockito.when(genreRepository.getAll()).thenReturn(testMovie.getGenres());
		command.execute(request,response);
		String result = command.execute(request,response);
		assertEquals(Path.ADD_NEW_MOVIE_PAGE, result);
	}

	@Test
	public void failedNotValidDescription() throws Exception{
		Mockito.when(request.getParameter("name")).thenReturn(testMovie.getName());
		Mockito.when(request.getParameter("releaseDate")).thenReturn(testMovie.getReleaseDate().toString());
		Mockito.when(request.getParameter("length")).thenReturn(testMovie.getLengthInMinutes()+"");
		Mockito.when(request.getParameter("imageURL")).thenReturn(testMovie.getImageURL());
		Mockito.when(request.getParameter("description")).thenReturn(null);
		Mockito.when(request.getParameterValues("genre")).thenReturn(testMovie.getGenres().stream().map(x->x.getId()+"").toArray(String[]::new));
		Mockito.doNothing().when(movieRepository).add(Mockito.isA(Movie.class));
		Mockito.when(genreRepository.getAll()).thenReturn(testMovie.getGenres());
		command.execute(request,response);
		String result = command.execute(request,response);
		assertEquals(Path.ADD_NEW_MOVIE_PAGE, result);
	}

	@Test
	public void failedNotValidGenres() throws Exception{
		Mockito.when(request.getParameter("name")).thenReturn(testMovie.getName());
		Mockito.when(request.getParameter("releaseDate")).thenReturn(testMovie.getReleaseDate().toString());
		Mockito.when(request.getParameter("length")).thenReturn(testMovie.getLengthInMinutes()+"");
		Mockito.when(request.getParameter("imageURL")).thenReturn(testMovie.getImageURL());
		Mockito.when(request.getParameter("description")).thenReturn(testMovie.getDescription());
		Mockito.when(request.getParameterValues("genre")).thenReturn(null);
		Mockito.doNothing().when(movieRepository).add(Mockito.isA(Movie.class));
		Mockito.when(genreRepository.getAll()).thenReturn(testMovie.getGenres());
		command.execute(request,response);
		String result = command.execute(request,response);
		assertEquals(Path.ADD_NEW_MOVIE_PAGE, result);
	}
}