package com.gym.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.StringUtils;

import com.gym.support.QueryFilterParam.DataFieldType;
import com.gym.support.QueryFilterParam.JoinModel;
import com.gym.support.QueryFilterParam.OperationModel;

/**
 * 查询参数帮助类
 * 
 * @author daidd
 * 
 */
public class QueryParamUtil {

	/**
	 * 从请求对象获取查询参数,并进行构造
	 * <p>
	 * 参数名格式必须为: Q_firstName_S_EQ 其中Q_表示该参数为查询的参数，firstName查询的字段名称，和实体类的字段名称一致
	 * S代表该参数的类型为字符串类型,该位置的其他值有： D=日期，N=number,S=字符串 EQ代表等于。 该位置的其他值有：<br/>
	 * LT，GT，EQ，LE，GE,LK,NE<br/>
	 * 要别代表<,>,=,<=,>=,like的条件查询
	 * <p>
	 * 
	 * @param filterParam
	 */
	public static List<QueryFilterParam> parseQueryFilterParams(String filterParam) {

		List<QueryFilterParam> queryParams = new ArrayList<QueryFilterParam>();
		if (filterParam == null || filterParam.trim().isEmpty()) {
			return queryParams;
		}

		String[] paramPairs = filterParam.split(";");
		String[] paramValPair;
		String parseParam = "";
		for (int i = 0; i < paramPairs.length; i++) {
			if (paramPairs[i].trim().isEmpty()) {
				continue;
			}
			QueryFilterParam queryParam = new QueryFilterParam();
			paramValPair = paramPairs[i].split("=");
			parseParam = paramValPair[0].trim();

			String[] fieldInfo = parseParam.split("_");

			// 设置连接条件
			String joinParam = fieldInfo[0];
			if (joinParam.toUpperCase().equals("Q") || joinParam.toUpperCase().equals("QA")) {
				queryParam.setJoinModel(JoinModel.AND);
			} else if (joinParam.toUpperCase().equals("QOR")) {
				queryParam.setJoinModel(JoinModel.OR);
			}

			// 设置字段名称
			queryParam.setParamName(fieldInfo[1].trim());
			queryParam.setParamValue(paramValPair[1].trim());

			// 设置字段类型
			String fieldType = fieldInfo[2];
			if (fieldType.toUpperCase().equals("N")) {
				queryParam.setFieldType(DataFieldType.NumberField);
			} else if (fieldType.toUpperCase().equals("S")) {
				queryParam.setFieldType(DataFieldType.StringField);
			} else if (fieldType.toUpperCase().equals("D")) {
				queryParam.setFieldType(DataFieldType.DateField);
			}

			// 设置操作符
			String operation = fieldInfo[3];
			if (operation.toUpperCase().equals("EQ")) {
				queryParam.setOperationMode(OperationModel.EQ);
			} if (operation.toUpperCase().equals("NE")) {
				queryParam.setOperationMode(OperationModel.NE);
			} else if (operation.toUpperCase().equals("LK")) {
				queryParam.setOperationMode(OperationModel.LK);
			} else if (operation.toUpperCase().equals("LFK")) {
				queryParam.setOperationMode(OperationModel.LFK);
			} else if (operation.toUpperCase().equals("RHK")) {
				queryParam.setOperationMode(OperationModel.RHK);
			} else if (operation.toUpperCase().equals("BTW")) {
				queryParam.setOperationMode(OperationModel.BTW);
			} else if (operation.toUpperCase().equals("LT")) {
				queryParam.setOperationMode(OperationModel.LT);
			} else if (operation.toUpperCase().equals("GT")) {
				queryParam.setOperationMode(OperationModel.GT);
			} else if (operation.toUpperCase().equals("LE")) {
				queryParam.setOperationMode(OperationModel.LE);
			} else if (operation.toUpperCase().equals("GE")) {
				queryParam.setOperationMode(OperationModel.GE);
			} else if (operation.toUpperCase().equals("IN")) {
				queryParam.setOperationMode(OperationModel.IN);
			} else if (operation.toUpperCase().equals("NOTIN")) {
				queryParam.setOperationMode(OperationModel.NOTIN);
			}

		

			queryParams.add(queryParam);
		}

		return queryParams;
	}

	/**
	 * 从请求对象获取排序参数,并进行构造
	 * <p>
	 * 参数名格式必须为: SORT_firstName_ASC
	 * 其中SORT表示该参数为排序参数,firstName查询的字段名称，和实体类的字段名称一致 ASC代表该参数为升序
	 * 
	 * @param filterParam
	 */
	public static Sort parseSortParams(String sortParam) {

		if (StringUtils.isEmpty(sortParam)) {
			return null;
		}

		List<Order> list = new ArrayList<Order>();
		String[] paramPairs = sortParam.split(";");
		for (int i = 0; i < paramPairs.length; i++) {
			String parseParam = paramPairs[i];
			String[] fieldInfo = parseParam.split("_");
			if (fieldInfo[0].startsWith("SORT")) {
				Order o = new Order(Direction.fromString(fieldInfo[2]), fieldInfo[1]);
				list.add(o);
			}
		}
		return new Sort(list);
	}
}
