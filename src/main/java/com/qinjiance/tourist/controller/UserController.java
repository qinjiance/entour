package com.qinjiance.tourist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qinjiance.tourist.annotation.NeedCookie;
import com.qinjiance.tourist.constants.Constants;
import com.qinjiance.tourist.manager.IUserManager;
import com.qinjiance.tourist.manager.exception.ManagerException;
import com.qinjiance.tourist.model.po.FavoriteRoute;
import com.qinjiance.tourist.model.po.ShoppingCart;
import com.qinjiance.tourist.model.po.User;
import com.qinjiance.tourist.model.vo.RegisterForm;
import com.qinjiance.tourist.util.CheckStyleUtil;
import com.qinjiance.tourist.util.CookieUtil;

import module.laohu.commons.model.ResponseCode;
import module.laohu.commons.model.ResponseResult;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年7月3日
 * 
 * @time 下午5:21:51
 * 
 * @desc
 * 
 */
@Controller
public class UserController extends BaseTouristController {

	@Autowired
	private IUserManager userManager;

	/**
	 * 注册.
	 * 
	 * @param registerForm
	 * @param validResult
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Object> registerUser(@Valid RegisterForm registerForm, BindingResult validResult,
			HttpServletResponse response) {

		ResponseResult<Object> rr = new ResponseResult<Object>();
		rr.setCode(Constants.CODE_FAIL);
		Map<String, String> errors = new HashMap<String, String>();
		// 验证参数
		if (validResult.hasErrors()) {
			for (FieldError error : validResult.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			rr.setCode(Constants.CODE_VALIDATE_FAILED);
			rr.setMessage("参数验证未通过");
			rr.setResult(errors);
		} else {
			// 进一步验证参数
			try {
				if (userManager.checkEmailExists(registerForm.getEmail())) {
					errors.put("email", "邮箱已被注册");
				}
			} catch (ManagerException e) {
				logger.error(e.getMessage());
				errors.put("email", e.getMessage());
			}
			if (!registerForm.getPassword().equals(registerForm.getRePassword())) {
				errors.put("rePassword", "两次输入密码不相同");
			}
			// 验证验证码
			if (!userManager.checkRegCaptcha(registerForm.getEmail(), registerForm.getCaptcha())) {
				errors.put("captcha", "验证码错误");
			}
			if (!errors.isEmpty()) {
				rr.setCode(Constants.CODE_VALIDATE_FAILED);
				rr.setMessage("参数验证未通过");
				rr.setResult(errors);
			} else {
				// 注册
				try {
					boolean result = userManager.register(registerForm);
					if (!result) {
						rr.setMessage("注册失败，请重试！");
					} else {
						rr.setCode(Constants.CODE_SUCC);
						rr.setMessage("注册成功！正在登录...");
						userManager.login(response, registerForm.getEmail(), registerForm.getPassword(), "0", true);
					}
				} catch (ManagerException e) {
					logger.error(e.getMessage());
					rr.setMessage(e.getMessage());
				}
			}
		}
		return rr;
	}

	/**
	 * 发送注册验证码.
	 * 
	 * @param cellphone
	 * @return
	 */
	@RequestMapping("/sendRegCaptcha")
	@ResponseBody
	public ResponseCode sendRegCaptcha(@RequestParam String email) {

		ResponseCode rc = new ResponseCode();
		rc.setCode(Constants.CODE_FAIL);
		try {
			boolean result = userManager.sendRegCaptcha(email);
			if (!result) {
				rc.setMessage("发送失败，请稍后重试！");
			} else {
				rc.setCode(Constants.CODE_SUCC);
				rc.setMessage("验证码已发送！");
			}
		} catch (ManagerException e) {
			logger.error(e.getMessage());
			rc.setMessage(e.getMessage());
		}
		return rc;
	}

