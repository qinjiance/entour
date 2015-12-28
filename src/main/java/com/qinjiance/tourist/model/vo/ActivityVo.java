package com.qinjiance.tourist.model.vo;

import java.util.List;

import module.laohu.commons.model.BaseObject;

public class ActivityVo extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4108947298753992573L;
	
	private Integer id;
	private String name;
	private String desc;
	private String stars;
	private String currency;
	private Integer minChildAge;
	private Integer maxChildAge;
	private String thumb;
	private String address;
	private String country;
	private List<ActivityOptionVo> activityOptionVos;
	
	public ActivityVo() {
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getMinChildAge() {
		return minChildAge;
	}

	public void setMinChildAge(Integer minChildAge) {
		this.minChildAge = minChildAge;
	}

	public Integer getMaxChildAge() {
		return maxChildAge;
	}

	public void setMaxChildAge(Integer maxChildAge) {
		this.maxChildAge = maxChildAge;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<ActivityOptionVo> getActivityOptionVos() {
		return activityOptionVos;
	}

	public void setActivityOptionVos(List<ActivityOptionVo> activityOptionVos) {
		this.activityOptionVos = activityOptionVos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
