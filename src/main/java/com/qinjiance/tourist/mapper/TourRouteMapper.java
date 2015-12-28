package com.qinjiance.tourist.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.qinjiance.tourist.model.po.TourRoute;

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
public interface TourRouteMapper {

	@Select("select * from tour_route where id = #{id}")
	public TourRoute getById(@Param("id") Long id);

	public PageList<TourRoute> getByConditions(Map<String, Object> conditions, PageBounds pageBounds);
}
