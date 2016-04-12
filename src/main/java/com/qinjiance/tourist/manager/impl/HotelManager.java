/**
 * 
 */
package com.qinjiance.tourist.manager.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import module.laohu.commons.util.HexUtil;
import module.laohu.commons.util.JsonUtils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.arcgames.pay.constants.BillingDisplayType;
import cn.arcgames.pay.constants.BillingGameShowType;
import cn.arcgames.pay.constants.ChargeStatus;
import cn.arcgames.pay.constants.Constants;
import cn.arcgames.pay.constants.OrderType;
import cn.arcgames.pay.constants.PayStatus;
import cn.arcgames.pay.constants.PayTypeCategory;
import cn.arcgames.pay.constants.UserTerminal;
import cn.arcgames.pay.manager.exception.PayManagerException;
import cn.arcgames.pay.manager.impl.BillingOrderManager;
import cn.arcgames.pay.manager.impl.StatisticLogManager;
import cn.arcgames.pay.model.po.BillingOrder;
import cn.arcgames.pay.model.vo.BillingGame;
import cn.arcgames.pay.model.vo.BillingGameDiscountVo;
import cn.arcgames.pay.model.vo.BillingGatewayPayType;
import cn.arcgames.pay.model.vo.GameChargeOrder;
import cn.arcgames.pay.model.vo.GameServer;
import cn.arcgames.pay.model.vo.PayUrlResult;
import cn.arcgames.pay.model.vo.ThirdPayExtData;
import cn.arcgames.pay.support.exception.SupportServiceException;

import com.qinjiance.tourist.constants.Currency;
import com.qinjiance.tourist.constants.PayType;
import com.qinjiance.tourist.constants.SuppChargeType;
import com.qinjiance.tourist.manager.IHotelManager;
import com.qinjiance.tourist.manager.IUserManager;
import com.qinjiance.tourist.manager.exception.ManagerException;
import com.qinjiance.tourist.model.po.User;
import com.qinjiance.tourist.model.vo.BoardbasePrice;
import com.qinjiance.tourist.model.vo.BoardbaseVo;
import com.qinjiance.tourist.model.vo.HoltelDetPrice;
import com.qinjiance.tourist.model.vo.HoltelDetVo;
import com.qinjiance.tourist.model.vo.HotelBookRoomInfo;
import com.qinjiance.tourist.model.vo.HotelVo;
import com.qinjiance.tourist.model.vo.OccupancyVo;
import com.qinjiance.tourist.model.vo.RoomPrice;
import com.qinjiance.tourist.model.vo.RoomTypePrice;
import com.qinjiance.tourist.model.vo.RoomTypeVo;
import com.qinjiance.tourist.model.vo.SearchHotelResult;
import com.qinjiance.tourist.model.vo.SupplementPrice;
import com.qinjiance.tourist.model.vo.SupplementVo;
import com.qinjiance.tourist.support.tourico.client.TouricoWSClient;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.Amenity_type0;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.ArrayOfBoardbase;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.ArrayOfChildAge;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.ArrayOfHotelIdInfo;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.ArrayOfOccupancy;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.ArrayOfPrice;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.ArrayOfRoom;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.ArrayOfRoomInfo;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.ArrayOfRoomType;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.ArrayOfSupplement;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.Boardbase;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.ChildAge;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.Facility_type0;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.Hotel;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.HotelIdInfo;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.Hotel_type0;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.Image_type0;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.Occupancy;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.Price;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.PropertyType;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.RefPoints_type0;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.Room;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.RoomInfo;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.RoomType;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.RoomType_type0;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.SearchHotelsByIdRequest;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.SearchRequest;
import com.qinjiance.tourist.support.tourico.hotel.HotelFlowStub.Supplement;
import com.qinjiance.tourist.util.CheckStyleUtil;

/**
 * @author Jiance Qin
 * 
 * @Revision revision
 * 
 * @Date 2014-7-26
 * 
 * @Time 上午2:20:39
 * 
 */
@Service
public class HotelManager implements IHotelManager {

	protected static final Logger logger = LoggerFactory.getLogger(UserManager.class);

	@Autowired
	private TouricoWSClient touricoWSClient;
	@Autowired
	private IUserManager userManager;

