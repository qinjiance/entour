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
 * @Time 上午12:56:57
 * 
 */
public class CacheException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2424222564657566568L;

	/**
	 * 
	 */
	public CacheException() {

	}

	/**
	 * @param message
	 */
	public CacheException(String message) {

		super(message);
	}

	/**
	 * @param cause
	 */
	public CacheException(Throwable cause) {

		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CacheException(String message, Throwable cause) {

		super(message, cause);
	}

}
