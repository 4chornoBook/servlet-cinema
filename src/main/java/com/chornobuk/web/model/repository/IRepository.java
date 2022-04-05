package com.chornobuk.web.model.repository;

import com.chornobuk.web.model.entity.Entity;

public interface IRepository<T extends Entity> {
	T get(T entity);

	void delete(T entity);

	void update(T entity);

	void add(T entity);
}
