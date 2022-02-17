package com.chornobuk.web.controller.filter;

import com.chornobuk.web.model.dao.GenreDao;
import com.chornobuk.web.model.dao.MovieSessionDao;
import com.chornobuk.web.model.entity.MovieSession;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class MoviesListFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		todo rewrite filter mapping for more pages (user can make request not only from index page)
//		todo check if session already have values with sessions,genres etc.
//		get values from table
		MovieSessionDao movieSessionDao = new MovieSessionDao();
		GenreDao genreDao = new GenreDao();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		add values to session
		int numberOfSessions = movieSessionDao.getNumberOfAvailableSessions();
		int limit = 1;
		int currentPage = 1;
		int numberOfPages = numberOfSessions / limit;
		if (numberOfSessions % limit != 0)
			numberOfPages += 1;

		List<MovieSession> availableSessions = movieSessionDao.getSomeElements(currentPage - 1, limit);
		httpServletRequest.getSession().setAttribute("currentPage", currentPage);
		httpServletRequest.getSession().setAttribute("numberOfPages", numberOfPages);
		httpServletRequest.getSession().setAttribute("limit", limit);
		httpServletRequest.getSession().setAttribute("numberOfSessions", numberOfSessions);
		httpServletRequest.getSession().setAttribute("availableSessions", availableSessions);
		httpServletRequest.getSession().setAttribute("genres", genreDao.getAll());
//		get number of all pages
//		set elements per page
		chain.doFilter(request, response);
	}
}
