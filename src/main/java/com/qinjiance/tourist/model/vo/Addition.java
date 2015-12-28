/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import java.util.List;

import module.laohu.commons.model.BaseObject;

/**
 * @author "Jiance Qin"
 * 
 * @date 2015年12月16日
 * 
 * @time 下午6:32:51
 * 
 * @desc
 * 
 */
public class Addition extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7843553570736745198L;
	private Integer typeId;
	private String type;
	private Integer minVal;
	private Integer maxVal;
	private String category;
	private List<String> valueList;
	private String value;

	/**
	 * 
	 */
	public Addition() {
	}

	/**
	 * @return the valueList
	 */
	public List<String> getValueList() {
		return valueList;
	}

	/**
	 * @param valueList
	 *            the valueList to set
	 */
	public void setValueList(List<String> valueList) {
		this.valueList = valueList;
	}

	/**
	 * @return the typeId
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the minVal
	 */
	public Integer getMinVal() {
		return minVal;
	}

	/**
	 * @param minVal
	 *            the minVal to set
	 */
	public void setMinVal(Integer minVal) {
		this.minVal = minVal;
	}

	/**
	 * @return the maxVal
	 */
	public Integer getMaxVal() {
		return maxVal;
	}

	/**
	 * @param maxVal
	 *            the maxVal to set
	 */
	public void setMaxVal(Integer maxVal) {
		this.maxVal = maxVal;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
