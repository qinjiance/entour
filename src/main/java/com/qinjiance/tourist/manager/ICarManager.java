/**
 * 
 */
package com.qinjiance.tourist.manager;

import java.util.Date;

import com.qinjiance.tourist.manager.exception.ManagerException;
import com.qinjiance.tourist.model.vo.CarDetVo;
import com.qinjiance.tourist.model.vo.SearchCarResult;

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
public interface ICarManager {

	SearchCarResult searchCars(String pickUp, String dropOff, Date pickUpDate, Date dropOffDate, Integer pickUpHour,
			Integer dropOffHour, Integer driverAge, String searchType, String searchClass, String searchCompany)
					throws ManagerException;

	CarDetVo getDet(String productId, Integer pickUpStationId, Integer dropOffStationId) throws ManagerException;
}
