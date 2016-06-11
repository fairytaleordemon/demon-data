package com.yuanbosu.data.jpa.domain.dto;

import java.io.Serializable;

public class MessageOutput implements Serializable {
	private static final long serialVersionUID = -3124596963691360882L;
	private String code;
	private String message;
	private Object data;

	public MessageOutput(String code) {
		this.code = code;
	}

	public MessageOutput(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public MessageOutput(String code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return this.data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
