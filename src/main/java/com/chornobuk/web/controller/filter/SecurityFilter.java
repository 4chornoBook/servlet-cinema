package com.chornobuk.web.controller.filter;

import com.chornobuk.web.model.entity.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class SecurityFilter implements Filter {

	private static List<String> common = new LinkedList<>();
	private static Map<UserRole, List<String>> accessMap = new HashMap<>();
	private static List<String> outOfControl = new LinkedList<>();
	private int error;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (isActionAllowed(request)) {
			chain.doFilter(request, response);
		} else {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendError(error);
		}
	}

	public boolean isActionAllowed(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String command = httpRequest.getParameter("action");

		if (command == null || command.isEmpty()) {
			error = 404;
			return false;
		}
		if (outOfControl.contains(command)) {
			return true;
		}
		HttpSession session = httpRequest.getSession(false);
		if (session == null) {
			error = 403;
			return false;
		}
		UserRole role = (UserRole) session.getAttribute("role");
		if (role == null) {
			error = 403;
			return false;
		}
		if (accessMap.get(role).contains(command) || common.contains(command)) {
			return true;
		} else {
			error = 403;
			return false;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		common = getParametersAsList(filterConfig.getInitParameter("commonCommands"));
		accessMap.put(UserRole.ADMIN, getParametersAsList(filterConfig.getInitParameter("adminCommands")));
		accessMap.put(UserRole.USER, getParametersAsList(filterConfig.getInitParameter("userCommands")));
		outOfControl = getParametersAsList(filterConfig.getInitParameter("outOfControlCommands"));
		error = 0;
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}

	private List<String> getParametersAsList(String parameters) {
		List<String> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(parameters);
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		return list;
	}
}