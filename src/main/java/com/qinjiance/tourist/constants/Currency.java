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
public enum Currency {

	CNY("CNY", "¥"), USD("USD", "$"), EUR("EUR", "€");

	private final String name;
	private final String symbol;

	private Currency(String name, String symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public static Currency getEnum(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		for (Currency currency : Currency.values()) {
			if (currency.getName().equalsIgnoreCase(name)) {
				return currency;
			}
		}
		return null;
	}
}
