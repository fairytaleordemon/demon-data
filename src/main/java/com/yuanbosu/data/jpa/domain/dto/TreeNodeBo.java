package com.yuanbosu.data.jpa.domain.dto;


import com.yuanbosu.data.common.dto.BaseBo;
import com.yuanbosu.data.jpa.domain.model.TreeNode;

public class TreeNodeBo extends BaseBo<TreeNode> {
	private String parentId;
	private String name;
	private Boolean leaf;
	private Integer subNodesCount;

	public TreeNodeBo() {
	}

	public TreeNodeBo(TreeNode model) {
		fromModel(model);
	}

	protected void processBean(TreeNode obj) {
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Boolean getLeaf() {
		return this.leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSubNodesCount() {
		return this.subNodesCount;
	}

	public void setSubNodesCount(Integer subNodesCount) {
		this.subNodesCount = subNodesCount;
	}

	public String toString() {
		return "TreeNodeBo [parentId=" + this.parentId + ", name=" + this.name + ", leaf=" + this.leaf
				+ ", subNodesCount=" + this.subNodesCount + "]";
	}
}
