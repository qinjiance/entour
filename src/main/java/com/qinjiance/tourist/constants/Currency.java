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

	CNY("CNY", "¥", 0), USD("USD", "$", 1), EUR("EUR", "€", 2);

	private final String name;
	private final String symbol;
	private final int exchangeType;

	private Currency(String name, String symbol, int exchangeType) {
		this.name = name;
		this.symbol = symbol;
		this.exchangeType = exchangeType;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	/**
	 * @return the exchangeType
	 */
	public int getExchangeType() {
		return exchangeType;
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
