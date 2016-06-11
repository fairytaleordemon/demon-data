package com.yuanbosu.data.mongo.dto;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

import com.google.common.collect.Lists;

public abstract class BaseSearch<T> implements Search<T> {
	private Logger log;
	private int pageNumber;
	private int pageSize;
	private Sort defaultSort;
	private List<Criteria> criteries;

	public BaseSearch() {
		this.log = LoggerFactory.getLogger(BaseSearch.class);

		this.pageSize = 50;

		this.defaultSort = new Sort(Sort.Direction.DESC, new String[] { "timeCreated" });

		this.criteries = Lists.newLinkedList();
	}

	public void addCriteria(Criteria criteria) {
		this.criteries.add(criteria);
	}

	public Criteria makeCriteria(Criteria root) {
		if (!(this.criteries.isEmpty())) {
			root.andOperator((Criteria[]) this.criteries.toArray(new Criteria[this.criteries.size()]));
		}

		this.log.debug("Criteria: [{}]", root.getCriteriaObject().toMap());

		return root;
	}

	public Sort getSort() {
		return this.defaultSort;
	}

	public void setSort(Sort sort) {
		this.defaultSort = sort;
	}

	public boolean isCountElements() {
		return true;
	}

	public int getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}