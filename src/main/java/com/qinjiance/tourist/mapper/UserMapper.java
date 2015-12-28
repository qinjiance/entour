package com.qinjiance.tourist.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qinjiance.tourist.model.po.User;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年7月11日
 * 
 * @time 下午4:57:27
 * 
 * @desc
 * 
 */
public interface UserMapper {

	public void insert(User user);

	@Select("select id, nickname, email, mobile, level, salt, password, INET_NTOA(register_ip) as register_ip, register_time, update_time from user where id = #{id}")
	public User getUserById(@Param("id") Long id);

	@Select("select id, nickname, email, mobile, level, salt, password, INET_NTOA(register_ip) as register_ip, register_time, update_time from user where nickname = #{nickname}")
	public User getUserByNickname(@Param("nickname") String nickname);

	@Select("select id, nickname, email, mobile, level, salt, password, INET_NTOA(register_ip) as register_ip, register_time, update_time from user where email = #{email}")
	public User getUserByEmail(@Param("email") String email);

	@Select("select id, nickname, email, mobile, level, salt, password, INET_NTOA(register_ip) as register_ip, register_time, update_time from user where mobile = #{mobile}")
	public User getUserByMobile(@Param("mobile") String mobile);

	@Select("select count(*) from user where email = #{email}")
	public Long countEmail(@Param("email") String email);

	@Select("select count(*) from user where mobile = #{mobile}")
	public Long countMobile(@Param("mobile") String mobile);

	@Update("update user set password = #{password}, salt = #{salt} where email = #{email}")
	public int updatePwd(@Param("email") String email, @Param("password") String password, @Param("salt") String salt);

	@Update("update user set nickname = #{nickname}, mobile = #{mobile} where id = #{userId}")
	public int updateUserInfo(@Param("nickname") String nickname, @Param("mobile") String mobile,
			@Param("userId") Long userId);
}
