package com.chornobuk.web.model.filter;

import com.chornobuk.web.model.MovieSessionQueryConstructor;
import com.chornobuk.web.model.entity.MovieSession;
import com.chornobuk.web.model.repository.implementation.MovieRepository;
import com.chornobuk.web.model.repository.implementation.MovieSessionRepository;
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
		MovieRepository movieRepository = new MovieRepository();
		httpServletRequest.getSession().setAttribute("availableMovies",movieRepository.getAvailable());

//		if user don't have sessions add them into its session
		if (httpServletRequest.getSession().getAttribute("availableSessions") == null) {
			log.debug("user don't have available sessions");
			MovieSessionRepository movieSessionRepository = new MovieSessionRepository();
			MovieSessionQueryConstructor constructor = new MovieSessionQueryConstructor();
			constructor.setSortingByTime("ascending");
			constructor.setSortingByDate("ascending");
			httpServletRequest.getSession().setAttribute("queryConstructor",constructor);
			int numberOfSessions = movieSessionRepository.getAvailable().size();
			int limit = 2;
			int currentPage = 1;
			int numberOfPages = numberOfSessions / limit;
			if (numberOfSessions % limit != 0)
				numberOfPages += 1;

			List<MovieSession> availableSessions = movieSessionRepository.getLimitedWithOffset(constructor.getQuery(), currentPage - 1, limit);
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
