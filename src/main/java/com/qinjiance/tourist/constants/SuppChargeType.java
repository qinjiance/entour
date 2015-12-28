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
public enum SuppChargeType {

	INC(1, "Included"), ADD(2, "Addition"), AP(3, "At Property");

	private final int val;
	private final String name;

	private SuppChargeType(int val, String name) {
		this.val = val;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getVal() {
		return val;
	}

	public static SuppChargeType getEnum(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		for (SuppChargeType currency : SuppChargeType.values()) {
			if (currency.getName().equalsIgnoreCase(name)) {
				return currency;
			}
		}
		return null;
	}
}
