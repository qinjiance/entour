package com.qinjiance.tourist.support.alipay.constant;

/**
 * @author "Jiance Qin"
 * 
 * @date 2015年11月23日
 * 
 * @time 下午4:35:28
 * 
 * @desc
 * 
 */
public class AlipayConstant {

	public final static String RESPONSE_TO_ALIPAY_SUCCESS = "success";  
	public final static String RESPONSE_TO_ALIPAY_FAILED = "failed";

	public static int SUCCESS = 0;
	public static int FAILED = 1;
	public static int PROCESSING = 2;

	// /////////////////////////////////// 接口 /////////////////////////////////
	/**
	 * 支付宝提供给商户的服务接入网关URL
	 */
	public static final String ALIPAY_GATEWAY = "https://mapi.alipay.com/gateway.do";
	/**
	 * 支付宝Wap支付网关URL
	 */
	public static final String ALIPAY_WAP_GATEWAY = "http://wappaygw.alipay.com/service/rest.htm";
	/**
	 * 支付宝通知验证地址
	 */
	public static final String NOTIFY_VERIFY_SERVICE = "notify_verify";
	/**
	 * 即时到帐接口
	 */
	public static final String DERECT_PAY_SERVICE = "create_direct_pay_by_user";
	/**
	 * 防钓鱼时间戳接口
	 */
	public static final String ANTI_FISH_TIMESTAMP_SERVICE = "query_timestamp";
	/**
	 * WAP支付获取token接口
	 */
	public static final String WAP_AUTH_TOKEN_SERVICE = "alipay.wap.trade.create.direct";
	/**
	 * WAP支付接口
	 */
	public static final String WAP_PAY_SERVICE = "alipay.wap.auth.authAndExecute";
	/**
	 * 二维码管理接口
	 */
	public static final String MOBILE_QRCODE_SERVICE = "alipay.mobile.qrcode.manage";

	/**
	 * 支付请求业务参数-支付类型
	 */
	public static final String DERECT_PAY_PAYMENTTYPE = "1";
	/**
	 * 小额代扣个人协议签约接口，限额：一笔2000，一天2000，一个月10000
	 */
	public static final String DUT_CUSTOMER_AGREEMENT_SIGN = "alipay.dut.customer.agreement.page.sign";
	/**
	 * 小额代扣个人协议查询接口
	 */
	public static final String DUT_CUSTOMER_AGREEMENT_QUERY = "alipay.dut.customer.agreement.query";
	/**
	 * 小额代扣个人协议解约接口
	 */
	public static final String DUT_CUSTOMER_AGREEMENT_UNSIGN = "alipay.dut.customer.agreement.unsign";
	/**
	 * 小额代扣扣款接口
	 */
	public static final String ACQUIRE_CREATE_AND_PAY = "alipay.acquire.createandpay";

	// //////////////////////////// 参数 ///////////////////////////////////
	/**
	 * 扫码支付，跳转模式
	 */
	public static final String QRCODE_MODE_2 = "2";
	/**
	 * 网银支付模式
	 */
	public static final String BANK_PAY_METHOD = "bankPay";
	/**
	 * 小额代扣个人协议操作接口产品码
	 */
	public static final String DUT_AGREEMENT_PRODUCT_CODE = "GENERAL_WITHHOLDING_P";
	/**
	 * 小额代扣扣款接口产品码
	 */
	public static final String ACQUIRE_CREATE_AND_PAY_PRODUCT_CODE = "GENERAL_WITHHOLDING";
	/**
	 * 小额代扣场景码
	 */
	public static final String DUT_SCENE = "INDUSTRY|GAME_CHARGE";

	/**
	 * 接口调用成功
	 */
	public static final String IS_SUCCESS = "T";
}
