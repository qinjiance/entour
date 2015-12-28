package com.qinjiance.tourist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.qinjiance.tourist.model.po.View;

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
public interface ViewMapper {

	@Select("select * from view v inner join route_view rv on v.id = rv.view_id where rv.route_id = #{routeId}")
	public List<View> getByRouteId(@Param("routeId") Long routeId);

	@Select("select * from view where id = #{id}")
	public View getById(@Param("id") Long id);

	@Select("select * from view where area_id = #{areaId} order by id")
	public List<View> getByAreaId(@Param("areaId") Long areaId);

	@Select("select * from view")
	public List<View> getAll();

	@Select("select * from view order by hot_point desc")
	public PageList<View> getHotViews(PageBounds pageBounds);
}
