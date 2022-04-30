package com.chornobuk.web.controller.filter;

import com.chornobuk.web.model.entity.UserRole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;

public class SecurityFilterTest {

	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;

	@Mock
	FilterChain filterChain;

	@Mock
	FilterConfig filterConfig;

	@Mock
	HttpSession session;

	@InjectMocks
	SecurityFilter filter;



	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		Mockito.when(filterConfig.getInitParameter("commonCommands")).thenReturn("showProfile logout redirect");
		Mockito.when(filterConfig.getInitParameter("adminCommands")).thenReturn("addNewMovie newMovieSession newMovieForm addNewMovieSession removeMovieSession");
		Mockito.when(filterConfig.getInitParameter("userCommands")).thenReturn("buyTickets");
		Mockito.when(filterConfig.getInitParameter("outOfControlCommands")).thenReturn("login lang submitOrder registration showMovieSession pagination sessionsSorting");
		filter.init(filterConfig);
	}

	@After
	public void destroy() {
		filter.destroy();
	}

	@Test
	public void doFilterAdminAddNewMovie() throws IOException, ServletException {
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(request.getParameter("action")).thenReturn("addNewMovie");
		Mockito.when(session.getAttribute("role")).thenReturn(UserRole.ADMIN);
		filter.doFilter(request, response, filterChain);
		Mockito.verify(filterChain, Mockito.times(1)).doFilter(Mockito.isA(HttpServletRequest.class) ,Mockito.isA(HttpServletResponse.class));
	}

	@Test
	public void doFilterAdminNewMovieSession() throws IOException, ServletException {
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(request.getParameter("action")).thenReturn("newMovieSession");
		Mockito.when(session.getAttribute("role")).thenReturn(UserRole.ADMIN);
		filter.doFilter(request, response, filterChain);
		Mockito.verify(filterChain, Mockito.times(1)).doFilter(Mockito.isA(HttpServletRequest.class) ,Mockito.isA(HttpServletResponse.class));
	}

	@Test
	public void doFilterAdminNewMovieForm() throws IOException, ServletException {
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(request.getParameter("action")).thenReturn("newMovieForm");
		Mockito.when(session.getAttribute("role")).thenReturn(UserRole.ADMIN);
		filter.doFilter(request, response, filterChain);
		Mockito.verify(filterChain, Mockito.times(1)).doFilter(Mockito.isA(HttpServletRequest.class) ,Mockito.isA(HttpServletResponse.class));
	}
	@Test
	public void doFilterAdminAddNewMovieSession() throws IOException, ServletException {
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(request.getParameter("action")).thenReturn("addNewMovieSession");
		Mockito.when(session.getAttribute("role")).thenReturn(UserRole.ADMIN);
		filter.doFilter(request, response, filterChain);
		Mockito.verify(filterChain, Mockito.times(1)).doFilter(Mockito.isA(HttpServletRequest.class) ,Mockito.isA(HttpServletResponse.class));
	}
	@Test
	public void doFilterAdminRemoveMovieSession() throws IOException, ServletException {
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(request.getParameter("action")).thenReturn("removeMovieSession");
		Mockito.when(session.getAttribute("role")).thenReturn(UserRole.ADMIN);
		filter.doFilter(request, response, filterChain);
		Mockito.verify(filterChain, Mockito.times(1)).doFilter(Mockito.isA(HttpServletRequest.class) ,Mockito.isA(HttpServletResponse.class));
	}

	@Test
	public void doFilterUserBuyTickets() throws IOException, ServletException{
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(request.getParameter("action")).thenReturn("buyTickets");
		Mockito.when(session.getAttribute("role")).thenReturn(UserRole.USER);
		filter.doFilter(request, response, filterChain);
		Mockito.verify(filterChain, Mockito.times(1)).doFilter(Mockito.isA(HttpServletRequest.class) ,Mockito.isA(HttpServletResponse.class));
	}

	@Test
	public void doFilterUserCallsAdminsCommand() throws IOException, ServletException{
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(request.getParameter("action")).thenReturn("removeMovieSession");
		Mockito.when(session.getAttribute("role")).thenReturn(UserRole.USER);
		filter.doFilter(request, response, filterChain);
		Mockito.verify(response, Mockito.times(1)).sendError(Mockito.isA(int.class));
	}

	@Test
	public void doFilterAdminCallsUserCommand() throws IOException, ServletException{
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(request.getParameter("action")).thenReturn("buyTickets");
		Mockito.when(session.getAttribute("role")).thenReturn(UserRole.ADMIN);
		filter.doFilter(request, response, filterChain);
		Mockito.verify(response, Mockito.times(1)).sendError(Mockito.isA(int.class));
	}

	@Test
	public void doFilterNotAuthorizedUserCallsAdminCommand() throws IOException, ServletException{
		Mockito.when(request.getSession(false)).thenReturn(null);
		Mockito.when(request.getParameter("action")).thenReturn("removeMovieSession");
		filter.doFilter(request, response, filterChain);
		Mockito.verify(response, Mockito.times(1)).sendError(Mockito.isA(int.class));
	}

	@Test
	public void doFilterNotAuthorizedUserCallsUserCommand() throws IOException, ServletException{
		Mockito.when(request.getSession(false)).thenReturn(null);
		Mockito.when(request.getParameter("action")).thenReturn("buyTickets");
		filter.doFilter(request, response, filterChain);
		Mockito.verify(response, Mockito.times(1)).sendError(Mockito.isA(int.class));
	}

	@Test
	public void doFilterUserCallsCommonCommand() throws IOException, ServletException{
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(request.getParameter("action")).thenReturn("showProfile");
		Mockito.when(session.getAttribute("role")).thenReturn(UserRole.USER);
		filter.doFilter(request, response, filterChain);
		Mockito.verify(filterChain, Mockito.times(1)).doFilter(Mockito.isA(HttpServletRequest.class) ,Mockito.isA(HttpServletResponse.class));
	}

	@Test
	public void doFilterAdminCallsCommonCommand() throws IOException, ServletException{
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(request.getParameter("action")).thenReturn("showProfile");
		Mockito.when(session.getAttribute("role")).thenReturn(UserRole.USER);
		filter.doFilter(request, response, filterChain);
		Mockito.verify(filterChain, Mockito.times(1)).doFilter(Mockito.isA(HttpServletRequest.class) ,Mockito.isA(HttpServletResponse.class));
	}

	@Test
	public void doFilterOutOfControlCommand() throws IOException, ServletException{
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(request.getParameter("action")).thenReturn("lang");
		Mockito.when(session.getAttribute("role")).thenReturn(UserRole.USER);
		filter.doFilter(request, response, filterChain);
		Mockito.verify(filterChain, Mockito.times(1)).doFilter(Mockito.isA(HttpServletRequest.class) ,Mockito.isA(HttpServletResponse.class));
	}
}