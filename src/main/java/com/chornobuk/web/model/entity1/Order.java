package com.chornobuk.web.model.entity1;

import java.time.LocalDateTime;

public class Order extends Entity{
	private long userId;
	private long statusId;
	private LocalDateTime creationDate;

	public Order(long id, long userId, long statusId, LocalDateTime creationDate) {
		super(id);
		this.userId = userId;
		this.statusId = statusId;
		this.creationDate = creationDate;
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

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
}
