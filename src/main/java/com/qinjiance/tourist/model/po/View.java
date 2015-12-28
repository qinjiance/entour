package com.qinjiance.tourist.model.po;

import java.util.Date;

import module.laohu.commons.model.BaseObject;

import org.apache.ibatis.type.Alias;

@Alias("view")
public class View extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7081816808907682529L;

	private Long id;
	private Long areaId;
	private String name;
	private Long hotPoint;
	private String description;
	private Date createTime;
	private Date updateTime;

	/**
	 * @return the id
	 */
	public Long getId() {

		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {

		this.id = id;
	}

	/**
	 * @return the hotPoint
	 */
	public Long getHotPoint() {

		return hotPoint;
	}

	/**
	 * @param hotPoint
	 *            the hotPoint to set
	 */
	public void setHotPoint(Long hotPoint) {

		this.hotPoint = hotPoint;
	}

	/**
	 * @return the areaId
	 */
	public Long getAreaId() {

		return areaId;
	}

	/**
	 * @param areaId
	 *            the areaId to set
	 */
	public void setAreaId(Long areaId) {

		this.areaId = areaId;
	}

	/**
	 * @return the name
	 */
	public String getName() {

		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {

		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {

		this.description = description;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {

		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {

		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {

		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {

		this.updateTime = updateTime;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {

		return serialVersionUID;
	}

}
