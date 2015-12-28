/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import java.util.List;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年12月15日 上午12:17:09
 *
 * @desc
 */
public class ActivityCategoryVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3124481383856858835L;

	private Integer id;
	private String name;
	List<ActivityVo> activityVos;

	/**
	 * 
	 */
	public ActivityCategoryVo() {
	}

	public List<ActivityVo> getActivityVos() {
		return activityVos;
	}

	public void setActivityVos(List<ActivityVo> activityVos) {
		this.activityVos = activityVos;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
