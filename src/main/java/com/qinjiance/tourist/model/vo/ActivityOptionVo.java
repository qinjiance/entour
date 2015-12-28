/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import java.util.List;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年12月14日 上午3:45:34
 *
 * @desc 
 */
public class ActivityOptionVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8701386275895745968L;
	private Integer  id;
	private String name;
	private String type;
	private List<AvailabilitiesVo> availabilitiesVos;

	/**
	 * 
	 */
	public ActivityOptionVo() {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<AvailabilitiesVo> getAvailabilitiesVos() {
		return availabilitiesVos;
	}

	public void setAvailabilitiesVos(List<AvailabilitiesVo> availabilitiesVos) {
		this.availabilitiesVos = availabilitiesVos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
