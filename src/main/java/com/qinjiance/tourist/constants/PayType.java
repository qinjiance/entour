package com.qinjiance.tourist.constants;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年7月23日
 * 
 * @time 上午10:54:08
 * 
 * @desc 支付方式ID
 * 
 */
public enum PayType {

	/**
	 * 支付宝
	 */
	ALIPAY("支付宝", 1);

	private final String payType;
	private final int payTypeId;

	private PayType(String payType, int payTypeId) {
		this.payTypeId = payTypeId;
		this.payType = payType;
	}

	public int getPayTypeId() {
		return payTypeId;
	}

	/**
	 * @return the payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * @param payTypeId
	 * @return
	 */
	public static PayType getEnum(Integer payTypeId) {
		if (payTypeId == null) {
			return null;
		}
		for (PayType enumItem : PayType.values()) {
			if (enumItem.getPayTypeId() == payTypeId.intValue()) {
				return enumItem;
			}
		}
		return null;
	}
}