	/**
	 * 登陆
	 * 
	 * @param email
	 * @param password
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseCode login(@RequestParam String email, @RequestParam String password, @RequestParam String autoLogin,
			HttpServletResponse response) {

		ResponseCode rc = new ResponseCode();
		rc.setCode(Constants.CODE_FAIL);
		try {
			boolean result = userManager.login(response, email, password, autoLogin, true);
			if (result) {
				rc.setCode(Constants.CODE_SUCC);
			} else {
				rc.setMessage("登录失败");
			}
		} catch (ManagerException e) {
			logger.error(e.getMessage());
			rc.setMessage(e.getMessage());
		}
		return rc;
	}

	/**
	 * 退出登陆.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletResponse response) {

		userManager.logout(response);
		return "redirect:/";
	}

	/**
	 * 验证邮箱.
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping("/checkEmail")
	@ResponseBody
	public ResponseCode checkEmail(@RequestParam String email) {

		ResponseCode rc = new ResponseCode();
		rc.setCode(Constants.CODE_FAIL);
		try {
			boolean exists = userManager.checkEmailExists(email);
			if (exists) {
				rc.setCode(Constants.CODE_EMAIL_EXIST);
				rc.setMessage("邮箱已被注册！");
			} else {
				rc.setCode(Constants.CODE_SUCC);
				rc.setMessage("邮箱未被使用！");
			}
		} catch (ManagerException e) {
			logger.error(e.getMessage());
			rc.setMessage(e.getMessage());
		}
		return rc;
	}

	/**
	 * 获取用户基本信息.
	 * 
	 * @param username
	 * @param password
	 * @param response
	 * @return
	 */
	@NeedCookie
	@RequestMapping(value = "/getUserInfo")
	@ResponseBody
	public ResponseResult<User> getUserInfo() {

		ResponseResult<User> rr = new ResponseResult<User>();
		rr.setCode(Constants.CODE_FAIL);
		try {
			Long userId = CookieUtil.getUserIdFromCookie();
			User user = userManager.getUserInfo(userId);
			rr.setCode(Constants.CODE_SUCC);
			rr.setMessage("获取用户信息成功");
			rr.setResult(user);
		} catch (ManagerException e) {
			logger.error(e.getMessage());
			rr.setMessage(e.getMessage());
		}
		return rr;
	}

	/**
	 * 发送忘记密码验证码.
	 * 
	 * @param cellphone
	 * @return
	 */
	@RequestMapping("/sendForgetCaptcha")
	@ResponseBody
	public ResponseCode sendForgetCaptcha(@RequestParam String email) {

		ResponseCode rc = new ResponseCode();
		rc.setCode(Constants.CODE_FAIL);
		try {
			boolean result = userManager.sendForgetCaptcha(email);
			if (!result) {
				rc.setMessage("发送失败，请稍后重试！");
			} else {
				rc.setCode(Constants.CODE_SUCC);
				rc.setMessage("验证码已发送！");
			}
		} catch (ManagerException e) {
			logger.error(e.getMessage());
			rc.setMessage(e.getMessage());
		}
		return rc;
	}

	/**
	 * 忘记密码后修改.
	 * 
	 * @param registerForm
	 * @param validResult
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/chForgetPwd", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Object> changeForgetPwd(@RequestParam String newPassword, @RequestParam String rePassword,
			@RequestParam String email, @RequestParam String captcha) {

		ResponseResult<Object> rr = new ResponseResult<Object>();
		rr.setCode(Constants.CODE_FAIL);
		Map<String, String> errors = new HashMap<String, String>();
		try {
			if (!CheckStyleUtil.checkStyle(newPassword, CheckStyleUtil.PATTERN_PWD)) {
				errors.put("newPassword", "请填写6~20位密码");
			}
			if (!newPassword.equals(rePassword)) {
				errors.put("rePassword", "两次输入密码不相同");
			}
			// 验证验证码
			if (!userManager.checkForgetCaptcha(email, captcha)) {
				errors.put("captcha", "验证码错误");
			}
			if (!errors.isEmpty()) {
				rr.setCode(Constants.CODE_VALIDATE_FAILED);
				rr.setMessage("参数验证未通过");
				rr.setResult(errors);
			} else {
				// 修改密码
				boolean result = userManager.updatePassword(email, newPassword);
				if (!result) {
					rr.setMessage("修改失败，请重试！");
				} else {
					rr.setCode(Constants.CODE_SUCC);
					rr.setMessage("修改成功！请登录");
				}
			}
		} catch (ManagerException e) {
			logger.error(e.getMessage());
			rr.setMessage(e.getMessage());
		}
		return rr;
	}

	/**
	 * @return
	 */
	@NeedCookie
	@RequestMapping(value = "/user/userInfo")
	public String userInfo(ModelMap model) {

		User user = null;
		try {
			user = userManager.getCurrentUserInfo();
		} catch (ManagerException e) {
			logger.error(e.getMessage());
		}
		if (user == null) {
			user = new User();
			model.put("sysMsg", "获取用户信息失败，请重新登录");
		}
		model.put("user", user);
		return "user/userInfo";
	}

	/**
	 * @return
	 */
	@NeedCookie
	@RequestMapping(value = "/user/editInfo")
	public String editInfo(@RequestParam String nickname, @RequestParam String mobile, HttpServletResponse response,
			ModelMap model) {

		User user = null;
		try {
			user = userManager.updateCurrentUserInfo(response, nickname, mobile);
			if (user == null) {
				model.put("sysMsg", "修改失败，请重试");
			}
		} catch (ManagerException e) {
			logger.error(e.getMessage());
			model.put("sysMsg", e.getMessage());
		}
		if (user == null) {
			user = new User();
			user.setNickname(nickname);
			user.setEmail(CookieUtil.getEmailFromCookie());
			user.setMobile(mobile);
		}
		model.put("user", user);
		return "user/userInfo";
	}

	/**
	 * @return
	 */
	@NeedCookie
	@RequestMapping(value = "/user/chPwd")
	public String chPwd() {

		return "user/chPwd";
	}

