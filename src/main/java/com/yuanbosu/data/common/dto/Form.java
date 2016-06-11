package com.yuanbosu.data.common.dto;

import java.lang.reflect.ParameterizedType;

public interface Form<T> {
	
	/**
	 * 创建泛型实例
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public default T createBeanInstance()
    throws InstantiationException, IllegalAccessException
  {
    return createBean().newInstance();
  }

	/**
	 * 获取超类的泛型类型
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public default Class<T> createBean() throws InstantiationException, IllegalAccessException
  {
    return ((Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
  }

	public abstract String getId();

	public abstract void setId(String paramString);

	public abstract void toModel(T paramT, boolean paramBoolean);

	public abstract boolean isUpdateTimeModified();
}
