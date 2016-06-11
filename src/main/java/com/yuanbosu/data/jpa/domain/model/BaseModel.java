package com.yuanbosu.data.jpa.domain.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@MappedSuperclass
public class BaseModel extends PkModel<String> {
	
	
	private static final long serialVersionUID = -715456645278100196L;
	protected static final int pk_length = 36;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 36)
	private String id;

	@Column(updatable = false)
	private Long timeCreated;

	@Basic
	private Long timeModified;

	@Basic
	@Type(type = "org.hibernate.type.NumericBooleanType")
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

	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (!(getClass().equals(obj.getClass()))) {
			return false;
		}

		PkModel<?> that = (PkModel<?>) obj;

		return ((null == getId()) ? false : getId().equals(that.getId()));
	}

	public int hashCode() {
		int hashCode = 17;

		hashCode += ((null == getId()) ? 0 : getId().hashCode() * 31);

		return hashCode;
	}

	@PrePersist
	void onCreate() {
		setTimeCreated(Long.valueOf(System.currentTimeMillis()));
		setTimeModified(Long.valueOf(System.currentTimeMillis()));
		setDeleted(Boolean.valueOf(false));
	}

	@PreUpdate
	void onPersist() {
		setTimeModified(Long.valueOf(System.currentTimeMillis()));
	}
}
