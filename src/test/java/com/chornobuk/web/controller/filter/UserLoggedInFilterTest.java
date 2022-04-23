package com.chornobuk.web.controller.filter;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoggedInFilterTest {

	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;

	@Mock
	FilterChain filterChain;

	@InjectMocks
	UserLoggedInFilter filter;

	@Mock
	HttpSession session;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void doFilter() throws Exception{
		Mockito.when( request.getSession()).thenReturn(session);
		Mockito.when(session.getAttribute("user")).thenReturn(new User());
		Mockito.doNothing().when(filterChain).doFilter(Mockito.isA(ServletRequest.class),Mockito.isA(ServletResponse.class));
		filter.doFilter(request, response, filterChain);
		Mockito.verify(filterChain, Mockito.times(0)).doFilter(Mockito.isA(ServletRequest.class),Mockito.isA(ServletResponse.class));
	}

	@Test
	public void doFilterUserNotAuthorized() throws Exception{
		Mockito.doNothing().when(filterChain).doFilter(Mockito.isA(ServletRequest.class),Mockito.isA(ServletResponse.class));
		filter.doFilter(request, response, filterChain);
		Mockito.verify(filterChain, Mockito.times(1)).doFilter(Mockito.isA(ServletRequest.class),Mockito.isA(ServletResponse.class));
	}
}