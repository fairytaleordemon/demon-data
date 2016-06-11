package com.yuanbosu.data.jpa.domain.dto;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.yuanbosu.data.common.dto.BaseBo;

public abstract class BaseListOutput<From, To extends BaseBo<From>> implements ListOutput<From, To> {
	List<To> list;
	Page<To> page;
	private Logger log = LoggerFactory.getLogger(BaseListOutput.class);

	@SuppressWarnings("unchecked")
	public BaseListOutput(Object from) {
		try {
			if (from instanceof List) {
				this.list = fromModel((List<From>)from);
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

	public void fromModel(Page<From> from) throws Exception {
		this.list = fromModel(from.getContent());
		this.page = new PageImpl<To>(this.list, new PageRequest(from.getNumber(), from.getSize()), from.getTotalElements());
	}

	public void fromModel(Page<From> from, To to) throws Exception {
		this.list = fromModel(from.getContent(), to);
		this.page = new PageImpl<To>(this.list, new PageRequest(from.getNumber(), from.getSize()), from.getTotalElements());
	}

	public Page<To> getPage() {
		return this.page;
	}

	public List<To> getList() {
		return this.list;
	}
}