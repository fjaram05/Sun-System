package com.galaxy.system.exception.entity;

import java.time.LocalDateTime;

/**
 * The Class ErrorDetail.
 */
public class ErrorDetail {
	
	/** The error time. */
	private LocalDateTime errorTime;

	/** The message. */
	private String message;
	
	/** The detail. */
	private String detail;
	
	/**
	 * Gets the error time.
	 *
	 * @return the errorTime
	 */
	public LocalDateTime getErrorTime() {
		return errorTime;
	}

	/**
	 * Sets the error time.
	 *
	 * @param errorTime the errorTime to set
	 */
	public void setErrorTime(LocalDateTime errorTime) {
		this.errorTime = errorTime;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the detail.
	 *
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * Sets the detail.
	 *
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * Instantiates a new error detail.
	 *
	 * @param errorTime the error time
	 * @param message the message
	 * @param detail the detail
	 */
	public ErrorDetail(LocalDateTime errorTime, String message, String detail) {
		super();
		this.errorTime = errorTime;
		this.message = message;
		this.detail = detail;
	}

}
