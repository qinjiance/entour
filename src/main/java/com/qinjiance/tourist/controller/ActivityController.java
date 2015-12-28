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
import com.qinjiance.tourist.manager.IActivityManager;
import com.qinjiance.tourist.manager.IDestinationManager;
import com.qinjiance.tourist.manager.exception.ManagerException;
import com.qinjiance.tourist.model.vo.ActivityDetVo;
import com.qinjiance.tourist.model.vo.ActivityPreBookVo;
import com.qinjiance.tourist.model.vo.DestinationVo;
import com.qinjiance.tourist.model.vo.SearchActivityResult;

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
public class ActivityController extends BaseTouristController {

	@Autowired
	private IDestinationManager destinationManager;
	@Autowired
	private IActivityManager activityManager;

	/**
	 * @return
	 */
	@RequestMapping(value = "/getActDes")
	@ResponseBody
	public List<DestinationVo> getActDes(@RequestParam String q) {
		List<DestinationVo> desList = destinationManager.getByActDes(q);
		return desList;
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/searchActs")
	public String searchActs(@RequestParam String desLabel, @RequestParam String desValue, @RequestParam Date from,
			@RequestParam Date to, ModelMap model) {

		model.put("desLabel", desLabel);
		model.put("desValue", desValue);
		model.put("from", DateUtils.formatAsString(from, "yyyy-MM-dd"));
		model.put("to", DateUtils.formatAsString(to, "yyyy-MM-dd"));

		return "act/act";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/getActs")
	@ResponseBody
	public ResponseResult<SearchActivityResult> getActs(@RequestParam String desLabel, @RequestParam String desValue,
			@RequestParam Date from, @RequestParam Date to, String searchStars, String searchCategory) {
		ResponseResult<SearchActivityResult> rr = new ResponseResult<SearchActivityResult>();
		rr.setCode(Constants.CODE_FAIL);
		try {
			SearchActivityResult ret = activityManager.searchActivities(desLabel, desValue, from, to, searchStars,
					searchCategory);
			rr.setCode(Constants.CODE_SUCC);
			rr.setResult(ret);
		} catch (ManagerException e) {
			rr.setMessage(e.getMessage());
		}
		return rr;
	}

	@RequestMapping(value = "/actDet")
	public String actDet(@RequestParam Integer activityId, @RequestParam Date from, @RequestParam Date to,
			ModelMap model) {

		model.put("activityId", activityId);
		model.put("from", DateUtils.formatAsString(from, "yyyy-MM-dd"));
		model.put("to", DateUtils.formatAsString(to, "yyyy-MM-dd"));

		try {
			ActivityDetVo ret = activityManager.getActDetail(activityId, from, to);
			model.put("actDet", ret);
		} catch (ManagerException e) {
			logger.error("ManagerException: ", e);
		}

		return "act/actDetail";
	}

	@RequestMapping(value = "/arcPreorder")
	public String arcPreorder(@RequestParam Integer arcId, @RequestParam Integer optionId,
			@RequestParam String optionName, @RequestParam Date date, @RequestParam Integer adult,
			@RequestParam Integer child, @RequestParam Integer unit, ModelMap model) {

		model.put("activityId", arcId);
		model.put("date", DateUtils.formatAsString(date, "yyyy-MM-dd"));
		model.put("optionId", optionId);
		model.put("optionName", optionName);
		model.put("adult", adult);
		model.put("child", child);
		model.put("unit", unit);

		try {
			ActivityPreBookVo ret = activityManager.preBook(arcId, optionId, date, adult, child, unit);
			model.put("preBook", ret);
		} catch (ManagerException e) {
			logger.error("ManagerException: ", e);
		}

		return "act/actBook";
	}
}
