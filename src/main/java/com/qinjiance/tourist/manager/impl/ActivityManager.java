/**
 * 
 */
package com.qinjiance.tourist.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qinjiance.tourist.constants.Currency;
import com.qinjiance.tourist.manager.IActivityManager;
import com.qinjiance.tourist.manager.exception.ManagerException;
import com.qinjiance.tourist.model.vo.ActivityCategoryVo;
import com.qinjiance.tourist.model.vo.ActivityDetVo;
import com.qinjiance.tourist.model.vo.ActivityOptionVo;
import com.qinjiance.tourist.model.vo.ActivityPreBookVo;
import com.qinjiance.tourist.model.vo.ActivityPreInfoVo;
import com.qinjiance.tourist.model.vo.ActivityPriceBreakDown;
import com.qinjiance.tourist.model.vo.ActivityVo;
import com.qinjiance.tourist.model.vo.Addition;
import com.qinjiance.tourist.model.vo.AvailabilitiesVo;
import com.qinjiance.tourist.model.vo.DescFrag;
import com.qinjiance.tourist.model.vo.PassengerInfoVo;
import com.qinjiance.tourist.model.vo.SearchActivityResult;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.ActivitiesSelectedOptions;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.Activity;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.ActivityDetails;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.ActivityInfo;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.ActivityOption;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.ArrayOfInt;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.ArrayOfPreBookOption;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.Availability;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.Category;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.DescriptionFragment;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.Image;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.ListAddition;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.NumericAddition;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.NumericRangeAddition;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.PassengerInfo;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.PreBookOption;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.PreBookRequest;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.SearchActivityByActivityIdsRequest;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.SearchActivityByDestinationIdsRequest;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.TextAddition;
import com.qinjiance.tourist.support.tourico.activity.ActivityBookFlowStub.TrueFalseAddition;
import com.qinjiance.tourist.support.tourico.client.TouricoWSClient;

import module.laohu.commons.util.DateUtils;

/**
 * @author Administrator
 * 
 * @datetime 2015年12月15日 上午12:22:02
 * 
 * @desc
 */
@Service
public class ActivityManager implements IActivityManager {

	@Autowired
	private TouricoWSClient touricoWSClient;

