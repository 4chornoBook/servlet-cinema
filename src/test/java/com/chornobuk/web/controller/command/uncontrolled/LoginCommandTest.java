package com.chornobuk.web.controller.command.uncontrolled;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.model.HashingAlgorithm;
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
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

public class LoginCommandTest {

	@Mock
	UserRepository repository;

	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;

	@Mock
	HttpSession session;

	@InjectMocks
	LoginCommand command;

	User user;

	@Before
	public void setUp() throws Exception {
		user = new User();
		user.setName("bob");
		user.setSurname("johnson");
		user.setLogin("test");
		user.setId(2);
		user.setRoleId(1);
		user.setSalt("123");
		user.setPassword(HashingAlgorithm.cryptPassword("123",user.getSalt()));
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void login() throws Exception {
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(request.getParameter("login")).thenReturn("test");
		Mockito.when(request.getParameter("password")).thenReturn("123");
		Mockito.when(repository.getByLogin(Mockito.isA(String.class))).thenReturn(user);

		String result = command.execute(request,response);
		Mockito.verify(response, Mockito.times(1)).sendRedirect(Path.INDEX_PAGE);
	}

	@Test
	public void invalidLogin() {
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(request.getParameter("login")).thenReturn("test");
		Mockito.when(request.getParameter("password")).thenReturn("111");
		Mockito.when(repository.getByLogin(Mockito.isA(String.class))).thenReturn(user);

		assertEquals(Path.LOGIN_PAGE, command.execute(request, response));
	}
}