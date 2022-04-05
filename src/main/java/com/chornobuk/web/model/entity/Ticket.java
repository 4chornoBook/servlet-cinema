package com.chornobuk.web.model.entity;

public class Ticket extends Entity {
	private int placeNumber;
	private long orderId;
	private long sessionId;

	public Ticket(long id, int placeNumber, long orderId, long sessionId) {
		super(id);
		this.placeNumber = placeNumber;
		this.orderId = orderId;
		this.sessionId = sessionId;
	}

	public Ticket(long id) {
		super(id);
	}

	public Ticket() {

	}

	public int getPlaceNumber() {
		return placeNumber;
	}

	public void setPlaceNumber(int placeNumber) {
		this.placeNumber = placeNumber;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
}
