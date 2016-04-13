package com.qinjiance.tourist.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qinjiance.tourist.model.po.Exchange;

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
public interface ExchangeMapper {

	@Select("select * from exchange where type = #{type}")
	public Exchange getByType(@Param("type") Integer type);
}
