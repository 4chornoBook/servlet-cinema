package com.chornobuk.web.controller.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
//	@Override
//	public boolean isLoggable(LogRecord record) {
//		return false;
//	}

	public void destroy() {
//		do nothing
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		Filter.super.init(filterConfig);
//	}

}
