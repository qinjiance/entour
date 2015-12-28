/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import java.util.List;
import java.util.Map;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年10月24日 下午9:12:07
 *
 * @desc
 */
public class SearchCarResult extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1033006269402703791L;

	private List<Integer> typesCata;
	private List<Integer> catalogCata;
	private Map<String, Integer> companyCata;
	private List<CarVo> carVos;

	/**
	 * 
	 */
	public SearchCarResult() {
	}

	public List<Integer> getTypesCata() {
		return typesCata;
	}

	public void setTypesCata(List<Integer> typesCata) {
		this.typesCata = typesCata;
	}

	public List<Integer> getCatalogCata() {
		return catalogCata;
	}

	public void setCatalogCata(List<Integer> catalogCata) {
		this.catalogCata = catalogCata;
	}

	public Map<String, Integer> getCompanyCata() {
		return companyCata;
	}

	public void setCompanyCata(Map<String, Integer> companyCata) {
		this.companyCata = companyCata;
	}

	public List<CarVo> getCarVos() {
		return carVos;
	}

	public void setCarVos(List<CarVo> carVos) {
		this.carVos = carVos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
