package com.galaxy.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class SystemNotFoundException.
 *
 * @author ajaramillo
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SystemNotFoundException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8667953647286216701L;

	/**
	 * Instantiates a new system not found exception.
	 *
	 * @param message the message
	 */
	public SystemNotFoundException(String message) {
		super(message);
	}

}
