package com.qinjiance.tourist.model.po;

import java.util.Date;

import module.laohu.commons.model.BaseObject;

import org.apache.ibatis.type.Alias;

@Alias("tourRoute")
public class TourRoute extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7081816808907682529L;

	private Long id;
	private Long areaId;
	private Long departureId;
	private Long arrivalId;
	private Long discount;
	private Long recommend;
	private Long saleNumber;
	private Long index;
	private String name;
	private Long days;
	private String departureDate;
	private Long price;
	private String extra;
	private String priceDetail;
	private String description;
	private Date createTime;
	private Date updateTime;

	/**
	 * @return the index
	 */
	public Long getIndex() {
	
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(Long index) {
	
		this.index = index;
	}

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
	 * @return the discount
	 */
	public Long getDiscount() {

		return discount;
	}

	/**
	 * @param discount
	 *            the discount to set
	 */
	public void setDiscount(Long discount) {

		this.discount = discount;
	}

	/**
	 * @return the recommend
	 */
	public Long getRecommend() {

		return recommend;
	}

	/**
	 * @param recommend
	 *            the recommend to set
	 */
	public void setRecommend(Long recommend) {

		this.recommend = recommend;
	}

	/**
	 * @return the saleNumber
	 */
	public Long getSaleNumber() {

		return saleNumber;
	}

	/**
	 * @param saleNumber
	 *            the saleNumber to set
	 */
	public void setSaleNumber(Long saleNumber) {

		this.saleNumber = saleNumber;
	}

	/**
	 * @return the arrivalId
	 */
	public Long getArrivalId() {

		return arrivalId;
	}

	/**
	 * @param arrivalId
	 *            the arrivalId to set
	 */
	public void setArrivalId(Long arrivalId) {

		this.arrivalId = arrivalId;
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
	 * @return the departureId
	 */
	public Long getDepartureId() {

		return departureId;
	}

	/**
	 * @param departureId
	 *            the departureId to set
	 */
	public void setDepartureId(Long departureId) {

		this.departureId = departureId;
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
