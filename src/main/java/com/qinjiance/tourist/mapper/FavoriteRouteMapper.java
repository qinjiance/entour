package com.qinjiance.tourist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qinjiance.tourist.model.po.FavoriteRoute;

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
public interface FavoriteRouteMapper {

	@Select("select * from favorite_route where user_id = #{userId} order by create_time desc")
	public List<FavoriteRoute> getByUserId(@Param("userId") Long userId);

	@Delete("delete from favorite_route where id = #{id} and user_id = #{userId}")
	public Integer delete(@Param("userId") Long userId, @Param("id") Long id);

	@Insert("insert into favorite_route (area_id, user_id, route_id, route_name, create_time, update_time) values (#{fr.areaId}, #{fr.userId}, #{fr.routeId}, #{fr.routeName}, now(), now())")
	public int insert(@Param("fr") FavoriteRoute favoriteRoute);
}
