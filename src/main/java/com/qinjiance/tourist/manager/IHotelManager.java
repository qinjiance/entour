/**
 * 
 */
package com.qinjiance.tourist.manager;

import java.util.Date;
import java.util.List;

import com.qinjiance.tourist.constants.PayType;
import com.qinjiance.tourist.manager.exception.ManagerException;
import com.qinjiance.tourist.model.vo.HoltelDetPrice;
import com.qinjiance.tourist.model.vo.HoltelDetVo;
import com.qinjiance.tourist.model.vo.HotelBookRoomInfo;
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

	HoltelDetPrice checkAvailabilityAndPrice(Integer hotelId, Date checkIn, Date checkOut, String roomInfo,
			Integer hotelRoomTypeId) throws ManagerException;

	String prePay(Long orderId, Integer hotelId, Date checkIn, Date checkOut,
			String hotelBookRoomInfosStr, String roomInfo, Integer hotelRoomTypeId, String confirmEmail,
			Integer payTypeId, Long totalDaofu, Long totalYufu, Long userId, String bookCurrency)
			throws ManagerException;

	String getThirdPayUrl(PayType PayType, String orderId, Long payAmount, String subject, String description)
			throws ManagerException;
}
