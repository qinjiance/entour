/**
 * 
 */
package com.qinjiance.tourist.manager;

import java.util.Date;

import com.qinjiance.tourist.manager.exception.ManagerException;
import com.qinjiance.tourist.model.vo.ActivityDetVo;
import com.qinjiance.tourist.model.vo.ActivityPreBookVo;
import com.qinjiance.tourist.model.vo.SearchActivityResult;

/**
 * @author Jiance Qin
 * 
 * @Revision revision
 * 
 * @Date 2014-7-26
 * 
 * @Time 上午2:20:17
 * 
 */
public interface IActivityManager {

	SearchActivityResult searchActivities(String desLabel, String desValue, Date from, Date to, String searchStar,
			String searchCategory) throws ManagerException;

	ActivityDetVo getActDetail(Integer activityId, Date from, Date to) throws ManagerException;

	ActivityPreBookVo preBook(Integer activityId, Integer optionId, Date date, Integer adult, Integer children,
			Integer unit) throws ManagerException;;
}
