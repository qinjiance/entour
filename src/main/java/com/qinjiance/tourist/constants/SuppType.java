/**
 * 
 */
package com.qinjiance.tourist.constants;

import org.apache.commons.lang.StringUtils;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年7月8日
 * 
 * @time 下午2:24:03
 * 
 * @desc
 * 
 */
public enum SuppType {

	ROOM(1, "PerRoomSupplement "), PERSON(2, "PerPersonSupplement ") ;

	private final int val;
	private final String name;

	private SuppType(int val, String name) {
		this.val = val;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getVal() {
		return val;
	}

	public static SuppType getEnum(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		for (SuppType currency : SuppType.values()) {
			if (currency.getName().equalsIgnoreCase(name)) {
				return currency;
			}
		}
		return null;
	}
}
