/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年12月10日 上午1:26:27
 *
 * @desc
 */
public class CarStationVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5986114733947945960L;

	private Integer id;
	private String name;
	private String address;
	private String phone;
	private String opHoursPickUp;
	private String opHoursDropOff;

	/**
	 * 
	 */
	public CarStationVo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOpHoursPickUp() {
		return opHoursPickUp;
	}

	public void setOpHoursPickUp(String opHoursPickUp) {
		this.opHoursPickUp = opHoursPickUp;
	}

	public String getOpHoursDropOff() {
		return opHoursDropOff;
	}

	public void setOpHoursDropOff(String opHoursDropOff) {
		this.opHoursDropOff = opHoursDropOff;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
