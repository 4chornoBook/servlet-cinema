package com.chornobuk.web.controller.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
	private String encoding;

	public void destroy() {
//		do nothing
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		String requestEncoding = request.getCharacterEncoding();
		if(requestEncoding == null) {
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request,response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");

	}

}
