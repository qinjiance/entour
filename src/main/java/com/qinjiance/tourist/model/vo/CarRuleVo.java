/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年12月10日 上午1:38:31
 *
 * @desc
 */
public class CarRuleVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9028502679292329759L;

	private String title;
	private String rule;

	/**
	 * 
	 */
	public CarRuleVo() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
