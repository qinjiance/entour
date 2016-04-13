/**
 * 
 */
package com.qinjiance.tourist.support.alipay.model;

import module.laohu.commons.model.BaseObject;

import com.qinjiance.tourist.support.alipay.constant.AlipaySignType;

/**
 * @author "Jiance Qin"
 * 
 * @date 2015年4月3日
 * 
 * @time 下午3:52:31
 * 
 * @desc
 * 
 */
public class AlipayAccountConfig extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3056419097280699524L;

	private String partnerId;
	private String signKey;
	private String clientPrivateKey;
	private String serverPublicKey;
	private String wapServerPublicKey;
	private AlipaySignType signType;
	private Boolean antiFlag;
	private String sellerId;
	private String sellerAccountName;

	/**
	 * 
	 */
	public AlipayAccountConfig() {

	}

	/**
	 * @return the wapServerPublicKey
	 */
	public String getWapServerPublicKey() {
		return wapServerPublicKey;
	}

	/**
	 * @param wapServerPublicKey
	 *            the wapServerPublicKey to set
	 */
	public void setWapServerPublicKey(String wapServerPublicKey) {
		this.wapServerPublicKey = wapServerPublicKey;
	}

	/**
	 * @return the sellerAccountName
	 */
	public String getSellerAccountName() {
		return sellerAccountName;
	}

	/**
	 * @param sellerAccountName
	 *            the sellerAccountName to set
	 */
	public void setSellerAccountName(String sellerAccountName) {
		this.sellerAccountName = sellerAccountName;
	}

	/**
	 * @return the partnerId
	 */
	public String getPartnerId() {
		return partnerId;
	}

	/**
	 * @param partnerId
	 *            the partnerId to set
	 */
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * @return the signKey
	 */
	public String getSignKey() {
		return signKey;
	}

	/**
	 * @param signKey
	 *            the signKey to set
	 */
	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}

	/**
	 * @return the clientPrivateKey
	 */
	public String getClientPrivateKey() {
		return clientPrivateKey;
	}

	/**
	 * @param clientPrivateKey
	 *            the clientPrivateKey to set
	 */
	public void setClientPrivateKey(String clientPrivateKey) {
		this.clientPrivateKey = clientPrivateKey;
	}

	/**
	 * @return the serverPublicKey
	 */
	public String getServerPublicKey() {
		return serverPublicKey;
	}

	/**
	 * @param serverPublicKey
	 *            the serverPublicKey to set
	 */
	public void setServerPublicKey(String serverPublicKey) {
		this.serverPublicKey = serverPublicKey;
	}

	/**
	 * @return the signType
	 */
	public AlipaySignType getSignType() {
		return signType;
	}

	/**
	 * @param signType
	 *            the signType to set
	 */
	public void setSignType(AlipaySignType signType) {
		this.signType = signType;
	}

	/**
	 * @return the antiFlag
	 */
	public Boolean getAntiFlag() {
		return antiFlag;
	}

	/**
	 * @param antiFlag
	 *            the antiFlag to set
	 */
	public void setAntiFlag(Boolean antiFlag) {
		this.antiFlag = antiFlag;
	}

	/**
	 * @return the sellerId
	 */
	public String getSellerId() {
		return sellerId;
	}

	/**
	 * @param sellerId
	 *            the sellerId to set
	 */
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
