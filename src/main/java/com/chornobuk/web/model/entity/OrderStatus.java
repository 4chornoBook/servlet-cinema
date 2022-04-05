package com.chornobuk.web.model.entity;

public class OrderStatus extends Entity {
	private String name;

	public OrderStatus() {
	}

	public OrderStatus(long id, String name) {
		super(id);
		this.name = name;
	}

	public OrderStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
