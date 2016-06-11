package com.yuanbosu.data.mongo.service;

import java.util.List;

import com.yuanbosu.data.mongo.dto.Page;
import com.yuanbosu.data.mongo.dto.Search;
import com.yuanbosu.data.mongo.model.BaseModel;

public abstract interface BaseService<Model extends BaseModel> {
	public abstract List<Model> list(Search<Model> paramSearch);

	public abstract Page<Model> pageList(Search<Model> paramSearch);

	public abstract Model save(Model paramModel);

	public abstract List<Model> save(List<Model> paramList);

	public abstract Model detail(Search<Model> paramSearch);

	public abstract Model detail(String paramString);

	public abstract void delete(String paramString);

	public abstract void delete(Model paramModel);
}