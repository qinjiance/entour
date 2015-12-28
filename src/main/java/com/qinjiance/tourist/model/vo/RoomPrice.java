package com.qinjiance.tourist.model.vo;

import java.util.List;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年11月29日 上午1:23:57
 *
 * @desc
 */
public class RoomPrice extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7305606712321677963L;

	private Integer roomId;
	private Integer adultNum;
	private Integer childNum;
	private List<Integer> childAges;
	private List<OccupancyVo> occupancyVos;

	public RoomPrice() {
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getAdultNum() {
		return adultNum;
	}

	public void setAdultNum(Integer adultNum) {
		this.adultNum = adultNum;
	}

	public Integer getChildNum() {
		return childNum;
	}

	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}

	public List<Integer> getChildAges() {
		return childAges;
	}

	public void setChildAges(List<Integer> childAges) {
		this.childAges = childAges;
	}

	public List<OccupancyVo> getOccupancyVos() {
		return occupancyVos;
	}

	public void setOccupancyVos(List<OccupancyVo> occupancyVos) {
		this.occupancyVos = occupancyVos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
