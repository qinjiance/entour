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
 * @time 下午7:05:54
 *
 * @desc
 *
 */
public class PassengerInfoVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7940097320816964190L;

	private Boolean needFirstName;
	private Boolean needLastName;
	private Boolean needMobilePhone;
	private Boolean isMainContact;
	private Integer seqNumber;
	private String type;
	private String typeName;
	private List<Addition> additions;

	/**
	 * 
	 */
	public PassengerInfoVo() {
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Boolean getNeedFirstName() {
		return needFirstName;
	}

	public void setNeedFirstName(Boolean needFirstName) {
		this.needFirstName = needFirstName;
	}

	public Boolean getNeedLastName() {
		return needLastName;
	}

	public void setNeedLastName(Boolean needLastName) {
		this.needLastName = needLastName;
	}

	public Boolean getNeedMobilePhone() {
		return needMobilePhone;
	}

	public void setNeedMobilePhone(Boolean needMobilePhone) {
		this.needMobilePhone = needMobilePhone;
	}

	public Boolean getIsMainContact() {
		return isMainContact;
	}

	public void setIsMainContact(Boolean isMainContact) {
		this.isMainContact = isMainContact;
	}

	public Integer getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Addition> getAdditions() {
		return additions;
	}

	public void setAdditions(List<Addition> additions) {
		this.additions = additions;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
