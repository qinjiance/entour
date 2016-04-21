/**
 * 
 */
package com.qinjiance.tourist.manager.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import module.laohu.commons.pay.alipay.model.AlipayNotifyAsync;
import module.laohu.commons.pay.alipay.model.AlipayNotifySync;
import module.laohu.commons.util.HexUtil;
import module.laohu.commons.util.JsonUtils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;
import org.springframework.stereotype.Service;

import com.qinjiance.tourist.constants.Constants;
import com.qinjiance.tourist.constants.Currency;
import com.qinjiance.tourist.constants.PayType;
import com.qinjiance.tourist.constants.SuppChargeType;
import com.qinjiance.tourist.manager.IHotelManager;
import com.qinjiance.tourist.manager.IUserManager;
import com.qinjiance.tourist.manager.exception.ManagerException;
import com.qinjiance.tourist.mapper.BillingHotelMapper;
import com.qinjiance.tourist.mapper.ExchangeMapper;
import com.qinjiance.tourist.model.po.BillingHotel;
import com.qinjiance.tourist.model.po.Exchange;
import com.qinjiance.tourist.model.po.User;
import com.qinjiance.tourist.model.vo.BoardbasePrice;
import com.qinjiance.tourist.model.vo.BoardbaseVo;
import com.qinjiance.tourist.model.vo.BookRoomBoadBase;
import com.qinjiance.tourist.model.vo.BookRoomContactPassenger;
import com.qinjiance.tourist.model.vo.BookRoomInfo;
import com.qinjiance.tourist.model.vo.BookRoomSupplement;
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
import com.qinjiance.tourist.support.alipay.AlipaySupport;
import com.qinjiance.tourist.support.alipay.constant.AlipaySignType;
import com.qinjiance.tourist.support.alipay.constant.AlipayTradeStatus;
import com.qinjiance.tourist.support.alipay.model.AlipayAccountConfig;
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
	@Autowired
	private ExchangeMapper exchangeMapper;
	@Autowired
	private BillingHotelMapper billingHotelMapper;
	@Autowired
	private MySQLMaxValueIncrementer orderIdSeqGenerater;

	@Value(value = "#{configProperties['pay.host']}")
	private String PAY_HOST;
	@Value(value = "#{configProperties['alipay.partnerid']}")
	private String ALIPAY_PARTNERID;
	@Value(value = "#{configProperties['alipay.signkey']}")
	private String ALIPAY_SIGNKEY;

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
		result.setCurrency(ht.getCurrency());
		Currency cry = Currency.getEnum(ht.getCurrency());
		if (cry == null) {
			result.setCurrencySymbol(ht.getCurrency());
		} else {
			result.setCurrencySymbol(cry.getSymbol());
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
								supplementPrice.setSuppType(sup.getSupptType());
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
	public Map<String, String> prePay(Long orderId, Integer hotelId, Date checkIn, Date checkOut,
			String hotelBookRoomInfosStr, String roomInfo, Integer hotelRoomTypeId, String confirmEmail,
			Integer payTypeId, Long totalDaofu, Long totalYufu, Long userId, String bookCurrency)
			throws ManagerException {
		if (hotelId == null) {
			throw new ManagerException("未选择酒店，请选择");
		}
		if (hotelRoomTypeId == null) {
			throw new ManagerException("未选择房型，请返回酒店");
		}
		if (checkIn.after(checkOut)) {
			throw new ManagerException("入住时间不可晚于退房时间");
		}
		if (totalYufu == null) {
			throw new ManagerException("支付金额不可为空");
		}
		if (StringUtils.isBlank(bookCurrency)) {
			throw new ManagerException("货币类型不可为空");
		}
		if (totalDaofu == null) {
			throw new ManagerException("到付金额不可为空");
		}
		List<HotelBookRoomInfo> hotelBookRoomInfos = JsonUtils.parseToList(
				new String(HexUtil.toByteArray(hotelBookRoomInfosStr)), HotelBookRoomInfo.class);
		if (hotelBookRoomInfos == null || hotelBookRoomInfos.isEmpty()) {
			throw new ManagerException("未选择房间，请选择");
		}
		if (StringUtils.isBlank(confirmEmail) || !CheckStyleUtil.checkStyle(confirmEmail, CheckStyleUtil.PATTERN_EMAIL)) {
			throw new ManagerException("未填写确认电子邮箱，请填写");
		}
		HoltelDetPrice holtelDetPrice = checkAvailabilityAndPrice(hotelId, checkIn, checkOut, roomInfo, hotelRoomTypeId);
		if (holtelDetPrice == null) {
			throw new ManagerException("该房型已不可预定，请重新查询酒店房型(err=1)");
		}
		if (!holtelDetPrice.getCurrency().equalsIgnoreCase(bookCurrency)) {
			throw new ManagerException("货币类型变化，请重新查询酒店房型");
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
		List<BookRoomInfo> bookRoomInfos = new ArrayList<BookRoomInfo>();
		BookRoomInfo bookRoomInfo = null;
		BookRoomContactPassenger contactPassenger = null;
		BookRoomBoadBase boadBase = null;
		List<BookRoomSupplement> supplements = null;
		BookRoomSupplement supplement = null;
		for (HotelBookRoomInfo hotelBookRoomInfo : hotelBookRoomInfos) {
			roomDaofu = 0L;
			roomYufu = 0L;
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
			if (hotelBookRoomInfo.getBbId() != null) {
				if (occu.getBoardbases() == null || occu.getBoardbases().isEmpty()) {
					throw new ManagerException("该房型已不可预定，请重新查询酒店房型(err=6)");
				}
				for (BoardbasePrice boardbasePrice : occu.getBoardbases()) {
					boardbasePriceMap.put(boardbasePrice.getBbId(), boardbasePrice);
				}
				bb = boardbasePriceMap.get(hotelBookRoomInfo.getBbId());
				if (bb == null) {
					throw new ManagerException("该房型已不可预定，请重新查询酒店房型(err=7)");
				}
				roomYufu += Long.valueOf(new DecimalFormat("#").format(BigDecimal
						.valueOf(Double.valueOf(bb.getBbPublishPrice())).multiply(BigDecimal.valueOf(100))
						.doubleValue()));
			}
			if (hotelBookRoomInfo.getSuppIds() != null && !hotelBookRoomInfo.getSuppIds().isEmpty()) {
				if (occu.getSupplements() == null || occu.getSupplements().isEmpty()) {
					throw new ManagerException("该房型已不可预定，请重新查询酒店房型(err=8)");
				}
				for (SupplementPrice supplementPrice : occu.getSupplements()) {
					supplementPriceMap.put(supplementPrice.getSuppId(), supplementPrice);
				}
				supplements = new ArrayList<BookRoomSupplement>();
				for (Integer suppId : hotelBookRoomInfo.getSuppIds()) {
					supp = supplementPriceMap.get(suppId);
					if (supp == null) {
						throw new ManagerException("该房型已不可预定，请重新查询酒店房型(err=9)");
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
					supplement = new BookRoomSupplement();
					supplement.setPublishPrice(Long.valueOf(new DecimalFormat("#").format(BigDecimal
							.valueOf(Double.valueOf(supp.getPublishPrice())).multiply(BigDecimal.valueOf(100))
							.doubleValue())));
					supplement.setSuppChargeType(supp.getSuppChargeType());
					supplement.setSuppId(supp.getSuppId());
					supplement.setSuppIsMandatory(supp.getSuppIsMandatory());
					supplement.setSuppName(supp.getSuppName());
					supplement.setSuppType(supp.getSuppType());
					supplements.add(supplement);
				}
			}
			if (!roomYufu.equals(hotelBookRoomInfo.getYufu()) || !roomDaofu.equals(hotelBookRoomInfo.getDaofu())) {
				throw new ManagerException("该房型已不可预定，请重新查询酒店房型(err=10)");
			}
			totalRoomDaofu += roomDaofu;
			totalRoomYufu += roomYufu;

			// 组装房间数据
			bookRoomInfo = new BookRoomInfo();
			bookRoomInfo.setAdultNum(roomPrice.getAdultNum());
			bookRoomInfo.setBedding(occu.getBedding());
			bookRoomInfo.setChildNum(roomPrice.getChildNum());
			bookRoomInfo.setChildAges(roomPrice.getChildAges());
			bookRoomInfo.setOccuId(occu.getOccuId());
			bookRoomInfo.setOccuPrice(Long.valueOf(new DecimalFormat("#").format(BigDecimal
					.valueOf(Double.valueOf(occu.getOccuPubPrice())).multiply(BigDecimal.valueOf(100)).doubleValue())));
			bookRoomInfo.setRoomId(roomPrice.getRoomId());

			contactPassenger = new BookRoomContactPassenger();
			contactPassenger.setFirstname(hotelBookRoomInfo.getFirstname());
			contactPassenger.setLastname(hotelBookRoomInfo.getLastname());
			contactPassenger.setMobilephone(hotelBookRoomInfo.getPhonenumber());
			bookRoomInfo.setContactPassenger(contactPassenger);

			if (hotelBookRoomInfo.getBbId() != null) {
				boadBase = new BookRoomBoadBase();
				boadBase.setBbId(bb.getBbId());
				boadBase.setName(bb.getBbName());
				boadBase.setPrice(Long.valueOf(new DecimalFormat("#").format(BigDecimal
						.valueOf(Double.valueOf(bb.getBbPublishPrice())).multiply(BigDecimal.valueOf(100))
						.doubleValue())));
				bookRoomInfo.setBoadBase(boadBase);
			}

			if (hotelBookRoomInfo.getSuppIds() != null && !hotelBookRoomInfo.getSuppIds().isEmpty()) {
				bookRoomInfo.setSupplements(supplements);
			}
			bookRoomInfos.add(bookRoomInfo);
		}
		if (!totalRoomDaofu.equals(totalDaofu) || !totalRoomYufu.equals(totalYufu)) {
			throw new ManagerException("该房型已不可预定，请重新查询酒店房型(err=11)");
		}
		User user = null;
		String username = null;
		if (userId != null) {
			user = userManager.getUserInfo(userId);
			if (user == null) {
				throw new ManagerException("账号不存在，请重新登录");
			}
			username = user.getEmail();
		}
		// 换算人民币
		Currency currency = Currency.getEnum(holtelDetPrice.getCurrency());
		if (currency == null) {
			throw new ManagerException("该币种不支持");
		}
		Exchange exchange = exchangeMapper.getByType(currency.getExchangeType());
		if (exchange == null) {
			throw new ManagerException("该币种不支持支付");
		}
		Long payYufu = Double.valueOf(Math.ceil(totalYufu * exchange.getExchange())).longValue();
		Long payDaofu = Double.valueOf(Math.ceil(totalDaofu * exchange.getExchange())).longValue();
		// 去支付网关支付
		String subject = holtelDetPrice.getHotelName() + "-" + holtelDetPrice.getRoomType().getName();
		String desc = holtelDetPrice.getHotelName() + "-" + holtelDetPrice.getRoomType().getName() + "-"
				+ hotelBookRoomInfos.size() + "间房-" + holtelDetPrice.getRoomType().getNights() + "晚";
		BillingHotel billingHotel = null;
		if (orderId == null) {
			orderId = orderIdSeqGenerater.nextLongValue();
			// 新建订单
			billingHotel = new BillingHotel();
			billingHotel.setId(orderId);
			billingHotel.setChargeStatus(0);
			billingHotel.setConfirmationEmail(confirmEmail);
			billingHotel.setConfirmationLogo("");
			billingHotel.setContactInfo(confirmEmail);
			billingHotel.setCurrency(holtelDetPrice.getCurrency());
			billingHotel.setPayCurrency(Currency.CNY.getName());
			billingHotel.setDeltaPrice(0);
			billingHotel.setHotelAddress(holtelDetPrice.getAddress());
			billingHotel.setHotelCity(holtelDetPrice.getCity());
			billingHotel.setHotelCountry(holtelDetPrice.getCountry());
			billingHotel.setHotelId(hotelId);
			billingHotel.setHotelName(holtelDetPrice.getHotelName());
			billingHotel.setIsOnlyAvailable(true);
			billingHotel.setPaymentType("Obligo");
			billingHotel.setCheckIn(checkIn);
			billingHotel.setCheckOut(checkOut);
			billingHotel.setPayStatus(0);
			billingHotel.setPayType(payType.getPayType());
			billingHotel.setPrice(totalYufu);
			billingHotel.setPayPrice(payYufu);
			billingHotel.setPriceAtproperty(totalDaofu);
			billingHotel.setPayPriceAtproperty(payDaofu);
			billingHotel.setExchange(exchange.getExchange());
			billingHotel.setRoomInfos(JsonUtils.objectToJson(bookRoomInfos));
			billingHotel.setRoomNum(roomInfo);
			billingHotel.setRoomType(holtelDetPrice.getRoomType().getName());
			billingHotel.setRoomTypeId(hotelRoomTypeId);
			billingHotel.setUserId(userId);
			billingHotel.setUsername(username);
			Integer result = billingHotelMapper.insert(billingHotel);
			if (result == null || result != 1) {
				throw new ManagerException("订单创建失败");
			}
		} else {
			// 支付旧订单
			billingHotel = billingHotelMapper.getById(orderId);
			if (billingHotel == null) {
				throw new ManagerException("该订单不存在");
			}
			if (userId == null || billingHotel.getUserId() == null || billingHotel.getUserId().equals(userId)) {
				throw new ManagerException("您的订单不存在");
			}
			if (billingHotel.getPayStatus().intValue() != 0 || billingHotel.getChargeStatus().intValue() != 0) {
				throw new ManagerException("该订单已完成支付");
			}
			Integer result = billingHotelMapper.updatePayInfo(exchange.getExchange(), payDaofu, payType.getPayType(),
					payYufu, orderId);
			if (result == null || result != 1) {
				throw new ManagerException("该订单不可支付");
			}
		}
		String payUri = getThirdPayUrl(payType, orderId.toString(), payYufu, subject, desc);
		if (StringUtils.isBlank(payUri)) {
			throw new ManagerException("打开支付失败，请稍后再试");
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderId", orderId.toString());
		map.put("payUri", payUri);
		return map;
	}

	@Override
	public String getThirdPayUrl(PayType payType, String orderId, Long payAmount, String subject, String description)
			throws ManagerException {
		if (payType == null) {
			throw new ManagerException("不支持的支付方式");
		}
		// 根据支付方式支付
		switch (payType) {
		case ALIPAY:
			return buildAlipayUrl(payType, orderId, payAmount, subject, description);
		default:
			throw new ManagerException("不支持的支付方式");
		}
	}

	/**
	 * 构建支付宝表单
	 * 
	 * @param gatewayPayType
	 * @param billingHotel
	 * @param subject
	 * @param body
	 * @param displayType
	 * @return
	 */
	protected String buildAlipayUrl(PayType payType, String orderId, Long payAmount, String subject, String description)
			throws ManagerException {
		AlipayAccountConfig alipayAccountConfig = new AlipayAccountConfig();
		alipayAccountConfig.setAntiFlag(true);
		alipayAccountConfig.setPartnerId(ALIPAY_PARTNERID);
		alipayAccountConfig.setSellerId(ALIPAY_PARTNERID);
		alipayAccountConfig.setSignKey(ALIPAY_SIGNKEY);
		alipayAccountConfig.setSignType(AlipaySignType.MD5);
		// post表单构建
		String syncNotifyUrl = PAY_HOST + Constants.HOTEL_SYNC_NOTIFY_URL + "/" + payType.getPayTypeId();
		String asyncNotifyUrl = PAY_HOST + Constants.HOTEL_ASYNC_NOTIFY_URL + "/" + payType.getPayTypeId();

		// 把请求参数打包成数组
		Map<String, String> params = new HashMap<String, String>();
		params.put("out_trade_no", orderId);
		params.put("subject", subject);
		params.put("body", description);
		params.put("total_fee", new DecimalFormat("#.##").format(payAmount / (double) 100));
		params.put("paymethod", "directPay");
		params.put("defaultbank", "");
		params.put("anti_phishing_key", "");
		params.put("exter_invoke_ip", "");
		// 公用回传参数，如果用户请求时传递了该参数，则返回给商户时会回传该参数。可存放任何内容（除=、&等特殊字符外），不会显示在页面上。
		params.put("extra_common_param", "");
		params.put("buyer_email", "");
		params.put("show_url", "");
		params.put("royalty_type", "");
		params.put("royalty_parameters", "");
		// 余额支付
		return AlipaySupport.createDerectPayRequest(params, syncNotifyUrl, asyncNotifyUrl, false, alipayAccountConfig);
	}

	@Override
	public Map<String, String> handleAlipaySyncNotify(Map<String, String> params) {
		Map<String, String> result = null;
		AlipayNotifySync notifySync = JsonUtils.parseToObject(JsonUtils.objectToJson(params), AlipayNotifySync.class);
		if (notifySync != null) {
			// 检查订单是否存在
			BillingHotel billingHotel = billingHotelMapper.getById(Long.valueOf(notifySync.getOut_trade_no()));
			if (billingHotel == null) {
				logger.error("订单不存在. params: " + notifySync.toString());
			} else {
				AlipayAccountConfig alipayAccountConfig = new AlipayAccountConfig();
				alipayAccountConfig.setAntiFlag(true);
				alipayAccountConfig.setPartnerId(ALIPAY_PARTNERID);
				alipayAccountConfig.setSellerId(ALIPAY_PARTNERID);
				alipayAccountConfig.setSignKey(ALIPAY_SIGNKEY);
				alipayAccountConfig.setSignType(AlipaySignType.MD5);
				boolean verifyOk = AlipaySupport.verifyNotify(params, alipayAccountConfig);
				if (verifyOk) {
					AlipayTradeStatus tradeStatus = AlipayTradeStatus.getEnum(notifySync.getTrade_status());
					// 支付成功
					if (tradeStatus == AlipayTradeStatus.TRADE_SUCCESS
							|| tradeStatus == AlipayTradeStatus.TRADE_FINISHED) {
						Long payAmount = Long.valueOf(new DecimalFormat("#").format(BigDecimal
								.valueOf(Double.valueOf(notifySync.getTotal_fee())).multiply(BigDecimal.valueOf(100))
								.doubleValue()));
						result = thirdPayOrderSuccuss(notifySync.getTrade_no(), payAmount,
								notifySync.getExtra_common_param(), billingHotel);
						// 同步通知地址
						return result;
					} else {
						logger.error("网关支付失败. params: " + notifySync.toString());
					}
				} else {
					logger.error("通知验证无效. params: " + notifySync.toString());
				}
			}
		} else {
			logger.error("通知参数有误. params: " + params.toString());
		}
		result = new HashMap<String, String>();
		result.put("err", "1");
		result.put("payedOrderId", notifySync.getOut_trade_no());
		return result;
	}

	@Override
	public String handleAlipayAsyncNotify(Map<String, String> params) {
		AlipayNotifyAsync notifyAsync = JsonUtils
				.parseToObject(JsonUtils.objectToJson(params), AlipayNotifyAsync.class);
		if (notifyAsync != null) {
			// 检查订单是否存在
			BillingHotel billingHotel = billingHotelMapper.getById(Long.valueOf(notifyAsync.getOut_trade_no()));
			if (billingHotel == null) {
				logger.error("订单不存在. params: " + notifyAsync.toString());
			} else {
				AlipayAccountConfig alipayAccountConfig = new AlipayAccountConfig();
				alipayAccountConfig.setAntiFlag(true);
				alipayAccountConfig.setPartnerId(ALIPAY_PARTNERID);
				alipayAccountConfig.setSellerId(ALIPAY_PARTNERID);
				alipayAccountConfig.setSignKey(ALIPAY_SIGNKEY);
				alipayAccountConfig.setSignType(AlipaySignType.MD5);
				boolean verifyOk = AlipaySupport.verifyNotify(params, alipayAccountConfig);
				if (verifyOk) {
					AlipayTradeStatus tradeStatus = AlipayTradeStatus.getEnum(notifyAsync.getTrade_status());
					// 支付成功
					if (tradeStatus == AlipayTradeStatus.TRADE_SUCCESS
							|| tradeStatus == AlipayTradeStatus.TRADE_FINISHED) {
						Long payAmount = Long.valueOf(new DecimalFormat("#").format(BigDecimal
								.valueOf(Double.valueOf(notifyAsync.getTotal_fee())).multiply(BigDecimal.valueOf(100))
								.doubleValue()));
						Map<String, String> result = thirdPayOrderSuccuss(notifyAsync.getTrade_no(), payAmount,
								notifyAsync.getExtra_common_param(), billingHotel);
						if (result != null && !result.isEmpty()) {
							return AlipaySupport.RESPONSE_TO_ALIPAY_SUCCESS;
						}
					} else {
						logger.error("网关支付失败. params: " + notifyAsync.toString());
					}
				} else {
					logger.error("通知验证无效. params: " + notifyAsync.toString());
				}
			}
		} else {
			logger.error("通知参数有误. params: " + params.toString());
		}
		return AlipaySupport.RESPONSE_TO_ALIPAY_FAILED;
	}

	/**
	 * 处理支付成功的订单
	 */
	protected Map<String, String> thirdPayOrderSuccuss(String gatewayOrderId, Long realPayAmount, String payExtraInfo,
			BillingHotel billingHotel) {
		Long orderId = billingHotel.getId();
		StringBuilder paramSb = new StringBuilder();
		paramSb.append("orderId=").append(orderId).append(", gatewayOrderId=").append(gatewayOrderId)
				.append(", realPayAmount=").append(realPayAmount).append(", payExtraInfo=").append(payExtraInfo);
		if (StringUtils.isBlank(gatewayOrderId) || realPayAmount == null) {
			logger.error("Some of param is null. params: " + paramSb.toString());
			billingHotelMapper.updatePayFailedInfo(orderId, "支付流水号|支付金额为空. params: " + paramSb.toString());
			return null;
		}
		// 检查订单是否已支付（重复通知）
		if (billingHotel.getPayStatus().intValue() != 1) {
			// 检查订单是否未支付
			if (billingHotel.getPayStatus().intValue() != 0) {
				logger.error("Order cannot be payed. params:" + paramSb.toString());
				return null;
			}

			// 金额判断实际支付金额不能小于平台要求的支付金额
			if (realPayAmount < billingHotel.getPayPrice()) {
				logger.error("Wrong pay amount: order payAmount=" + billingHotel.getPayPrice() + ", revc: "
						+ paramSb.toString());
				billingHotelMapper.updatePayFailedInfo(orderId, "实际支付金额小于应支付金额. params: " + paramSb.toString());
				return null;
			}
			// 更新订单状态
			Integer result = billingHotelMapper.updatePayOk(gatewayOrderId, realPayAmount, orderId);
			if (result == null || result != 1) {
				logger.error("Update order's pay status failed. params: " + paramSb.toString());
				billingHotelMapper.updatePayFailedInfo(orderId, "更新订单状态失败. params: " + paramSb.toString());
				return null;
			}
		} else { // 重复通知(常发生于同步跟异步重复)
			logger.warn("Order is payed, repeat nofify. params:" + paramSb.toString());
		}
		// 返回
		Map<String, String> map = new HashMap<String, String>();
		map.put("payedOrderId", orderId.toString());
		return map;
	}

	@Override
	public Map<String, String> queryUserOrderStatus(Long userId, Long orderId) throws ManagerException {
		if (orderId == null) {
			throw new ManagerException("订单ID为空");
		}
		BillingHotel billingHotel = billingHotelMapper.getById(orderId);
		if (billingHotel == null) {
			throw new ManagerException("订单不存在");
		}
		if (userId.longValue() != billingHotel.getUserId().longValue()) {
			throw new ManagerException("您的订单不存在");
		}

		// 返回结果
		Map<String, String> map = new HashMap<String, String>();
		map.put("payStatus", billingHotel.getPayStatus().toString());
		map.put("chargeStatus", billingHotel.getChargeStatus().toString());
		map.put("payedOrderId", orderId.toString());
		return map;
	}
}