	/**
	 * 
	 */
	public HotelManager() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qinjiance.tourist.manager.IHotelManager#searchHotels(java.lang.
	 * String, java.lang.String, java.util.Date, java.util.Date,
	 * java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public SearchHotelResult searchHotels(String desLabel, String desValue, Date checkIn, Date checkOut,
			String roomInfo, Integer searchStars, Integer searchPrices, String searchHotelCat) throws ManagerException {
		String[] desS = desValue.split(",");
		if (desS.length != 2 && desS.length != 3) {
			throw new ManagerException("目的地非法，请重新输入");
		}
		if (checkIn.after(checkOut)) {
			throw new ManagerException("入住时间不可晚于退房时间");
		}
		SearchRequest param = new SearchRequest();
		param.setCheckIn(checkIn);
		param.setCheckOut(checkOut);
		param.setAvailableOnly(true);
		param.setDestination(desS[desS.length - 1]);
		param.setHotelCityName(desS[desS.length - 2]);
		if (desS.length == 3) {
			param.setHotelLocationName(desS[0]);
		}
		param.setPropertyType(PropertyType.NotSet);
		ArrayOfRoomInfo ar = new ArrayOfRoomInfo();
		RoomInfo ri = null;
		ArrayOfChildAge aoa = null;
		ChildAge ca = null;
		String[] values = null;
		for (String roomIn : roomInfo.split(",")) {
			values = roomIn.split("-");
			if (values.length >= 2) {
				ri = new RoomInfo();
				ri.setAdultNum(Integer.valueOf(values[0]));
				ri.setChildNum(Integer.valueOf(values[1]));
				if (values.length > 2 && Integer.valueOf(values[1]) > 0) {
					aoa = new ArrayOfChildAge();
					for (int i = 2; i < values.length; i++) {
						ca = new ChildAge();
						ca.setAge(Integer.valueOf(values[i]));
						aoa.addChildAge(ca);
					}
					ri.setChildAges(aoa);
				}
				ar.addRoomInfo(ri);
			}
		}
		param.setRoomsInformation(ar);
		if (searchStars != null) {
			param.setStarLevel(new BigDecimal(searchStars));
		} else {
			param.setStarLevel(new BigDecimal(0));
		}
		if (searchPrices != null) {
			param.setMaxPrice(new BigDecimal(searchPrices));
		} else {
			param.setMaxPrice(new BigDecimal(0));
		}
		Hotel[] hs = touricoWSClient.searchHotels(param);
		SearchHotelResult result = new SearchHotelResult();
		List<Integer> startsCat = new ArrayList<Integer>(5);
		List<Integer> pricesCat = new ArrayList<Integer>(5);
		List<Integer> hotelTypeCat = new ArrayList<Integer>(5);
		for (int i = 0; i < 5; i++) {
			startsCat.add(0);
			pricesCat.add(0);
			hotelTypeCat.add(0);
		}
		List<HotelVo> hotels = new ArrayList<HotelVo>();
		if (hs != null && hs.length > 0) {
			HotelVo hotel = null;
			ArrayOfRoomType roomTypes = null;
			String roomTypeNames = null;
			String boardBaseNames = null;
			String supplementNames = null;
			Currency cry = null;
			BigDecimal starsLevel = null;
			BigDecimal price = null;
			String hotelCat = null;
			for (Hotel h : hs) {
				if (searchStars != null && h.getStarsLevel() != null
						&& !h.getStarsLevel().toString().startsWith(searchStars.toString())) {
					continue;
				}
				if (StringUtils.isNotBlank(searchHotelCat) && h.getCategory() != null) {
					if (searchHotelCat.equals("other")
							&& (h.getCategory().equalsIgnoreCase("deluxe")
									|| h.getCategory().equalsIgnoreCase("first class")
									|| h.getCategory().equalsIgnoreCase("superior first class")
									|| h.getCategory().equalsIgnoreCase("moderate") || h.getCategory()
									.equalsIgnoreCase("department"))) {
						continue;
					} else if (searchHotelCat.equals("first class") && !h.getCategory().equalsIgnoreCase("first class")
							&& !h.getCategory().equalsIgnoreCase("superior first class")) {
						continue;
					} else if (!searchHotelCat.equals("other") && !searchHotelCat.equals("first class")
							&& !h.getCategory().equalsIgnoreCase(searchHotelCat)) {
						continue;
					}
				}
				if (searchPrices != null && h.getMinAverPublishPrice() != null) {
					if (searchPrices == 1200 && h.getMinAverPublishPrice().floatValue() <= 600) {
						continue;
					} else if (searchPrices == 1500 && h.getMinAverPublishPrice().floatValue() <= 1200) {
						continue;
					} else if (searchPrices == 2500 && h.getMinAverPublishPrice().floatValue() <= 1500) {
						continue;
					} else if (searchPrices == 10000000 && h.getMinAverPublishPrice().floatValue() <= 2500) {
						continue;
					}
				}
				hotel = new HotelVo();
				hotel.setBrandName(h.getBrandName());
				cry = Currency.getEnum(h.getCurrency());
				if (cry == null) {
					hotel.setCurrency(h.getCurrency());
				} else {
					hotel.setCurrency(cry.getSymbol());
				}
				hotel.setHotelId(h.getHotelId());
				hotel.setHotelName(h.getName());
				hotel.setAddress(h.getLocation().getAddress());
				hotelCat = h.getCategory();
				if (hotelCat != null) {
					hotel.setHotelCatalog(hotelCat);
					if (hotelCat.equalsIgnoreCase("deluxe")) {
						hotelTypeCat.set(4, hotelTypeCat.get(4) + 1);
					} else if (hotelCat.equalsIgnoreCase("first class")
							|| hotelCat.equalsIgnoreCase("superior first class")) {
						hotelTypeCat.set(3, hotelTypeCat.get(3) + 1);
					} else if (hotelCat.equalsIgnoreCase("moderate")) {
						hotelTypeCat.set(2, hotelTypeCat.get(2) + 1);
					} else if (hotelCat.equalsIgnoreCase("apartment")) {
						hotelTypeCat.set(1, hotelTypeCat.get(1) + 1);
					} else {
						hotelTypeCat.set(0, hotelTypeCat.get(0) + 1);
					}
				}
				price = h.getMinAverPublishPrice();
				if (price != null) {
					hotel.setMinAverPublishPrice(price.toString());
					if (price.floatValue() <= 600) {
						pricesCat.set(4, pricesCat.get(4) + 1);
					} else if (price.floatValue() > 600 && price.floatValue() <= 1200) {
						pricesCat.set(3, pricesCat.get(3) + 1);
					} else if (price.floatValue() > 1200 && price.floatValue() <= 1500) {
						pricesCat.set(2, pricesCat.get(2) + 1);
					} else if (price.floatValue() > 1500 && price.floatValue() <= 2500) {
						pricesCat.set(1, pricesCat.get(1) + 1);
					} else {
						pricesCat.set(0, pricesCat.get(0) + 1);
					}
				}
				starsLevel = h.getStarsLevel();
				if (starsLevel != null) {
					hotel.setStarsLevel(starsLevel.toString());
					if (starsLevel.toString().startsWith("5")) {
						startsCat.set(4, startsCat.get(4) + 1);
					} else if (starsLevel.toString().startsWith("4")) {
						startsCat.set(3, startsCat.get(3) + 1);
					} else if (starsLevel.toString().startsWith("3")) {
						startsCat.set(2, startsCat.get(2) + 1);
					} else if (starsLevel.toString().startsWith("2")) {
						startsCat.set(1, startsCat.get(1) + 1);
					} else if (starsLevel.toString().startsWith("1")) {
						startsCat.set(0, startsCat.get(0) + 1);
					}
				}
				hotel.setThumb(h.getThumb());
				roomTypes = h.getRoomTypes();
				if (roomTypes != null && roomTypes.getRoomType() != null) {
					roomTypeNames = "";
					ArrayOfOccupancy occupancys = null;
					for (RoomType roomType : roomTypes.getRoomType()) {
						roomTypeNames += roomType.getName() + "  ";
						occupancys = roomType.getOccupancies();
						if (occupancys != null && occupancys.getOccupancy() != null) {
							ArrayOfBoardbase boardbases = null;
							ArrayOfSupplement supplements = null;
							for (Occupancy occupancy : occupancys.getOccupancy()) {
								boardbases = occupancy.getBoardBases();
								if (boardbases != null && boardbases.getBoardbase() != null) {
									boardBaseNames = "";
									for (Boardbase boardbase : boardbases.getBoardbase()) {
										boardBaseNames += boardbase.getBbName() + "  ";
									}
								}
								supplements = occupancy.getSelctedSupplements();
								if (supplements != null && supplements.getSupplement() != null) {
									supplementNames = "";
									for (Supplement supplement : supplements.getSupplement()) {
										supplementNames += supplement.getSuppName() + "  ";
									}
								}
							}
						}
					}
				}
				hotel.setRoomTypes(roomTypeNames);
				hotel.setBoardbases(boardBaseNames);
				hotel.setSupplements(supplementNames);
				hotels.add(hotel);
			}
		}
		result.setHotelvos(hotels);
		result.setHotelTypeCata(hotelTypeCat);
		result.setPricesCata(pricesCat);
		result.setStarsCata(startsCat);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IHotelManager#getHotelDetail(java.lang.
	 * Integer, java.util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	public HoltelDetVo getHotelDetail(Integer hotelId, Date checkIn, Date checkOut, String roomInfo)
			throws ManagerException {
		if (hotelId == null) {
			throw new ManagerException("未选择酒店，请选择");
		}
		if (checkIn.after(checkOut)) {
			throw new ManagerException("入住时间不可晚于退房时间");
		}
		SearchHotelsByIdRequest request = new SearchHotelsByIdRequest();
		request.setAvailableOnly(true);
		request.setCheckIn(checkIn);
		request.setCheckOut(checkOut);
		request.setMaxPrice(new BigDecimal(0));
		request.setStarLevel(new BigDecimal(0));
		ArrayOfHotelIdInfo ha = new ArrayOfHotelIdInfo();
		HotelIdInfo hi = new HotelIdInfo();
		hi.setId(hotelId);
		ha.addHotelIdInfo(hi);
		request.setHotelIdsInfo(ha);
		ArrayOfRoomInfo ar = new ArrayOfRoomInfo();
		RoomInfo ri = null;
		ArrayOfChildAge aoa = null;
		ChildAge ca = null;
		String[] values = null;
		for (String roomIn : roomInfo.split(",")) {
			values = roomIn.split("-");
			if (values.length >= 2) {
				ri = new RoomInfo();
				ri.setAdultNum(Integer.valueOf(values[0]));
				ri.setChildNum(Integer.valueOf(values[1]));
				if (values.length > 2 && Integer.valueOf(values[1]) > 0) {
					aoa = new ArrayOfChildAge();
					for (int i = 2; i < values.length; i++) {
						ca = new ChildAge();
						ca.setAge(Integer.valueOf(values[i]));
						aoa.addChildAge(ca);
					}
					ri.setChildAges(aoa);
				}
				ar.addRoomInfo(ri);
			}
		}
		request.setRoomsInformation(ar);
		Hotel[] hs = touricoWSClient.getHotels(request);
		if (hs == null || hs.length != 1) {
			return null;
		}
		Hotel ht = hs[0];
		HoltelDetVo result = new HoltelDetVo();
		Map<Integer, List<String>> facilitiesMap = new HashMap<Integer, List<String>>();
		Hotel_type0 hd = touricoWSClient.getHotelDetail(hotelId);
		if (hd != null) {
			result.setCountry(hd.getLocation()[0].getCountry());
			result.setHotelPhone(hd.getHotelPhone());
			result.setLongDesc(hd.getDescriptions()[0].getLongDescription()[0].getDescription()[0].getValue());
			result.setShortDesc(hd.getDescriptions()[0].getShortDescription()[0].getDesc());
			result.setState(hd.getLocation()[0].getState());
			result.setZip(hd.getLocation()[0].getZip());
			result.setVoucherRemark(hd.getDescriptions()[0].getVoucherRemark());
			Amenity_type0[] amenTypes = null;
			if (hd.getAmenities() != null && hd.getAmenities().length > 0) {
				amenTypes = hd.getAmenities()[0].getAmenity();
				if (amenTypes != null && amenTypes.length > 0) {
					List<String> amenities = new ArrayList<String>();
					for (Amenity_type0 ament : amenTypes) {
						amenities.add(ament.getName());
					}
					result.setAmenities(amenities);
				}
			}
			Image_type0[] imgTypes = null;
			if ((hd.getMedia() != null && hd.getMedia().length > 0)
					&& (hd.getMedia()[0].getImages() != null && hd.getMedia()[0].getImages().length > 0)) {
				imgTypes = hd.getMedia()[0].getImages()[0].getImage();
				if (imgTypes != null && imgTypes.length > 0) {
					List<String> imgs = new ArrayList<String>();
					for (Image_type0 it : imgTypes) {
						imgs.add(it.getPath());
					}
					result.setImgs(imgs);
				}
			}
			RefPoints_type0[] rfType = hd.getRefPoints();
			if (rfType != null && rfType.length > 0) {
				List<String> refPoints = new ArrayList<String>();
				for (RefPoints_type0 rf : rfType) {
					refPoints.add(rf.getType() + "-" + rf.getDistance() + rf.getUnit() + "-" + rf.getName());
				}
				result.setRefPoints(refPoints);
			}
			RoomType_type0[] roomType_type0s = hd.getRoomType();
			if (roomType_type0s != null && roomType_type0s.length > 0) {
				List<String> fas = null;
				Facility_type0[] fasType0 = null;
				for (RoomType_type0 type0 : roomType_type0s) {
					fas = new ArrayList<String>();
					if (type0.getFacilities() != null && type0.getFacilities().length > 0) {
						fasType0 = type0.getFacilities()[0].getFacility();
						if (fasType0 != null && fasType0.length > 0) {
							for (Facility_type0 fat : fasType0) {
								fas.add(fat.getName());
							}
							facilitiesMap.put(type0.getRoomId(), fas);
						}
					}
				}
			}
		}
		result.setAddress(ht.getLocation().getAddress());
		result.setBrandName(ht.getBrandName());
		result.setCity(ht.getLocation().getCity());

		Currency cry = Currency.getEnum(ht.getCurrency());
		if (cry == null) {
			result.setCurrency(ht.getCurrency());
		} else {
			result.setCurrency(cry.getSymbol());
		}

		result.setMinAverPublishPrice(ht.getMinAverPublishPrice().toString());
		result.setHotelCatalog(ht.getCategory());
		result.setHotelId(ht.getHotelId());
		result.setHotelName(ht.getName());
		result.setLocation(ht.getLocation().getLocation());
		result.setStarsLevel(ht.getStarsLevel().toString());

		ArrayOfRoomType roomTypes = ht.getRoomTypes();
		if (roomTypes != null && roomTypes.getRoomType() != null) {
			List<RoomTypeVo> roomTypeVos = new ArrayList<RoomTypeVo>();
			RoomTypeVo roomTypeVo = null;
			ArrayOfOccupancy occupancys = null;
			ArrayOfBoardbase boardbases = null;
			List<BoardbaseVo> boardbaseVos = null;
			BoardbaseVo boardbaseVo = null;
			ArrayOfSupplement supps = null;
			List<SupplementVo> supplementVos = null;
			SupplementVo supplementVo = null;
			for (RoomType type : roomTypes.getRoomType()) {
				roomTypeVo = new RoomTypeVo();
				roomTypeVo.setName(type.getName());
				roomTypeVo.setHotelRoomTypeId(type.getHotelRoomTypeId());
				roomTypeVo.setNumOfBathrooms(type.getNumOfBathrooms());
				roomTypeVo.setRoomId(type.getRoomId());
				roomTypeVo.setRoomTypeCategory(type.getRoomTypeCategory());
				roomTypeVo.setFacilities(facilitiesMap.get(type.getRoomId()));

				// 床型
				occupancys = type.getOccupancies();
				if (occupancys != null && occupancys.getOccupancy() != null) {
					for (Occupancy occu : occupancys.getOccupancy()) {
						// 最小价格
						if (roomTypeVo.getAvrNightPublishPrice() == null
								|| occu.getAvrNightPublishPrice().compareTo(
										new BigDecimal(roomTypeVo.getAvrNightPublishPrice())) < 0) {
							roomTypeVo.setAvrNightPublishPrice(occu.getAvrNightPublishPrice().toString());
						}
						// 最大人数
						if (roomTypeVo.getMaxChild() == null || occu.getMaxChild() > roomTypeVo.getMaxChild()) {
							roomTypeVo.setMaxChild(occu.getMaxChild());
						}
						// 最大人数
						if (roomTypeVo.getMaxGuests() == null || occu.getMaxGuests() > roomTypeVo.getMaxGuests()) {
							roomTypeVo.setMaxGuests(occu.getMaxGuests());
						}
						// 最大床数
						if (roomTypeVo.getHasBed() == null
								|| Integer.valueOf(occu.getBedding().split(",")[1]) > roomTypeVo.getHasBed()) {
							roomTypeVo.setHasBed(Integer.valueOf(occu.getBedding().split(",")[1]));
							roomTypeVo.setBedType(Double.valueOf(
									Math.ceil(Double.valueOf(occu.getBedding().split(",")[0])
											/ Double.valueOf(occu.getBedding().split(",")[1]))).intValue());
						}
						boardbases = occu.getBoardBases();
						if (boardbases != null && boardbases.getBoardbase() != null) {
							if (roomTypeVo.getBoardbases() == null
									|| boardbases.getBoardbase().length > roomTypeVo.getBoardbases().size()) {
								boardbaseVos = new ArrayList<BoardbaseVo>();
								for (Boardbase bb : boardbases.getBoardbase()) {
									boardbaseVo = new BoardbaseVo();
									boardbaseVo.setBbName(bb.getBbName());
									boardbaseVo.setBbPublishPrice(bb.getBbPublishPrice().toString());
									boardbaseVos.add(boardbaseVo);
								}
								roomTypeVo.setBoardbases(boardbaseVos);
							}
						}
						supps = occu.getSelctedSupplements();
						if (supps != null && supps.getSupplement() != null) {
							if (roomTypeVo.getSupplements() == null
									|| supps.getSupplement().length > roomTypeVo.getSupplements().size()) {
								supplementVos = new ArrayList<SupplementVo>();
								for (Supplement sup : supps.getSupplement()) {
									supplementVo = new SupplementVo();
									supplementVo.setPublishPrice(sup.getPublishPrice().toString());
									supplementVo.setSuppIsMandatory(sup.getSuppIsMandatory());
									supplementVo.setSuppName(sup.getSuppName());
									supplementVos.add(supplementVo);
								}
								roomTypeVo.setSupplements(supplementVos);
							}
						}
					}
				}
				roomTypeVos.add(roomTypeVo);
			}
			result.setRoomTypes(roomTypeVos);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IHotelManager#ckeckAvailabilityAndPrice(
	 * java.lang.Integer, java.util.Date, java.util.Date, java.lang.String,
	 * java.lang.Integer)
	 */
	@Override
	public HoltelDetPrice checkAvailabilityAndPrice(Integer hotelId, Date checkIn, Date checkOut, String roomInfo,
			Integer hotelRoomTypeId) throws ManagerException {
		if (hotelId == null) {
			throw new ManagerException("未选择酒店，请选择");
		}
		if (hotelRoomTypeId == null) {
			throw new ManagerException("未选择房型，请返回酒店");
		}
		if (checkIn.after(checkOut)) {
			throw new ManagerException("入住时间不可晚于退房时间");
		}
		SearchHotelsByIdRequest request = new SearchHotelsByIdRequest();
		request.setAvailableOnly(true);
		request.setCheckIn(checkIn);
		request.setCheckOut(checkOut);
		request.setMaxPrice(new BigDecimal(0));
		request.setStarLevel(new BigDecimal(0));
		ArrayOfHotelIdInfo ha = new ArrayOfHotelIdInfo();
		HotelIdInfo hi = new HotelIdInfo();
		hi.setId(hotelId);
		ha.addHotelIdInfo(hi);
		request.setHotelIdsInfo(ha);
		ArrayOfRoomInfo ar = new ArrayOfRoomInfo();
		RoomInfo ri = null;
		ArrayOfChildAge aoa = null;
		ChildAge ca = null;
		String[] values = null;
		for (String roomIn : roomInfo.split(",")) {
			values = roomIn.split("-");
			if (values.length >= 2) {
				ri = new RoomInfo();
				ri.setAdultNum(Integer.valueOf(values[0]));
				ri.setChildNum(Integer.valueOf(values[1]));
				if (values.length > 2 && Integer.valueOf(values[1]) > 0) {
					aoa = new ArrayOfChildAge();
					for (int i = 2; i < values.length; i++) {
						ca = new ChildAge();
						ca.setAge(Integer.valueOf(values[i]));
						aoa.addChildAge(ca);
					}
					ri.setChildAges(aoa);
				}
				ar.addRoomInfo(ri);
			}
		}
		request.setRoomsInformation(ar);
		Hotel[] hs = touricoWSClient.checkAvailability(request);
		if (hs == null || hs.length != 1) {
			throw new ManagerException("该酒店信息有变化，请重新查询");
		}
		Hotel ht = hs[0];
		HoltelDetPrice result = new HoltelDetPrice();

		result.setCountry(ht.getLocation().getCountryCode());
		result.setCity(ht.getLocation().getCity());
		result.setAddress(ht.getLocation().getAddress());
		result.setBrandName(ht.getBrandName());
		result.setThumb(ht.getThumb());

		Currency cry = Currency.getEnum(ht.getCurrency());
		if (cry == null) {
			result.setCurrency(ht.getCurrency());
		} else {
			result.setCurrency(cry.getSymbol());
		}

		result.setHotelCatalog(ht.getCategory());
		result.setHotelId(ht.getHotelId());
		result.setHotelName(ht.getName());
		result.setLocation(ht.getLocation().getLocation());
		result.setStarsLevel(ht.getStarsLevel().toString());

		ArrayOfRoomType roomTypes = ht.getRoomTypes();
		if (roomTypes == null || roomTypes.getRoomType() == null) {
			throw new ManagerException("该房型信息有变化，请重新查询");
		}
		RoomTypePrice roomTypePrice = null;
		Map<Integer, RoomPrice> roomPriceMap = new TreeMap<Integer, RoomPrice>();
		RoomPrice roomPrice = null;
		ArrayOfOccupancy occupancys = null;
		List<OccupancyVo> occupancyVos = null;
		OccupancyVo occupancyVo = null;
		ArrayOfBoardbase boardbases = null;
		List<BoardbasePrice> boardbasePrices = null;
		BoardbasePrice boardbasePrice = null;
		ArrayOfSupplement supps = null;
		List<SupplementPrice> supplementPrices = null;
		SupplementPrice supplementPrice = null;
		ArrayOfPrice prices = null;
		List<String> priceBreakdown = null;
		List<Integer> childAgeList = null;
		ArrayOfRoom rooms = null;
		ArrayOfChildAge childAges = null;
		List<RoomPrice> roomPrices = new ArrayList<RoomPrice>();
		for (RoomType type : roomTypes.getRoomType()) {
			if (type.getHotelRoomTypeId() == hotelRoomTypeId) {
				roomTypePrice = new RoomTypePrice();
				roomTypePrice.setHotelRoomTypeId(type.getHotelRoomTypeId());
				roomTypePrice.setName(type.getName());
				roomTypePrice.setRoomTypeCategory(type.getRoomTypeCategory());
				roomTypePrice.setNights(type.getNights());

				// 床型
				occupancys = type.getOccupancies();
				if (occupancys != null && occupancys.getOccupancy() != null) {
					for (Occupancy occu : occupancys.getOccupancy()) {
						occupancyVo = new OccupancyVo();
						occupancyVo.setBedding(occu.getBedding());
						occupancyVo.setBedNum(Integer.valueOf(occu.getBedding().split(",")[1]));
						occupancyVo.setBedType(Double.valueOf(
								Math.ceil(Double.valueOf(occu.getBedding().split(",")[0])
										/ Double.valueOf(occu.getBedding().split(",")[1]))).intValue());
						occupancyVo.setOccuId(occu.getOccupId());
						occupancyVo.setOccuPubPrice(occu.getOccupPublishPrice().toString());
						occupancyVo.setTaxPubPrice(occu.getTaxPublish().toString());
						// 每晚价格
						prices = occu.getPriceBreakdown();
						if (prices != null && prices.getPrice() != null) {
							priceBreakdown = new ArrayList<String>();
							for (Price price : prices.getPrice()) {
								priceBreakdown.add(price.getValuePublish().toString());
							}
							occupancyVo.setPriceBreakdown(priceBreakdown);
						}
						// 早餐
						boardbases = occu.getBoardBases();
						if (boardbases != null && boardbases.getBoardbase() != null) {
							boardbasePrices = new ArrayList<BoardbasePrice>();
							for (Boardbase bb : boardbases.getBoardbase()) {
								boardbasePrice = new BoardbasePrice();
								boardbasePrice.setBbId(bb.getBbId());
								boardbasePrice.setBbName(bb.getBbName());
								boardbasePrice.setBbPublishPrice(bb.getBbPublishPrice().toString());
								boardbasePrices.add(boardbasePrice);
							}
							occupancyVo.setBoardbases(boardbasePrices);
						}
						// 选项
						supps = occu.getSelctedSupplements();
						if (supps != null && supps.getSupplement() != null) {
							supplementPrices = new ArrayList<SupplementPrice>();
							for (Supplement sup : supps.getSupplement()) {
								supplementPrice = new SupplementPrice();
								supplementPrice.setPublishPrice(sup.getPublishPrice().toString());
								supplementPrice.setSuppChargeType(SuppChargeType.getEnum(
										sup.getSuppChargeType().getValue()).getVal());
								supplementPrice.setSuppId(sup.getSuppId());
								supplementPrice.setSuppIsMandatory(sup.getSuppIsMandatory());
								supplementPrice.setSuppName(sup.getSuppName());
								supplementPrices.add(supplementPrice);
							}
							occupancyVo.setSupplements(supplementPrices);
						}
						// 房间
						rooms = occu.getRooms();
						if (rooms != null && rooms.getRoom() != null) {
							for (Room room : rooms.getRoom()) {
								roomPrice = roomPriceMap.get(room.getSeqNum());
								if (roomPrice == null) {
									roomPrice = new RoomPrice();
									roomPrice.setAdultNum(room.getAdultNum());
									roomPrice.setChildNum(room.getChildNum());
									roomPrice.setRoomId(room.getSeqNum());
									// 年龄
									childAges = room.getChildAges();
									if (childAges != null && childAges.getChildAge() != null) {
										childAgeList = new ArrayList<Integer>();
										for (ChildAge childAge : childAges.getChildAge()) {
											childAgeList.add(childAge.getAge());
										}
										roomPrice.setChildAges(childAgeList);
									}
									roomPrice.setOccupancyVos(new ArrayList<OccupancyVo>());
									roomPriceMap.put(room.getSeqNum(), roomPrice);
								}
								occupancyVos = roomPrice.getOccupancyVos();
								occupancyVos.add(occupancyVo);
							}
						}
					}
				}
				for (Entry<Integer, RoomPrice> entry : roomPriceMap.entrySet()) {
					roomPrices.add(entry.getValue());
				}
				roomTypePrice.setRoomPrices(roomPrices);
				break;
			}
		}
		if (roomTypePrice == null) {
			throw new ManagerException("该房型已不可预定，请重新查询酒店房型");
		}
		result.setRoomType(roomTypePrice);
		return result;
	}

	@Override
	public String prePay(Long orderId, Integer hotelId, Date checkIn, Date checkOut, List<HotelBookRoomInfo> bookRoomInfo,
			String roomInfo, Integer hotelRoomTypeId, String confirmEmail, Integer payTypeId, Long totalDaofu,
			Long totalYufu, Long userId) throws ManagerException {
		if (hotelId == null) {
			throw new ManagerException("未选择酒店，请选择");
		}
		if (hotelRoomTypeId == null) {
			throw new ManagerException("未选择房型，请返回酒店");
		}
		if (checkIn.after(checkOut)) {
			throw new ManagerException("入住时间不可晚于退房时间");
		}
		if (totalYufu==null) {
			throw new ManagerException("支付金额不可为空");
		}
		if (totalDaofu==null) {
			throw new ManagerException("到付金额不可为空");
		}
		if (bookRoomInfo == null || bookRoomInfo.isEmpty()) {
			throw new ManagerException("未选择房间，请选择");
		}
		if (StringUtils.isBlank(confirmEmail) || CheckStyleUtil.checkStyle(confirmEmail, CheckStyleUtil.PATTERN_EMAIL)) {
			throw new ManagerException("未填写确认电子邮箱，请填写");
		}
		HoltelDetPrice holtelDetPrice = checkAvailabilityAndPrice(hotelId, checkIn, checkOut, roomInfo, hotelRoomTypeId);
		if (holtelDetPrice == null) {
			throw new ManagerException("该房型已不可预定，请重新查询酒店房型(err=1)");
		}
		PayType payType = PayType.getEnum(payTypeId);
		if (payType == null) {
			throw new ManagerException("支付方式不可用，请重新选择支付方式");
		}

		List<RoomPrice> roomPrices = holtelDetPrice.getRoomType().getRoomPrices();
		if (roomPrices == null || roomPrices.isEmpty()) {
			throw new ManagerException("该房型已不可预定，请重新查询酒店房型(err=2)");
		}
		Map<Integer, RoomPrice> roomPriceMap = new HashMap<Integer, RoomPrice>();
		for (RoomPrice roomPrice : roomPrices) {
			roomPriceMap.put(roomPrice.getRoomId(), roomPrice);
		}
		RoomPrice roomPrice = null;
		Long totalRoomDaofu = 0L;
		Long totalRoomYufu = 0L;
		Long roomDaofu = 0L;
		Long roomYufu = 0L;
		OccupancyVo occu = null;
		Map<Integer, BoardbasePrice> boardbasePriceMap = new HashMap<Integer, BoardbasePrice>();
		BoardbasePrice bb = null;
		Map<Integer, SupplementPrice> supplementPriceMap = new HashMap<Integer, SupplementPrice>();
		SupplementPrice supp = null;
		for (HotelBookRoomInfo hotelBookRoomInfo : bookRoomInfo) {
			roomPrice = roomPriceMap.get(hotelBookRoomInfo.getRoomId());
			if (roomPrice == null) {
				throw new ManagerException("该房型已不可预定，请重新查询酒店房型(err=3)");
			}
			if (roomPrice.getOccupancyVos() == null || roomPrice.getOccupancyVos().isEmpty()) {
				throw new ManagerException("该房型已不可预定，请重新查询酒店房型(err=4)");
			}
			for (OccupancyVo occupancyVo : roomPrice.getOccupancyVos()) {
				if (occupancyVo.getOccuId().equals(hotelBookRoomInfo.getOccuId())) {
					occu = occupancyVo;
					break;
				}
			}
			if (occu == null) {
				throw new ManagerException("该房型已不可预定，请重新查询酒店房型(err=5)");
			}
			roomYufu += Long.valueOf(new DecimalFormat("#").format(BigDecimal
					.valueOf(Double.valueOf(occu.getOccuPubPrice())).multiply(BigDecimal.valueOf(100)).doubleValue()));
			if (occu.getBoardbases() == null || occu.getBoardbases().isEmpty()) {
				throw new ManagerException("该房型已不可预定，请重新查询酒店房型(err=6)");
			}
			for (BoardbasePrice boardbasePrice : occu.getBoardbases()) {
				boardbasePriceMap.put(boardbasePrice.getBbId(), boardbasePrice);
			}
			for (Integer bbId : hotelBookRoomInfo.getBbIds()) {
				bb = boardbasePriceMap.get(bbId);
				if (bb == null) {
					throw new ManagerException("该房型已不可预定，请重新查询酒店房型(err=7)");
				}
				roomYufu += Long.valueOf(new DecimalFormat("#").format(BigDecimal
						.valueOf(Double.valueOf(bb.getBbPublishPrice())).multiply(BigDecimal.valueOf(100))
						.doubleValue()));
			}
			for (SupplementPrice supplementPrice : occu.getSupplements()) {
				supplementPriceMap.put(supplementPrice.getSuppId(), supplementPrice);
			}
			for (Integer suppId : hotelBookRoomInfo.getSuppIds()) {
				supp = supplementPriceMap.get(suppId);
				if (supp == null) {
					throw new ManagerException("该房型已不可预定，请重新查询酒店房型(err=8)");
				}
				if (supp.getSuppChargeType() == SuppChargeType.ADD.getVal()) {
					roomYufu += Long.valueOf(new DecimalFormat("#").format(BigDecimal
							.valueOf(Double.valueOf(supp.getPublishPrice())).multiply(BigDecimal.valueOf(100))
							.doubleValue()));
				} else if (supp.getSuppChargeType() == SuppChargeType.AP.getVal()) {
					roomDaofu += Long.valueOf(new DecimalFormat("#").format(BigDecimal
							.valueOf(Double.valueOf(supp.getPublishPrice())).multiply(BigDecimal.valueOf(100))
							.doubleValue()));
				}
			}
			if (!roomYufu.equals(hotelBookRoomInfo.getYufu()) || !roomDaofu.equals(hotelBookRoomInfo.getDaofu())) {
				throw new ManagerException("该房型已不可预定，请重新查询酒店房型(err=9)");
			}
			totalRoomDaofu += roomDaofu;
			totalRoomYufu += roomYufu;
		}
		if (!totalRoomDaofu.equals(totalDaofu) || !totalRoomYufu.equals(totalYufu)) {
			throw new ManagerException("该房型已不可预定，请重新查询酒店房型(err=10)");
		}
		// 支付方式
		User user = null;
		String username = null;
		if (userId != null) {
			user = userManager.getUserInfo(userId);
			if (user == null) {
				throw new ManagerException("账号不存在，请重新登录");
			}
			username = user.getEmail();
		}
		// 去支付网关支付
		String payUrlResult = thirdPay(orderId, totalYufu,
				payType, userId, username);
		if (payUrlResult == null) {
			throw new ManagerException("打开支付失败，请稍后再试");
		}
		return payUrlResult;
	}
	
	/**
	 * 第三方支付网关支付
	 * 
	 */
	protected String thirdPay(Long orderId, 
			Long chargeAmount,  PayType payType, Long userId,
			String username)
			throws PayManagerException {

		Long preOrderId = orderId;
		if (preOrderId == null) {
			preOrderId = sequenceManager.getOrderIdSeq();
		}

		// 方法调用性能统计开始
		Date methodStateTime = new Date();

		try {
			orderType = orderType == null ? OrderType.GAME : orderType;
			Long gameId = orderType == OrderType.COUPON ? null : billingGame.getGameId();
			String gameName = orderType == OrderType.COUPON ? null : billingGame.getGameInfo().getName();

			// 计算赠送和折扣
			BillingGameDiscountVo billingGameDiscountVo = null;
			if (gameId != null) {
				billingGameDiscountVo = billingConfigManager.getBillingGameDiscount(gameId);
			}
			Long bonus = billingConfigManager.getBonus(billingGameDiscountVo, billingGatewayPayType, chargeAmount);
			Double discount = billingConfigManager.getDiscount(billingGameDiscountVo, billingGatewayPayType,
					chargeAmount);
			Long realChargeAmount = billingConfigManager.getRealChargeAmount(billingGameDiscountVo,
					billingGatewayPayType, chargeAmount);
			Long payAmount = billingConfigManager.getPayAmount(billingGameDiscountVo, billingGatewayPayType,
					chargeAmount);

			// 记录充值信息
			StringBuilder productInfo = new StringBuilder();
			productInfo.append(billingGatewayPayType.getGatewayName()).append("-")
					.append(billingGatewayPayType.getPayTypeName()).append(": ")
					.append(new DecimalFormat("#.##").format(payAmount / (double) 100)).append("元");

			Long auOrderId = null;
			String gameOrderId = "";
			String gameExtraInfo = "";
			Long to173userId = null;
			switch (orderType) {
			case COUPON:
				// 到完美支付获取订单号，防止重复
				try {
					gameOrderId = preOrderId.toString();
					auOrderId = Long.valueOf(wanmeiPayService.getTradeSequence(billingGatewayPayType.getGatewayId()));
				} catch (SupportServiceException e) {
					throw new PayManagerException("订单号创建失败");
				} catch (NumberFormatException e) {
					throw new PayManagerException("订单号创建失败");
				}
				break;
			default:
				// 无冬使用AU充值
				if (gameServerManager.isAUGame(billingGame.getGameInfo()) || billingGame.getGameId() == 200007L) {
					// 到完美支付获取订单号，防止重复
					try {
						gameOrderId = preOrderId.toString();
						auOrderId = Long
								.valueOf(wanmeiPayService.getTradeSequence(billingGatewayPayType.getGatewayId()));
					} catch (SupportServiceException e) {
						throw new PayManagerException("订单号创建失败");
					} catch (NumberFormatException e) {
						throw new PayManagerException("订单号创建失败");
					}
				} else {
					Long gameOrderUserId = toUserId;
					// 173的游戏需要173UserId
					if (gameServerManager.is173Game(billingGame.getGameInfo())) {
						try {
							to173userId = auUserService.get173UserIdByUserId(toUserId);
						} catch (SupportServiceException e) {
							throw new PayManagerException(e.getMessage());
						}
						// 用户无对应的173userId，则直接提示未创建角色，因为只要玩过173游戏就会有173userId
						if (to173userId == null) {
							throw new PayManagerException("该账号未创建游戏角色");
						}
						gameOrderUserId = to173userId;
					}
					// 是否需要到游戏下单
					if (billingGame.getNeedPreorder() != null && billingGame.getNeedPreorder().booleanValue()) {
						GameChargeOrder gameOrder = gameConnectManager.preChargeOrder(billingGame.getGameId(),
								serverId, gameOrderUserId, payAmount, realChargeAmount);
						if (gameOrder == null) {
							throw new PayManagerException("游戏下单失败");
						}
						gameOrderId = gameOrder.getGameOrderId();
						gameExtraInfo = gameOrder.getGameExtraInfo();
					} else {
						gameOrderId = preOrderId.toString();
					}
				}
				break;
			}
			// 不可为null，否则支付通知是url编码会抛空指针
			if (StringUtils.isBlank(gameExtraInfo)) {
				gameExtraInfo = "";
			}
			if (StringUtils.isBlank(gameOrderId)) {
				throw new PayManagerException("订单号创建失败");
			}
			if (!isGameOrderIdValid(gameId, serverId, gameOrderId, orderType)) {
				throw new PayManagerException("游戏订单号重复");
			}
			BillingOrder billingOrder = null;
			// 透传信息
			ThirdPayExtData thirdPayExtData = new ThirdPayExtData();
			if (showType != null) {
				thirdPayExtData.setShowType(showType.getShowType());
			}
			String payExtraInfo = null;
			try {
				payExtraInfo = HexUtil.toHexString(JsonUtils.objectToJson(thirdPayExtData).getBytes(Constants.CHARSET));
			} catch (UnsupportedEncodingException e) {
				logger.warn("UnsupportedEncodingException", e);
				payExtraInfo = HexUtil.toHexString(JsonUtils.objectToJson(thirdPayExtData).getBytes());
			}
			if (orderId == null) {
				// 新建订单
				orderId = preOrderId;
				billingOrder = createNewBillingOrder(orderId, gameId, gameName, gameOrderId, gameRole,
						billingGatewayPayType.getGatewayId(), discount, bonus, chargeAmount, realChargeAmount,
						payAmount, userTerminal, billingGatewayPayType.getPayTypeId(),
						billingGatewayPayType.getPayTypeName(), payUserId, toUserId, productInfo.toString(), serverId,
						gameServer.getFullName(), orderType, payExtraInfo, gameExtraInfo, auOrderId, to173userId,
						orderIp);
				logger.info("Creating billing order: " + billingOrder.toString());
				Integer result = billingOrderMapper.insert(billingOrder);
				if (result == null || result != 1) {
					logger.info("Create billing order failed: " + billingOrder.toString());
					throw new PayManagerException("订单创建失败");
				}
			} else {
				// 支付旧订单
				billingOrder = billingOrderMapper.getById(orderId);
				if (billingOrder == null) {
					throw new PayManagerException("该订单不存在");
				}
				if (billingOrder.getPayUserId().longValue() != payUserId.longValue()) {
					throw new PayManagerException("您的订单不存在");
				}
				if (billingOrder.getPayStatus().intValue() != PayStatus.NOT_PAY.getStatus()
						|| billingOrder.getChargeStatus().intValue() != ChargeStatus.NOT_CHARGE.getStatus()) {
					throw new PayManagerException("该订单已进行过支付操作");
				}
				billingOrder.setGameId(gameId);
				billingOrder.setTo173UserId(to173userId);
				billingOrder.setAuOrderId(auOrderId);
				billingOrder.setGameName(billingGame.getGameInfo().getName());
				billingOrder.setGameOrderId(gameOrderId);
				billingOrder.setPayExtraInfo(payExtraInfo);
				billingOrder.setGameExtraInfo(gameExtraInfo);
				billingOrder.setGameRole(gameRole);
				billingOrder.setGatewayId(billingGatewayPayType.getGatewayId());
				billingOrder.setId(orderId);
				billingOrder.setBonusAmount(bonus);
				billingOrder.setPayDiscount(discount);
				billingOrder.setRealChargeAmount(realChargeAmount);
				billingOrder.setOrderAmount(chargeAmount);
				billingOrder.setPayAmount(payAmount);
				billingOrder.setOrderIp(orderIp);
				billingOrder.setOrderSource(userTerminal.getTerminal());
				billingOrder.setPayTypeId(billingGatewayPayType.getPayTypeId());
				billingOrder.setPayTypeName(billingGatewayPayType.getPayTypeName());
				billingOrder.setProductInfo(productInfo.toString());
				billingOrder.setServerId(serverId);
				billingOrder.setServerName(gameServer.getFullName());
				billingOrder.setToUserId(toUserId);
				logger.info("Recreating billing order: " + billingOrder.toString());
				Integer result = billingOrderMapper.updateNewOrder(billingOrder);
				if (result == null || result != 1) {
					logger.info("Recreate billing order failed: " + billingOrder.toString());
					throw new PayManagerException("该订单不可支付");
				}
			}
			// 构造支付表单字符串
			String subject = "ARC充值";
			String description = "ARC充值";
			switch (orderType) {
			case COUPON:
				subject = "完美点券";
				description = couponManager.getCouponByYuan(realChargeAmount / 100) + "点";
				break;
			case GAME:
				subject = billingGame.getGameInfo().getName() + "-" + billingGame.getCurrencyName();
				description = gameServer.getFullName() + "--" + realChargeAmount / 100
						* billingGame.getYuanToCurrencyRatio() + billingGame.getCurrencyName();
				break;
			default:
				break;
			}
			PayTypeCategory payTypeCategory = PayTypeCategory.getEnum(billingGatewayPayType.getCategory());
			if (payTypeCategory == null) {
				payTypeCategory = PayTypeCategory.DIRECT;
			}
			BillingDisplayType displayType = BillingDisplayType.WEB;
			switch (payTypeCategory) {
			case QRCODE:
				displayType = BillingDisplayType.QRCODE;
				break;
			default:
				displayType = BillingDisplayType.WEB;
				break;
			}
			// 充值调用性能统计开始
			Date payStateTime = new Date();

			String payUri = null;
			try {
				payUri = getThirdPayForm(billingGatewayPayType, billingOrder, subject, description, displayType);
			} finally {
				// 充值调用性能统计结束
				StatisticLogManager.logPerformance(BillingOrderManager.class.getSimpleName() + "#ThirdPay#"
						+ billingGatewayPayType.getGatewayName(), "orderId=" + preOrderId.toString(), payStateTime,
						new Date());
			}

			if (StringUtils.isBlank(payUri)) {
				logger.error("Create third pay form failed. params: " + billingOrder.toString());
				throw new PayManagerException("下单失败");
			}
			PayUrlResult result = new PayUrlResult();
			result.setOrderId(billingOrder.getId());
			switch (payTypeCategory) {
			case QRCODE:
				result.setPayUrl(payUri);
				return result;
			default:
				memcacheManager.put(getPayKeyCacheKey(orderId.toString()), payUri, Constants.MIN_1_CACHE_TIME);
				return result;
			}
		} finally {
			// 方法调用性能统计结束
			StatisticLogManager.logPerformance(BillingOrderManager.class.getSimpleName() + "#ThirdPay", "orderId="
					+ preOrderId.toString(), methodStateTime, new Date());
		}
	}
}
