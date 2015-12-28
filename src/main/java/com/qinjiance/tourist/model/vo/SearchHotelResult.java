/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import java.util.List;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年10月24日 下午9:12:07
 *
 * @desc
 */
public class SearchHotelResult extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1033006269402703791L;

	private List<Integer> pricesCata;
	private List<Integer> starsCata;
	private List<Integer> hotelTypeCata;
	private List<HotelVo> hotelvos;

	/**
	 * 
	 */
	public SearchHotelResult() {
	}


	public List<Integer> getPricesCata() {
		return pricesCata;
	}


	public void setPricesCata(List<Integer> pricesCata) {
		this.pricesCata = pricesCata;
	}


	public List<Integer> getStarsCata() {
		return starsCata;
	}


	public void setStarsCata(List<Integer> starsCata) {
		this.starsCata = starsCata;
	}


	public List<Integer> getHotelTypeCata() {
		return hotelTypeCata;
	}


	public void setHotelTypeCata(List<Integer> hotelTypeCata) {
		this.hotelTypeCata = hotelTypeCata;
	}


	public List<HotelVo> getHotelvos() {
		return hotelvos;
	}

	public void setHotelvos(List<HotelVo> hotelvos) {
		this.hotelvos = hotelvos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
