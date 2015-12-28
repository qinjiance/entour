/**
 * 
 */
package com.qinjiance.tourist.controller.editor;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import module.laohu.commons.util.StringHelper;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年3月18日
 * 
 * @time 上午10:24:15
 * 
 * @desc
 * 
 */
public class TimestampEditor extends PropertyEditorSupport {

	/**
	 * 按照定义时加入列表的顺序优先匹配.
	 */
	private final List<SimpleDateFormat> dateFormat;
	private final boolean allowEmpty;
	private final int exactDateLength;

	public TimestampEditor(List<SimpleDateFormat> dateFormat, boolean allowEmpty) {
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
		this.exactDateLength = -1;
	}

	public TimestampEditor(List<SimpleDateFormat> dateFormat,
			boolean allowEmpty, int exactDateLength) {
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
		this.exactDateLength = exactDateLength;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && StringHelper.isBlank(text)) {
			setValue(null);
		} else {
			if (text != null && this.exactDateLength >= 0
					&& text.length() != this.exactDateLength) {
				throw new IllegalArgumentException(
						"Could not parse date: it is not exactly"
								+ this.exactDateLength + "characters long");
			}
			for (int i = 0; i < this.dateFormat.size(); i++) {
				try {
					setValue(new Timestamp(this.dateFormat.get(i).parse(text)
							.getTime()));
					break;
				} catch (ParseException ex) {
					if (i == this.dateFormat.size() - 1) {
						throw new IllegalArgumentException(
								"Could not parse date: " + ex.getMessage(), ex);
					}
					continue;
				}
			}
		}
	}

	@Override
	public String getAsText() {
		Timestamp stamp = (Timestamp) getValue();
		String text = "";
		if (stamp != null) {
			for (int i = 0; i < this.dateFormat.size(); i++) {
				text = this.dateFormat.get(i).format(stamp);
				if (StringHelper.isNotBlank(text)) {
					break;
				}
			}
		}
		return text;
	}
}