	/**
	 * 
	 */
	public ActivityManager() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IActivityManager#searchActivities(java.lang
	 * .String, java.lang.String, java.util.Date, java.util.Date,
	 * java.lang.String)
	 */
	@Override
	public SearchActivityResult searchActivities(String desLabel, String desValue, Date from, Date to,
			String searchStar, String searchCategory) throws ManagerException {
		if (from.after(to)) {
			throw new ManagerException("开始时间不可晚于截止时间");
		}
		SearchActivityByDestinationIdsRequest request = new SearchActivityByDestinationIdsRequest();
		request.setFromDate(from);
		request.setToDate(to);
		ArrayOfInt r = new ArrayOfInt();
		r.set_int(new int[] { Integer.valueOf(desValue) });
		request.setDestinationIds(r);

		Category[] hs = touricoWSClient.searchActivitisByDest(request);
		SearchActivityResult result = new SearchActivityResult();
		Map<String, Integer> categoryMap = new HashMap<String, Integer>();
		List<Integer> starList = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			starList.add(0);
		}
		List<ActivityCategoryVo> actCateVos = new ArrayList<ActivityCategoryVo>();
		Integer cateCount = null;
		String starsLevel = null;
		if (hs != null && hs.length > 0) {
			ActivityCategoryVo actCateVo = null;
			ActivityVo activityVo = null;
			Currency cry = null;
			String actCate = null;
			for (Category h : hs) {
				if (StringUtils.isNotBlank(searchCategory) && h.getCategoryName() != null
						&& !h.getCategoryName().equalsIgnoreCase(searchCategory)) {
					continue;
				}
				actCateVo = new ActivityCategoryVo();
				actCateVo.setId(h.getCategoryId());
				actCateVo.setName(h.getCategoryName());
				List<ActivityVo> activityVos = new ArrayList<ActivityVo>();
				for (Activity a : h.getActivities().getActivity()) {
					if (StringUtils.isNotBlank(searchStar) && a.getStarsLevel() != 0
							&& !String.valueOf(a.getStarsLevel()).startsWith(searchStar)) {
						continue;
					}
					activityVo = new ActivityVo();
					activityVo.setAddress(a.getLocation().getAddress());
					activityVo.setCountry(a.getLocation().getCountryCode());
					cry = Currency.getEnum(a.getCurrency());
					if (cry == null) {
						activityVo.setCurrency(a.getCurrency());
					} else {
						activityVo.setCurrency(cry.getSymbol());
					}
					activityVo.setDesc(a.getDescription());
					activityVo.setId(a.getActivityId());
					activityVo.setMaxChildAge(a.getMaxChildAge());
					activityVo.setMinChildAge(a.getMinChildAge());
					activityVo.setName(a.getName());
					activityVo.setStars(String.valueOf(a.getStarsLevel()));
					activityVo.setThumb(a.getThumbURL());
					actCate = h.getCategoryName();
					if (StringUtils.isNotBlank(actCate)) {
						cateCount = categoryMap.get(actCate);
						if (cateCount == null) {
							cateCount = 0;
						}
						categoryMap.put(actCate, cateCount + 1);
					}
					starsLevel = String.valueOf(a.getStarsLevel());
					if (starsLevel != null) {
						if (starsLevel.toString().startsWith("5")) {
							starList.set(4, starList.get(4) + 1);
						} else if (starsLevel.toString().startsWith("4")) {
							starList.set(3, starList.get(3) + 1);
						} else if (starsLevel.toString().startsWith("3")) {
							starList.set(2, starList.get(2) + 1);
						} else if (starsLevel.toString().startsWith("2")) {
							starList.set(1, starList.get(1) + 1);
						} else if (starsLevel.toString().startsWith("1")) {
							starList.set(0, starList.get(0) + 1);
						}
					}
					activityVos.add(activityVo);
				}
				actCateVo.setActivityVos(activityVos);
				actCateVos.add(actCateVo);
			}
		}
		result.setActVos(actCateVos);
		result.setCategoryMap(categoryMap);
		result.setStarList(starList);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IActivityManager#getActDetail(java.lang
	 * .Integer, java.util.Date, java.util.Date)
	 */
	@Override
	public ActivityDetVo getActDetail(Integer activityId, Date from, Date to) throws ManagerException {
		if (from.after(to)) {
			throw new ManagerException("开始时间不可晚于截止时间");
		}
		SearchActivityByActivityIdsRequest request = new SearchActivityByActivityIdsRequest();
		request.setFromDate(from);
		request.setToDate(to);
		ArrayOfInt r = new ArrayOfInt();
		r.set_int(new int[] { Integer.valueOf(activityId) });
		request.setActivityIds(r);

		Category[] hs = touricoWSClient.searchActivitisById(request);
		ActivityDetVo result = new ActivityDetVo();
		if (hs != null && hs.length > 0 && hs[0].getActivities().getActivity() != null
				&& hs[0].getActivities().getActivity().length > 0) {
			Currency cry = null;
			Activity a = hs[0].getActivities().getActivity()[0];

			result.setCategoryId(hs[0].getCategoryId());
			result.setCategoryName(hs[0].getCategoryName());
			result.setAddress(a.getLocation().getAddress());
			result.setCountry(a.getLocation().getCountryCode());
			cry = Currency.getEnum(a.getCurrency());
			if (cry == null) {
				result.setCurrency(a.getCurrency());
			} else {
				result.setCurrency(cry.getSymbol());
			}
			result.setActivityDesc(a.getDescription());
			result.setActivityId(a.getActivityId());
			result.setMaxChildAge(a.getMaxChildAge());
			result.setMinChildAge(a.getMinChildAge());
			result.setActivityName(a.getName());
			result.setStars(String.valueOf(a.getStarsLevel()));

			List<ActivityOptionVo> aovs = new ArrayList<ActivityOptionVo>();
			ActivityOptionVo aov = null;
			for (ActivityOption ao : a.getActivityOptions().getActivityOption()) {
				aov = new ActivityOptionVo();
				aov.setId(ao.getOptionId());
				aov.setName(ao.getName());
				aov.setType(ao.getType());

				List<AvailabilitiesVo> avos = new ArrayList<AvailabilitiesVo>();
				AvailabilitiesVo av = null;
				Date avilFrom = null;
				Date avilTo = null;
				Date tmp = null;
				for (Availability aa : ao.getAvailabilities().getAvailability()) {
					av = new AvailabilitiesVo();
					av.setAdultPrice(aa.getAdultPrice().toString());
					av.setChildPrice(aa.getChildPrice().toString());
					av.setMaxAdults(aa.getMaxAdults());
					av.setMaxChildren(aa.getMaxChildren());
					av.setMaxUnits(aa.getMaxUnits());
					av.setUnitPrice(aa.getUnitPrice().toString());
					List<String> dates = new ArrayList<String>();
					avilFrom = aa.getFromDate();
					avilTo = aa.getToDate();
					tmp = new Date(avilFrom.getTime());
					while (!tmp.after(avilTo)) {
						dates.add(DateUtils.formatAsString(tmp, "yyyy-MM-dd"));
						tmp = org.apache.commons.lang.time.DateUtils.addDays(tmp, 1);
					}
					av.setDates(dates);
					avos.add(av);
				}
				aov.setAvailabilitiesVos(avos);
				aovs.add(aov);
			}
			result.setActivityOptionVos(aovs);

			ActivityDetails ad = touricoWSClient.getActivitisDet(activityId);
			if (ad != null) {
				result.setPhone(ad.getActivityPhone());
				result.setVoucherRemarks(ad.getDescription().getLongDescription().getVoucherRemarks().getDesc());
				List<DescFrag> frags = new ArrayList<DescFrag>();
				DescFrag frag = null;
				for (DescriptionFragment df : ad.getDescription().getLongDescription().getFragments()
						.getDescriptionFragment()) {
					frag = new DescFrag();
					frag.setDesc(df.getValue());
					frag.setType(df.getType());
					frags.add(frag);
				}
				result.setDescFrags(frags);

				List<String> imgs = new ArrayList<String>();
				for (Image img : ad.getMedia().getImages().getImage()) {
					imgs.add(img.getPath());
				}
				result.setImgs(imgs);
			}
		}
		return result;
	}

	@Override
	public ActivityPreBookVo preBook(Integer activityId, Integer optionId, Date date, Integer adult, Integer children,
			Integer unit) throws ManagerException {
		if (adult == 0 && children == 0 && unit == 0) {
			throw new ManagerException("您未选择参加活动的人数");
		}

		ActivityDetails ad = touricoWSClient.getActivitisDet(activityId);
		ActivityPreBookVo result = new ActivityPreBookVo();
		if (ad != null) {
			result.setCategoryName(ad.getCategoryName());
			result.setAddress(ad.getLocation().getAddress());
			result.setCountry(ad.getLocation().getCountryCode());
			result.setActivityDesc(ad.getDescription().getShortDescription().getDesc());
			result.setActivityId(ad.getActivityId());
			result.setActivityName(ad.getName());
			result.setPhone(ad.getActivityPhone());
			result.setStars(String.valueOf(ad.getStarsLevel()));
			result.setVoucherRemark(ad.getDescription().getLongDescription().getVoucherRemarks().getDesc());

			PreBookRequest request = new PreBookRequest();
			ArrayOfPreBookOption abo = new ArrayOfPreBookOption();
			PreBookOption pbo = new PreBookOption();
			pbo.setActivityId(activityId);
			pbo.setDate(date);
			pbo.setNumOfAdults(adult);
			pbo.setNumOfChildren(children);
			pbo.setNumOfUnits(unit);
			pbo.setOptionId(optionId);
			abo.addPreBookOption(pbo);
			request.setBookActivityOptions(abo);

			ActivitiesSelectedOptions aso = touricoWSClient.preBook(request);
			if (aso != null) {
				Double totalPrice = 0D;
				Double totalTax = 0D;
				ActivityInfo[] ais = aso.getActivitiesInfo().getActivityInfo();
				if (ais != null && ais.length > 0) {

					Currency cry = null;
					cry = Currency.getEnum(ais[0].getActivityPricing().getCurrency());
					if (cry == null) {
						result.setCurrency(ais[0].getActivityPricing().getCurrency());
					} else {
						result.setCurrency(cry.getSymbol());
					}

					List<ActivityPreInfoVo> apvs = new ArrayList<ActivityPreInfoVo>();
					ActivityPreInfoVo apv = null;
					for (ActivityInfo ai : ais) {
						apv = new ActivityPreInfoVo();

						apv.setActivityId(ai.getActivityId());
						apv.setDate(DateUtils.formatAsString(ai.getDate().getTime(), "yyyy-MM-dd"));
						apv.setOptionId(ai.getOptionId());
						apv.setPriceSum(ai.getActivityPricing().getPrice().toString());
						totalPrice+=ai.getActivityPricing().getPrice().doubleValue();
						apv.setTaxSum(ai.getActivityPricing().getTax().toString());
						totalTax+=ai.getActivityPricing().getTax().doubleValue();

						List<ActivityPriceBreakDown> priceBreakDowns = new ArrayList<ActivityPriceBreakDown>();
						if (ai.getActivityPricing().getPriceBreakdown().getAdult() != null) {
							ActivityPriceBreakDown priceBreakDown = new ActivityPriceBreakDown();
							priceBreakDown.setName("成人");
							priceBreakDown.setAmount(
									ai.getActivityPricing().getPriceBreakdown().getAdult().getAmount().toString());
							priceBreakDown.setNum(ai.getActivityPricing().getPriceBreakdown().getAdult().getNumbers());
							priceBreakDowns.add(priceBreakDown);
						}
						if (ai.getActivityPricing().getPriceBreakdown().getChild() != null) {
							ActivityPriceBreakDown priceBreakDown = new ActivityPriceBreakDown();
							priceBreakDown.setName("儿童");
							priceBreakDown.setAmount(
									ai.getActivityPricing().getPriceBreakdown().getChild().getAmount().toString());
							priceBreakDown.setNum(ai.getActivityPricing().getPriceBreakdown().getChild().getNumbers());
							priceBreakDowns.add(priceBreakDown);
						}
						if (ai.getActivityPricing().getPriceBreakdown().getUnit() != null) {
							ActivityPriceBreakDown priceBreakDown = new ActivityPriceBreakDown();
							priceBreakDown.setName("项目");
							priceBreakDown.setAmount(
									ai.getActivityPricing().getPriceBreakdown().getUnit().getAmount().toString());
							priceBreakDown.setNum(ai.getActivityPricing().getPriceBreakdown().getUnit().getNumbers());
							priceBreakDowns.add(priceBreakDown);
						}
						apv.setPriceBreakDown(priceBreakDowns);

						if (ai.getActivityAdditions() != null) {
							List<Addition> activityAdditions = new ArrayList<Addition>();
							Addition activityAddition = null;
							if (ai.getActivityAdditions().getListAdditions() != null
									&& ai.getActivityAdditions().getListAdditions().getListAddition() != null
									&& ai.getActivityAdditions().getListAdditions().getListAddition().length > 0) {
								for (ListAddition la : ai.getActivityAdditions().getListAdditions().getListAddition()) {
									activityAddition = new Addition();
									activityAddition.setCategory("list");
									activityAddition.setType(la.getAdditionType());
									activityAddition.setTypeId(la.getAdditionTypeID());
									activityAddition.setValueList(Arrays.asList(la.getValidValues().getString()));
									activityAdditions.add(activityAddition);
								}
							}
							if (ai.getActivityAdditions().getNumericAdditions() != null
									&& ai.getActivityAdditions().getNumericAdditions().getNumericAddition() != null
									&& ai.getActivityAdditions().getNumericAdditions()
											.getNumericAddition().length > 0) {
								for (NumericAddition la : ai.getActivityAdditions().getNumericAdditions()
										.getNumericAddition()) {
									activityAddition = new Addition();
									activityAddition.setCategory("num");
									activityAddition.setType(la.getAdditionType());
									activityAddition.setTypeId(la.getAdditionTypeID());
									activityAdditions.add(activityAddition);
								}
							}
							if (ai.getActivityAdditions().getNumericRangeAdditions() != null
									&& ai.getActivityAdditions().getNumericRangeAdditions()
											.getNumericRangeAddition() != null
									&& ai.getActivityAdditions().getNumericRangeAdditions()
											.getNumericRangeAddition().length > 0) {
								for (NumericRangeAddition la : ai.getActivityAdditions().getNumericRangeAdditions()
										.getNumericRangeAddition()) {
									activityAddition = new Addition();
									activityAddition.setCategory("numrange");
									activityAddition.setType(la.getAdditionType());
									activityAddition.setTypeId(la.getAdditionTypeID());
									activityAddition.setMinVal(la.getMinValue());
									activityAddition.setMaxVal(la.getMaxValue());
									activityAdditions.add(activityAddition);
								}
							}
							if (ai.getActivityAdditions().getTextAdditions() != null
									&& ai.getActivityAdditions().getTextAdditions().getTextAddition() != null
									&& ai.getActivityAdditions().getTextAdditions().getTextAddition().length > 0) {
								for (TextAddition la : ai.getActivityAdditions().getTextAdditions().getTextAddition()) {
									activityAddition = new Addition();
									activityAddition.setCategory("text");
									activityAddition.setType(la.getAdditionType());
									activityAddition.setTypeId(la.getAdditionTypeID());
									activityAdditions.add(activityAddition);
								}
							}
							if (ai.getActivityAdditions().getTrueFalseAdditions() != null
									&& ai.getActivityAdditions().getTrueFalseAdditions().getTrueFalseAddition() != null
									&& ai.getActivityAdditions().getTrueFalseAdditions()
											.getTrueFalseAddition().length > 0) {
								for (TrueFalseAddition la : ai.getActivityAdditions().getTrueFalseAdditions()
										.getTrueFalseAddition()) {
									activityAddition = new Addition();
									activityAddition.setCategory("bool");
									activityAddition.setType(la.getAdditionType());
									activityAddition.setTypeId(la.getAdditionTypeID());
									activityAdditions.add(activityAddition);
								}
							}
							apv.setActivityAdditions(activityAdditions);
						}
						if (ai.getActivityAdditions() != null) {
							List<Addition> activityAdditions = new ArrayList<Addition>();
							Addition activityAddition = null;
							if (ai.getActivityAdditions().getListAdditions() != null
									&& ai.getActivityAdditions().getListAdditions().getListAddition() != null
									&& ai.getActivityAdditions().getListAdditions().getListAddition().length > 0) {
								for (ListAddition la : ai.getActivityAdditions().getListAdditions().getListAddition()) {
									activityAddition = new Addition();
									activityAddition.setCategory("list");
									activityAddition.setType(la.getAdditionType());
									activityAddition.setTypeId(la.getAdditionTypeID());
									activityAddition.setValueList(Arrays.asList(la.getValidValues().getString()));
									activityAdditions.add(activityAddition);
								}
							}
							if (ai.getActivityAdditions().getNumericAdditions() != null
									&& ai.getActivityAdditions().getNumericAdditions().getNumericAddition() != null
									&& ai.getActivityAdditions().getNumericAdditions()
											.getNumericAddition().length > 0) {
								for (NumericAddition la : ai.getActivityAdditions().getNumericAdditions()
										.getNumericAddition()) {
									activityAddition = new Addition();
									activityAddition.setCategory("num");
									activityAddition.setType(la.getAdditionType());
									activityAddition.setTypeId(la.getAdditionTypeID());
									activityAdditions.add(activityAddition);
								}
							}
							if (ai.getActivityAdditions().getNumericRangeAdditions() != null
									&& ai.getActivityAdditions().getNumericRangeAdditions()
											.getNumericRangeAddition() != null
									&& ai.getActivityAdditions().getNumericRangeAdditions()
											.getNumericRangeAddition().length > 0) {
								for (NumericRangeAddition la : ai.getActivityAdditions().getNumericRangeAdditions()
										.getNumericRangeAddition()) {
									activityAddition = new Addition();
									activityAddition.setCategory("numrange");
									activityAddition.setType(la.getAdditionType());
									activityAddition.setTypeId(la.getAdditionTypeID());
									activityAddition.setMinVal(la.getMinValue());
									activityAddition.setMaxVal(la.getMaxValue());
									activityAdditions.add(activityAddition);
								}
							}
							if (ai.getActivityAdditions().getTextAdditions() != null
									&& ai.getActivityAdditions().getTextAdditions().getTextAddition() != null
									&& ai.getActivityAdditions().getTextAdditions().getTextAddition().length > 0) {
								for (TextAddition la : ai.getActivityAdditions().getTextAdditions().getTextAddition()) {
									activityAddition = new Addition();
									activityAddition.setCategory("text");
									activityAddition.setType(la.getAdditionType());
									activityAddition.setTypeId(la.getAdditionTypeID());
									activityAdditions.add(activityAddition);
								}
							}
							if (ai.getActivityAdditions().getTrueFalseAdditions() != null
									&& ai.getActivityAdditions().getTrueFalseAdditions().getTrueFalseAddition() != null
									&& ai.getActivityAdditions().getTrueFalseAdditions()
											.getTrueFalseAddition().length > 0) {
								for (TrueFalseAddition la : ai.getActivityAdditions().getTrueFalseAdditions()
										.getTrueFalseAddition()) {
									activityAddition = new Addition();
									activityAddition.setCategory("bool");
									activityAddition.setType(la.getAdditionType());
									activityAddition.setTypeId(la.getAdditionTypeID());
									activityAdditions.add(activityAddition);
								}
							}
							apv.setActivityAdditions(activityAdditions);
						}
						if (ai.getPassengers().getPassengerInfo() != null
								&& ai.getPassengers().getPassengerInfo().length > 0) {
							List<PassengerInfoVo> passengerInfoVos = new ArrayList<PassengerInfoVo>();
							PassengerInfoVo passengerInfoVo = null;
							for (PassengerInfo pass : ai.getPassengers().getPassengerInfo()) {
								passengerInfoVo = new PassengerInfoVo();
								if (pass.getIsMainContact()) {
									passengerInfoVo.setIsMainContact(true);
								}
								if (pass.getFirstName() != null) {
									passengerInfoVo.setNeedFirstName(true);
								}
								if (pass.getLastName() != null) {
									passengerInfoVo.setNeedLastName(true);
								}
								if (pass.getMobilePhone() != null) {
									passengerInfoVo.setNeedMobilePhone(true);
								}
								passengerInfoVo.setSeqNumber(pass.getSeqNumber());
								passengerInfoVo.setType(pass.getType().getValue());
								if (pass.getType().getValue().equalsIgnoreCase("adult")) {
									passengerInfoVo.setTypeName("成人");
								} else if (pass.getType().getValue().equalsIgnoreCase("child")) {
									passengerInfoVo.setTypeName("儿童");
								} else if (pass.getType().getValue().equalsIgnoreCase("unit")) {
									passengerInfoVo.setTypeName("项目");
								}
								List<Addition> additions = new ArrayList<Addition>();
								Addition addition = null;
								if (pass.getPassengerAdditions() != null) {
									if (pass.getPassengerAdditions().getListAdditions() != null
											&& pass.getPassengerAdditions().getListAdditions().getListAddition() != null
											&& pass.getPassengerAdditions().getListAdditions()
													.getListAddition().length > 0) {
										for (ListAddition la : pass.getPassengerAdditions().getListAdditions()
												.getListAddition()) {
											addition = new Addition();
											addition.setCategory("list");
											addition.setType(la.getAdditionType());
											addition.setTypeId(la.getAdditionTypeID());
											addition.setValueList(Arrays.asList(la.getValidValues().getString()));
											additions.add(addition);
										}
									}
									if (pass.getPassengerAdditions().getNumericAdditions() != null
											&& pass.getPassengerAdditions().getNumericAdditions()
													.getNumericAddition() != null
											&& pass.getPassengerAdditions().getNumericAdditions()
													.getNumericAddition().length > 0) {
										for (NumericAddition la : pass.getPassengerAdditions().getNumericAdditions()
												.getNumericAddition()) {
											addition = new Addition();
											addition.setCategory("num");
											addition.setType(la.getAdditionType());
											addition.setTypeId(la.getAdditionTypeID());
											additions.add(addition);
										}
									}
									if (pass.getPassengerAdditions().getNumericRangeAdditions() != null
											&& pass.getPassengerAdditions().getNumericRangeAdditions()
													.getNumericRangeAddition() != null
											&& pass.getPassengerAdditions().getNumericRangeAdditions()
													.getNumericRangeAddition().length > 0) {
										for (NumericRangeAddition la : pass.getPassengerAdditions()
												.getNumericRangeAdditions().getNumericRangeAddition()) {
											addition = new Addition();
											addition.setCategory("numrange");
											addition.setType(la.getAdditionType());
											addition.setTypeId(la.getAdditionTypeID());
											addition.setMinVal(la.getMinValue());
											addition.setMaxVal(la.getMaxValue());
											additions.add(addition);
										}
									}
									if (pass.getPassengerAdditions().getTextAdditions() != null
											&& pass.getPassengerAdditions().getTextAdditions().getTextAddition() != null
											&& pass.getPassengerAdditions().getTextAdditions()
													.getTextAddition().length > 0) {
										for (TextAddition la : pass.getPassengerAdditions().getTextAdditions()
												.getTextAddition()) {
											addition = new Addition();
											addition.setCategory("text");
											addition.setType(la.getAdditionType());
											addition.setTypeId(la.getAdditionTypeID());
											additions.add(addition);
										}
									}
									if (pass.getPassengerAdditions().getTrueFalseAdditions() != null
											&& pass.getPassengerAdditions().getTrueFalseAdditions()
													.getTrueFalseAddition() != null
											&& pass.getPassengerAdditions().getTrueFalseAdditions()
													.getTrueFalseAddition().length > 0) {
										for (TrueFalseAddition la : pass.getPassengerAdditions().getTrueFalseAdditions()
												.getTrueFalseAddition()) {
											addition = new Addition();
											addition.setCategory("bool");
											addition.setType(la.getAdditionType());
											addition.setTypeId(la.getAdditionTypeID());
											additions.add(addition);
										}
									}
								}
								passengerInfoVo.setAdditions(additions);
								passengerInfoVos.add(passengerInfoVo);
							}
							apv.setPassengerInfos(passengerInfoVos);
						}
						apvs.add(apv);
					}
					result.setActivityPreInfoVos(apvs);
				}
				result.setTotalPrice(String.valueOf(totalPrice));
				result.setTotalTax(String.valueOf(totalTax));
			}
		}
		return result;
	}
}
