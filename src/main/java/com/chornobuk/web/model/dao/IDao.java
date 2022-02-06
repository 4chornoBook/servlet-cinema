package com.chornobuk.web.model.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {
	T get(long id);

	List<T> getAll();

	void add(T t);

	void update(T t);

	void delete(T t);

}
