/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年10月18日 上午12:22:30
 *
 * @desc
 */
public class CarDestinationVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3177284712873286597L;

	private String label;
	private String value;

	/**
	 * 
	 */
	public CarDestinationVo() {
	}

	public CarDestinationVo(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
