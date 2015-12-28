package com.qinjiance.tourist.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qinjiance.tourist.constants.Constants;
import com.qinjiance.tourist.manager.IDestinationManager;
import com.qinjiance.tourist.manager.IHotelManager;
import com.qinjiance.tourist.manager.exception.ManagerException;
import com.qinjiance.tourist.model.vo.DestinationVo;
import com.qinjiance.tourist.model.vo.HoltelDetPrice;
import com.qinjiance.tourist.model.vo.HoltelDetVo;
import com.qinjiance.tourist.model.vo.SearchHotelResult;

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
public class HotelController extends BaseTouristController {

	@Autowired
	private IDestinationManager destinationManager;
	@Autowired
	private IHotelManager hotelManager;

	/**
	 * @return
	 */
	@RequestMapping(value = "/getDes")
	@ResponseBody
	public List<DestinationVo> getDes(@RequestParam String q) {
		List<DestinationVo> desList = destinationManager.getByDes(q);
		return desList;
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/searchHotels")
	public String searchHotels(@RequestParam String desLabel, @RequestParam String desValue, @RequestParam Date checkIn,
			@RequestParam Date checkOut, @RequestParam String roomInfo, ModelMap model) {

		model.put("desLabel", desLabel);
		model.put("desValue", desValue);
		model.put("checkIn", DateUtils.formatAsString(checkIn, "yyyy-MM-dd"));
		model.put("checkOut", DateUtils.formatAsString(checkOut, "yyyy-MM-dd"));
		model.put("roomInfo", roomInfo);

		return "hotel/hotel";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/getHotels")
	@ResponseBody
	public ResponseResult<SearchHotelResult> getHotels(@RequestParam String desLabel, @RequestParam String desValue,
			@RequestParam Date checkIn, @RequestParam Date checkOut, @RequestParam String roomInfo, Integer searchStars,
			Integer searchPrices, String searchHotelCat) {
		ResponseResult<SearchHotelResult> rr = new ResponseResult<SearchHotelResult>();
		rr.setCode(Constants.CODE_FAIL);
		try {
			SearchHotelResult ret = hotelManager.searchHotels(desLabel, desValue, checkIn, checkOut, roomInfo,
					searchStars, searchPrices, searchHotelCat);
			rr.setCode(Constants.CODE_SUCC);
			rr.setResult(ret);
		} catch (ManagerException e) {
			rr.setMessage(e.getMessage());
		}
		return rr;
	}

	@RequestMapping(value = "/hotelDet")
	public String hotelDet(@RequestParam Integer hotelId, @RequestParam Date checkIn, @RequestParam Date checkOut,
			@RequestParam String roomInfo, ModelMap model) {

		model.put("hotelId", hotelId);
		model.put("checkIn", DateUtils.formatAsString(checkIn, "yyyy-MM-dd"));
		model.put("checkOut", DateUtils.formatAsString(checkOut, "yyyy-MM-dd"));
		model.put("roomInfo", roomInfo);

		try {
			HoltelDetVo ret = hotelManager.getHotelDetail(hotelId, checkIn, checkOut, roomInfo);
			model.put("holtelDet", ret);
		} catch (ManagerException e) {
			logger.error("ManagerException: ", e);
		}

		return "hotel/hotelDetail";
	}

	@RequestMapping(value = "/hotelPreorder")
	public String hotelPreorder(@RequestParam Integer hotelId, @RequestParam Date checkIn, @RequestParam Date checkOut,
			@RequestParam String roomInfo, @RequestParam Integer hotelRoomTypeId, ModelMap model) {

		model.put("hotelId", hotelId);
		model.put("checkIn", DateUtils.formatAsString(checkIn, "yyyy-MM-dd"));
		model.put("checkOut", DateUtils.formatAsString(checkOut, "yyyy-MM-dd"));
		model.put("roomInfo", roomInfo);
		model.put("hotelRoomTypeId", hotelRoomTypeId);

		try {
			HoltelDetPrice ret = hotelManager.checkAvailabilityAndPrice(hotelId, checkIn, checkOut, roomInfo,
					hotelRoomTypeId);
			model.put("holtelDetPrice", ret);
		} catch (ManagerException e) {
			logger.error("ManagerException: ", e);
		}

		return "hotel/hotelBook";
	}
}
