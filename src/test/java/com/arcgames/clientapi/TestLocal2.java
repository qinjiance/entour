/**
 * 
 */
package com.arcgames.clientapi;

import module.laohu.commons.util.EncryptUtils;

/**
 * @author "Jiance Qin"
 *
 * @date 2014年7月15日
 *
 * @time 下午2:01:20
 *
 * @desc 
 *
 */
public class TestLocal2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(EncryptUtils.saltSHA256("onekeyopcredentialssalt","admin"));
	}

}
