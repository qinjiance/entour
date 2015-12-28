/**
 * 
 */
package com.qinjiance.tourist.constants;

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
public enum UserLevel {

	COMMON(0), VIP_TOP(100);

	private final int level;

	private UserLevel(int level) {

		this.level = level;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {

		return level;
	}

}
