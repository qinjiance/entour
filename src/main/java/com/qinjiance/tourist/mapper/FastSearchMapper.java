package com.qinjiance.tourist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qinjiance.tourist.model.po.FastSearch;

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
public interface FastSearchMapper {

	@Select("select * from fast_search where id = #{id}")
	public FastSearch getById(@Param("id") Long id);

	@Select("select * from fast_search order by area_id, min_days")
	public List<FastSearch> getAll();
}
