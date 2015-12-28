package com.qinjiance.tourist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qinjiance.tourist.model.po.Photo;

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
public interface PhotoMapper {

	@Select("select * from photo where route_id = #{routeId} and scale = #{scale}")
	public List<Photo> getByRouteId(@Param("routeId") Long routeId, @Param("scale") int scale);
}
