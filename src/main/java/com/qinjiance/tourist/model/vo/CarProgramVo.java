/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import java.util.Map;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年12月10日 上午1:30:56
 *
 * @desc
 */
public class CarProgramVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5310927661551632069L;

	private String id;
	private String name;
	private String price;
	private String tax;
	private String mandatoryFees;
	private Map<String, String> componentMap;

	/**
	 * 
	 */
	public CarProgramVo() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getMandatoryFees() {
		return mandatoryFees;
	}

	public void setMandatoryFees(String mandatoryFees) {
		this.mandatoryFees = mandatoryFees;
	}

	public Map<String, String> getComponentMap() {
		return componentMap;
	}

	public void setComponentMap(Map<String, String> componentMap) {
		this.componentMap = componentMap;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
