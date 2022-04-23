package com.chornobuk.web.controller.filter;

import com.chornobuk.web.controller.Path;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;

public class UserLoggedInFilter implements Filter {
	private static final Logger log = LogManager.getLogger(UserLoggedInFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.debug("Filter started");
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		log.debug(session);
		if(session !=null)
			log.debug("session isn't null");
		if(session != null && session.getAttribute("user") != null) {
			log.debug("user was logged in. Redirect on index page");
			servletResponse.sendRedirect(Path.INDEX_PAGE);
		}
		else {
			log.debug("user wasn't logged in");
			chain.doFilter(request, response);
		}
		log.debug("Filter finished");
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}
}
