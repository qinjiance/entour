package com.qinjiance.tourist.manager;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.qinjiance.tourist.manager.exception.ManagerException;
import com.qinjiance.tourist.model.po.FavoriteRoute;
import com.qinjiance.tourist.model.po.ShoppingCart;
import com.qinjiance.tourist.model.po.User;
import com.qinjiance.tourist.model.vo.RegisterForm;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年7月9日
 * 
 * @time 下午4:41:06
 * 
 * @desc
 * 
 */
public interface IUserManager {

	/**
	 * 退出，清除cookie.
	 * 
	 * @param response
	 * @return
	 */
	public void logout(HttpServletResponse response);

	/**
	 * 验证邮箱是否已存在.
	 * 
	 * @param email
	 * @return
	 * @throws ManagerException
	 */
	public boolean checkEmailExists(String email) throws ManagerException;

	/**
	 * 发送注册验证码.
	 * 
	 * @param email
	 * @return
	 * @throws ManagerException
	 */
	public boolean sendRegCaptcha(String email) throws ManagerException;

	/**
	 * 发送忘记密码验证码.
	 * 
	 * @param email
	 * @return
	 * @throws ManagerException
	 */
	public boolean sendForgetCaptcha(String email) throws ManagerException;

	/**
	 * 验证注册验证码.
	 * 
	 * @param username
	 * @param captcha
	 * @return
	 * @throws ManagerException
	 */
	public boolean checkRegCaptcha(String email, String captcha);

	/**
	 * 验证忘记密码验证码.
	 * 
	 * @param username
	 * @param captcha
	 * @return
	 * @throws ManagerException
	 */
	public boolean checkForgetCaptcha(String email, String captcha);

	/**
	 * 注册.
	 * 
	 * @param registerForm
	 * @return
	 * @throws ManagerException
	 */
	public boolean register(RegisterForm registerForm) throws ManagerException;

	/**
	 * 用户名密码登陆.
	 * 
	 * @param response
	 * @param username
	 * @param password
	 * @param autoLogin
	 * @param isSetCookie
	 * @return
	 * @throws ManagerException
	 */
	public boolean login(HttpServletResponse response, String email, String password, String autoLogin,
			boolean isSetCookie) throws ManagerException;

	/**
	 * 获取用户信息.
	 * 
	 * @param userId
	 * @return
	 * @throws ManagerException
	 */
	public User getUserInfo(Long userId) throws ManagerException;

	/**
	 * 修改密码.
	 * 
	 * @param email
	 * @param newPassword
	 * @return
	 * @throws ManagerException
	 */
	public boolean updatePassword(String email, String newPassword) throws ManagerException;

	/**
	 * 获取当前登录用户的信息
	 * 
	 * @return
	 */
	public User getCurrentUserInfo() throws ManagerException;

	/**
	 * @param response
	 * @param nickname
	 * @param mobile
	 * @return
	 * @throws ManagerException
	 */
	public User updateCurrentUserInfo(HttpServletResponse response, String nickname, String mobile)
			throws ManagerException;

	/**
	 * @param password
	 * @param newPassword
	 * @return
	 */
	public boolean updateCurrentPassword(String password, String newPassword) throws ManagerException;

	/**
	 * @return
	 */
	public List<ShoppingCart> getCurrentShoppingCart() throws ManagerException;

	/**
	 * @return
	 */
	public List<FavoriteRoute> getCurrentFavoRoute() throws ManagerException;

	/**
	 * @param cartId
	 * @return
	 */
	public boolean deleteCurrentShoppingCart(Long cartId) throws ManagerException;

	/**
	 * @param favoId
	 * @return
	 */
	public boolean deleteCurrentFavoriteRoute(Long favoId) throws ManagerException;

	/**
	 * @param routeId
	 * @return
	 */
	public boolean addCurrentFavoRoute(Long routeId) throws ManagerException;

	/**
	 * @return
	 */
	public Integer getCurrentShoppingCartCount();

	/**
	 * @param routeId
	 * @param personNumber
	 * @param departureDate
	 * @return
	 */
	public boolean addCurrentShoppingCart(Long routeId, Long personNumber, String departureDate)
			throws ManagerException;
}
