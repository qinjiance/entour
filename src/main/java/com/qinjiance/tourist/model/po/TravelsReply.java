/**
 * 
 */
package com.qinjiance.tourist.model.po;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import module.laohu.commons.model.BaseObject;

/**
 * @author Jiance Qin
 * 
 * @Revision revision
 * 
 * @Date 2014-9-1
 * 
 * @Time 下午11:00:58
 * 
 */
@Alias("travelsReply")
public class TravelsReply extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7291321900692071282L;
	private Long id;
	private Long travelsId;
	private Long userId;
	private String content;
	private Date createTime;
	private Date updateTime;

	/**
	 * 
	 */
	public TravelsReply() {

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
	 * @return the travelsId
	 */
	public Long getTravelsId() {

		return travelsId;
	}

	/**
	 * @param travelsId
	 *            the travelsId to set
	 */
	public void setTravelsId(Long travelsId) {

		this.travelsId = travelsId;
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
	 * @return the content
	 */
	public String getContent() {

		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {

		this.content = content;
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
	 * @return the updateTime
	 */
	public Date getUpdateTime() {

		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {

		this.updateTime = updateTime;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {

		return serialVersionUID;
	}

}
