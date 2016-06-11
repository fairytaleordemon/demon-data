package com.yuanbosu.data.common.dto;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;
import com.yuanbosu.common.bean.Beans;

public interface ListOutput<From, To extends BaseBo<From>> {

	@SuppressWarnings("unchecked")
	public default To createBeanInstance() throws InstantiationException, IllegalAccessException {
		return (To) ((BaseBo<To>) createBean().newInstance());
	}

	@SuppressWarnings("unchecked")
	public default Class<To> createBean() throws InstantiationException, IllegalAccessException {
		return ((Class<To>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
	}

	@SuppressWarnings("unchecked")
	public default List<To> fromModel(List<From> fromList) throws Exception {
		if (fromList == null)
			return Lists.newArrayList();
		List<To> to_items = new ArrayList<>();

		Class<To> toClazz = createBean();

		for (Iterator<From> localIterator = fromList.iterator(); localIterator.hasNext();) {
			Object from = localIterator.next();
			BaseBo<From> to = toClazz.newInstance();
			to.fromModel((From) from);
			to_items.add((To) to);
		}

		return to_items;
	}

	@SuppressWarnings("unchecked")
	public default List<To> fromModel(List<From> fromList, To to) throws Exception {
		List<From> from_items = fromList;
		List<To> to_items = new ArrayList<>();

		if (fromList == null)
			return to_items;

		Class<To> toClazz = createBean();

		for (Iterator<From> localIterator = from_items.iterator(); localIterator.hasNext();) {
			Object from = localIterator.next();
			BaseBo<To> newTo = (BaseBo<To>) toClazz.newInstance();
			Beans.copy(to, newTo);

			newTo.fromModel((To) from);
			to_items.add((To) newTo);
		}

		return to_items;
	}
}
