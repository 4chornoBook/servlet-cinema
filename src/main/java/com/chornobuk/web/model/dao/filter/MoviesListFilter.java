package com.chornobuk.web.model.dao.filter;

import com.chornobuk.web.model.MovieSessionQueryConstructor;
import com.chornobuk.web.model.dao.MovieDao;
import com.chornobuk.web.model.dao.MovieSessionDao;
import com.chornobuk.web.model.entity.MovieSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class MoviesListFilter implements Filter {
	private static final Logger log = LogManager.getLogger(MoviesListFilter.class);
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.debug("Filter started");

//		get available movies for filter in front
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		MovieDao movieDao = new MovieDao();
		httpServletRequest.getSession().setAttribute("availableMovies",movieDao.getAvailableMovies());

//		if user don't have sessions add them into its session
		if (httpServletRequest.getSession().getAttribute("availableSessions") == null) {
			log.debug("user don't have available sessions");
			MovieSessionDao movieSessionDao = new MovieSessionDao();
			MovieSessionQueryConstructor constructor = new MovieSessionQueryConstructor();
			constructor.setSortingByTime("ascending");
			constructor.setSortingByDate("ascending");
			httpServletRequest.getSession().setAttribute("queryConstructor",constructor);
			int numberOfSessions = movieSessionDao.getNumberOfAvailableSessions();
			int limit = 2;
			int currentPage = 1;
			int numberOfPages = numberOfSessions / limit;
			if (numberOfSessions % limit != 0)
				numberOfPages += 1;

			List<MovieSession> availableSessions = movieSessionDao.getSomeElements(currentPage - 1, limit);
			httpServletRequest.getSession().setAttribute("currentPage", currentPage);
			httpServletRequest.getSession().setAttribute("numberOfPages", numberOfPages);
			httpServletRequest.getSession().setAttribute("limit", limit);
			httpServletRequest.getSession().setAttribute("availableSessions", availableSessions);
			httpServletRequest.getSession().setAttribute("byDateAscending", "selected");
			httpServletRequest.getSession().setAttribute("byTimeAscending", "selected");
		}
		log.debug("Filter ended");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("Filter initialization started");

		log.debug("Filter initialization ended");
	}

	@Override
	public void destroy() {
		log.debug("Filter destroyed");
	}
}
