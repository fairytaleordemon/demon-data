package com.yuanbosu.data.jpa.domain.dto;

import org.apache.commons.lang3.StringUtils;

import com.yuanbosu.data.common.dto.BaseForm;
import com.yuanbosu.data.jpa.domain.model.TreeModel;

public abstract class TreeNodeForm<Model extends TreeModel> extends BaseForm<Model> {
	private String name;
	private String parentId;
	private Integer treeLevel;
	private Boolean leaf;
	private Long leftP;
	private Long rightP;
	private boolean countSub;

	public TreeNodeForm() {
		this.countSub = false;
	}

	public String getParentId() {
		return StringUtils.trimToNull(this.parentId);
	}

	public void setParentId(String parentId) {
		this.parentId = StringUtils.trimToNull(parentId);
	}

	public Integer getTreeLevel() {
		return this.treeLevel;
	}

	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
	}

	public Boolean getLeaf() {
		return this.leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Long getLeftP() {
		return this.leftP;
	}

	public void setLeftP(Long leftP) {
		this.leftP = leftP;
	}

	public Long getRightP() {
		return this.rightP;
	}

	public void setRightP(Long rightP) {
		this.rightP = rightP;
	}

	public String getName() {
		return StringUtils.trimToNull(this.name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCountSub() {
		return this.countSub;
	}

	public void setCountSub(boolean countSub) {
		this.countSub = countSub;
	}

	public String toNodeString() {
		return "TreeNodeForm [name=" + this.name + ",parentId=" + this.parentId + ", treeLevel=" + this.treeLevel
				+ ", leaf=" + this.leaf + ", leftP=" + this.leftP + ", rightP=" + this.rightP + "]";
	}
}
