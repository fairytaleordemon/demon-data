package com.yuanbosu.data.mongo.model;

import java.io.Serializable;

import javax.persistence.Id;

public class BaseModel implements Serializable {
	private static final long serialVersionUID = -715456645278100196L;
	protected static final int pk_length = 36;

	@Id
	private String id;
	private Long timeCreated;
	private Long timeModified;
	private Boolean deleted;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getTimeCreated() {
		return this.timeCreated;
	}

	public void setTimeCreated(Long timeCreated) {
		this.timeCreated = timeCreated;
	}

	public Long getTimeModified() {
		return this.timeModified;
	}

	public void setTimeModified(Long timeModified) {
		this.timeModified = timeModified;
	}

	public Boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	void onCreate() {
		if (this.id == null) {
			setTimeCreated(Long.valueOf(System.currentTimeMillis()));
			setTimeModified(Long.valueOf(System.currentTimeMillis()));
			setDeleted(Boolean.valueOf(false));
		}
	}

	void onPersist() {
		if (this.timeModified != null)
			setTimeModified(Long.valueOf(System.currentTimeMillis()));
	}

	public void onSave() {
		if (this.id == null) {
			setTimeCreated(Long.valueOf(System.currentTimeMillis()));
			setDeleted(Boolean.valueOf(false));
		}

		setTimeModified(Long.valueOf(System.currentTimeMillis()));
	}
}
