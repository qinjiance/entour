/**
 * 
 */
package com.qinjiance.tourist.manager.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qinjiance.tourist.constants.Currency;
import com.qinjiance.tourist.manager.ICarManager;
import com.qinjiance.tourist.manager.exception.ManagerException;
import com.qinjiance.tourist.model.vo.CarDetVo;
import com.qinjiance.tourist.model.vo.CarProgramVo;
import com.qinjiance.tourist.model.vo.CarRuleVo;
import com.qinjiance.tourist.model.vo.CarStationVo;
import com.qinjiance.tourist.model.vo.CarVo;
import com.qinjiance.tourist.model.vo.SearchCarResult;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.BaseCarStation;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.Car;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.CarProgram;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.CarStation;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.CompanyRules;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.Component;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.GetRulesAndRestrictionsRequest;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.Route;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.Rule;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.SearchCarInfo;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.SearchCarsRequest;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.SelectStationsRequest;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.StationsPair;
import com.qinjiance.tourist.support.tourico.client.TouricoWSClient;

/**
 * @author Administrator
 *
 * @datetime 2015年12月8日 上午12:28:40
 *
 * @desc
 */
@Service
public class CarManager implements ICarManager {

	protected static final Logger logger = LoggerFactory.getLogger(CarManager.class);

	@Autowired
	private TouricoWSClient touricoWSClient;

	/**
	 * 
	 */
	public CarManager() {
	}

