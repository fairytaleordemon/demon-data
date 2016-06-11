package com.yuanbosu.data.jpa.domain.dto;


import com.yuanbosu.data.common.dto.BaseBo;
import com.yuanbosu.data.jpa.domain.model.TreeModel;

public abstract class TreeBo<T extends TreeModel> extends BaseBo<T> {
	private TreeNodeBo node;

	public TreeBo() {
		this.node = new TreeNodeBo();
	}

	protected void initTree(T model) {
		if (model.getNode() != null)
			setNode(new TreeNodeBo(model.getNode()));
	}

	public TreeNodeBo getNode() {
		return this.node;
	}

	public void setNode(TreeNodeBo node) {
		this.node = node;
	}
}
