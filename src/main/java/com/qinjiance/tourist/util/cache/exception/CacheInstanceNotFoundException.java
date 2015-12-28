/**
 * 
 */
package com.qinjiance.tourist.util.cache.exception;

/**
 * @author Jiance Qin
 * 
 * @Revision revision
 * 
 * @Date 2014-4-4
 * 
 * @Time 上午12:57:13
 * 
 */
public class CacheInstanceNotFoundException extends CacheException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8845760677623382798L;

	/**
	 * 
	 */
	public CacheInstanceNotFoundException() {

	}

	/**
	 * @param message
	 */
	public CacheInstanceNotFoundException(String message) {

		super(message);
	}

	/**
	 * @param cause
	 */
	public CacheInstanceNotFoundException(Throwable cause) {

		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CacheInstanceNotFoundException(String message, Throwable cause) {

		super(message, cause);
	}

}
