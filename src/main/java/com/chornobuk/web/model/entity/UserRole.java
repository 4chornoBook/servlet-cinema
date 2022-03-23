package com.chornobuk.web.model.entity;

public enum UserRole {
	ADMIN, USER;

	public static UserRole getUserRole(User user) {
		int roleId= user.getRoleId();
		return UserRole.values()[--roleId];
	}
}
