/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年11月2日 上午12:34:07
 *
 * @desc
 */
public class BoardbasePrice extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5268480225627595797L;

	private Integer bbId;
	private String bbName;
	private String bbPublishPrice;

	/**
	 * 
	 */
	public BoardbasePrice() {
	}

	public Integer getBbId() {
		return bbId;
	}

	public void setBbId(Integer bbId) {
		this.bbId = bbId;
	}

	public String getBbName() {
		return bbName;
	}

	public void setBbName(String bbName) {
		this.bbName = bbName;
	}

	public String getBbPublishPrice() {
		return bbPublishPrice;
	}

	public void setBbPublishPrice(String bbPublishPrice) {
		this.bbPublishPrice = bbPublishPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
