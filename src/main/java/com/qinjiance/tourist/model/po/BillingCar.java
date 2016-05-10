package com.qinjiance.tourist.model.po;

import java.util.Date;

import module.laohu.commons.model.BaseObject;

import org.apache.ibatis.type.Alias;

@Alias("billingCar")
public class BillingCar extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7081816808907682529L;

	private Long id;
	private String username;
	private Long userId;
	private Long transactionNum;
	private Long rgid;
	private String carType;
	private String carCompany;
	private String carName;
	private String carClass;
	private String carTrans;
	private Integer carAc;
	private Integer carMaxpax;
	private Integer carDoors;
	private Integer carLuggageLarge;
	private Integer carLuggageSmall;
	private Date pickUpDate;
	private Date dropOffDate;
	private String productId;
	private String programId;
	private String carInfos;
	private Long deltaPrice;
	private Long payPrice;
	private Long realPayAmount;
	private Long price;
	private Long payPriceAtproperty;
	private Long priceAtproperty;
	private String paymentType;
	private String payType;
	private String currency;
	private String payCurrency;
	private Double exchange;
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
	 * @return the realPayAmount
	 */
	public Long getRealPayAmount() {
		return realPayAmount;
	}

	/**
	 * @param realPayAmount the realPayAmount to set
	 */
	public void setRealPayAmount(Long realPayAmount) {
		this.realPayAmount = realPayAmount;
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
	 * @param rgid
	 *            the rgid to set
	 */
	public void setRgid(Long rgid) {
		this.rgid = rgid;
	}

	/**
	 * @return the carType
	 */
	public String getCarType() {
		return carType;
	}

	/**
	 * @param carType
	 *            the carType to set
	 */
	public void setCarType(String carType) {
		this.carType = carType;
	}

	/**
	 * @return the carCompany
	 */
	public String getCarCompany() {
		return carCompany;
	}

	/**
	 * @param carCompany
	 *            the carCompany to set
	 */
	public void setCarCompany(String carCompany) {
		this.carCompany = carCompany;
	}

	/**
	 * @return the carName
	 */
	public String getCarName() {
		return carName;
	}

	/**
	 * @param carName
	 *            the carName to set
	 */
	public void setCarName(String carName) {
		this.carName = carName;
	}

	/**
	 * @return the carClass
	 */
	public String getCarClass() {
		return carClass;
	}

	/**
	 * @param carClass
	 *            the carClass to set
	 */
	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}

	/**
	 * @return the carTrans
	 */
	public String getCarTrans() {
		return carTrans;
	}

	/**
	 * @param carTrans
	 *            the carTrans to set
	 */
	public void setCarTrans(String carTrans) {
		this.carTrans = carTrans;
	}

	/**
	 * @return the carAc
	 */
	public Integer getCarAc() {
		return carAc;
	}

	/**
	 * @param carAc
	 *            the carAc to set
	 */
	public void setCarAc(Integer carAc) {
		this.carAc = carAc;
	}

	/**
	 * @return the carMaxpax
	 */
	public Integer getCarMaxpax() {
		return carMaxpax;
	}

	/**
	 * @param carMaxpax
	 *            the carMaxpax to set
	 */
	public void setCarMaxpax(Integer carMaxpax) {
		this.carMaxpax = carMaxpax;
	}

	/**
	 * @return the carDoors
	 */
	public Integer getCarDoors() {
		return carDoors;
	}

	/**
	 * @param carDoors
	 *            the carDoors to set
	 */
	public void setCarDoors(Integer carDoors) {
		this.carDoors = carDoors;
	}

	/**
	 * @return the carLuggageLarge
	 */
	public Integer getCarLuggageLarge() {
		return carLuggageLarge;
	}

	/**
	 * @param carLuggageLarge
	 *            the carLuggageLarge to set
	 */
	public void setCarLuggageLarge(Integer carLuggageLarge) {
		this.carLuggageLarge = carLuggageLarge;
	}

	/**
	 * @return the carLuggageSmall
	 */
	public Integer getCarLuggageSmall() {
		return carLuggageSmall;
	}

	/**
	 * @param carLuggageSmall
	 *            the carLuggageSmall to set
	 */
	public void setCarLuggageSmall(Integer carLuggageSmall) {
		this.carLuggageSmall = carLuggageSmall;
	}

	/**
	 * @return the pickUpDate
	 */
	public Date getPickUpDate() {
		return pickUpDate;
	}

	/**
	 * @param pickUpDate
	 *            the pickUpDate to set
	 */
	public void setPickUpDate(Date pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	/**
	 * @return the dropOffDate
	 */
	public Date getDropOffDate() {
		return dropOffDate;
	}

	/**
	 * @param dropOffDate
	 *            the dropOffDate to set
	 */
	public void setDropOffDate(Date dropOffDate) {
		this.dropOffDate = dropOffDate;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the programId
	 */
	public String getProgramId() {
		return programId;
	}

	/**
	 * @param programId
	 *            the programId to set
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}

	/**
	 * @return the carInfos
	 */
	public String getCarInfos() {
		return carInfos;
	}

	/**
	 * @param carInfos
	 *            the carInfos to set
	 */
	public void setCarInfos(String carInfos) {
		this.carInfos = carInfos;
	}

	/**
	 * @return the deltaPrice
	 */
	public Long getDeltaPrice() {
		return deltaPrice;
	}

	/**
	 * @param deltaPrice
	 *            the deltaPrice to set
	 */
	public void setDeltaPrice(Long deltaPrice) {
		this.deltaPrice = deltaPrice;
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
