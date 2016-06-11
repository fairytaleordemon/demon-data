package com.yuanbosu.data.jpa.domain.dto;

import com.yuanbosu.data.common.dto.BaseBo;
import com.yuanbosu.data.jpa.domain.model.TreeModel;

public class TreePropBo<T extends TreeModel> extends BaseBo<T> {
	private String id;
	private String name;

	public TreePropBo() {
	}

	public TreePropBo(T model) {
		fromModel(model);
	}

	protected void processBean(T model) {
		if (model.getNode() != null)
			setName(model.getNode().getName());
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
