package com.yuanbosu.data.jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.yuanbosu.data.jpa.domain.dto.Search;

class KeywordsPredicate {
	public static void addExpression(Root<?> r, CriteriaBuilder cb, Predicate p, Search<?> _form,
			String[] _properties) {
		if (StringUtils.isNotBlank(_form.getKeywords())) {
			String[] arrayOfString;
			int i;
			int j;
			String property;
			String searchValue = _form.getKeywords();

			if (!(_form.isKeywordsMatchExact())) {
				searchValue = "%" + searchValue + "%";
			}

			if (_form.isKeywordsMatchAll()) {
				Predicate and = cb.conjunction();

				arrayOfString = _properties;
				i = arrayOfString.length;
				for (j = 0; j < i; ++j) {
					property = arrayOfString[j];
					matchKeywords(r, cb, and, _form, property, searchValue);
				}

				p.getExpressions().add(and);
			} else {
				Predicate or = cb.disjunction();

				arrayOfString = _properties;
				i = arrayOfString.length;
				for (j = 0; j < i; ++j) {
					property = arrayOfString[j];
					matchKeywords(r, cb, or, _form, property, searchValue);
				}

				p.getExpressions().add(or);
			}
		}
	}

	private static void matchKeywords(Root<?> r, CriteriaBuilder cb, Predicate p, Search<?> _form, String _property,
			String _value) {
		Expression<String> property = propertyCaseConvert(r, cb, _form, _property);
		String value = valueCaseConvert(_form, _value);

		if (!(_form.isKeywordsMatchNot())) {
			if (_form.isKeywordsMatchExact()) {
				p.getExpressions().add(cb.equal(property, value));
			} else {
				p.getExpressions().add(cb.like(property, value));
			}

		} else if (_form.isKeywordsMatchExact())
			p.getExpressions().add(cb.notEqual(property, value));
		else
			p.getExpressions().add(cb.notLike(property, value));
	}

	private static Expression<String> propertyCaseConvert(Root<?> r, CriteriaBuilder cb, Search<?> _form,
			String _property) {
		if (_form.isKewywordsIgnoreCase())
			return cb.upper(r.get(_property));

		return r.get(_property);
	}

	private static String valueCaseConvert(Search<?> _form, String _value) {
		if (_form.isKewywordsIgnoreCase())
			return _value.toUpperCase();

		return _value;
	}
}
