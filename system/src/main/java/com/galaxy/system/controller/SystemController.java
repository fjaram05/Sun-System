package com.galaxy.system.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.system.data.mongodb.model.SystemDay;
import com.galaxy.system.entity.ClimateCondition;
import com.galaxy.system.exception.SystemNotFoundException;
import com.galaxy.system.services.SystemServicesInterface;

/**
 * The Class SystemController.
 * 
 * @author ajaramillo
 */
@RestController
@RequestMapping("api/v1")
@CrossOrigin("*")
public class SystemController {
	
	/** The Constant logger. */
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SystemController.class);
	
	/** The Constant ERROR. */
	private static final String ERROR = "Batch Generated Error";
	
	/** The Constant DATEINFO. */
	private static final String DAY = "day";
	
	/** The Constant DATEINFO. */
	private static final String DATEINFO = "dateInfo";
	
	/** The Constant DATEINI. */
	private static final String DATEINI = "dateIni";
	
	/** The Constant DATEFIN. */
	private static final String DATEFIN = "dateFin";
	
	/** The Constant YEARS_PER_PERIOD. */
	private static final int YEARS_PER_PERIOD = 10;
	
	/** The system services. */
	@Autowired
	SystemServicesInterface systemServices;
	
	/**
	 * Status.
	 *
	 * @return the string
	 */
	@GetMapping(value = "/status")
	public String status() {
		return "ok";
	}
	
	/**
	 * Gets the day.
	 *
	 * @param date the date
	 * @return the day
	 * @throws SystemNotFoundException the system not found exception
	 */
	@GetMapping(value = "/findDay", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<SystemDay> getDay(@RequestParam(DATEINFO)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws SystemNotFoundException {
		try {
			return ResponseEntity.ok(systemServices.consultationDay(date));
		} catch (SystemNotFoundException e) {
			logger.error(ERROR,e);
			throw new SystemNotFoundException(e.getMessage());
		}
		
	}
	
	/**
	 * Gets the day.
	 *
	 * @param date the date
	 * @return the day
	 * @throws SystemNotFoundException the system not found exception
	 */
	@GetMapping(value = "/findNumberDay", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ClimateCondition> getDayNumber(@RequestParam(DAY)
	Integer day) throws SystemNotFoundException {
		try {
			return ResponseEntity.ok(systemServices.consultationNumberDay(day));
		} catch (SystemNotFoundException e) {
			logger.error(ERROR,e);
			throw new SystemNotFoundException(e.getMessage());
		}
		
	}
	
	/**
	 * Sets the job years.
	 *
	 * @param initialDate the initial date
	 * @param finalDate the final date
	 * @return the response entity
	 * @throws SystemNotFoundException the system not found exception
	 */
	@PutMapping(value = "/jobYears", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<HttpStatus> setJobYears(@RequestParam(DATEINI) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate, 
	@RequestParam(DATEFIN) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finalDate) throws SystemNotFoundException {
		try {
			systemServices.batchCalculationDays(initialDate, finalDate);
			return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
		} catch (SystemNotFoundException e) {
			logger.error(ERROR,e);
			throw new SystemNotFoundException(e.getMessage());
		}
	}
	
	/**
	 * Sets the job period.
	 *
	 * @return the response entity
	 * @throws SystemNotFoundException the system not found exception
	 */
	@PutMapping(value = "/jobPeriod", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<HttpStatus> setJobPeriod() throws SystemNotFoundException {
		try {
			systemServices.batchCalculationDays(LocalDate.now(), LocalDate.now().plusYears(YEARS_PER_PERIOD));
			return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
		} catch (SystemNotFoundException e) {
			logger.error(ERROR,e);
			throw new SystemNotFoundException(e.getMessage());
		}
	}
	
	/**
	 * Gets the period data.
	 *
	 * @return the period data
	 * @throws SystemNotFoundException the system not found exception
	 */
	@GetMapping(value = "/periodData", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ClimateCondition> getPeriodData() throws SystemNotFoundException {
		try {
			return ResponseEntity.ok(systemServices.periodData());
		} catch (SystemNotFoundException e) {
			logger.error(ERROR,e);
			throw new SystemNotFoundException(e.getMessage());
		}
	}
	
	/**
	 * Gets the dry spells.
	 *
	 * @return the dry spells
	 * @throws SystemNotFoundException the system not found exception
	 */
	@GetMapping(value = "/drySpells", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ClimateCondition> getDrySpells() throws SystemNotFoundException {
		try {
			return ResponseEntity.ok(systemServices.getDryPeriod());
		} catch (SystemNotFoundException e) {
			logger.error(ERROR,e);
			throw new SystemNotFoundException(e.getMessage());
		}
	}
	
	/**
	 * Gets the rainy periods.
	 *
	 * @return the rainy periods
	 * @throws SystemNotFoundException the system not found exception
	 */
	@GetMapping(value = "/rainyPeriods", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ClimateCondition> getRainyPeriods() throws SystemNotFoundException {
		try {
			return ResponseEntity.ok(systemServices.getRainPeriod());
		} catch (SystemNotFoundException e) {
			logger.error(ERROR,e);
			throw new SystemNotFoundException(e.getMessage());
		}
	}
	
	/**
	 * Gets the optimal conditions.
	 *
	 * @return the optimal conditions
	 * @throws SystemNotFoundException the system not found exception
	 */
	@GetMapping(value = "/optimalConditions", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ClimateCondition> getOptimalConditions() throws SystemNotFoundException {
		try {
			return ResponseEntity.ok(systemServices.getOptimalConditions());
		} catch (SystemNotFoundException e) {
			logger.error(ERROR,e);
			throw new SystemNotFoundException(e.getMessage());
		}
	}
	
	/**
	 * Gets the maximum rain day.
	 *
	 * @return the maximum rain day
	 * @throws SystemNotFoundException the system not found exception
	 */
	@GetMapping(value = "/maximumRainDay", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ClimateCondition> getMaximumRainDay() throws SystemNotFoundException {
		try {
			return ResponseEntity.ok(systemServices.getMaximumRainDay());
		} catch (SystemNotFoundException e) {
			logger.error(ERROR,e);
			throw new SystemNotFoundException(e.getMessage());
		}
	}

}
