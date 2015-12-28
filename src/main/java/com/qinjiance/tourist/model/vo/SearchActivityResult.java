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
/**
 * @author Administrator
 *
 * @datetime 2015年12月15日 上午12:21:02
 *
 * @desc
 */
public class SearchActivityResult extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1033006269402703791L;

	private List<Integer> starList;
	private Map<String, Integer> categoryMap;
	private List<ActivityCategoryVo> actVos;

	/**
	 * 
	 */
	public SearchActivityResult() {
	}

	public Map<String, Integer> getCategoryMap() {
		return categoryMap;
	}

	public void setCategoryMap(Map<String, Integer> categoryMap) {
		this.categoryMap = categoryMap;
	}

	public List<Integer> getStarList() {
		return starList;
	}

	public void setStarList(List<Integer> starList) {
		this.starList = starList;
	}

	public List<ActivityCategoryVo> getActVos() {
		return actVos;
	}

	public void setActVos(List<ActivityCategoryVo> actVos) {
		this.actVos = actVos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