	@Override
	public SearchCarResult searchCars(String pickUp, String dropOff, Date pickUpDate, Date dropOffDate,
			Integer pickUpHour, Integer dropOffHour, Integer driverAge, String searchType, String searchClass,
			String searchCompany) throws ManagerException {
		Calendar c = Calendar.getInstance();
		c.setTime(pickUpDate);
		c.set(Calendar.HOUR_OF_DAY, pickUpHour);
		c.add(Calendar.HOUR_OF_DAY, 2);

		Calendar c1 = Calendar.getInstance();
		c1.setTime(dropOffDate);
		c1.set(Calendar.HOUR_OF_DAY, dropOffHour);
		if (c.getTime().after(c1.getTime())) {
			throw new ManagerException("还车时间必须晚于取车时间2个小时");
		}
		boolean isAirCode = true;
		if (pickUp.split(",").length == 1) {
			isAirCode = false;
		}
		SearchCarsRequest param = new SearchCarsRequest();
		param.setCarCompany(0);
		param.setDriverAge(driverAge);
		param.setDropOffDate(dropOffDate);
		param.setDropOffHour(dropOffHour);
		param.setPickUpDate(pickUpDate);
		param.setPickUpHour(pickUpHour);
		param.setTotalPax(0);
		param.setVehicleType(0);
		Route r = new Route();
		Car[] cars = null;
		SearchCarInfo[] carInfos = null;
		if (isAirCode) {
			r.setPickUp(pickUp.split(",")[1]);
			if (StringUtils.isNotBlank(dropOff) && dropOff.split(",").length == 2) {
				r.setDropOff(dropOff.split(",")[1]);
			}
			param.setRoute(r);
			carInfos = touricoWSClient.searchCarsByAirportCode(param);
		} else {
			r.setPickUp(pickUp.split(",")[0]);
			param.setRoute(r);
			cars = touricoWSClient.searchCars(param);
		}
		SearchCarResult result = new SearchCarResult();
		List<Integer> typesCata = new ArrayList<Integer>(4);
		List<Integer> catalogCata = new ArrayList<Integer>(5);
		Map<String, Integer> companyCata = new HashMap<String, Integer>();
		for (int i = 0; i < 7; i++) {
			if (i < 4) {
				typesCata.add(0);
			}
			catalogCata.add(0);
		}
		List<CarVo> carVos = new ArrayList<CarVo>();
		if ((cars != null && cars.length > 0) || (carInfos != null && carInfos.length > 0)) {
			CarVo carVo = null;
			Currency cry = null;
			String carType = null;
			String carClass = null;
			String carCompany = null;
			Integer companyCount = null;
			BaseCarStation pickUpStation = null;
			CarStation pickUpStation1 = null;
			BaseCarStation dropOffStation = null;
			if (isAirCode) {
				for (SearchCarInfo car : carInfos) {
					if (!car.getStatus().equalsIgnoreCase("Available")) {
						continue;
					}
					if (StringUtils.isNotBlank(searchType) && StringUtils.isNotBlank(car.getType())
							&& !car.getType().equalsIgnoreCase(searchType)) {
						continue;
					}
					if (StringUtils.isNotBlank(searchClass) && StringUtils.isNotBlank(car.get_class())
							&& !car.get_class().equalsIgnoreCase(searchClass)) {
						continue;
					}
					if (StringUtils.isNotBlank(searchCompany) && StringUtils.isNotBlank(car.getCarCompanyName())
							&& !car.getCarCompanyName().equalsIgnoreCase(searchCompany)) {
						continue;
					}
					carVo = new CarVo();
					carVo.setAc(car.getAc());
					carVo.setCarClass(car.get_class());
					cry = Currency.getEnum(car.getCurrency());
					if (cry == null) {
						carVo.setCurrency(car.getCurrency());
					} else {
						carVo.setCurrency(cry.getSymbol());
					}
					carVo.setCarCompanyName(car.getCarCompanyName());
					carVo.setCarName(car.getCarName());
					carVo.setThumb(car.getCarThumb());
					carVo.setCarType(car.getType());
					carVo.setDoors(car.getDoors());
					carVo.setLuggageLarge(car.getLuggageLarge());
					carVo.setLuggageSmall(car.getLuggageSmall());
					carVo.setMaxPassengers(car.getMaxPassengers());
					carVo.setMinPrice(String.valueOf(car.getMinPrice()));
					carVo.setProductId(car.getProductId());
					carVo.setTransmission(car.getTransmission());
					carType = car.getType();
					if (StringUtils.isNotBlank(carType)) {
						if (carType.equalsIgnoreCase("Car")) {
							typesCata.set(3, typesCata.get(3) + 1);
						} else if (carType.equalsIgnoreCase("SUV")) {
							typesCata.set(2, typesCata.get(2) + 1);
						} else if (carType.equalsIgnoreCase("Van")) {
							typesCata.set(1, typesCata.get(1) + 1);
						} else {
							typesCata.set(0, typesCata.get(0) + 1);
						}
					}
					carClass = car.get_class();
					if (StringUtils.isNotBlank(carClass)) {
						if (carClass.equalsIgnoreCase("Mini")) {
							catalogCata.set(6, catalogCata.get(6) + 1);
						} else if (carClass.equalsIgnoreCase("Compact")) {
							catalogCata.set(5, catalogCata.get(5) + 1);
						} else if (carClass.equalsIgnoreCase("Economy")) {
							catalogCata.set(4, catalogCata.get(4) + 1);
						} else if (carClass.equalsIgnoreCase("Standard")) {
							catalogCata.set(3, catalogCata.get(3) + 1);
						} else if (carClass.equalsIgnoreCase("Full Size")) {
							catalogCata.set(2, catalogCata.get(2) + 1);
						} else if (carClass.equalsIgnoreCase("Luxury")) {
							catalogCata.set(1, catalogCata.get(1) + 1);
						} else if (carClass.equalsIgnoreCase("Premium")) {
							catalogCata.set(0, catalogCata.get(0) + 1);
						}
					}
					carCompany = car.getCarCompanyName();
					if (StringUtils.isNotBlank(carCompany)) {
						companyCount = companyCata.get(carCompany);
						if (companyCount == null) {
							companyCount = 0;
						}
						companyCata.put(carCompany, companyCount + 1);
					}
					pickUpStation = car.getRouteOptions().getRouteOption()[0].getRouteStations().getPickUpStation();
					carVo.setPickUpStation(pickUpStation.getName());
					carVo.setPickUpStationId(pickUpStation.getId());
					dropOffStation = car.getRouteOptions().getRouteOption()[0].getRouteStations().getDropOffStation();
					carVo.setDropOffStation(dropOffStation.getName());
					carVo.setDropOffStationId(dropOffStation.getId());
					carVos.add(carVo);
				}
			} else {
				for (Car car : cars) {
					if (!car.getStatus().equalsIgnoreCase("Available")) {
						continue;
					}
					if (StringUtils.isNotBlank(searchType) && StringUtils.isNotBlank(car.getType())
							&& !car.getType().equalsIgnoreCase(searchType)) {
						continue;
					}
					if (StringUtils.isNotBlank(searchClass) && StringUtils.isNotBlank(car.get_class())
							&& !car.get_class().equalsIgnoreCase(searchClass)) {
						continue;
					}
					if (StringUtils.isNotBlank(searchCompany) && StringUtils.isNotBlank(car.getCarCompanyName())
							&& !car.getCarCompanyName().equalsIgnoreCase(searchCompany)) {
						continue;
					}
					carVo = new CarVo();
					carVo.setAc(car.getAc());
					carVo.setCarClass(car.get_class());
					cry = Currency.getEnum(car.getCurrency());
					if (cry == null) {
						carVo.setCurrency(car.getCurrency());
					} else {
						carVo.setCurrency(cry.getSymbol());
					}
					carVo.setCarCompanyName(car.getCarCompanyName());
					carVo.setCarName(car.getCarName());
					carVo.setCarType(car.getType());
					carVo.setDoors(car.getDoors());
					carVo.setLuggageLarge(car.getLuggageLarge());
					carVo.setLuggageSmall(car.getLuggageSmall());
					carVo.setMaxPassengers(car.getMaxPassengers());
					carVo.setMinPrice(String.valueOf(car.getMinPrice()));
					carVo.setProductId(car.getProductId());
					carVo.setThumb(car.getCarThumb());
					carVo.setTransmission(car.getTransmission());
					carType = car.getType();
					if (StringUtils.isNotBlank(carType)) {
						if (carType.equalsIgnoreCase("Car")) {
							typesCata.set(3, typesCata.get(3) + 1);
						} else if (carType.equalsIgnoreCase("SUV")) {
							typesCata.set(2, typesCata.get(2) + 1);
						} else if (carType.equalsIgnoreCase("Van")) {
							typesCata.set(1, typesCata.get(1) + 1);
						} else {
							typesCata.set(0, typesCata.get(0) + 1);
						}
					}
					carClass = car.get_class();
					if (StringUtils.isNotBlank(carClass)) {
						if (carClass.equalsIgnoreCase("Mini")) {
							catalogCata.set(6, catalogCata.get(6) + 1);
						} else if (carClass.equalsIgnoreCase("Compact")) {
							catalogCata.set(5, catalogCata.get(5) + 1);
						} else if (carClass.equalsIgnoreCase("Economy")) {
							catalogCata.set(4, catalogCata.get(4) + 1);
						} else if (carClass.equalsIgnoreCase("Standard")) {
							catalogCata.set(3, catalogCata.get(3) + 1);
						} else if (carClass.equalsIgnoreCase("Full Size")) {
							catalogCata.set(2, catalogCata.get(2) + 1);
						} else if (carClass.equalsIgnoreCase("Luxury")) {
							catalogCata.set(1, catalogCata.get(1) + 1);
						} else if (carClass.equalsIgnoreCase("Premium")) {
							catalogCata.set(0, catalogCata.get(0) + 1);
						}
					}
					carCompany = car.getCarCompanyName();
					if (StringUtils.isNotBlank(carCompany)) {
						companyCount = companyCata.get(carCompany);
						if (companyCount == null) {
							companyCount = 0;
						}
						companyCata.put(carCompany, companyCount + 1);
					}
					pickUpStation1 = car.getRouteStations().getStations().getCarStation()[0];
					carVo.setPickUpStation(pickUpStation1.getName());
					carVo.setPickUpStationId(pickUpStation1.getId());
					carVo.setDropOffStation(pickUpStation1.getName());
					carVo.setDropOffStationId(pickUpStation1.getId());
					carVos.add(carVo);
				}
			}
		}
		result.setCarVos(carVos);
		result.setCatalogCata(catalogCata);
		result.setCompanyCata(companyCata);
		result.setTypesCata(typesCata);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qinjiance.tourist.manager.ICarManager#getDet(java.lang.String,
	 * java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public CarDetVo getDet(String productId, Integer pickUpStationId, Integer dropOffStationId)
			throws ManagerException {
		if (StringUtils.isBlank(productId)) {
			throw new ManagerException("未选择车型，请选择");
		}
		if (pickUpStationId == null) {
			throw new ManagerException("未取车地点，请返回酒店");
		}
		if (dropOffStationId == null) {
			throw new ManagerException("未还车地点，请返回酒店");
		}
		SelectStationsRequest param = new SelectStationsRequest();
		param.setProductId(productId);
		StationsPair r = new StationsPair();
		r.setStationId(pickUpStationId);
		r.setDropOffStationId(dropOffStationId);
		param.setStations(r);
		Car car = touricoWSClient.selectStation(param);
		if (car == null || !car.getStatus().equalsIgnoreCase("Available")) {
			throw new ManagerException("该车型信息有变化，请重新查询");
		}
		CarDetVo result = new CarDetVo();
		result.setAc(car.getAc());
		result.setCarCompanyName(car.getCarCompanyName());
		result.setCarName(car.getCarName());
		result.setCarType(car.getType());
		Currency cry = Currency.getEnum(car.getCurrency());
		if (cry == null) {
			result.setCurrency(car.getCurrency());
		} else {
			result.setCurrency(cry.getSymbol());
		}
		result.setDoors(car.getDoors());
		result.setLuggageLarge(car.getLuggageLarge());
		result.setLuggageSmall(car.getLuggageSmall());
		result.setMaxPassengers(car.getMaxPassengers());
		result.setThumb(car.getCarThumb());
		result.setTransmission(car.getTransmission());
		CarStation pkst = car.getRouteStations().getStations().getCarStation()[0];
		CarStationVo pickUpStation = new CarStationVo();
		pickUpStation.setAddress(pkst.getAddress());
		pickUpStation.setId(pkst.getId());
		pickUpStation.setName(pkst.getName());
		pickUpStation.setOpHoursPickUp(pkst.getOperationHoursPickUp());
		pickUpStation.setOpHoursDropOff(pkst.getOperationHoursDropOff());
		result.setPickUpStation(pickUpStation);
		CarStation dfst = pkst;
		if (car.getRouteStations().getDropOffStations() != null) {
			dfst = car.getRouteStations().getDropOffStations().getCarStation()[0];
		}
		CarStationVo dropOffStation = new CarStationVo();
		dropOffStation.setAddress(dfst.getAddress());
		dropOffStation.setId(dfst.getId());
		dropOffStation.setName(dfst.getName());
		dropOffStation.setOpHoursPickUp(dfst.getOperationHoursPickUp());
		dropOffStation.setOpHoursDropOff(dfst.getOperationHoursDropOff());
		result.setDropOffStation(dropOffStation);
		List<CarProgramVo> carProgramVos = new ArrayList<CarProgramVo>();
		CarProgramVo carProgramVo = null;
		int i = 0;
		for (CarProgram carProgram : car.getProgramList().getCarProgram()) {
			i++;
			carProgramVo = new CarProgramVo();
			carProgramVo.setId(carProgram.getId());
			carProgramVo.setName("套餐" + i);
			if (StringUtils.isNotBlank(carProgram.getName())) {
				carProgramVo.setName(carProgram.getName());
			}
			carProgramVo.setPrice(String.valueOf(carProgram.getPrice()));
			carProgramVo.setTax(String.valueOf(carProgram.getTax()));
			carProgramVo.setMandatoryFees("0");
			if (carProgram.getMandatoryFeesDueAtPickup() != null) {
				carProgramVo.setMandatoryFees(carProgram.getMandatoryFeesDueAtPickup().getPrice().toString());
			}
			Map<String, String> componentMap = new HashMap<String, String>();
			for (Component component : carProgram.getProgramIncludes().getComponent()) {
				componentMap.put(component.getName(), component.getDesc());
			}
			carProgramVo.setComponentMap(componentMap);
			carProgramVos.add(carProgramVo);
		}
		result.setCarProgramVos(carProgramVos);

		GetRulesAndRestrictionsRequest request = new GetRulesAndRestrictionsRequest();
		request.setCarCompanyId(car.getCarCompanyId());
		CompanyRules companyRules = touricoWSClient.getRulesAndRestric(request);
		if (companyRules != null) {
			result.setCarCompanyLogoUrl(companyRules.getCarCompanyLogoUrl());
			List<CarRuleVo> carRuleVos = new ArrayList<CarRuleVo>();
			CarRuleVo carRuleVo = null;
			for (Rule rule : companyRules.getRule()) {
				carRuleVo = new CarRuleVo();
				carRuleVo.setTitle(rule.getTitle());
				carRuleVo.setRule(rule.getString());
				carRuleVos.add(carRuleVo);
			}
			result.setCarRuleVos(carRuleVos);
		}
		return result;
	}

}
