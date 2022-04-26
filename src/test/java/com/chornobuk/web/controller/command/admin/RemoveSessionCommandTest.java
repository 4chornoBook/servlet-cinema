package com.chornobuk.web.controller.command.admin;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.model.entity.MovieSession;
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

import static org.junit.Assert.*;

public class RemoveSessionCommandTest {

	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;

	@Mock
	HttpSession session;

	@Mock
	MovieSessionRepository repository;

	@InjectMocks
	RemoveSessionCommand command;


	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.openMocks(this);
		Mockito.when(repository.get(Mockito.isA(MovieSession.class))).thenReturn(new MovieSession(1));
		Mockito.doNothing().when(repository).delete(Mockito.isA(MovieSession.class));
		Mockito.when(request.getSession()).thenReturn(session);
	}

	@Test
	public void execute()throws Exception {
		Mockito.when(request.getParameter("sessionId")).thenReturn("1");
		command.execute(request,response);
		Mockito.verify(response, Mockito.times(1)).sendRedirect(Path.INDEX_PAGE);
	}

	@Test
	public void executeWithoutParameter() throws Exception{
		Mockito.when(request.getParameter("sessionId")).thenReturn("");
		Mockito.verify(response, Mockito.times(0)).sendRedirect(Path.INDEX_PAGE);
	}
}