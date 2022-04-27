package com.chornobuk.web.controller.command.user;

import com.chornobuk.web.model.entity.Movie;
import com.chornobuk.web.model.entity.MovieSession;
import com.chornobuk.web.model.repository.implementation.MovieRepository;
import com.chornobuk.web.model.repository.implementation.MovieSessionRepository;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class SubmitOrderCommandTest {

	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;

	@Mock
	HttpSession session;

	@Mock
	MovieSessionRepository movieSessionRepository;

	@Mock
	MovieRepository movieRepository;

	@InjectMocks
	BuyTicketsCommand command;

	MovieSession testMovieSession;

	Movie testMovie;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		testMovieSession.setMovieDate(LocalDate.of(2023,12,10));
		testMovieSession.setBeginningTime(LocalTime.of(12,00,00));
		testMovieSession.setEndingTime(LocalTime.of(14,00,00));
		testMovieSession.setTicketPrice(100);
		testMovieSession.setMovieId(1);

		testMovie = new Movie();
		testMovie.setName("testName");
		testMovie.setReleaseDate(LocalDate.now());
		testMovie.setImageURL("testURL");
		testMovie.setLengthInMinutes(120);
		testMovie.setDescription("test description");

		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(movieSessionRepository.get(Mockito.isA(MovieSession.class))).thenReturn(testMovieSession);
		Mockito.when(movieRepository.get(Mockito.isA(Movie.class))).thenReturn(testMovie);

	}

}