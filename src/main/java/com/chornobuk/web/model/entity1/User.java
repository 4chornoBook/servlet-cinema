package com.chornobuk.web.model.entity1;

public class User extends Entity {
	private String login;
	private String name;
	private String surname;
	private String password;
	private int roleId;
	private String salt;

	public User() {
	}

	public User(long id, String login, String name, String surname, String password, int roleId, String salt) {
		super(id);
		this.login = login;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.roleId = roleId;
		this.salt = salt;
	}

	public User(long id) {
		super(id);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
