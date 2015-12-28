package com.qinjiance.tourist.manager.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import module.laohu.commons.util.EncryptUtils;
import module.laohu.commons.util.SecurityUtil;
import module.laohu.commons.util.SpringUtils;
import module.laohu.commons.util.WebUtils;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qinjiance.tourist.constants.UserLevel;
import com.qinjiance.tourist.manager.IEhCacheManager;
import com.qinjiance.tourist.manager.IEmailManager;
import com.qinjiance.tourist.manager.IUserManager;
import com.qinjiance.tourist.manager.exception.ManagerException;
import com.qinjiance.tourist.manager.impl.EhCacheManager.CacheType;
import com.qinjiance.tourist.mapper.FavoriteRouteMapper;
import com.qinjiance.tourist.mapper.ShoppingCartMapper;
import com.qinjiance.tourist.mapper.TourRouteMapper;
import com.qinjiance.tourist.mapper.UserMapper;
import com.qinjiance.tourist.model.po.FavoriteRoute;
import com.qinjiance.tourist.model.po.ShoppingCart;
import com.qinjiance.tourist.model.po.TourRoute;
import com.qinjiance.tourist.model.po.User;
import com.qinjiance.tourist.model.vo.RegisterForm;
import com.qinjiance.tourist.util.CheckStyleUtil;
import com.qinjiance.tourist.util.CookieUtil;
import com.qinjiance.tourist.util.RandomUtil;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年7月8日
 * 
 * @time 下午6:38:09
 * 
 * @desc
 * 
 */
@Service
public class UserManager implements IUserManager {

	protected static final Logger logger = LoggerFactory.getLogger(UserManager.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	@Autowired
	private FavoriteRouteMapper favoriteRouteMapper;
	@Autowired
	private IEhCacheManager ehCacheManager;
	@Autowired
	private IEmailManager emailManager;
	@Autowired
	private TourRouteMapper tourRouteMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IUserManager#checkEmailExists(java.lang
	 * .String)
	 */
	@Override
	public boolean checkEmailExists(String email) throws ManagerException {

		if (StringUtils.isBlank(email)) {
			throw new ManagerException("邮箱不能为空");
		}
		if (!CheckStyleUtil.checkStyle(email, CheckStyleUtil.PATTERN_EMAIL)) {
			throw new ManagerException("邮箱格式不正确");
		}
		if (userMapper.countEmail(email) > 0) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IUserManager#sendRegCaptcha(java.lang.String
	 * )
	 */
	@Override
	public boolean sendRegCaptcha(String email) throws ManagerException {

		if (checkEmailExists(email)) {
			throw new ManagerException("邮箱已被注册");
		}
		ehCacheManager.acquireWriteLockOnKey(CacheType.MIN1, getRegEmailCaptchaIvCacheKey(email));
		if (null != ehCacheManager.getFromCache(CacheType.MIN1, (getRegEmailCaptchaIvCacheKey(email)))) {
			ehCacheManager.releaseWriteLockOnKey(CacheType.MIN1, getRegEmailCaptchaIvCacheKey(email));
			throw new ManagerException("您发送邮箱验证码过于频繁，请稍后再试");
		}
		boolean result = false;
		try {
			String captcha = RandomUtil.getRandomNumeric6();
			result = emailManager.sendEmailCaptcha(email, captcha);
			if (result) {
				// captcha cache
				ehCacheManager.putToCache(CacheType.MIN3, getRegEmailCaptchaCacheKey(email), captcha);
				// interval cache
				ehCacheManager.putToCache(CacheType.MIN1, getRegEmailCaptchaIvCacheKey(email),
						System.currentTimeMillis());
			} else {
				logger.info("Failed to send email captcha '" + captcha + "' to " + email
						+ " for register user, result: " + result);
			}
		} catch (Exception e) {
			logger.error("Failed to send email captcha to " + email + " for register user", e);
			result = false;
		}
		ehCacheManager.releaseWriteLockOnKey(CacheType.MIN1, getRegEmailCaptchaIvCacheKey(email));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IUserManager#sendForgetCaptcha(java.lang
	 * .String)
	 */
	@Override
	public boolean sendForgetCaptcha(String email) throws ManagerException {

		if (!checkEmailExists(email)) {
			throw new ManagerException("邮箱未注册");
		}
		ehCacheManager.acquireWriteLockOnKey(CacheType.MIN1, getForgetEmailCaptchaIvCacheKey(email));
		if (null != ehCacheManager.getFromCache(CacheType.MIN1, (getForgetEmailCaptchaIvCacheKey(email)))) {
			ehCacheManager.releaseWriteLockOnKey(CacheType.MIN1, getForgetEmailCaptchaIvCacheKey(email));
			throw new ManagerException("您发送邮箱验证码过于频繁，请稍后再试");
		}
		boolean result = false;
		try {
			String captcha = RandomUtil.getRandomNumeric6();
			result = emailManager.sendEmailCaptcha(email, captcha);
			if (result) {
				// captcha cache
				ehCacheManager.putToCache(CacheType.MIN3, getForgetEmailCaptchaCacheKey(email), captcha);
				// interval cache
				ehCacheManager.putToCache(CacheType.MIN1, getForgetEmailCaptchaIvCacheKey(email),
						System.currentTimeMillis());
			} else {
				logger.info("Failed to send email captcha '" + captcha + "' to " + email
						+ " for forget password, result: " + result);
			}
		} catch (Exception e) {
			logger.error("Failed to send email captcha to " + email + " for forget password", e);
			result = false;
		}
		ehCacheManager.releaseWriteLockOnKey(CacheType.MIN1, getForgetEmailCaptchaIvCacheKey(email));
		return result;
	}

