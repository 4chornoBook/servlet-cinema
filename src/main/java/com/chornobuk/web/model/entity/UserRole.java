package com.chornobuk.web.model.entity;

public enum UserRole {
	ADMIN, USER, GUEST;

	public static UserRole getUserRole(User user) {
		if (user.getRoleId() == 1)
			return ADMIN;
		else if (user.getRoleId() == 2)
			return USER;
		else
			return GUEST;
	}
}
