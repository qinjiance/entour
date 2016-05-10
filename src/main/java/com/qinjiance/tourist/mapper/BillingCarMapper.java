package com.qinjiance.tourist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qinjiance.tourist.model.po.BillingCar;

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
public interface BillingCarMapper {

	public Integer insert(BillingCar BillingCar);

	@Update("update billing_car set exchange = #{exchange}, pay_price_atproperty = #{payPriceAtproperty}, pay_type = #{payType}, pay_price = #{payPrice} where id = #{orderId}")
	public Integer updatePayInfo(@Param("exchange") Double exchange,
			@Param("payPriceAtproperty") Long payPriceAtproperty, @Param("payType") String payType,
			@Param("payPrice") Long payPrice, @Param("orderId") Long orderId);

	@Update("update billing_car set pay_status = 1, gateway_order_id = #{gatewayOrderId}, real_pay_amount = #{realPayAmount}, pay_time = now() where id = #{id} and pay_status = 0")
	public Integer updatePayOk(@Param("gatewayOrderId") String gatewayOrderId,
			@Param("realPayAmount") Long realPayAmount, @Param("id") Long id);

	@Update("update billing_car set pay_failed_info = #{failedInfo}, pay_time = now() where id = #{id} and pay_status != 1 and charge_status != 1")
	public Integer updatePayFailedInfo(@Param("id") Long id, @Param("failedInfo") String failedInfo);

	@Select("select * from billing_car where id = #{id}")
	public BillingCar getById(@Param("id") Long id);

	@Select("select * from billing_car where user_id = #{userId} order by create_time desc")
	public List<BillingCar> getByUserId(@Param("userId") Long userId);
}
