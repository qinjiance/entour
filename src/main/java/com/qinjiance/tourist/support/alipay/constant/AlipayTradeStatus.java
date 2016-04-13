package com.qinjiance.tourist.support.alipay.constant;

import org.apache.commons.lang.StringUtils;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年7月24日
 * 
 * @time 下午4:33:13
 * 
 * @desc
 * 
 */
public enum AlipayTradeStatus {

	/**
	 * 交易创建，等待买家付款。
	 */
	WAIT_BUYER_PAY("WAIT_BUYER_PAY"),
	/**
	 * 在指定时间段内未支付时关闭的交易； 在交易完成全额退款成功时关闭的交易。
	 */
	TRADE_CLOSED("TRADE_CLOSED"),
	/**
	 * 交易成功，且可对该交易做操作，如：多级分润、退款等。
	 */
	TRADE_SUCCESS("TRADE_SUCCESS"),
	/**
	 * 等待卖家收款（买家付款后，如果卖家账号被冻结）。
	 */
	TRADE_PENDING("TRADE_PENDING"),
	/**
	 * 交易成功且结束，即不可再做任何操作
	 */
	TRADE_FINISHED("TRADE_FINISHED");

	private String tradeStatus;

	private AlipayTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	/**
	 * @return the tradeStatus
	 */
	public String getTradeStatus() {
		return tradeStatus;
	}

	/**
	 * @param tradeStatus
	 * @return
	 */
	public static AlipayTradeStatus getEnum(String tradeStatus) {
		if (StringUtils.isBlank(tradeStatus)) {
			return null;
		}
		for (AlipayTradeStatus st : AlipayTradeStatus.values()) {
			if (st.getTradeStatus().equalsIgnoreCase(tradeStatus)) {
				return st;
			}
		}
		return null;
	}

}
