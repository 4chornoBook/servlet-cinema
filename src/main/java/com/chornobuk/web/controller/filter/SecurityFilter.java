package com.chornobuk.web.controller.filter;

import com.chornobuk.web.model.entity.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class SecurityFilter implements Filter {

	private static List<String> common = new LinkedList<>();
	private static Map<UserRole, List<String>> accessMap = new HashMap<>();
	private static List<String> outOfControl = new LinkedList<>();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (isAllowed(request)) {
			chain.doFilter(request, response);
		} else {
			request.getRequestDispatcher("/403.jsp").forward(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		common = getParametersAsList(filterConfig.getInitParameter("commonCommands"));
		accessMap.put(UserRole.ADMIN, getParametersAsList(filterConfig.getInitParameter("adminCommands")));
		accessMap.put(UserRole.USER, getParametersAsList(filterConfig.getInitParameter("userCommands")));
		outOfControl = getParametersAsList(filterConfig.getInitParameter("outOfControlCommands"));
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}

	public boolean isAllowed(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String command = httpRequest.getParameter("action");
		if (command == null || command.isEmpty()) {
			return false;
		}
		if (outOfControl.contains(command)) {
			return true;
		}
		HttpSession session = httpRequest.getSession(false);
		if (session == null) {
			return false;
		}
		UserRole role = (UserRole) session.getAttribute("role");
		if (role == null) {
			return false;
		}
		return accessMap.get(role).contains(command) || common.contains(command);
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