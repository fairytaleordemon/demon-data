package com.yuanbosu.data.constants;

import org.apache.commons.lang3.StringUtils;

public enum SortBy {
	timeCreatedDesc, timeCreatedAsc, timeModifiedDesc, timeModifiedAsc;

	private String value;
	private String name;

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}

	public static SortBy formatEnum(String key) {
		if (StringUtils.isBlank(key))
			return null;
		try {
			return valueOf(key);
		} catch (Exception e) {
		}
		return timeCreatedDesc;
	}
}
