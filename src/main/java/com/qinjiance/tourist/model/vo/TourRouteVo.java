package com.qinjiance.tourist.model.vo;

import java.util.Date;
import java.util.List;

import module.laohu.commons.model.BaseObject;

import com.qinjiance.tourist.model.po.Destination;
import com.qinjiance.tourist.model.po.Arrival;
import com.qinjiance.tourist.model.po.Departure;
import com.qinjiance.tourist.model.po.Photo;
import com.qinjiance.tourist.model.po.View;

public class TourRouteVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7081816808907682529L;

	private Long id;
	private Destination area;
	private Departure departure;
	private Arrival arrival;
	private List<View> views;
	private String name;
	private Long days;
	private String departureDate;
	private String noDepartureDate;
	private Long price;
	private String extra;
	private String priceDetail;
	private String description;
	private List<Photo> bigPhotoes;
	private List<Photo> middlePhotoes;
	private List<Photo> smallPhotoes;
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
	 * @return the bigPhotoes
	 */
	public List<Photo> getBigPhotoes() {

		return bigPhotoes;
	}

	/**
	 * @param bigPhotoes
	 *            the bigPhotoes to set
	 */
	public void setBigPhotoes(List<Photo> bigPhotoes) {

		this.bigPhotoes = bigPhotoes;
	}

	/**
	 * @return the middlePhotoes
	 */
	public List<Photo> getMiddlePhotoes() {

		return middlePhotoes;
	}

	/**
	 * @param middlePhotoes
	 *            the middlePhotoes to set
	 */
	public void setMiddlePhotoes(List<Photo> middlePhotoes) {

		this.middlePhotoes = middlePhotoes;
	}

	/**
	 * @return the smallPhotoes
	 */
	public List<Photo> getSmallPhotoes() {

		return smallPhotoes;
	}

	/**
	 * @param smallPhotoes
	 *            the smallPhotoes to set
	 */
	public void setSmallPhotoes(List<Photo> smallPhotoes) {

		this.smallPhotoes = smallPhotoes;
	}

	/**
	 * @return the extra
	 */
	public String getExtra() {

		return extra;
	}

	/**
	 * @param extra
	 *            the extra to set
	 */
	public void setExtra(String extra) {

		this.extra = extra;
	}

	/**
	 * @return the priceDetail
	 */
	public String getPriceDetail() {

		return priceDetail;
	}

	/**
	 * @param priceDetail
	 *            the priceDetail to set
	 */
	public void setPriceDetail(String priceDetail) {

		this.priceDetail = priceDetail;
	}

	/**
	 * @return the area
	 */
	public Destination getArea() {

		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(Destination area) {

		this.area = area;
	}

	/**
	 * @return the departure
	 */
	public Departure getDeparture() {

		return departure;
	}

	/**
	 * @param departure
	 *            the departure to set
	 */
	public void setDeparture(Departure departure) {

		this.departure = departure;
	}

	/**
	 * @return the arrival
	 */
	public Arrival getArrival() {

		return arrival;
	}

	/**
	 * @param arrival
	 *            the arrival to set
	 */
	public void setArrival(Arrival arrival) {

		this.arrival = arrival;
	}

	/**
	 * @return the views
	 */
	public List<View> getViews() {

		return views;
	}

	/**
	 * @param views
	 *            the views to set
	 */
	public void setViews(List<View> views) {

		this.views = views;
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
	 * @return the days
	 */
	public Long getDays() {

		return days;
	}

	/**
	 * @param days
	 *            the days to set
	 */
	public void setDays(Long days) {

		this.days = days;
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
	 * @return the noDepartureDate
	 */
	public String getNoDepartureDate() {

		return noDepartureDate;
	}

	/**
	 * @param noDepartureDate
	 *            the noDepartureDate to set
	 */
	public void setNoDepartureDate(String noDepartureDate) {

		this.noDepartureDate = noDepartureDate;
	}

	/**
	 * @return the price
	 */
	public Long getPrice() {

		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Long price) {

		this.price = price;
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
