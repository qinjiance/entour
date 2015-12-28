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
public enum PhotoScale {

	BIG(1), MIDDLE(2), SMALL(3);

	private final int scale;

	private PhotoScale(int scale) {

		this.scale = scale;
	}

	/**
	 * @return the scale
	 */
	public int getScale() {

		return scale;
	}

}
