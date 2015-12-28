package com.qinjiance.tourist.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.qinjiance.tourist.model.po.Travels;

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
public interface TravelsMapper {

	@Select("select * from travels where id = #{id}")
	public Travels getById(@Param("id") Long id);

	@Select("select * from travels where user_id = #{userId} order by update_time desc")
	public PageList<Travels> getByUserId(@Param("userId") Long userId, PageBounds pageBounds);

	@Select("select * from travels where route_id = #{routeId} order by fans desc, click desc")
	public PageList<Travels> getByRouteId(@Param("routeId") Long routeId, PageBounds pageBounds);

	@Delete("delete from travels where id = #{id}")
	public int delete(@Param("id") Long id);

	@Insert("insert into travels (area_id,user_id,route_id,route_name,fans,click,title,content,create_time,update_time) values (#{t.areaId},#{t.userId},#{t.routeId},#{t.routeName},#{t.fans},#{t.click},#{t.title},#{t.content},now(),now())")
	public int insert(@Param("t") Travels travels);
}
