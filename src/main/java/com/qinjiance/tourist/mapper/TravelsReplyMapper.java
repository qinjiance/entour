package com.qinjiance.tourist.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.qinjiance.tourist.model.po.TravelsReply;

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
public interface TravelsReplyMapper {

	@Select("select * from travels_reply where travels_id = #{travelsId} order by create_time desc")
	public PageList<TravelsReply> getByTravelsId(@Param("travelsId") Long travelsId, PageBounds pageBounds);

	@Delete("delete from travels_reply where id = #{id}")
	public int delete(@Param("id") Long id);

	@Insert("insert into travels_reply (travels_id,user_id,content,create_time,update_time) values (#{tr.travelsId},#{tr.userId},#{tr.content},now(),now())")
	public int insert(@Param("tr") TravelsReply travelsReply);
}
