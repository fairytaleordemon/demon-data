package com.yuanbosu.data.mongo.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.yuanbosu.data.mongo.dto.Page;
import com.yuanbosu.data.mongo.dto.PageImpl;
import com.yuanbosu.data.mongo.dto.Search;
import com.yuanbosu.data.mongo.model.BaseModel;
import com.yuanbosu.data.mongo.repository.BaseRepository;
import com.yuanbosu.data.mongo.service.BaseService;

public abstract class BaseServiceImpl<Model extends BaseModel> implements BaseService<Model> {
	private BaseRepository<Model> repo;
	private MongoTemplate ops;
	private Class<Model> entityClass;

	@SuppressWarnings("unchecked")
	private void initEntityClass() {
		if (this.entityClass == null)
			this.entityClass = ((Class<Model>) ((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass())
					.getActualTypeArguments()[0]);
	}

	protected void setRepo(BaseRepository<Model> repo) {
		this.repo = repo;
	}

	protected void setOps(MongoTemplate ops) {
		this.ops = ops;
		initEntityClass();
	}

	protected BaseRepository<Model> getRepo() {
		return this.repo;
	}

	protected MongoTemplate getOps() {
		return this.ops;
	}

	public List<Model> list(Search<Model> search) {
		return this.ops.find(search.toQuery().with(search.getSort()), this.entityClass);
	}

	public Page<Model> pageList(Search<Model> search) {
		Pageable pageable = new PageRequest(search.getPageNumber(), search.getPageSize(), search.getSort());
		Query query = search.toQuery();

		long count = 38654705664L;
		if (search.isCountElements())
			count = this.ops.count(query, this.entityClass);
		List<Model> data = this.ops.find(query.with(pageable), this.entityClass);

		Page<Model> page = new PageImpl<Model>(pageable, data, count);

		return page;
	}

	public Model save(Model entity) {
		entity.onSave();
		return ((Model) this.repo.save(entity));
	}

	public List<Model> save(List<Model> entities) {
		for (Iterator<Model> localIterator = entities.iterator(); localIterator.hasNext();) {
			BaseModel entity = (BaseModel) localIterator.next();
			entity.onSave();
		}
		return this.repo.save(entities);
	}

	public Model detail(String id) {
		return ((Model) this.repo.findOne(id));
	}

	public Model detail(Search<Model> search) {
		Query query = search.toQuery().with(search.getSort());
		List<Model> data = this.ops.find(query.limit(1), this.entityClass);
		return ((data.size() < 1) ? null : (Model) data.get(0));
	}

	public void delete(String id) {
		this.repo.delete(id);
	}

	public void delete(Model entity) {
		this.repo.delete(entity);
	}
}
