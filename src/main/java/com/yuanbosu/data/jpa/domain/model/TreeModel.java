package com.yuanbosu.data.jpa.domain.model;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class TreeModel extends BaseModel {
	private static final long serialVersionUID = 7855542439922113948L;

	@Embedded
	private TreeNode node;

	public TreeModel() {
	}

	public TreeModel(String id, TreeNode node) {
		super.setId(id);
		this.node = node;
	}

	public TreeNode getNode() {
		return this.node;
	}

	public void setNode(TreeNode node) {
		this.node = node;
	}

	public String toString() {
		String data = null;
		if (this.node != null)
			data = this.node.toString();
		return data;
	}
}