package com.qinjiance.tourist.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import module.laohu.commons.model.ResponseResult;
import module.laohu.commons.util.DateUtils;
import module.laohu.commons.util.JsonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qinjiance.tourist.annotation.NeedCookie;
import com.qinjiance.tourist.constants.Constants;
import com.qinjiance.tourist.constants.Currency;
import com.qinjiance.tourist.manager.IDestinationManager;
import com.qinjiance.tourist.manager.IHotelManager;
import com.qinjiance.tourist.manager.exception.ManagerException;
import com.qinjiance.tourist.model.po.BillingHotel;
import com.qinjiance.tourist.model.vo.BookRoomInfo;
import com.qinjiance.tourist.model.vo.DestinationVo;
import com.qinjiance.tourist.model.vo.HoltelDetPrice;
import com.qinjiance.tourist.model.vo.HoltelDetVo;
import com.qinjiance.tourist.model.vo.SearchHotelResult;
import com.qinjiance.tourist.util.CookieUtil;

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
	public String searchHotels(@RequestParam String desLabel, @RequestParam String desValue,
			@RequestParam Date checkIn, @RequestParam Date checkOut, @RequestParam String roomInfo, ModelMap model) {

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
	@NeedCookie
	@RequestMapping(value = "/hotelOrder")
	public String hotelOrder(ModelMap model) {
		try {
			List<BillingHotel> ret = hotelManager.getBillingHotels(CookieUtil.getUserIdFromCookie());
			model.put("holtels", ret);
		} catch (ManagerException e) {
			logger.error("ManagerException: ", e);
		}

		return "user/hotelOrder";
	}

	/**
	 * @return
	 */
	@NeedCookie
	@RequestMapping(value = "/hotelOrderDet")
	public String hotelOrderDet(@RequestParam Long orderId, ModelMap model) {
		try {
			BillingHotel ret = hotelManager.getBillingHotel(CookieUtil.getUserIdFromCookie(), orderId);
			if (ret != null) {
				List<BookRoomInfo> bookRoomInfos = JsonUtils.parseToList(ret.getRoomInfos(), BookRoomInfo.class);
				model.put("rooms", bookRoomInfos);

				Currency origCurrency = Currency.getEnum(ret.getCurrency());
				Currency payCurrency = Currency.getEnum(ret.getPayCurrency());
				model.put("origCurrency", origCurrency);
				model.put("payCurrency", payCurrency);
			}
			model.put("holtel", ret);
		} catch (ManagerException e) {
			logger.error("ManagerException: ", e);
		}

		return "hotel/hotelBill";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/getHotels")
	@ResponseBody
	public ResponseResult<SearchHotelResult> getHotels(@RequestParam String desLabel, @RequestParam String desValue,
			@RequestParam Date checkIn, @RequestParam Date checkOut, @RequestParam String roomInfo,
			Integer searchStars, Integer searchPrices, String searchHotelCat) {
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

	@NeedCookie
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

	/**
	 * @return
	 */
	@NeedCookie
	@RequestMapping(value = "/hotelPrepay")
	@ResponseBody
	public ResponseResult<Map<String, String>> hotelPrepay(Long orderId, @RequestParam Integer hotelId,
			@RequestParam Date checkIn, @RequestParam Date checkOut, @RequestParam String hotelBookRoomInfosStr,
			@RequestParam String roomInfo, @RequestParam Integer hotelRoomTypeId, @RequestParam String confirmEmail,
			@RequestParam Integer payTypeId, @RequestParam Long totalDaofu, @RequestParam Long totalYufu,
			@RequestParam String bookCurrency) {
		ResponseResult<Map<String, String>> rr = new ResponseResult<Map<String, String>>();
		rr.setCode(Constants.CODE_FAIL);
		try {
			Map<String, String> ret = hotelManager.prePay(orderId, hotelId, checkIn, checkOut, hotelBookRoomInfosStr,
					roomInfo, hotelRoomTypeId, confirmEmail, payTypeId, totalDaofu, totalYufu,
					CookieUtil.getUserIdFromCookie(), bookCurrency);
			rr.setCode(Constants.CODE_SUCC);
			rr.setResult(ret);
		} catch (ManagerException e) {
			rr.setMessage(e.getMessage());
		}
		return rr;
	}

	/**
	 * 查询我的单个订单状态
	 */
	@NeedCookie
	@RequestMapping(value = "/queryCurrUserOrderStatus")
	@ResponseBody
	public ResponseResult<Map<String, String>> queryCurrUserOrderStatus(@RequestParam Long orderId) {
		ResponseResult<Map<String, String>> rr = new ResponseResult<Map<String, String>>();
		rr.setCode(Constants.CODE_FAIL);
		try {
			Map<String, String> result = hotelManager.queryUserOrderStatus(CookieUtil.getUserIdFromCookie(), orderId);
			if (result != null && !result.isEmpty()) {
				rr.setCode(Constants.CODE_SUCC);
				rr.setResult(result);
			}
		} catch (ManagerException e) {
			rr.setMessage(e.getMessage());
		}
		return rr;
	}
}
