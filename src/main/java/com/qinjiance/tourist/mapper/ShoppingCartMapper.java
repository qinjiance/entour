package com.qinjiance.tourist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qinjiance.tourist.model.po.ShoppingCart;

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
public interface ShoppingCartMapper {

	@Select("select * from shopping_cart where user_id = #{userId} order by create_time desc")
	public List<ShoppingCart> getByUserId(@Param("userId") Long userId);

	@Delete("delete from shopping_cart where id = #{id} and user_id = #{userId}")
	public Integer delete(@Param("userId") Long userId, @Param("id") Long id);

	@Select("select count(1) from shopping_cart where user_id = #{userId}")
	public Integer countByUserId(@Param("userId") Long userId);

	@Insert("insert into shopping_cart (area_id, user_id, route_id, route_name, person_number, departure_date, create_time, update_time) values (#{sc.areaId}, #{sc.userId}, #{sc.routeId}, #{sc.routeName}, #{sc.personNumber}, #{sc.departureDate}, now(), now())")
	public int insert(@Param("sc") ShoppingCart shoppingCart);
}
