/**
 * 
 */
package com.qinjiance.tourist.support.alipay.constant;

/**
 * @author "Jiance Qin"
 * 
 * @date 2015年4月8日
 * 
 * @time 下午4:33:53
 * 
 * @desc 签名方式
 * 
 */
public enum AlipaySignType {
	MD5(0, "MD5"), RSA(1, "RSA"), DSA(2, "DSA");

	private int signType;
	private String signParam;

	private AlipaySignType(int signType, String signParam) {
		this.signType = signType;
		this.signParam = signParam;
	}

	/**
	 * @return the signType
	 */
	public int getSignType() {
		return signType;
	}

	/**
	 * @return the signParam
	 */
	public String getSignParam() {
		return signParam;
	}

	/**
	 * @param signType
	 * @return
	 */
	public static AlipaySignType getEnum(int signType) {
		for (AlipaySignType st : AlipaySignType.values()) {
			if (st.getSignType() == signType) {
				return st;
			}
		}
		return null;
	}
}
