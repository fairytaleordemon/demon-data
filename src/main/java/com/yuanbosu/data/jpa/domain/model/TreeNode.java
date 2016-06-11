package com.yuanbosu.data.jpa.domain.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

@Embeddable
public class TreeNode {

	@Column(nullable = false, length = 50)
	private String name;

	@Column(length = 36)
	private String parentId;

	@Column(length = 255)
	private String idPath;

	@Column(length = 11)
	private Long leftP;

	@Column(length = 11)
	private Long rightP;

	@Column(length = 11)
	private Integer treeLevel;

	@Basic
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean leaf;

	@Basic
	private Integer subNodesCount;

	public String getIdPath() {
		return this.idPath;
	}

	public void setIdPath(String idPath) {
		this.idPath = idPath;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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
		return "TreeNode [name=" + this.name + ", parentId=" + this.parentId + ", idPath=" + this.idPath + ", leftP="
				+ this.leftP + ", rightP=" + this.rightP + ", treeLevel=" + this.treeLevel + ", leaf=" + this.leaf
				+ ", subNodesCount=" + this.subNodesCount + "]";
	}
}