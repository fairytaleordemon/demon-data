package com.yuanbosu.data.jpa.domain.dto;

import org.springframework.data.jpa.domain.Specification;

public interface Search<T> extends com.yuanbosu.data.common.dto.Search {
	public abstract Specification<T> getSpecification();

	public abstract String getKeywords();

	public abstract void setKeywords(String paramString);

	public abstract boolean isKeywordsMatchExact();

	public abstract void setKeywordsMatchExact(boolean paramBoolean);

	public abstract boolean isKeywordsMatchAll();

	public abstract void setKeywordsMatchAll(boolean paramBoolean);

	public abstract boolean isKeywordsMatchNot();

	public abstract void setKeywordsMatchNot(boolean paramBoolean);

	public abstract boolean isKewywordsIgnoreCase();

	public abstract void setKewywordsIgnoreCase(boolean paramBoolean);
}