	/**
	 * @return
	 */
	@NeedCookie
	@RequestMapping(value = "/user/modifyPwd")
	public String modifyPwd(@RequestParam String password, @RequestParam String newPassword,
			@RequestParam String rePassword, ModelMap model) {

		boolean result = false;
		try {
			if (!newPassword.equals(rePassword)) {
				model.put("sysMsg", "重复密码与新密码不一致");
			} else {
				result = userManager.updateCurrentPassword(password, newPassword);
				if (!result) {
					model.put("sysMsg", "修改失败，请重试");
				} else {
					model.put("sysMsg", "修改成功!");
				}
			}
		} catch (ManagerException e) {
			logger.error(e.getMessage());
			model.put("sysMsg", e.getMessage());
		}
		return "user/chPwd";
	}

	/**
	 * @return
	 */
	@NeedCookie
	@RequestMapping(value = "/user/cart/add")
	@ResponseBody
	public Map<String, Object> addCart(@RequestParam Long routeId, @RequestParam Long personNumber,
			@RequestParam String departureDate, ModelMap model) {

		Map<String, Object> responseJson = new HashMap<String, Object>();
		responseJson.put("code", 1);
		boolean result = false;
		try {
			result = userManager.addCurrentShoppingCart(routeId, personNumber, departureDate);
		} catch (ManagerException e) {
			logger.error(e.getMessage());
		}
		if (!result) {
			responseJson.put("msg", "加入购物车失败");
		} else {
			responseJson.put("code", 0);
		}
		return responseJson;
	}

	/**
	 * @return
	 */
	@NeedCookie
	@RequestMapping(value = "/user/cart")
	public String userCart(ModelMap model) {

		List<ShoppingCart> shoppingCarts = null;
		try {
			shoppingCarts = userManager.getCurrentShoppingCart();
		} catch (ManagerException e) {
			logger.error(e.getMessage());
		}
		if (shoppingCarts == null) {
			model.put("sysMsg", "获取购物车失败");
		}
		model.put("shoppingCarts", shoppingCarts);
		return "user/cart";
	}

	/**
	 * @return
	 */
	@NeedCookie
	@RequestMapping(value = "/user/cart/rm")
	public String deleteCart(@RequestParam Long cartId, ModelMap model) {

		boolean result = false;
		try {
			result = userManager.deleteCurrentShoppingCart(cartId);
		} catch (ManagerException e) {
			logger.error(e.getMessage());
		}
		if (!result) {
			model.put("sysMsg", "删除失败");
		}
		List<ShoppingCart> shoppingCarts = null;
		Integer shoppingCartCount = null;
		try {
			shoppingCarts = userManager.getCurrentShoppingCart();
			shoppingCartCount = userManager.getCurrentShoppingCartCount();
		} catch (ManagerException e) {
			logger.error(e.getMessage());
		}
		model.put("shoppingCarts", shoppingCarts);
		model.put("shoppingCartCount", shoppingCartCount);
		return "user/cart";
	}

	/**
	 * @return
	 */
	@NeedCookie
	@RequestMapping(value = "/user/favo/add")
	@ResponseBody
	public Map<String, Object> addFavo(@RequestParam Long routeId, ModelMap model) {

		Map<String, Object> responseJson = new HashMap<String, Object>();
		responseJson.put("code", 1);
		boolean result = false;
		try {
			result = userManager.addCurrentFavoRoute(routeId);
		} catch (ManagerException e) {
			logger.error(e.getMessage());
		}
		if (!result) {
			responseJson.put("msg", "收藏失败");
		} else {
			responseJson.put("code", 0);
		}
		return responseJson;
	}

	/**
	 * @return
	 */
	@NeedCookie
	@RequestMapping(value = "/user/favo")
	public String userFavo(ModelMap model) {

		List<FavoriteRoute> favoriteRoutes = null;
		try {
			favoriteRoutes = userManager.getCurrentFavoRoute();
		} catch (ManagerException e) {
			logger.error(e.getMessage());
		}
		if (favoriteRoutes == null) {
			model.put("sysMsg", "获取收藏夹失败");
		}
		model.put("favoriteRoutes", favoriteRoutes);
		return "user/favo";
	}

	/**
	 * @return
	 */
	@NeedCookie
	@RequestMapping(value = "/user/favo/rm")
	public String deleteFavo(@RequestParam Long favoId, ModelMap model) {

		boolean result = false;
		try {
			result = userManager.deleteCurrentFavoriteRoute(favoId);
		} catch (ManagerException e) {
			logger.error(e.getMessage());
		}
		if (!result) {
			model.put("sysMsg", "删除失败");
		}
		List<FavoriteRoute> favoriteRoutes = null;
		try {
			favoriteRoutes = userManager.getCurrentFavoRoute();
		} catch (ManagerException e) {
			logger.error(e.getMessage());
		}
		model.put("favoriteRoutes", favoriteRoutes);
		return "user/favo";
	}
}
