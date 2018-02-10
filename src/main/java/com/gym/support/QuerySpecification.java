package com.gym.support;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.gym.support.QueryFilterParam.JoinModel;
import com.gym.support.QueryFilterParam.OperationModel;

public class QuerySpecification<T> implements Specification<T> {

	String filterParam;

	public QuerySpecification(String filterParam) {

		this.filterParam = filterParam;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		final List<QueryFilterParam> queryParamFilters = QueryParamUtil.parseQueryFilterParams(filterParam);
		Predicate restrictions = buildPredicate(queryParamFilters, root, cb);
		return restrictions;
	}
	
	public static Predicate buildPredicate(final List<QueryFilterParam> queryParamFilters, Root<?> root,
			CriteriaBuilder cb) {
		Predicate restrictions = null;
		Predicate preD = null;
		
		Map<String, Join<Object,Object>>joinMap = new HashMap<String, Join<Object,Object>>();
		
		for (int i = 0; i < queryParamFilters.size(); i++) {
			QueryFilterParam queryParamFilter = queryParamFilters.get(i);
			OperationModel opModel = queryParamFilter.getOperationMode();

			String propName = queryParamFilter.getParamName();
			String[] tmp = 	StringUtils.split(propName, '.');
		
			switch (queryParamFilter.getFieldType()) {
			case DateField:
				Path<Date> exp;
				if (tmp.length == 2) {
					if(!joinMap.containsKey(tmp[0])){
						joinMap.put(tmp[0], root.join(tmp[0],JoinType.LEFT));
					}
					exp = joinMap.get(tmp[0]).<Date> get(tmp[1]);
				} else {
					exp = root.<Date> get(propName);
				}

				switch (opModel) {
				case EQ:
					long timeInt = Long.parseLong(queryParamFilter.getParamValue().toString());
					Date queryDate = new Date(timeInt);
					preD = cb.equal(exp, queryDate);
					break;
				case NE:
					timeInt = Long.parseLong(queryParamFilter.getParamValue().toString());
				    queryDate = new Date(timeInt);
					preD = cb.notEqual(exp, queryDate);
					break;
				case GE:
					timeInt = Long.parseLong(queryParamFilter.getParamValue().toString());
					queryDate = new Date(timeInt);
					preD = cb.greaterThanOrEqualTo(exp, queryDate);
					break;
				case LE:
					timeInt = Long.parseLong(queryParamFilter.getParamValue().toString());
					queryDate = new Date(timeInt);
					preD = cb.lessThanOrEqualTo(exp, queryDate);
					break;
				case BTW:
					String[] timePair = queryParamFilter.getParamValue().toString().split("&&");
					long timelo = Long.parseLong(timePair[0]);
					long timego = Long.parseLong(timePair[1]);

					Date queryDatelo = new Date(timelo);
					Date queryDatego = new Date(timego);

					preD = cb.between(exp, queryDatelo, queryDatego);
					break;
				default:
					break;
				}
				break;
			case NumberField:
				Path<Number> exp2;
				if (tmp.length == 2) {
					if(!joinMap.containsKey(tmp[0])){
						joinMap.put(tmp[0], root.join(tmp[0]));
					}
					exp2 = joinMap.get(tmp[0]).<Number> get(tmp[1]);
				} else {
					exp2 = root.<Number> get(propName);
				}

				switch (opModel) {
				case EQ:
					preD = cb.equal(exp2, Long.valueOf(queryParamFilter.getParamValue().toString()));
					break;
				case NE:
					preD = cb.notEqual(exp2, Long.valueOf(queryParamFilter.getParamValue().toString()));
					break;
				case GE:
					preD = cb.ge(exp2, Long.valueOf(queryParamFilter.getParamValue().toString()));
					break;
				case LE:
					preD = cb.le(exp2, Long.valueOf(queryParamFilter.getParamValue().toString()));
					break;
				case GT:
					preD = cb.gt(exp2, Long.valueOf(queryParamFilter.getParamValue().toString()));
					break;
				case LT:
					preD = cb.lt(exp2, Long.valueOf(queryParamFilter.getParamValue().toString()));
					break;
				case IN:
					String[] numPair = queryParamFilter.getParamValue().toString().split(",");
					if(numPair.length>0){
						In<Number> in = cb.in(exp2);
						for(int k=0,length=numPair.length;k<length;k++){
							in.value(Long.valueOf(numPair[k]));
						}
						preD = in;
					}
					break;
				default:
					break;
				}
				break;
			case StringField:
				Path<String> exp3;
				if (tmp.length == 2) {
					if(!joinMap.containsKey(tmp[0])){
						joinMap.put(tmp[0], root.join(tmp[0]));
					}
					exp3 = joinMap.get(tmp[0]).<String> get(tmp[1]);
				} else {
					exp3 = root.<String> get(propName);
				}

				switch (opModel) {
				case EQ:
					preD = cb.equal(exp3, queryParamFilter.getParamValue());
					break;
				case NE:
					preD = cb.notEqual(exp3, queryParamFilter.getParamValue());
					break;
				case LK:
					preD = cb.like(exp3, "%" + queryParamFilter.getParamValue().toString() + "%");
					break;
				case LFK:
					preD = cb.like(exp3, queryParamFilter.getParamValue().toString() + "%");
					break;
				case RHK:
					preD = cb.like(exp3, "%" + queryParamFilter.getParamValue().toString());
					break;
				case IN:
					String[] strPair = queryParamFilter.getParamValue().toString().split(",");
					if(strPair.length>0){
						In<String> in = cb.in(exp3);
						for(int k=0,length=strPair.length;k<length;k++){
							in.value(strPair[k]);
						}
						preD = in;
					}
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}

			// 加入连接条件
			if (i == 0) {
				restrictions = preD;
			} else {
				if (queryParamFilter.getJoinModel() == JoinModel.AND) {
					restrictions = cb.and(preD, restrictions);
				} else if (queryParamFilter.getJoinModel() == JoinModel.OR) {
					restrictions = cb.or(preD, restrictions);
				}
			}

		}
		return restrictions;
	}
}
