/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年10月23日 上午12:00:47
 *
 * @desc
 */
public class CarVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7562552140434583280L;

	private String carCompanyName;
	private String productId;
	private String carName;
	private String carClass;
	private String transmission;
	private boolean ac;
	private String carType;
	private Integer maxPassengers;
	private Integer doors;
	private Integer luggageLarge;
	private Integer luggageSmall;
	private String currency;
	private String minPrice;
	private Integer pickUpStationId;
	private String pickUpStation;
	private Integer dropOffStationId;
	private String dropOffStation;
	private String thumb;

	/**
	 * 
	 */
	public CarVo() {

	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public Integer getPickUpStationId() {
		return pickUpStationId;
	}

	public void setPickUpStationId(Integer pickUpStationId) {
		this.pickUpStationId = pickUpStationId;
	}

	public String getPickUpStation() {
		return pickUpStation;
	}

	public void setPickUpStation(String pickUpStation) {
		this.pickUpStation = pickUpStation;
	}

	public Integer getDropOffStationId() {
		return dropOffStationId;
	}

	public void setDropOffStationId(Integer dropOffStationId) {
		this.dropOffStationId = dropOffStationId;
	}

	public String getDropOffStation() {
		return dropOffStation;
	}

	public void setDropOffStation(String dropOffStation) {
		this.dropOffStation = dropOffStation;
	}

	public String getCarCompanyName() {
		return carCompanyName;
	}

	public void setCarCompanyName(String carCompanyName) {
		this.carCompanyName = carCompanyName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarClass() {
		return carClass;
	}

	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public boolean isAc() {
		return ac;
	}

	public void setAc(boolean ac) {
		this.ac = ac;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Integer getMaxPassengers() {
		return maxPassengers;
	}

	public void setMaxPassengers(Integer maxPassengers) {
		this.maxPassengers = maxPassengers;
	}

	public Integer getDoors() {
		return doors;
	}

	public void setDoors(Integer doors) {
		this.doors = doors;
	}

	public Integer getLuggageLarge() {
		return luggageLarge;
	}

	public void setLuggageLarge(Integer luggageLarge) {
		this.luggageLarge = luggageLarge;
	}

	public Integer getLuggageSmall() {
		return luggageSmall;
	}

	public void setLuggageSmall(Integer luggageSmall) {
		this.luggageSmall = luggageSmall;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
