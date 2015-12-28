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
 * @Time 上午12:57:35
 * 
 */
public class NullCacheManagerException extends CacheException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3135684442682016344L;

	/**
	 * 
	 */
	public NullCacheManagerException() {

	}

	/**
	 * @param message
	 */
	public NullCacheManagerException(String message) {

		super(message);
	}

	/**
	 * @param cause
	 */
	public NullCacheManagerException(Throwable cause) {

		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NullCacheManagerException(String message, Throwable cause) {

		super(message, cause);
	}

}
