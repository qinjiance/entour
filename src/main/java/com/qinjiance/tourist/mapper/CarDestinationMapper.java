package com.qinjiance.tourist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qinjiance.tourist.model.po.CarDestination;

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
public interface CarDestinationMapper {

	@Select("select * from car_destination where company_key = #{des} or city_key = #{des} or state_key = #{des} or country_key = #{des} or station_key = #{des} or code_key = #{des}")
	public List<CarDestination> getByAll(@Param("des") String des);
}
