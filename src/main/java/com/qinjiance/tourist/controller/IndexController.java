package com.qinjiance.tourist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qinjiance.tourist.annotation.SkipWhenUserLogin;

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
public class IndexController extends BaseTouristController {

	/**
	 * @return
	 */
	@RequestMapping(value = { "/", "/index" })
	public String index(ModelMap model) {

		return "index";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/car" })
	public String car(ModelMap model) {

		return "carIndex";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/activity" })
	public String activity(ModelMap model) {

		return "activityIndex";
	}
	
	/**
	 * @return
	 */
	@SkipWhenUserLogin
	@RequestMapping(value = "/regPage")
	public String regPage(String email, ModelMap model) {

		model.put("email", email);
		return "regPage";
	}

	/**
	 * @return
	 */
	@SkipWhenUserLogin
	@RequestMapping(value = "/forgetPage")
	public String forgetPage() {

		return "forgetPage";
	}

	/**
	 * @return
	 */
	@SkipWhenUserLogin
	@RequestMapping(value = "/loginPage")
	public String loginPage(String email, ModelMap model) {

		model.put("email", email);
		return "loginPage";
	}
}
