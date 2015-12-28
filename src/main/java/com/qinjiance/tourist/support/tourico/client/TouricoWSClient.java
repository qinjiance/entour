/**
 * 
 */
package com.qinjiance.tourist.support.tourico.client;

import java.rmi.RemoteException;

import javax.annotation.PostConstruct;

import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.ADBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qinjiance.tourist.manager.exception.ManagerException;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.ActivitiesSelectedOptions;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.ActivityDetails;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.ActivityId;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.ActivityPreBook;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.ArrayOfActivityId;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.Category;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.GetActivityDetails;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.PreBookRequest;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.SearchActivityByActivityIds;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.SearchActivityByActivityIdsRequest;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.SearchActivityByDestinationIds;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.SearchActivityByDestinationIdsRequest;
import com.qinjiance.tourist.support.tourico.activity.IActivityBookFlow_ActivityPreBook_WSFault_FaultMessage;
import com.qinjiance.tourist.support.tourico.activity.IActivityBookFlow_GetActivityDetails_WSFault_FaultMessage;
import com.qinjiance.tourist.support.tourico.activity.IActivityBookFlow_SearchActivityByActivityIds_WSFault_FaultMessage;
import com.qinjiance.tourist.support.tourico.activity.IActivityBookFlow_SearchActivityByDestinationIds_WSFault_FaultMessage;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.Car;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.CompanyRules;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.GetRulesAndRestrictionsRequest;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.LoginHeader;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.LoginHeaderE;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.SearchCarInfo;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.SearchCars;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.SearchCarsByAirportCode;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.SearchCarsRequest;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.SelectStation;
import com.qinjiance.tourist.support.tourico.car.CarWebServiceStub.SelectStationsRequest;
import com.qinjiance.tourist.support.tourico.car.ICarService_GetRulesAndRestrictions_CustomCarExceptionFault_FaultMessage;
import com.qinjiance.tourist.support.tourico.car.ICarService_SearchCarsByAirportCode_CustomCarExceptionFault_FaultMessage;
import com.qinjiance.tourist.support.tourico.car.ICarService_SearchCars_CustomCarExceptionFault_FaultMessage;
import com.qinjiance.tourist.support.tourico.car.ICarService_SelectStations_CustomCarExceptionFault_FaultMessage;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.ArrayOfHotelID;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.AuthenticationHeader;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.AuthenticationHeaderE;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.CheckAvailabilityAndPrices;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.Culture;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.GetHotelDetailsV3;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.Hotel;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.HotelID;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.Hotel_type0;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.SearchHotels;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.SearchHotelsById;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.SearchHotelsByIdRequest;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.SearchRequest;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.TWS_HotelDetailsV3;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.TWS_HotelDetailsV3.Factory;
import com.qinjiance.tourist.support.tourico.hotel.IHotelFlow_CheckAvailabilityAndPrices_WSFault_FaultMessage;
import com.qinjiance.tourist.support.tourico.hotel.IHotelFlow_GetHotelDetailsV3_WSFault_FaultMessage;
import com.qinjiance.tourist.support.tourico.hotel.IHotelFlow_SearchHotelsById_WSFault_FaultMessage;
import com.qinjiance.tourist.support.tourico.hotel.IHotelFlow_SearchHotels_WSFault_FaultMessage;

/**
 * @author Administrator
 * 
 * @datetime 2015年11月4日 上午1:40:39
 * 
 * @desc
 */
@Service
public class TouricoWSClient {

	protected static final Logger logger = LoggerFactory.getLogger(TouricoWSClient.class);

	private static HotelFlowStub hotelStub;
	private static CarWebServiceStub carStub;
	private static ActivityBookFlowStub actStub;

	/**
	 * 
	 */
	public TouricoWSClient() {

	}

	@PostConstruct
	private void init() {
		try {
			hotelStub = new HotelFlowStub();
			carStub = new CarWebServiceStub();
			actStub = new ActivityBookFlowStub();
		} catch (AxisFault e) {
			logger.error("AxisFault: ", e);
		}
	}

