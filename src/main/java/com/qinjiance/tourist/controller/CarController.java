package com.qinjiance.tourist.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qinjiance.tourist.constants.Constants;
import com.qinjiance.tourist.manager.ICarManager;
import com.qinjiance.tourist.manager.IDestinationManager;
import com.qinjiance.tourist.manager.exception.ManagerException;
import com.qinjiance.tourist.model.vo.CarDestinationVo;
import com.qinjiance.tourist.model.vo.CarDetVo;
import com.qinjiance.tourist.model.vo.SearchCarResult;

import module.laohu.commons.model.ResponseResult;
import module.laohu.commons.util.DateUtils;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年7月5日
 * 
 * @time 下午4:55:00
 * 
 * @desc
 * 
 */
@Controller
public class CarController extends BaseTouristController {

	@Autowired
	private IDestinationManager destinationManager;
	@Autowired
	private ICarManager carManager;

	/**
	 * @return
	 */
	@RequestMapping(value = "/getCarDes")
	@ResponseBody
	public List<CarDestinationVo> getDes(@RequestParam String q) {
		List<CarDestinationVo> desList = destinationManager.getByCarDes(q);
		return desList;
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/searchCars")
	public String searchCars(@RequestParam String pickUpLabel, @RequestParam String pickUpValue,
			@RequestParam String dropOffLabel, @RequestParam String dropOffValue, @RequestParam Date pickUpDate,
			@RequestParam Date dropOffDate, @RequestParam Integer pickUpHour, @RequestParam Integer dropOffHour,
			@RequestParam Integer driverAge, ModelMap model) {

		if (pickUpValue.split(",").length == 1) {
			dropOffLabel = pickUpLabel;
			dropOffValue = pickUpValue;
		} else {
			if (StringUtils.isBlank(dropOffValue) || dropOffValue.split(",").length == 1) {
				dropOffLabel = pickUpLabel;
				dropOffValue = pickUpValue;
			}
		}

		model.put("pickUpStation", pickUpLabel.split(",")[0]);
		model.put("pickUpLabel", pickUpLabel);
		model.put("pickUpValue", pickUpValue);
		model.put("dropOffStation", dropOffLabel.split(",")[0]);
		model.put("dropOffLabel", dropOffLabel);
		model.put("dropOffValue", dropOffValue);
		model.put("pickUpDate", DateUtils.formatAsString(pickUpDate, "yyyy-MM-dd"));
		model.put("dropOffDate", DateUtils.formatAsString(dropOffDate, "yyyy-MM-dd"));
		model.put("pickUpHour", pickUpHour);
		model.put("dropOffHour", dropOffHour);
		model.put("driverAge", driverAge);

		return "car/car";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/getCars")
	@ResponseBody
	public ResponseResult<SearchCarResult> getCars(@RequestParam String pickUpValue, String dropOffValue,
			@RequestParam Date pickUpDate, @RequestParam Date dropOffDate, @RequestParam Integer pickUpHour,
			@RequestParam Integer dropOffHour, @RequestParam Integer driverAge, String searchType, String searchClass,
			String searchCompany) {
		ResponseResult<SearchCarResult> rr = new ResponseResult<SearchCarResult>();
		rr.setCode(Constants.CODE_FAIL);
		try {
			SearchCarResult ret = carManager.searchCars(pickUpValue, dropOffValue, pickUpDate, dropOffDate, pickUpHour,
					dropOffHour, driverAge, searchType, searchClass, searchCompany);
			rr.setCode(Constants.CODE_SUCC);
			rr.setResult(ret);
		} catch (ManagerException e) {
			rr.setMessage(e.getMessage());
		}
		return rr;
	}

	@RequestMapping(value = "/carDet")
	public String carDet(@RequestParam String productId, @RequestParam Integer pickUpStationId,
			@RequestParam Integer dropOffStationId, @RequestParam Date pickUpDate, @RequestParam Date dropOffDate,
			@RequestParam Integer pickUpHour, @RequestParam Integer dropOffHour, @RequestParam Integer driverAge,
			ModelMap model) {

		model.put("productId", productId);
		model.put("pickUpStationId", pickUpStationId);
		model.put("dropOffStationId", dropOffStationId);
		model.put("pickUpDate", DateUtils.formatAsString(pickUpDate, "yyyy-MM-dd"));
		model.put("dropOffDate", DateUtils.formatAsString(dropOffDate, "yyyy-MM-dd"));
		model.put("pickUpHour", pickUpHour);
		model.put("dropOffHour", dropOffHour);
		model.put("driverAge", driverAge);

		try {
			CarDetVo ret = carManager.getDet(productId, pickUpStationId, dropOffStationId);
			model.put("carDet", ret);
		} catch (ManagerException e) {
			logger.error("ManagerException: ", e);
		}

		return "car/carBook";
	}
}
