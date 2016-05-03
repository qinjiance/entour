package com.qinjiance.tourist.model.po;

import java.util.Date;

import module.laohu.commons.model.BaseObject;

import org.apache.ibatis.type.Alias;

@Alias("billingHotel")
public class BillingHotel extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7081816808907682529L;

	private Long id;
	private String username;
	private Long userId;
	private Long transactionNum;
	private Long rgid;
	private String hotelCountry;
	private String hotelCity;
	private Integer hotelId;
	private String hotelName;
	private String hotelAddress;
	private Integer roomTypeId;
	private String roomType;
	private Integer roomNum;
	private Date checkIn;
	private Date checkOut;
	private String roomInfos;
	private String roomInfo;
	private Long payPrice;
	private Long price;
	private Long payPriceAtproperty;
	private Long priceAtproperty;
	private String paymentType;
	private String payType;
	private String contactInfo;
	private Integer deltaPrice;
	private String currency;
	private String payCurrency;
	private Double exchange;
	private Boolean isOnlyAvailable;
	private String confirmationEmail;
	private String confirmationLogo;
	private Integer payStatus;
	private String gatewayOrderId;
	private String payFailedInfo;
	private Integer chargeStatus;
	private String chargeFailedInfo;
	private Date createTime;
	private Date payTime;
	private Date chargeTime;

	/**
	 * @return the roomInfo
	 */
	public String getRoomInfo() {
		return roomInfo;
	}

	/**
	 * @param roomInfo
	 *            the roomInfo to set
	 */
	public void setRoomInfo(String roomInfo) {
		this.roomInfo = roomInfo;
	}

	/**
	 * @return the checkIn
	 */
	public Date getCheckIn() {
		return checkIn;
	}

	/**
	 * @param checkIn
	 *            the checkIn to set
	 */
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	/**
	 * @return the checkOut
	 */
	public Date getCheckOut() {
		return checkOut;
	}

	/**
	 * @param checkOut
	 *            the checkOut to set
	 */
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	/**
	 * @return the exchange
	 */
	public Double getExchange() {
		return exchange;
	}

	/**
	 * @param exchange
	 *            the exchange to set
	 */
	public void setExchange(Double exchange) {
		this.exchange = exchange;
	}

	/**
	 * @return the payPrice
	 */
	public Long getPayPrice() {
		return payPrice;
	}

	/**
	 * @param payPrice
	 *            the payPrice to set
	 */
	public void setPayPrice(Long payPrice) {
		this.payPrice = payPrice;
	}

	/**
	 * @return the payPriceAtproperty
	 */
	public Long getPayPriceAtproperty() {
		return payPriceAtproperty;
	}

	/**
	 * @param payPriceAtproperty
	 *            the payPriceAtproperty to set
	 */
	public void setPayPriceAtproperty(Long payPriceAtproperty) {
		this.payPriceAtproperty = payPriceAtproperty;
	}

	/**
	 * @return the payCurrency
	 */
	public String getPayCurrency() {
		return payCurrency;
	}

	/**
	 * @param payCurrency
	 *            the payCurrency to set
	 */
	public void setPayCurrency(String payCurrency) {
		this.payCurrency = payCurrency;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the transactionNum
	 */
	public Long getTransactionNum() {
		return transactionNum;
	}

	/**
	 * @param transactionNum
	 *            the transactionNum to set
	 */
	public void setTransactionNum(Long transactionNum) {
		this.transactionNum = transactionNum;
	}


	/**
	 * @return the rgid
	 */
	public Long getRgid() {
		return rgid;
	}

	/**
	 * @param rgid the rgid to set
	 */
	public void setRgid(Long rgid) {
		this.rgid = rgid;
	}

	/**
	 * @return the hotelCountry
	 */
	public String getHotelCountry() {
		return hotelCountry;
	}

	/**
	 * @param hotelCountry
	 *            the hotelCountry to set
	 */
	public void setHotelCountry(String hotelCountry) {
		this.hotelCountry = hotelCountry;
	}

	/**
	 * @return the hotelCity
	 */
	public String getHotelCity() {
		return hotelCity;
	}

	/**
	 * @param hotelCity
	 *            the hotelCity to set
	 */
	public void setHotelCity(String hotelCity) {
		this.hotelCity = hotelCity;
	}

	/**
	 * @return the hotelId
	 */
	public Integer getHotelId() {
		return hotelId;
	}

	/**
	 * @param hotelId
	 *            the hotelId to set
	 */
	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	/**
	 * @return the hotelName
	 */
	public String getHotelName() {
		return hotelName;
	}

	/**
	 * @param hotelName
	 *            the hotelName to set
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	/**
	 * @return the hotelAddress
	 */
	public String getHotelAddress() {
		return hotelAddress;
	}

	/**
	 * @param hotelAddress
	 *            the hotelAddress to set
	 */
	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	/**
	 * @return the roomTypeId
	 */
	public Integer getRoomTypeId() {
		return roomTypeId;
	}

	/**
	 * @param roomTypeId
	 *            the roomTypeId to set
	 */
	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	/**
	 * @return the roomType
	 */
	public String getRoomType() {
		return roomType;
	}

	/**
	 * @param roomType
	 *            the roomType to set
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	/**
	 * @return the roomNum
	 */
	public Integer getRoomNum() {
		return roomNum;
	}

	/**
	 * @param roomNum
	 *            the roomNum to set
	 */
	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}

	/**
	 * @return the roomInfos
	 */
	public String getRoomInfos() {
		return roomInfos;
	}

	/**
	 * @param roomInfos
	 *            the roomInfos to set
	 */
	public void setRoomInfos(String roomInfos) {
		this.roomInfos = roomInfos;
	}

	/**
	 * @return the price
	 */
	public Long getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Long price) {
		this.price = price;
	}

	/**
	 * @return the priceAtproperty
	 */
	public Long getPriceAtproperty() {
		return priceAtproperty;
	}

	/**
	 * @param priceAtproperty
	 *            the priceAtproperty to set
	 */
	public void setPriceAtproperty(Long priceAtproperty) {
		this.priceAtproperty = priceAtproperty;
	}

	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType
	 *            the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @return the payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * @param payType
	 *            the payType to set
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * @return the contactInfo
	 */
	public String getContactInfo() {
		return contactInfo;
	}

	/**
	 * @param contactInfo
	 *            the contactInfo to set
	 */
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	/**
	 * @return the deltaPrice
	 */
	public Integer getDeltaPrice() {
		return deltaPrice;
	}

	/**
	 * @param deltaPrice
	 *            the deltaPrice to set
	 */
	public void setDeltaPrice(Integer deltaPrice) {
		this.deltaPrice = deltaPrice;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the isOnlyAvailable
	 */
	public Boolean getIsOnlyAvailable() {
		return isOnlyAvailable;
	}

	/**
	 * @param isOnlyAvailable
	 *            the isOnlyAvailable to set
	 */
	public void setIsOnlyAvailable(Boolean isOnlyAvailable) {
		this.isOnlyAvailable = isOnlyAvailable;
	}

	/**
	 * @return the confirmationEmail
	 */
	public String getConfirmationEmail() {
		return confirmationEmail;
	}

	/**
	 * @param confirmationEmail
	 *            the confirmationEmail to set
	 */
	public void setConfirmationEmail(String confirmationEmail) {
		this.confirmationEmail = confirmationEmail;
	}

	/**
	 * @return the confirmationLogo
	 */
	public String getConfirmationLogo() {
		return confirmationLogo;
	}

	/**
	 * @param confirmationLogo
	 *            the confirmationLogo to set
	 */
	public void setConfirmationLogo(String confirmationLogo) {
		this.confirmationLogo = confirmationLogo;
	}

	/**
	 * @return the payStatus
	 */
	public Integer getPayStatus() {
		return payStatus;
	}

	/**
	 * @param payStatus
	 *            the payStatus to set
	 */
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * @return the gatewayOrderId
	 */
	public String getGatewayOrderId() {
		return gatewayOrderId;
	}

	/**
	 * @param gatewayOrderId
	 *            the gatewayOrderId to set
	 */
	public void setGatewayOrderId(String gatewayOrderId) {
		this.gatewayOrderId = gatewayOrderId;
	}

	/**
	 * @return the payFailedInfo
	 */
	public String getPayFailedInfo() {
		return payFailedInfo;
	}

	/**
	 * @param payFailedInfo
	 *            the payFailedInfo to set
	 */
	public void setPayFailedInfo(String payFailedInfo) {
		this.payFailedInfo = payFailedInfo;
	}

	/**
	 * @return the chargeStatus
	 */
	public Integer getChargeStatus() {
		return chargeStatus;
	}

	/**
	 * @param chargeStatus
	 *            the chargeStatus to set
	 */
	public void setChargeStatus(Integer chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	/**
	 * @return the chargeFailedInfo
	 */
	public String getChargeFailedInfo() {
		return chargeFailedInfo;
	}

	/**
	 * @param chargeFailedInfo
	 *            the chargeFailedInfo to set
	 */
	public void setChargeFailedInfo(String chargeFailedInfo) {
		this.chargeFailedInfo = chargeFailedInfo;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the payTime
	 */
	public Date getPayTime() {
		return payTime;
	}

	/**
	 * @param payTime
	 *            the payTime to set
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	/**
	 * @return the chargeTime
	 */
	public Date getChargeTime() {
		return chargeTime;
	}

	/**
	 * @param chargeTime
	 *            the chargeTime to set
	 */
	public void setChargeTime(Date chargeTime) {
		this.chargeTime = chargeTime;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
