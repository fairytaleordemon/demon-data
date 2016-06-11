package com.yuanbosu.data.common.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public interface Search {
	public abstract int getStart();

	public abstract void setStart(int paramInt);

	public abstract int getSize();

	public abstract void setSize(int paramInt);

	public abstract Sort getSort();

	public abstract PageRequest getPage(Integer paramInteger);

	public abstract PageRequest getPage();
}
