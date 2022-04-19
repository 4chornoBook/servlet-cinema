package com.chornobuk.web.controller.command.uncontrolled;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.model.entity.User;
import com.chornobuk.web.model.repository.implementation.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

public class RegistrationCommandTest {

	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;

	@Mock
	UserRepository repository;

	@InjectMocks
	RegistrationCommand command;

	User user;

	@Before
	public void init() {
		user = new User();
		user.setLogin("testlogin");
		user.setName("tname");
		user.setSurname("tsurname");
		user.setPassword("tpass");
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void successfulRegistration() throws Exception{
		Mockito.when(request.getParameter("login")).thenReturn(user.getLogin());
		Mockito.when(request.getParameter("name")).thenReturn(user.getName());
		Mockito.when(request.getParameter("surname")).thenReturn(user.getSurname());
		Mockito.when(request.getParameter("password")).thenReturn(user.getPassword());
		Mockito.doNothing().when(repository).add(Mockito.isA(User.class));
		String result = command.execute(request, response);
		Mockito.verify(response, Mockito.times(1)).sendRedirect(Path.LOGIN_PAGE);
	}

	@Test
	public void failedRegistrationNotValidPassword() throws Exception{
		Mockito.when(request.getParameter("login")).thenReturn(user.getLogin());
		Mockito.when(request.getParameter("name")).thenReturn(user.getName());
		Mockito.when(request.getParameter("surname")).thenReturn(user.getSurname());
		Mockito.when(request.getParameter("password")).thenReturn("nope");
		Mockito.doNothing().when(repository).add(Mockito.isA(User.class));
		String result = command.execute(request, response);
		assertEquals(Path.REGISTRATION_PAGE, result);
	}

	@Test
	public void failedRegistrationNotValidLogin() throws Exception{
		Mockito.when(request.getParameter("login")).thenReturn("t");
		Mockito.when(request.getParameter("name")).thenReturn(user.getName());
		Mockito.when(request.getParameter("surname")).thenReturn(user.getSurname());
		Mockito.when(request.getParameter("password")).thenReturn(user.getPassword());
		Mockito.doNothing().when(repository).add(Mockito.isA(User.class));
		String result = command.execute(request, response);
		assertEquals(Path.REGISTRATION_PAGE, result);
	}


	@Test
	public void failedRegistrationAllFieldsNotValid() throws Exception{
		Mockito.when(request.getParameter("login")).thenReturn("t");
		Mockito.when(request.getParameter("name")).thenReturn("");
		Mockito.when(request.getParameter("surname")).thenReturn("");
		Mockito.when(request.getParameter("password")).thenReturn("nope");
		Mockito.doNothing().when(repository).add(Mockito.isA(User.class));
		String result = command.execute(request, response);
		assertEquals(Path.REGISTRATION_PAGE, result);
	}

	@Test
	public void failedRegistrationAllFieldsEmpty() throws Exception{
		Mockito.when(request.getParameter("login")).thenReturn("");
		Mockito.when(request.getParameter("name")).thenReturn("");
		Mockito.when(request.getParameter("surname")).thenReturn("");
		Mockito.when(request.getParameter("password")).thenReturn("");
		Mockito.doNothing().when(repository).add(Mockito.isA(User.class));
		String result = command.execute(request, response);
		assertEquals(Path.REGISTRATION_PAGE, result);
	}

}