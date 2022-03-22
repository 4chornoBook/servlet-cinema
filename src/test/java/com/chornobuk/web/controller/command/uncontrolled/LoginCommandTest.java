package com.chornobuk.web.controller.command.uncontrolled;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.model.HashingAlgorithm;
import com.chornobuk.web.model.dao.UserDao;
import com.chornobuk.web.model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LoginCommandTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	HttpSession session;

	@Mock
	UserDao userDao;

	@InjectMocks
	LoginCommand loginCommand;

	User admin;

	@Before
	public void before() {
		admin = new User();
		admin.setLogin("test");
		admin.setSalt(HashingAlgorithm.getSalt());
		admin.setPassword(HashingAlgorithm.cryptPassword("password", admin.getSalt()));
		admin.setName("tester");
		admin.setId(1);
		admin.setRoleId(1);
		admin.setSurname("testovich");

		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void executeWithoutLoginData() throws IOException {
		LoginCommand loginCommand = new LoginCommand();
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

		assertEquals(Path.LOGIN_PAGE, loginCommand.execute(request, response));
	}

	@Test
	public void execute() throws IOException {

		when(userDao.getUserByLogin(admin.getLogin())).thenReturn(admin);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("login")).thenReturn(admin.getLogin());
		when(request.getParameter("password")).thenReturn("password");

		loginCommand.execute(request, response);
		verify(response, times(1)).sendRedirect(Path.INDEX_PAGE);
	}

	@Test
	public void executeWrongPassword() {
		when(userDao.getUserByLogin(admin.getLogin())).thenReturn(admin);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("login")).thenReturn(admin.getLogin());
		when(request.getParameter("password")).thenReturn("wrongPassword");

		assertEquals(Path.LOGIN_PAGE, loginCommand.execute(request, response));
	}
}