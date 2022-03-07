package com.chornobuk.web.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
	private static final Logger log = LogManager.getLogger(EncodingFilter.class);
	private String encoding;

	public void destroy() {
		log.debug("Filter destroyed");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		String requestEncoding = request.getCharacterEncoding();
		log.debug("Filter started");
		if(requestEncoding == null) {
			request.setCharacterEncoding(encoding);
			log.debug("Request encoding is null, new encoding" + encoding);
		}
		log.debug("Filter ended");
		chain.doFilter(request,response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("Filter initialization started");
		encoding = filterConfig.getInitParameter("encoding");
		log.debug("Filter initialization ended");
	}

}
