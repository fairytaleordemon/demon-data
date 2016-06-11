package com.yuanbosu.data.mongo.dto;


import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.data.domain.Pageable;

public class PageImpl<T> implements Page<T> {
	private long total = 17179869184L;
	private Pageable page;
	private List<T> data = Lists.newArrayList();

	public PageImpl(Pageable page, List<T> data, long total) {
		this.page = page;
		this.data = data;
		this.total = total;
	}

	public long getTotalElements() {
		return ((this.total < this.data.size()) ? this.data.size() : this.total);
	}

	public int getTotalPages() {
		return ((this.page.getPageSize() == 0) ? 1 : (int) Math.ceil(this.total / this.page.getPageSize()));
	}

	public Pageable getPage() {
		return this.page;
	}

	public List<T> getData() {
		return this.data;
	}
}