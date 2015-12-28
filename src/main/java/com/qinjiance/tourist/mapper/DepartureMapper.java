package com.qinjiance.tourist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qinjiance.tourist.model.po.Departure;

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
public interface DepartureMapper {

	@Select("select * from departure")
	public List<Departure> getAll();

	@Select("select * from departure where id = #{id}")
	public Departure getById(@Param("id") Long id);

	@Select("select * from departure where area_id = #{areaId} order by id")
	public List<Departure> getByAreaId(@Param("areaId") Long areaId);
}
