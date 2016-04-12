package com.qinjiance.tourist.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.qinjiance.tourist.model.po.BillingHotel;

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
public interface BillingHotelMapper {

	public Integer insert(BillingHotel billingHotel);

	@Update("update billing_hotel set pay_status = 1, gateway_order_id = #{gatewayOrderId}, real_pay_amount = #{realPayAmount}, pay_time = now() where id = #{id} and pay_status = 0")
	public Integer updatePayOk(@Param("gatewayOrderId") String gatewayOrderId,
			@Param("realPayAmount") Long realPayAmount, @Param("id") Long id);

	@Update("update billing_hotel set pay_failed_info = #{failedInfo}, pay_time = now() where id = #{id} and pay_status != 1 and charge_status != 1")
	public Integer updatePayFailedInfo(@Param("id") Long id, @Param("failedInfo") String failedInfo);

	@Select("select * from billing_hotel where id = #{id}")
	public BillingHotel getById(@Param("id") Long id);
}
