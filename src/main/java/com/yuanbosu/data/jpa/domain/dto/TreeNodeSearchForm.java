package com.yuanbosu.data.jpa.domain.dto;

import org.apache.commons.lang3.StringUtils;

public abstract class TreeNodeSearchForm<T> extends BaseSearch<T> {
	private static final long serialVersionUID = -305401261217928643L;
	private String parentId;
	private String name;
	private String idPath;
	private String namePath;
	private Integer treeLevel;
	private Boolean leaf;
	private Long leftP;
	private Long rightP;

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = StringUtils.trimToNull(parentId);
	}

	public String getIdPath() {
		return this.idPath;
	}

	public void setIdPath(String idPath) {
		this.idPath = StringUtils.trimToNull(idPath);
	}

	public String getNamePath() {
		return this.namePath;
	}

	public void setNamePath(String namePath) {
		this.namePath = StringUtils.trimToNull(namePath);
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
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

