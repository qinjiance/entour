/**
 * 
 */
package com.qinjiance.tourist.manager;

import java.util.Date;

import com.qinjiance.tourist.manager.exception.ManagerException;
import com.qinjiance.tourist.model.vo.HoltelDetPrice;
import com.qinjiance.tourist.model.vo.HoltelDetVo;
import com.qinjiance.tourist.model.vo.SearchHotelResult;

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
public interface IHotelManager {

	SearchHotelResult searchHotels(String desLabel, String desValue, Date checkIn, Date checkOut, String roomInfo,
			Integer searchStars, Integer searchPrices, String searchHotelCat) throws ManagerException;

	HoltelDetVo getHotelDetail(Integer hotelId, Date checkIn, Date checkOut, String roomInfo) throws ManagerException;
	
	HoltelDetPrice checkAvailabilityAndPrice(Integer hotelId, Date checkIn, Date checkOut, String roomInfo,Integer hotelRoomTypeId) throws ManagerException;
	
	boolean checkAvailability(Integer hotelId, Date checkIn, Date checkOut, String roomInfo,
			Integer HotelRoomTypeId,Integer boardbaseId,String boardbasePrice,
			String suppJsonString,String bedding,String totalPrice)throws ManagerException;
}
