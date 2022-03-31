package com.chornobuk.web.model.entity1;

public class Genre extends Entity {
	private String name;

	public Genre(long id, String name) {
		super(id);
		this.name = name;
	}

	public Genre() {
	}

	public Genre(long id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
