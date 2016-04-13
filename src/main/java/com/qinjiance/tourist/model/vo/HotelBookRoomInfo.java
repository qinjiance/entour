/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import java.util.List;

import module.laohu.commons.model.BaseObject;

/**
 * @author "Jiance Qin"
 * 
 * @date 2016年4月12日
 * 
 * @time 上午7:55:31
 * 
 * @desc
 * 
 */
public class HotelBookRoomInfo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4491145363852725364L;

	private Integer roomId;
	private String occuId;
	private Integer bbId;
	private List<Integer> suppIds;
	private Long daofu;
	private Long yufu;
	private String firstname;
	private String lastname;
	private String phonenumber;

	/**
	 * 
	 */
	public HotelBookRoomInfo() {

	}

	/**
	 * @return the roomId
	 */
	public Integer getRoomId() {
		return roomId;
	}

	/**
	 * @param roomId
	 *            the roomId to set
	 */
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	/**
	 * @return the occuId
	 */
	public String getOccuId() {
		return occuId;
	}

	/**
	 * @param occuId
	 *            the occuId to set
	 */
	public void setOccuId(String occuId) {
		this.occuId = occuId;
	}

	/**
	 * @return the bbId
	 */
	public Integer getBbId() {
		return bbId;
	}

	/**
	 * @param bbId
	 *            the bbId to set
	 */
	public void setBbId(Integer bbId) {
		this.bbId = bbId;
	}

	/**
	 * @return the suppIds
	 */
	public List<Integer> getSuppIds() {
		return suppIds;
	}

	/**
	 * @param suppIds
	 *            the suppIds to set
	 */
	public void setSuppIds(List<Integer> suppIds) {
		this.suppIds = suppIds;
	}

	/**
	 * @return the daofu
	 */
	public Long getDaofu() {
		return daofu;
	}

	/**
	 * @param daofu
	 *            the daofu to set
	 */
	public void setDaofu(Long daofu) {
		this.daofu = daofu;
	}

	/**
	 * @return the yufu
	 */
	public Long getYufu() {
		return yufu;
	}

	/**
	 * @param yufu
	 *            the yufu to set
	 */
	public void setYufu(Long yufu) {
		this.yufu = yufu;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the phonenumber
	 */
	public String getPhonenumber() {
		return phonenumber;
	}

	/**
	 * @param phonenumber
	 *            the phonenumber to set
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
