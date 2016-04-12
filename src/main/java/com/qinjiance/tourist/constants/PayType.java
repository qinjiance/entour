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
	ALIPAY(1);

	private final int payTypeId;

	private PayType(int payTypeId) {
		this.payTypeId = payTypeId;
	}

	public int getPayTypeId() {
		return payTypeId;
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
