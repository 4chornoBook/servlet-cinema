package com.chornobuk.web.model.entity;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Order extends Entity{
	private long userId;
	private LocalDateTime creationDate;
	private int totalPrice;
	private List<Ticket> tickets;

	public Order(long id, long userId, LocalDateTime creationDate, int totalPrice) {
		super(id);
		this.userId = userId;
		this.creationDate = creationDate;
		this.totalPrice = totalPrice;
		this.tickets = new LinkedList<>();
	}

	public Order(long id) {
		super(id);
	}

	public Order() {

	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
}
