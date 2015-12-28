/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import java.util.List;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年11月29日 上午1:13:09
 *
 * @desc
 */
public class OccupancyVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4437791279731702235L;
	private String occuId;
	private String occuPubPrice;
	private String taxPubPrice;
	private String bedding;
	private Integer bedType;
	private Integer bedNum;
	private List<SupplementPrice> supplements;
	private List<BoardbasePrice> boardbases;
	private List<String> priceBreakdown;

	/**
	 * 
	 */
	public OccupancyVo() {

	}

	public List<String> getPriceBreakdown() {
		return priceBreakdown;
	}

	public void setPriceBreakdown(List<String> priceBreakdown) {
		this.priceBreakdown = priceBreakdown;
	}

	public String getOccuId() {
		return occuId;
	}

	public void setOccuId(String occuId) {
		this.occuId = occuId;
	}

	public String getOccuPubPrice() {
		return occuPubPrice;
	}

	public void setOccuPubPrice(String occuPubPrice) {
		this.occuPubPrice = occuPubPrice;
	}

	public String getTaxPubPrice() {
		return taxPubPrice;
	}

	public void setTaxPubPrice(String taxPubPrice) {
		this.taxPubPrice = taxPubPrice;
	}

	public String getBedding() {
		return bedding;
	}

	public void setBedding(String bedding) {
		this.bedding = bedding;
	}

	public Integer getBedType() {
		return bedType;
	}

	public void setBedType(Integer bedType) {
		this.bedType = bedType;
	}

	public Integer getBedNum() {
		return bedNum;
	}

	public void setBedNum(Integer bedNum) {
		this.bedNum = bedNum;
	}

	public List<SupplementPrice> getSupplements() {
		return supplements;
	}

	public void setSupplements(List<SupplementPrice> supplements) {
		this.supplements = supplements;
	}

	public List<BoardbasePrice> getBoardbases() {
		return boardbases;
	}

	public void setBoardbases(List<BoardbasePrice> boardbases) {
		this.boardbases = boardbases;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
