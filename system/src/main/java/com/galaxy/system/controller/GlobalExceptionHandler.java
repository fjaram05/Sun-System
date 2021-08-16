/**
 * 
 */
package com.galaxy.system.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.galaxy.system.exception.SystemNotFoundException;
import com.galaxy.system.exception.entity.ErrorDetail;

/**
 * The Class GlobalExceptionHandler.
 *
 * @author ajaramillo
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Handler exceptions.
	 *
	 * @param ex the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler(SystemNotFoundException.class)
	public ResponseEntity<Object> handlerExceptions(SystemNotFoundException ex, WebRequest request){
		ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
	}

}