	/**
	 * @param email
	 * @return
	 */
	protected String getRegEmailCaptchaCacheKey(String email) {

		return "RegEmailCaptcha#" + email;
	}

	/**
	 * @param email
	 * @return
	 */
	protected String getRegEmailCaptchaIvCacheKey(String email) {

		return "RegEmailCaptchaIv#" + email;
	}

	/**
	 * @param email
	 * @return
	 */
	protected String getForgetEmailCaptchaCacheKey(String email) {

		return "ForgetEmailCaptcha#" + email;
	}

	/**
	 * @param email
	 * @return
	 */
	protected String getForgetEmailCaptchaIvCacheKey(String email) {

		return "ForgetEmailCaptchaIv#" + email;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IUserManager#checkRegCaptcha(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public boolean checkRegCaptcha(String email, String captcha) {

		if (StringUtils.isBlank(email) || StringUtils.isBlank(captcha)) {
			return false;
		}
		boolean result = false;
		logger.info("Check email captcha for register user");
		String cacheCaptcha = ehCacheManager.getFromCache(CacheType.MIN3, getRegEmailCaptchaCacheKey(email));
		if (!StringUtils.isBlank(cacheCaptcha) && cacheCaptcha.equalsIgnoreCase(captcha)) {
			ehCacheManager.evictFromCache(CacheType.MIN3, getRegEmailCaptchaCacheKey(email));
			result = true;
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IUserManager#checkForgetCaptcha(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public boolean checkForgetCaptcha(String email, String captcha) {

		if (StringUtils.isBlank(email) || StringUtils.isBlank(captcha)) {
			return false;
		}
		boolean result = false;
		logger.info("Check email captcha for forget password");
		String cacheCaptcha = ehCacheManager.getFromCache(CacheType.MIN3, getForgetEmailCaptchaCacheKey(email));
		if (!StringUtils.isBlank(cacheCaptcha) && cacheCaptcha.equalsIgnoreCase(captcha)) {
			ehCacheManager.evictFromCache(CacheType.MIN3, getForgetEmailCaptchaCacheKey(email));
			result = true;
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IUserManager#register(com.qinjiance.tourist
	 * .model.vo.RegisterForm)
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean register(RegisterForm registerForm) throws ManagerException {

		if (registerForm == null) {
			throw new ManagerException("注册表单为空");
		}
		try {
			User newUser = createRegisterUser(registerForm);
			if (newUser != null) {
				userMapper.insert(newUser);
				logger.info("Add new register user: userId=" + newUser.getId() + ", " + registerForm.toString());
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ManagerException("注册失败，请重试");
		}
	}

	/**
	 * @param registerForm
	 * @return
	 */
	protected User createRegisterUser(RegisterForm registerForm) {

		if (registerForm == null) {
			logger.info("Register form is null.");
			return null;
		}
		User newUser = new User();
		newUser.setMobile(registerForm.getMobile());
		newUser.setEmail(registerForm.getEmail());
		// 默认昵称(email@ + ranNum4)
		newUser.setNickname(generateNickname(registerForm.getEmail()));
		String salt = SecurityUtil.getUUID();
		newUser.setSalt(salt);
		newUser.setLevel(UserLevel.COMMON.getLevel());
		newUser.setPassword(encryptPassword(registerForm.getPassword(), salt));
		newUser.setRegisterIp(WebUtils.getIpAddress(SpringUtils.getHttpServletRequest()));
		return newUser;
	}

	/**
	 * 生成昵称，唯一.
	 * 
	 * @param email
	 * @return
	 */
	public String generateNickname(String email) {

		String nickname = "";
		if (StringUtils.isBlank(email)) {
			nickname = RandomStringUtils.randomAlphanumeric(8);
		} else {
			nickname = email.split("@")[0];
		}
		User user = null;
		int i = 0;
		int padding = 15 - nickname.length();
		do {
			padding = 15 - nickname.length();
			if (i > 0) {
				nickname = nickname + RandomStringUtils.randomNumeric(i > padding ? padding : i);
			}
			user = userMapper.getUserByNickname(nickname);
			i++;
		} while (user != null);
		return nickname;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qinjiance.tourist.manager.IUserManager#login(javax.servlet.http.
	 * HttpServletResponse, java.lang.String, java.lang.String,
	 * java.lang.String, boolean)
	 */
	@Override
	public boolean login(HttpServletResponse response, String email, String password, String autoLogin,
			boolean isSetCookie) throws ManagerException {

		if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
			throw new ManagerException("邮箱或密码为空");
		}
		if (!CheckStyleUtil.checkStyle(email, CheckStyleUtil.PATTERN_EMAIL)) {
			throw new ManagerException("邮箱格式不正确");
		}
		User user = null;
		user = userMapper.getUserByEmail(email);
		if (user == null) {
			throw new ManagerException("邮箱或密码错误");
		}
		String ecryPwd = encryptPassword(password, user.getSalt());
		if (ecryPwd == null || !ecryPwd.equals(user.getPassword())) {
			throw new ManagerException("邮箱或密码错误");
		}
		boolean result = true;
		if (isSetCookie) {
			// 种cookie
			int expire = CookieUtil.COOKIE_LIVE_SESSION;
			boolean isAutoLogin = false;
			if (StringUtils.isNotBlank(autoLogin) && autoLogin.equals("1")) {
				expire = CookieUtil.COOKIE_LIVE_10_DAYS;
				isAutoLogin = true;
			}
			result = CookieUtil.setLoginCookie(response, user.getId(), user.getNickname(), email, user.getMobile(),
					expire, isAutoLogin);
		}
		return result;
	}

	/**
	 * 用户密码加密: SHA256(salt, md5(password+salt))
	 * 
	 * @param password
	 * @param salt
	 * @return
	 */
	protected String encryptPassword(String password, String salt) {

		return EncryptUtils.saltSHA256(salt, SecurityUtil.md5(password + salt));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IUserManager#getUserInfo(java.lang.Long)
	 */
	@Override
	public User getUserInfo(Long userId) throws ManagerException {

		if (userId == null) {
			throw new ManagerException("用户ID为空");
		}
		User user = userMapper.getUserById(userId);
		if (user == null) {
			throw new ManagerException("用户不存在");
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IUserManager#logout(javax.servlet.http.
	 * HttpServletResponse)
	 */
	@Override
	public void logout(HttpServletResponse response) {

		CookieUtil.clearCookie(response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IUserManager#updatePassword(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public boolean updatePassword(String email, String newPassword) throws ManagerException {

		if (!checkEmailExists(email)) {
			throw new ManagerException("邮箱未注册");
		}
		String salt = SecurityUtil.getUUID();
		int result = userMapper.updatePwd(email, encryptPassword(newPassword, salt), salt);
		return result == 1 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qinjiance.tourist.manager.IUserManager#getCurrentUserInfo()
	 */
	@Override
	public User getCurrentUserInfo() throws ManagerException {

		Long userId = CookieUtil.getUserIdFromCookie();
		if (userId == null) {
			throw new ManagerException("请先登录");
		}
		return userMapper.getUserById(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IUserManager#updateCurrentUserInfo(javax
	 * .servlet.http.HttpServletResponse, java.lang.String, java.lang.String)
	 */
	@Override
	public User updateCurrentUserInfo(HttpServletResponse response, String nickname, String mobile)
			throws ManagerException {

		Long userId = CookieUtil.getUserIdFromCookie();
		if (userId == null) {
			throw new ManagerException("请先登录");
		}
		if (StringUtils.isBlank(nickname) || StringUtils.isBlank(mobile)) {
			throw new ManagerException("昵称或手机号为空");
		}
		User user = userMapper.getUserByNickname(nickname);
		if (user != null && !user.getId().equals(userId)) {
			throw new ManagerException("昵称已被使用");
		}
		int result = userMapper.updateUserInfo(nickname, mobile, userId);
		if (result != 1) {
			return null;
		}
		CookieUtil.updateCookieInfo(response, userId, nickname, CookieUtil.getEmailFromCookie(), mobile,
				CookieUtil.COOKIE_LIVE_10_DAYS);
		return userMapper.getUserByNickname(nickname);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IUserManager#updateCurrentPassword(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public boolean updateCurrentPassword(String password, String newPassword) throws ManagerException {

		String email = CookieUtil.getEmailFromCookie();
		if (StringUtils.isBlank(email)) {
			throw new ManagerException("请先登录");
		}
		if (StringUtils.isBlank(password) || StringUtils.isBlank(newPassword)) {
			throw new ManagerException("原密码或新密码为空");
		}
		User user = userMapper.getUserByEmail(email);
		String ecryPwd = encryptPassword(password, user.getSalt());
		if (ecryPwd == null || !ecryPwd.equals(user.getPassword())) {
			throw new ManagerException("原密码错误");
		}
		return updatePassword(email, newPassword);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qinjiance.tourist.manager.IUserManager#getCurrentShoppingCart()
	 */
	@Override
	public List<ShoppingCart> getCurrentShoppingCart() throws ManagerException {

		Long userId = CookieUtil.getUserIdFromCookie();
		if (userId == null) {
			throw new ManagerException("请先登录");
		}
		return shoppingCartMapper.getByUserId(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qinjiance.tourist.manager.IUserManager#getCurrentFavoRoute()
	 */
	@Override
	public List<FavoriteRoute> getCurrentFavoRoute() throws ManagerException {

		Long userId = CookieUtil.getUserIdFromCookie();
		if (userId == null) {
			throw new ManagerException("请先登录");
		}
		return favoriteRouteMapper.getByUserId(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IUserManager#deleteCurrentShoppingCart(
	 * java.lang.Long)
	 */
	@Override
	public boolean deleteCurrentShoppingCart(Long cartId) throws ManagerException {

		Long userId = CookieUtil.getUserIdFromCookie();
		if (userId == null) {
			throw new ManagerException("请先登录");
		}
		int result = shoppingCartMapper.delete(userId, cartId);
		return result == 1 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IUserManager#deleteCurrentFavoriteRoute
	 * (java.lang.Long)
	 */
	@Override
	public boolean deleteCurrentFavoriteRoute(Long favoId) throws ManagerException {

		Long userId = CookieUtil.getUserIdFromCookie();
		if (userId == null) {
			throw new ManagerException("请先登录");
		}
		int result = favoriteRouteMapper.delete(userId, favoId);
		return result == 1 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IUserManager#addCurrentFavoRoute(java.lang
	 * .Long)
	 */
	@Override
	public boolean addCurrentFavoRoute(Long routeId) throws ManagerException {

		Long userId = CookieUtil.getUserIdFromCookie();
		if (userId == null) {
			throw new ManagerException("请先登录");
		}
		TourRoute tourRoute = tourRouteMapper.getById(routeId);
		if (tourRoute == null) {
			throw new ManagerException("该路线已结束");
		}
		FavoriteRoute favoriteRoute = new FavoriteRoute();
		favoriteRoute.setAreaId(tourRoute.getAreaId());
		favoriteRoute.setRouteId(tourRoute.getId());
		favoriteRoute.setRouteName(tourRoute.getName());
		favoriteRoute.setUserId(userId);
		int result = favoriteRouteMapper.insert(favoriteRoute);
		return result == 1 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IUserManager#getCurrentShoppingCartCount()
	 */
	@Override
	public Integer getCurrentShoppingCartCount() {

		Long userId = CookieUtil.getUserIdFromCookie();
		if (userId == null) {
			return null;
		}
		return shoppingCartMapper.countByUserId(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IUserManager#addCurrentShoppingCart(java
	 * .lang.Long, java.lang.Long, java.lang.String)
	 */
	@Override
	public boolean addCurrentShoppingCart(Long routeId, Long personNumber, String departureDate)
			throws ManagerException {

		Long userId = CookieUtil.getUserIdFromCookie();
		if (userId == null) {
			throw new ManagerException("请先登录");
		}
		TourRoute tourRoute = tourRouteMapper.getById(routeId);
		if (tourRoute == null) {
			throw new ManagerException("该路线已结束");
		}
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setAreaId(tourRoute.getAreaId());
		shoppingCart.setRouteId(tourRoute.getId());
		shoppingCart.setRouteName(tourRoute.getName());
		shoppingCart.setUserId(userId);
		shoppingCart.setDepartureDate(departureDate);
		shoppingCart.setPersonNumber(personNumber);
		int result = shoppingCartMapper.insert(shoppingCart);
		return result == 1 ? true : false;
	}
}
