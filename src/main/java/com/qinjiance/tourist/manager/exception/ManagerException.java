package com.qinjiance.tourist.manager.exception;

/**
 * @author Jiance Qin
 * 
 * @Revision revision
 * 
 * @Date 2014-8-10
 * 
 * @Time 下午12:28:00
 * 
 */
public class ManagerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2088330488857160882L;

	/**
	 * 
	 */
	public ManagerException() {

	}

	/**
	 * @param message
	 */
	public ManagerException(String message) {

		super(message);
	}

	/**
	 * @param cause
	 */
	public ManagerException(Throwable cause) {

		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ManagerException(String message, Throwable cause) {

		super(message, cause);
	}

}