	public Hotel[] searchHotels(SearchRequest request) throws ManagerException {
		Hotel[] hs = null;
		try {
			SearchHotels sh = new SearchHotels();
			sh.setRequest(request);
			AuthenticationHeaderE e = new AuthenticationHeaderE();
			AuthenticationHeader l = new AuthenticationHeader();
			l.setCulture(Culture.zh_CN);
			l.setLoginName("hosent");
			l.setPassword("111111");
			l.setVersion("7.123");
			e.setAuthenticationHeader(l);
			hs = hotelStub.searchHotels(sh, e).getSearchHotelsResult().getHotelList().getHotel();
		} catch (RemoteException e) {
			logger.error("RemoteException: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		} catch (IHotelFlow_SearchHotels_WSFault_FaultMessage e) {
			logger.error("IHotelFlow_SearchHotels_WSFault_FaultMessage: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		}
		return hs;
	}

	public Hotel[] getHotels(SearchHotelsByIdRequest request) throws ManagerException {
		Hotel[] hs = null;
		try {
			SearchHotelsById sh = new SearchHotelsById();
			sh.setRequest(request);
			AuthenticationHeaderE e = new AuthenticationHeaderE();
			AuthenticationHeader l = new AuthenticationHeader();
			l.setCulture(Culture.zh_CN);
			l.setLoginName("hosent");
			l.setPassword("111111");
			l.setVersion("7.123");
			e.setAuthenticationHeader(l);
			hs = hotelStub.searchHotelsById(sh, e).getSearchHotelsByIdResult().getHotelList().getHotel();
		} catch (RemoteException e) {
			logger.error("RemoteException: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		} catch (IHotelFlow_SearchHotelsById_WSFault_FaultMessage e) {
			logger.error("IHotelFlow_SearchHotelsById_WSFault_FaultMessage: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		}
		return hs;
	}

	public Hotel[] checkAvailability(SearchHotelsByIdRequest request) throws ManagerException {
		Hotel[] hs = null;
		try {
			CheckAvailabilityAndPrices sh = new CheckAvailabilityAndPrices();
			sh.setRequest(request);
			AuthenticationHeaderE e = new AuthenticationHeaderE();
			AuthenticationHeader l = new AuthenticationHeader();
			l.setCulture(Culture.zh_CN);
			l.setLoginName("hosent");
			l.setPassword("111111");
			l.setVersion("7.123");
			e.setAuthenticationHeader(l);
			hs = hotelStub.checkAvailabilityAndPrices(sh, e).getCheckAvailabilityAndPricesResult().getHotelList()
					.getHotel();
		} catch (RemoteException e) {
			logger.error("RemoteException: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		} catch (IHotelFlow_CheckAvailabilityAndPrices_WSFault_FaultMessage e) {
			logger.error("IHotelFlow_CheckAvailabilityAndPrices_WSFault_FaultMessage: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		}
		return hs;
	}

	public Hotel_type0 getHotelDetail(int hotelId) throws ManagerException {
		Hotel_type0 hs = null;
		try {
			GetHotelDetailsV3 sh = new GetHotelDetailsV3();
			ArrayOfHotelID param = new ArrayOfHotelID();
			HotelID id = new HotelID();
			id.setId(hotelId);
			param.addHotelID(id);
			sh.setHotelIds(param);
			AuthenticationHeaderE e = new AuthenticationHeaderE();
			AuthenticationHeader l = new AuthenticationHeader();
			l.setCulture(Culture.zh_CN);
			l.setLoginName("hosent");
			l.setPassword("111111");
			l.setVersion("7.123");
			e.setAuthenticationHeader(l);
			hs = Factory.parse(
					hotelStub.getHotelDetailsV3(sh, e).getGetHotelDetailsV3Result()
							.getPullParser(TWS_HotelDetailsV3.MY_QNAME)).getTWS_HotelDetailsV3Choice()[0].getHotel();
		} catch (ADBException e1) {
			logger.error("ADBException: ", e1);
			// throw new ManagerException("网络通信错误，请稍后再试");
		} catch (IHotelFlow_GetHotelDetailsV3_WSFault_FaultMessage e) {
			logger.error("IHotelFlow_GetHotelDetailsV3_WSFault_FaultMessage: ", e);
			// throw new ManagerException("网络通信错误，请稍后再试");
		} catch (RemoteException e) {
			logger.error("RemoteException: ", e);
			// throw new ManagerException("网络通信错误，请稍后再试");
		} catch (Exception e) {
			logger.error("Exception: ", e);
			// throw new ManagerException("网络通信错误，请稍后再试");
		}
		return hs;
	}

	public Car[] searchCars(SearchCarsRequest request) throws ManagerException {
		Car[] hs = null;
		try {
			SearchCars sh = new SearchCars();
			sh.setSearchCarsObj(request);
			LoginHeaderE e = new LoginHeaderE();
			LoginHeader l = new LoginHeader();
			l.setCulture("zh_CN");
			l.setUserName("hosent");
			l.setPassword("111111");
			l.setVersion("7.123");
			e.setLoginHeader(l);
			hs = carStub.searchCars(sh, e).getCarResults().getCar();
		} catch (RemoteException e) {
			logger.error("RemoteException: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		} catch (ICarService_SearchCars_CustomCarExceptionFault_FaultMessage e) {
			logger.error("ICarService_SearchCars_CustomCarExceptionFault_FaultMessage: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		}
		return hs;
	}

	public SearchCarInfo[] searchCarsByAirportCode(SearchCarsRequest request) throws ManagerException {
		SearchCarInfo[] hs = null;
		try {
			SearchCarsByAirportCode sh = new SearchCarsByAirportCode();
			sh.setSearchCarsObj(request);
			LoginHeaderE e = new LoginHeaderE();
			LoginHeader l = new LoginHeader();
			l.setCulture("zh_CN");
			l.setUserName("hosent");
			l.setPassword("111111");
			l.setVersion("7.123");
			e.setLoginHeader(l);
			hs = carStub.searchCarsByAirportCode(sh, e).getCarResults().getSearchCarInfo();
		} catch (RemoteException e) {
			logger.error("RemoteException: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		} catch (ICarService_SearchCarsByAirportCode_CustomCarExceptionFault_FaultMessage e) {
			logger.error("ICarService_SearchCars_CustomCarExceptionFault_FaultMessage: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		}
		return hs;
	}

	public Car selectStation(SelectStationsRequest request) throws ManagerException {
		Car hs = null;
		try {
			SelectStation sh = new SelectStation();
			sh.setSelectStationObj(request);
			LoginHeaderE e = new LoginHeaderE();
			LoginHeader l = new LoginHeader();
			l.setCulture("zh_CN");
			l.setUserName("hosent");
			l.setPassword("111111");
			l.setVersion("7.123");
			e.setLoginHeader(l);
			hs = carStub.selectStations(sh, e).getCar();
		} catch (RemoteException e) {
			logger.error("RemoteException: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		} catch (ICarService_SelectStations_CustomCarExceptionFault_FaultMessage e) {
			logger.error("ICarService_SearchCars_CustomCarExceptionFault_FaultMessage: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		}
		return hs;
	}

	public CompanyRules getRulesAndRestric(GetRulesAndRestrictionsRequest request) throws ManagerException {
		CompanyRules hs = null;
		try {
			LoginHeaderE e = new LoginHeaderE();
			LoginHeader l = new LoginHeader();
			l.setCulture("zh_CN");
			l.setUserName("hosent");
			l.setPassword("111111");
			l.setVersion("7.123");
			e.setLoginHeader(l);
			hs = carStub.getRulesAndRestrictions(request, e).getRules();
		} catch (RemoteException e) {
			logger.error("RemoteException: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		} catch (ICarService_GetRulesAndRestrictions_CustomCarExceptionFault_FaultMessage e) {
			logger.error("ICarService_SearchCars_CustomCarExceptionFault_FaultMessage: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		}
		return hs;
	}

	public Category[] searchActivitisByDest(SearchActivityByDestinationIdsRequest request) throws ManagerException {
		Category[] hs = null;
		try {
			SearchActivityByDestinationIds sh = new SearchActivityByDestinationIds();
			sh.setSearchRequest(request);
			com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.AuthenticationHeaderE e = new com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.AuthenticationHeaderE();
			com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.AuthenticationHeader l = new com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.AuthenticationHeader();
			l.setCulture(com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.Culture.zh_CN);
			l.setLoginName("hosent");
			l.setPassword("111111");
			l.setVersion("7.123");
			e.setAuthenticationHeader(l);
			hs = actStub.searchActivityByDestinationIds(sh, e).getSearchActivityByDestinationIdsResult()
					.getCategories().getCategory();
		} catch (RemoteException e) {
			logger.error("RemoteException: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		} catch (IActivityBookFlow_SearchActivityByDestinationIds_WSFault_FaultMessage e) {
			logger.error("IActivityBookFlow_SearchActivityByDestinationIds_WSFault_FaultMessage: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		}
		return hs;
	}

	public Category[] searchActivitisById(SearchActivityByActivityIdsRequest request) throws ManagerException {
		Category[] hs = null;
		try {
			SearchActivityByActivityIds sh = new SearchActivityByActivityIds();
			sh.setSearchRequest(request);
			com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.AuthenticationHeaderE e = new com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.AuthenticationHeaderE();
			com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.AuthenticationHeader l = new com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.AuthenticationHeader();
			l.setCulture(com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.Culture.zh_CN);
			l.setLoginName("hosent");
			l.setPassword("111111");
			l.setVersion("7.123");
			e.setAuthenticationHeader(l);
			hs = actStub.searchActivityByActivityIds(sh, e).getSearchActivityByActivityIdsResult().getCategories()
					.getCategory();
		} catch (RemoteException e) {
			logger.error("RemoteException: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		} catch (IActivityBookFlow_SearchActivityByActivityIds_WSFault_FaultMessage e) {
			logger.error("IActivityBookFlow_SearchActivityByDestinationIds_WSFault_FaultMessage: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		}
		return hs;
	}

	public ActivityDetails getActivitisDet(int activityId) throws ManagerException {
		ActivityDetails[] hs = null;
		ActivityDetails h = null;
		try {
			GetActivityDetails sh = new GetActivityDetails();
			ArrayOfActivityId aoi = new ArrayOfActivityId();
			ActivityId ai = new ActivityId();
			ai.setId(activityId);
			aoi.addActivityId(ai);
			sh.setActivitiesIds(aoi);
			com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.AuthenticationHeaderE e = new com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.AuthenticationHeaderE();
			com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.AuthenticationHeader l = new com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.AuthenticationHeader();
			l.setCulture(com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.Culture.zh_CN);
			l.setLoginName("hosent");
			l.setPassword("111111");
			l.setVersion("7.123");
			e.setAuthenticationHeader(l);
			hs = actStub.getActivityDetails(sh, e).getGetActivityDetailsResult().getActivitiesDetails()
					.getActivityDetails();
			if (hs.length > 0) {
				h = hs[0];
			}
		} catch (RemoteException e) {
			logger.error("RemoteException: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		} catch (IActivityBookFlow_GetActivityDetails_WSFault_FaultMessage e) {
			logger.error("IActivityBookFlow_SearchActivityByDestinationIds_WSFault_FaultMessage: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		}
		return h;
	}

	public ActivitiesSelectedOptions preBook(PreBookRequest request) throws ManagerException {
		ActivitiesSelectedOptions hs = null;
		try {
			ActivityPreBook sh = new ActivityPreBook();
			sh.setBookActivityOptions(request);
			com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.AuthenticationHeaderE e = new com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.AuthenticationHeaderE();
			com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.AuthenticationHeader l = new com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.AuthenticationHeader();
			l.setCulture(com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.Culture.zh_CN);
			l.setLoginName("hosent");
			l.setPassword("111111");
			l.setVersion("7.123");
			e.setAuthenticationHeader(l);
			hs = actStub.activityPreBook(sh, e).getActivityPreBookResult().getActivitiesSelectedOptions();
		} catch (RemoteException e) {
			logger.error("RemoteException: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		} catch (IActivityBookFlow_ActivityPreBook_WSFault_FaultMessage e) {
			logger.error("IActivityBookFlow_SearchActivityByDestinationIds_WSFault_FaultMessage: ", e);
			throw new ManagerException("网络通信错误，请稍后再试");
		}
		return hs;
	}
}
