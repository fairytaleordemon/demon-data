package com.yuanbosu.data.jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.yuanbosu.data.jpa.domain.dto.TreeNodeSearchForm;
import com.yuanbosu.data.jpa.domain.model.TreeModel;

public class TreeSpecification<Model extends TreeModel> implements Specification<Model> {
	private TreeNodeSearchForm<Model> form;

	public TreeSpecification(TreeNodeSearchForm<Model> searchForm) {
		this.form = searchForm;
	}

	public Predicate toPredicate(Root<Model> r, CriteriaQuery<?> q, CriteriaBuilder cb) {
		Predicate p = cb.conjunction();

		p.getExpressions().add(cb.and(new Predicate[] { cb.equal(r.get("deleted"), Boolean.valueOf(false)) }));

		TreePredicate.addExpression(r, cb, p, this.form);

		return p;
	}
}