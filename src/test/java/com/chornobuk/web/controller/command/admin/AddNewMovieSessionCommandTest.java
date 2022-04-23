package com.chornobuk.web.controller.command.admin;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.model.MovieSessionQueryConstructor;
import com.chornobuk.web.model.entity.Movie;
import com.chornobuk.web.model.entity.MovieSession;
import com.chornobuk.web.model.repository.implementation.MovieRepository;
import com.chornobuk.web.model.repository.implementation.MovieSessionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class AddNewMovieSessionCommandTest {

	@Mock
	MovieSessionRepository movieSessionRepository;

	@Mock
	MovieRepository movieRepository;

	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;

	@Mock
	HttpSession session;

	@InjectMocks
	AddNewMovieSessionCommand command;

	MovieSession testMovieSession;

	Movie testMovie;

	MovieSessionQueryConstructor queryConstructor;

	LinkedList<MovieSession> sessions;

	@Before
	public void setUp() throws Exception {
		queryConstructor = new MovieSessionQueryConstructor();
		sessions = new LinkedList<>();
		testMovieSession = new MovieSession();
//		date must be bigger than current
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

		queryConstructor.setSortingByDate("ascending");
		queryConstructor.setSortingByTime("ascending");

		sessions.add(testMovieSession);
		sessions.add(testMovieSession);
		MockitoAnnotations.openMocks(this);

		Mockito.when(movieRepository.get(Mockito.isA(Movie.class))).thenReturn(testMovie);
		Mockito.when(movieSessionRepository.getByTime(Mockito.isA(LocalDate.class), Mockito.isA(LocalTime.class),Mockito.isA(LocalTime.class))).thenReturn(new LinkedList<>());
		Mockito.doNothing().when(movieSessionRepository).add(Mockito.isA(MovieSession.class));
		Mockito.when(movieSessionRepository.getAvailable()).thenReturn(sessions);
		Mockito.when(movieSessionRepository.getLimitedWithOffset(Mockito.isA(String.class),Mockito.isA(Integer.class),Mockito.isA(Integer.class))).thenReturn(sessions);
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(session.getAttribute("queryConstructor")).thenReturn(queryConstructor);
		Mockito.when(session.getAttribute("limit")).thenReturn(2);
	}

	@Test
	public void failedMovieDateNotValid() throws Exception{
		Mockito.when(request.getParameter("movieDate")).thenReturn(testMovieSession.getMovieDate().toString());
		Mockito.when(request.getParameter("beginningTime")).thenReturn(testMovieSession.getBeginningTime().toString());
		Mockito.when(request.getParameter("movie")).thenReturn(testMovieSession.getMovieId()+"");
		Mockito.when(request.getParameter("ticketPrice")).thenReturn(testMovieSession.getTicketPrice()+"");
		command.execute(request,response);
		Mockito.verify(response, Mockito.times(1)).sendRedirect(Path.INDEX_PAGE);
	}

	@Test
	public void failedBeginningTimeFailed() throws Exception{
		Mockito.when(request.getParameter("movieDate")).thenReturn(LocalDate.of(2022,4,10).toString());
		Mockito.when(request.getParameter("beginningTime")).thenReturn(testMovieSession.getBeginningTime().toString());
		Mockito.when(request.getParameter("movie")).thenReturn(testMovieSession.getMovieId()+"");
		Mockito.when(request.getParameter("ticketPrice")).thenReturn(testMovieSession.getTicketPrice()+"");
		String result = command.execute(request,response);
		assertEquals(Path.ADD_NEW_SESSION_PAGE,result);
	}

	@Test
	public void failedEndingTimeNotValid() throws Exception{
		Mockito.when(request.getParameter("movieDate")).thenReturn(testMovieSession.getMovieDate().toString());
		Mockito.when(request.getParameter("beginningTime")).thenReturn(LocalTime.of(4,00,00).toString());
		Mockito.when(request.getParameter("movie")).thenReturn(testMovieSession.getMovieId()+"");
		Mockito.when(request.getParameter("ticketPrice")).thenReturn(testMovieSession.getTicketPrice()+"");
		command.execute(request,response);
		String result = command.execute(request,response);
		assertEquals(Path.ADD_NEW_SESSION_PAGE,result);
	}

	@Test
	public void failedMovieIdNotValid() throws Exception{
		Mockito.when(request.getParameter("movieDate")).thenReturn(testMovieSession.getMovieDate().toString());
		Mockito.when(request.getParameter("beginningTime")).thenReturn(testMovieSession.getBeginningTime().toString());
		Mockito.when(request.getParameter("movie")).thenReturn("sdf");
		Mockito.when(request.getParameter("ticketPrice")).thenReturn(testMovieSession.getTicketPrice()+"");
		command.execute(request,response);
		String result = command.execute(request,response);
		assertEquals(Path.ADD_NEW_SESSION_PAGE,result);
	}

	@Test
	public void failedTicketPriceNotValid() throws Exception{
		Mockito.when(request.getParameter("movieDate")).thenReturn(testMovieSession.getMovieDate().toString());
		Mockito.when(request.getParameter("beginningTime")).thenReturn(testMovieSession.getBeginningTime().toString());
		Mockito.when(request.getParameter("movie")).thenReturn(testMovieSession.getMovieId()+"");
		Mockito.when(request.getParameter("ticketPrice")).thenReturn("");
		command.execute(request,response);
		String result = command.execute(request,response);
		assertEquals(Path.ADD_NEW_SESSION_PAGE,result);
	}

	@Test
	public void failedTimeIsReserved() throws Exception{
		Mockito.when(movieSessionRepository.getByTime(Mockito.isA(LocalDate.class), Mockito.isA(LocalTime.class),Mockito.isA(LocalTime.class))).thenReturn(sessions);
		Mockito.when(request.getParameter("movieDate")).thenReturn(testMovieSession.getMovieDate().toString());
		Mockito.when(request.getParameter("beginningTime")).thenReturn(testMovieSession.getBeginningTime().toString());
		Mockito.when(request.getParameter("movie")).thenReturn(testMovieSession.getMovieId()+"");
		Mockito.when(request.getParameter("ticketPrice")).thenReturn(testMovieSession.getTicketPrice()+"");
		command.execute(request,response);
		String result = command.execute(request,response);
		assertEquals(Path.ADD_NEW_SESSION_PAGE,result);
	}

	@Test
	public void failedAllFieldsAreNotValid() throws Exception{
		Mockito.when(request.getParameter("movieDate")).thenReturn(null);
		Mockito.when(request.getParameter("beginningTime")).thenReturn(null);
		Mockito.when(request.getParameter("movie")).thenReturn(null);
		Mockito.when(request.getParameter("ticketPrice")).thenReturn(null);
		command.execute(request,response);
		String result = command.execute(request,response);
		assertEquals(Path.ADD_NEW_SESSION_PAGE,result);
	}
}