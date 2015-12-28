package com.qinjiance.tourist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qinjiance.tourist.model.po.Arrival;

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
public interface ArrivalMapper {

	@Select("select * from arrival where id = #{id}")
	public Arrival getById(@Param("id") Long id);

	@Select("select * from arrival where area_id = #{areaId} order by id")
	public List<Arrival> getByAreaId(@Param("areaId") Long areaId);
}
