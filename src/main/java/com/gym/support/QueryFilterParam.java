package com.gym.support;

/***
 * 查询参数
 * @author daidd
 *
 */
public class QueryFilterParam {
    
	/**
	 * 参数名称
	 */
	private String paramName = "";
    
	/**
	 * 参数名值
	 */
	private Object paramValue;
    
	/**
	 * 字段类型
	 */
	private DataFieldType fieldType = DataFieldType.StringField;
    
	/**
	 * 关系模型
	 */
	private OperationModel operationMode;
	
	

	private JoinModel  joinModel;
	
	

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public Object getParamValue() {
		return paramValue;
	}

	public void setParamValue(Object paramValue) {
		this.paramValue = paramValue;
	}

	public DataFieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(DataFieldType fieldType) {
		this.fieldType = fieldType;
	}
	
	public OperationModel getOperationMode() {
		return operationMode;
	}

	public void setOperationMode(OperationModel operationMode) {
		this.operationMode = operationMode;
	}
	
	public JoinModel getJoinModel() {
		return joinModel;
	}

	public void setJoinModel(JoinModel joinModel) {
		this.joinModel = joinModel;
	}
    
	/**
	 * 字段类型枚举
	 *
	 */
	public enum DataFieldType {
		NumberField, StringField, DateField
	}
    
	/**
	 * 值关系枚举
	 */
	public enum OperationModel {
		/**
		 * < 小于
		 */
		LT,
		/**
		 * >  大于
		 */
		GT,
		/**
		 * ==等于
		 */
		EQ, 
		/**
		 * <> != 不等于
		 */
		NE, 
		/**
		 * <= 小于等于
		 */
		LE, 
		/**
		 *  >= 大于等于
		 */
		GE, 
		/**
		 * in
		 */
		IN,
		/**
		 * not in
		 */
		NOTIN, 
		/**
		 * like %value%
		 */
		LK,
		/**
		 * 左like 如value%
		 */
		LFK,
		/**
		 * 右like 如%value
		 */
		RHK,
		/**
		 * between 
		 */
		BTW
	}
	
	public enum JoinModel{
		AND,OR
	}

}
