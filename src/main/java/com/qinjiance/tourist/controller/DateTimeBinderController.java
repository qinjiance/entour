package com.qinjiance.tourist.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import module.laohu.commons.mvc.BaseController;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.qinjiance.tourist.controller.editor.DateTimeEditor;
import com.qinjiance.tourist.controller.editor.TimestampEditor;


/**
 * @author "Jiance Qin"
 * 
 * @date 2014年3月18日
 * 
 * @time 上午10:18:39
 * 
 * @desc
 * 
 */
public class DateTimeBinderController extends BaseController {

	public static final String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMATTER = "yyyy-MM-dd";

	/**
	 * 
	 */
	public DateTimeBinderController() {
	}

	@InitBinder
	public void dateTimeBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMATTER);
		dateFormat.setLenient(false);
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(
				DATE_TIME_FORMATTER);
		dateFormat.setLenient(false);
		List<SimpleDateFormat> formatters = new ArrayList<SimpleDateFormat>();
		formatters.add(datetimeFormat);
		formatters.add(dateFormat);

		binder.registerCustomEditor(java.util.Date.class, new DateTimeEditor(
				formatters, true));
		binder.registerCustomEditor(java.sql.Timestamp.class,
				new TimestampEditor(formatters, true));

	}

}
