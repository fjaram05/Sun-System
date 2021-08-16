/**
 * 
 */
package com.galaxy.system.data.mongodb.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.galaxy.system.data.mongodb.model.SystemDay;

/**
 * The Interface SystemDayRepository.
 *
 * @author ajaramillo
 */
public interface SystemDayRepository extends MongoRepository<SystemDay, String> {

	/**
	 * Find by line.
	 *
	 * @param line the line
	 * @return the list
	 */
	List<SystemDay> findByLine(boolean line);
	
	/**
	 * Find by rain period.
	 *
	 * @param period the period
	 * @return the list
	 */
	List<SystemDay> findByRainPeriod(boolean period);
	
	/**
	 * Find by dry period.
	 *
	 * @param period the period
	 * @return the list
	 */
	List<SystemDay> findByDryPeriod(boolean period);
	
	/**
	 * Find by optimal pressure.
	 *
	 * @param period the period
	 * @return the list
	 */
	List<SystemDay> findByOptimalPressure(boolean period);
	
	/**
	 * Find by day.
	 *
	 * @param date the date
	 * @return the system day
	 */
	SystemDay findByDay(LocalDate date);
	  
}
