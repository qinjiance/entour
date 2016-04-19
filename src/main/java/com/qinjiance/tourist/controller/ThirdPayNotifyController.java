package com.qinjiance.tourist.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.qinjiance.tourist.constants.PayType;
import com.qinjiance.tourist.manager.IHotelManager;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年7月28日
 * 
 * @time 下午2:59:32
 * 
 * @desc 接收第三方支付网关通知
 * 
 */
@Controller
@RequestMapping("/thirdPay")
public class ThirdPayNotifyController extends BaseTouristController {

	@Autowired
	private IHotelManager billingHotelManager;

	/**
	 * 酒店，异步通知
	 * 
	 * @return
	 */
	@RequestMapping("/notify/hotel/async/{paytypeId}")
	@ResponseBody
	public String hotelAsyncNotify(@PathVariable Integer paytypeId, HttpServletRequest request) {
		// log记录结果用
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, String> params = null;
		String requestBody = null;
		String result = "failed";
		try {
			PayType paytype = PayType.getEnum(paytypeId);
			if (paytype != null) {
				switch (paytype) {
				case ALIPAY:
					params = getParameterMap(request);
					result = billingHotelManager.handleAlipayAsyncNotify(params);
					break;
				default:
					break;
				}
			}

		} catch (Exception e) {
			logger.error("Exception: ", e);
			resultMap.put(e.getClass().getSimpleName(), e.getMessage());
		}

		// log记录结果用
		return result;
	}

	/**
	 * 酒店，同步通知
	 * 
	 * @param alipayNotifySync
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/notify/hotel/sync/{paytypeId}")
	public String hotelSyncNotify(@PathVariable Integer paytypeId, HttpServletRequest request, ModelMap model) {
		// log记录结果用
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, String> params = getParameterMap(request);
		Map<String, String> result = null;
		try {
			PayType paytype = PayType.getEnum(paytypeId);
			if (paytype != null) {
				switch (paytype) {
				case ALIPAY:
					result = billingHotelManager.handleAlipaySyncNotify(params);
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			logger.error("Exception: ", e);
			resultMap.put(e.getClass().getSimpleName(), e.getMessage());
			return "/error/500";
		}

		if (result == null || result.get("err") != null) {
			return "pay/thirdPayFailed";
		} else {
			return "pay/thirdPaySuss";
		}
	}

	/**
	 * 去请求体字符串
	 * 
	 * @param request
	 * @return
	 */
	protected String getRequestBody(HttpServletRequest request) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			InputStream is = request.getInputStream();
			byte[] buffer = new byte[4096];
			int len = -1;
			while ((len = is.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
		} catch (IOException e) {
			logger.warn("IOException: ", e);
		}
		return os.toString();
	}

	/**
	 * 取请求参数，html escape
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, String> getParameterMap(HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<String, String>();
		Map<String, String[]> paramMap = request.getParameterMap();
		for (String key : paramMap.keySet()) {
			retMap.put(key, HtmlUtils.htmlEscape(request.getParameter(key).trim()));
		}
		return retMap;
	}
}
