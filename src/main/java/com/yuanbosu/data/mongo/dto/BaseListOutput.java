package com.yuanbosu.data.mongo.dto;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuanbosu.data.common.dto.BaseBo;

public abstract class BaseListOutput<From, To extends BaseBo<From>> implements ListOutput<From, To> {
	private Page<To> page;
	private List<To> list;
	private Logger log = LoggerFactory.getLogger(BaseListOutput.class);

	public BaseListOutput(Object from) {
		try {
			if (from instanceof List) {
				this.list = fromModel((List<From>) from);
			}

			if (from instanceof Page)
				fromModel((Page<From>) from);
		} catch (Exception ex) {
			this.log.error(ExceptionUtils.getStackTrace(ex));
		}
	}

	public BaseListOutput(Object from, To to) {
		try {
			if (from instanceof List) {
				this.list = fromModel((List<From>) from, to);
			}

			if (from instanceof Page)
				fromModel((Page<From>) from, to);
		} catch (Exception ex) {
			this.log.error(ExceptionUtils.getStackTrace(ex));
		}
	}

	private void fromModel(Page<From> from) throws Exception {
		this.list = fromModel(from.getData());
		this.page = new PageImpl<To>(from.getPage(), this.list, from.getTotalElements());
	}

	public void fromModel(Page<From> from, To to) throws Exception {
		this.list = fromModel(from.getData(), to);
		this.page = new PageImpl<To>(from.getPage(), this.list, from.getTotalElements());
	}

	public Page<To> getPage() {
		return this.page;
	}

	public List<To> getList() {
		return this.list;
	}
}
