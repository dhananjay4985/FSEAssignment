package com.hibernate.dao;

import java.io.Serializable;

public interface SubjectDAO<T, subjectId extends Serializable > {

	public void addSubject(T entity);
	public T searchById(subjectId subId);
	public void deleteById(subjectId subId);
}
