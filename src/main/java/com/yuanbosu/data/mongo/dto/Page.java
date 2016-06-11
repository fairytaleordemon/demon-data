package com.yuanbosu.data.mongo.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

public abstract interface Page<T> {
	public abstract long getTotalElements();

	public abstract int getTotalPages();

	public abstract List<T> getData();

	public abstract Pageable getPage();
}
