/**
 * 
 */
package com.galaxy.system.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class ClimateCondition.
 *
 * @author ajaramillo
 */
@JsonIgnoreProperties({"perimeter"})
@JsonInclude(Include.NON_NULL)
public class ClimateCondition {
	
	/** The dry period. */
	private Long dryPeriod;
	
	/** The rain period. */
	private Long rainPeriod;
	
	/** The optimal pressure. */
	private Long optimalPressure;
	
	/** The maximum rain day. */
	private LocalDate maximumRainDay;
	
	/** The climate. */
	private String climate;
	
	/** The day. */
	private Integer day;
	
	/** The perimeter. */
	private double perimeter;

	/**
	 * Gets the dry period.
	 *
	 * @return the dryPeriod
	 */
	public Long getDryPeriod() {
		return dryPeriod;
	}

	/**
	 * Sets the dry period.
	 *
	 * @param dryPeriod the dryPeriod to set
	 */
	public void setDryPeriod(Long dryPeriod) {
		this.dryPeriod = dryPeriod;
	}

	/**
	 * Gets the rain period.
	 *
	 * @return the rainPeriod
	 */
	public Long getRainPeriod() {
		return rainPeriod;
	}

	/**
	 * Sets the rain period.
	 *
	 * @param rainPeriod the rainPeriod to set
	 */
	public void setRainPeriod(Long rainPeriod) {
		this.rainPeriod = rainPeriod;
	}

	/**
	 * Gets the optimal pressure.
	 *
	 * @return the optimalPressure
	 */
	public Long getOptimalPressure() {
		return optimalPressure;
	}

	/**
	 * Sets the optimal pressure.
	 *
	 * @param optimalPressure the optimalPressure to set
	 */
	public void setOptimalPressure(Long optimalPressure) {
		this.optimalPressure = optimalPressure;
	}

	/**
	 * Gets the maximum rain day.
	 *
	 * @return the maximumRainDay
	 */
	public LocalDate getMaximumRainDay() {
		return maximumRainDay;
	}

	/**
	 * Sets the maximum rain day.
	 *
	 * @param maximumRainDay the maximumRainDay to set
	 */
	public void setMaximumRainDay(LocalDate maximumRainDay) {
		this.maximumRainDay = maximumRainDay;
	}

	/**
	 * Gets the perimeter.
	 *
	 * @return the perimeter
	 */
	public double getPerimeter() {
		return perimeter;
	}

	/**
	 * Sets the perimeter.
	 *
	 * @param perimeter the perimeter to set
	 */
	public void setPerimeter(double perimeter) {
		this.perimeter = perimeter;
	}

	/**
	 * Instantiates a new climate condition.
	 *
	 * @param dryPeriod the dry period
	 * @param rainPeriod the rain period
	 * @param optimalPressure the optimal pressure
	 * @param perimeter the perimeter
	 */
	public ClimateCondition(Long dryPeriod, Long rainPeriod, Long optimalPressure, double perimeter) {
		super();
		this.dryPeriod = dryPeriod;
		this.rainPeriod = rainPeriod;
		this.optimalPressure = optimalPressure;
		this.perimeter = perimeter;
	}
	
	/**
	 * Instantiates a new climate condition.
	 */
	public ClimateCondition() {
		
	}

	/**
	 * Gets the climate.
	 *
	 * @return the climate
	 */
	public String getClimate() {
		return climate;
	}

	/**
	 * Sets the climate.
	 *
	 * @param climate the climate to set
	 */
	public void setClimate(String climate) {
		this.climate = climate;
	}

	/**
	 * Gets the day.
	 *
	 * @return the day
	 */
	public Integer getDay() {
		return day;
	}

	/**
	 * Sets the day.
	 *
	 * @param day the day to set
	 */
	public void setDay(Integer day) {
		this.day = day;
	}
	
	

}
