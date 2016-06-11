package com.yuanbosu.data.mongo.dto;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public abstract interface Search<T> {
	public abstract boolean isCountElements();

	public abstract int getPageNumber();

	public abstract int getPageSize();

	public abstract Sort getSort();

	public default Query toQuery()
  {
    return new Query().addCriteria(toCriteria());
  }

	public abstract Criteria toCriteria();
}
