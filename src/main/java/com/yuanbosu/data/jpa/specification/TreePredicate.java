package com.yuanbosu.data.jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.yuanbosu.data.jpa.domain.dto.TreeNodeSearchForm;

public class TreePredicate {
	public static void addExpression(Root<?> r, CriteriaBuilder cb, Predicate p, TreeNodeSearchForm<?> _form) {
		if (StringUtils.isNotBlank(_form.getParentId())) {
			p.getExpressions().add(cb.equal(r.get("node").get("parentId"), _form.getParentId()));
		}

		if (StringUtils.isNotBlank(_form.getIdPath())) {
			p.getExpressions().add(cb.like(r.get("node").get("idPath"), _form.getIdPath() + "%"));
		}

		if (StringUtils.isNotBlank(_form.getName())) {
			if (!(_form.isKeywordsMatchExact()))
				p.getExpressions().add(cb.like(r.get("node").get("name"), "%" + _form.getName() + "%"));
			else
				p.getExpressions().add(cb.equal(r.get("node").get("name"), _form.getName()));
		}
	}
}

