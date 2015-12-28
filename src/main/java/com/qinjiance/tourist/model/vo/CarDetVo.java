/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import java.util.List;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年10月23日 上午12:00:47
 *
 * @desc
 */
public class CarDetVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7562552140434583280L;

	private String carCompanyLogoUrl;
	private String carCompanyName;
	private String carName;
	private String transmission;
	private boolean ac;
	private String carType;
	private Integer maxPassengers;
	private Integer doors;
	private Integer luggageLarge;
	private Integer luggageSmall;
	private String currency;
	private String thumb;
	private CarStationVo pickUpStation;
	private CarStationVo dropOffStation;
	private List<CarProgramVo> carProgramVos;
	private List<CarRuleVo> carRuleVos;

	/**
	 * 
	 */
	public CarDetVo() {

	}

	public String getCarCompanyLogoUrl() {
		return carCompanyLogoUrl;
	}

	public void setCarCompanyLogoUrl(String carCompanyLogoUrl) {
		this.carCompanyLogoUrl = carCompanyLogoUrl;
	}

	public String getCarCompanyName() {
		return carCompanyName;
	}

	public void setCarCompanyName(String carCompanyName) {
		this.carCompanyName = carCompanyName;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
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

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public CarStationVo getPickUpStation() {
		return pickUpStation;
	}

	public void setPickUpStation(CarStationVo pickUpStation) {
		this.pickUpStation = pickUpStation;
	}

	public CarStationVo getDropOffStation() {
		return dropOffStation;
	}

	public void setDropOffStation(CarStationVo dropOffStation) {
		this.dropOffStation = dropOffStation;
	}

	public List<CarProgramVo> getCarProgramVos() {
		return carProgramVos;
	}

	public void setCarProgramVos(List<CarProgramVo> carProgramVos) {
		this.carProgramVos = carProgramVos;
	}

	public List<CarRuleVo> getCarRuleVos() {
		return carRuleVos;
	}

	public void setCarRuleVos(List<CarRuleVo> carRuleVos) {
		this.carRuleVos = carRuleVos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
