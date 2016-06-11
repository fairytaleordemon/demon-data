package com.yuanbosu.data.common.dto;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuanbosu.common.bean.Beans;

public abstract class BaseBo<T> {

	private Logger log = LoggerFactory.getLogger(BaseBo.class);
	
	protected abstract void processBean(T paramT);
	
	protected void fromModel(T from){
		this.log.debug("From Model [{}] content [{}]", from.getClass().getName(), from.toString());
	    try
	    {
	      Beans.copy(from, this);
	      processBean(from);
	    } catch (Exception ex) {
	      this.log.error("Model reflection copy (all) error [{}] ! ", ExceptionUtils.getStackTrace(ex));
	    }

	    this.log.debug("To Model [{}] after process content [{}]", super.getClass().getName(), super.toString());
	}
		
}
