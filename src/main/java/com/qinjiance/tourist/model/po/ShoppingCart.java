package com.qinjiance.tourist.model.po;

import java.util.Date;

import module.laohu.commons.model.BaseObject;

import org.apache.ibatis.type.Alias;

@Alias("shoppingCart")
public class ShoppingCart extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7081816808907682529L;

	private Long id;
	private Long areaId;
	private Long userId;
	private Long personNumber;
	private String departureDate;
	private String routeName;
	private Long routeId;
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
	 * @return the personNumber
	 */
	public Long getPersonNumber() {

		return personNumber;
	}

	/**
	 * @param personNumber
	 *            the personNumber to set
	 */
	public void setPersonNumber(Long personNumber) {

		this.personNumber = personNumber;
	}

	/**
	 * @return the departureDate
	 */
	public String getDepartureDate() {

		return departureDate;
	}

	/**
	 * @param departureDate
	 *            the departureDate to set
	 */
	public void setDepartureDate(String departureDate) {

		this.departureDate = departureDate;
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
	 * @return the userId
	 */
	public Long getUserId() {

		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {

		this.userId = userId;
	}

	/**
	 * @return the routeName
	 */
	public String getRouteName() {

		return routeName;
	}

	/**
	 * @param routeName
	 *            the routeName to set
	 */
	public void setRouteName(String routeName) {

		this.routeName = routeName;
	}

	/**
	 * @return the routeId
	 */
	public Long getRouteId() {

		return routeId;
	}

	/**
	 * @param routeId
	 *            the routeId to set
	 */
	public void setRouteId(Long routeId) {

		this.routeId = routeId;
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
