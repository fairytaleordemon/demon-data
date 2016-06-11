package com.yuanbosu.data.jpa.domain.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.yuanbosu.data.constants.SortBy;

public abstract class BaseSearch<T> implements Serializable, Search<T> {
	private static final long serialVersionUID = 7954737573502075242L;
	protected int start;
	protected int size;
	protected String keywords;
	protected boolean keywordsMatchExact;
	protected boolean keywordsMatchAll;
	protected boolean keywordsMatchNot;
	protected boolean kewywordsIgnoreCase;
	protected SortBy sortby;

	public BaseSearch() {
		this.start = 0;

		this.size = 20;

		this.keywordsMatchExact = false;

		this.keywordsMatchAll = false;

		this.keywordsMatchNot = false;

		this.kewywordsIgnoreCase = false;

		this.sortby = SortBy.timeCreatedDesc;
	}

	public int getStart() {
		return this.start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getKeywords() {
		return StringUtils.trimToEmpty(this.keywords);
	}

	public void setKeywords(String keywords) {
		this.keywords = StringUtils.trimToEmpty(keywords);
	}

	public Sort getSort() {
		if (this.sortby.equals(SortBy.timeCreatedDesc))
			return new Sort(Sort.Direction.DESC, new String[] { "timeCreated" });

		if (this.sortby.equals(SortBy.timeCreatedAsc))
			return new Sort(Sort.Direction.ASC, new String[] { "timeCreated" });
		if (this.sortby.equals(SortBy.timeModifiedDesc))
			return new Sort(Sort.Direction.DESC, new String[] { "timeModified" });
		if (this.sortby.equals(SortBy.timeModifiedAsc))
			return new Sort(Sort.Direction.ASC, new String[] { "timeModified" });

		return new Sort(Sort.Direction.DESC, new String[] { "timeCreated" });
	}

	public PageRequest getPage(Integer size) {
		return new PageRequest(this.start, size.intValue(), getSort());
	}

	public PageRequest getPage() {
		return new PageRequest(this.start, this.size, getSort());
	}

	public boolean isKeywordsMatchExact() {
		return this.keywordsMatchExact;
	}

	public void setKeywordsMatchExact(boolean keywordsMatchExact) {
		this.keywordsMatchExact = keywordsMatchExact;
	}

	public boolean isKeywordsMatchAll() {
		return this.keywordsMatchAll;
	}

	public void setKeywordsMatchAll(boolean keywordsMatchAll) {
		this.keywordsMatchAll = keywordsMatchAll;
	}

	public boolean isKeywordsMatchNot() {
		return this.keywordsMatchNot;
	}

	public void setKeywordsMatchNot(boolean keywordsMatchNot) {
		this.keywordsMatchNot = keywordsMatchNot;
	}

	public boolean isKewywordsIgnoreCase() {
		return this.kewywordsIgnoreCase;
	}

	public void setKewywordsIgnoreCase(boolean kewywordsIgnoreCase) {
		this.kewywordsIgnoreCase = kewywordsIgnoreCase;
	}

	public SortBy getSortby() {
		return this.sortby;
	}

	public void setSortby(String sortby) {
		this.sortby = SortBy.formatEnum(sortby);
	}
}
