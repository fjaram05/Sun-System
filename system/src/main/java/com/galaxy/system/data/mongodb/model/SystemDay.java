package com.galaxy.system.data.mongodb.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Class System Day.
 */
@Document(collection = "systemDay")
public class SystemDay {
	

	/** The id. */
	@Id
	private String id;
	
	/** The feringi. */
	private Planet feringi;
	
	/** The vulcano. */
	private Planet vulcano;
	
	/** The betasoide. */
	private Planet betasoide;
	
	/** The perimeter. */
	private double perimeter = 0;
	
	/** The day. */
	private LocalDate day;
	
	/** The line. */
	private boolean line = false;
	
	/** The rain period. */
	private boolean rainPeriod = false;
	
	/** The inner sun. */
	private boolean innerSun = false;
	
	/** The dry period. */
	private boolean dryPeriod = false;
	
	/** The optimal pressure. */
	private boolean optimalPressure = false;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the feringi.
	 *
	 * @return the feringi
	 */
	public Planet getFeringi() {
		return feringi;
	}

	/**
	 * Sets the feringi.
	 *
	 * @param feringi the feringi to set
	 */
	public void setFeringi(Planet feringi) {
		this.feringi = feringi;
	}

	/**
	 * Gets the vulcano.
	 *
	 * @return the vulcano
	 */
	public Planet getVulcano() {
		return vulcano;
	}

	/**
	 * Sets the vulcano.
	 *
	 * @param vulcano the vulcano to set
	 */
	public void setVulcano(Planet vulcano) {
		this.vulcano = vulcano;
	}

	/**
	 * Gets the betasoide.
	 *
	 * @return the betasoide
	 */
	public Planet getBetasoide() {
		return betasoide;
	}

	/**
	 * Sets the betasoide.
	 *
	 * @param betasoide the betasoide to set
	 */
	public void setBetasoide(Planet betasoide) {
		this.betasoide = betasoide;
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
	 * Gets the day.
	 *
	 * @return the day
	 */
	public LocalDate getDay() {
		return day;
	}

	/**
	 * Sets the day.
	 *
	 * @param day the day to set
	 */
	public void setDay(LocalDate day) {
		this.day = day;
	}

	/**
	 * Checks if is line.
	 *
	 * @return the line
	 */
	public boolean isLine() {
		return line;
	}

	/**
	 * Sets the line.
	 *
	 * @param line the line to set
	 */
	public void setLine(boolean line) {
		this.line = line;
	}

	/**
	 * Checks if is rain period.
	 *
	 * @return the rainPeriod
	 */
	public boolean isRainPeriod() {
		return rainPeriod;
	}

	/**
	 * Sets the rain period.
	 *
	 * @param rainPeriod the rainPeriod to set
	 */
	public void setRainPeriod(boolean rainPeriod) {
		this.rainPeriod = rainPeriod;
	}

	/**
	 * Checks if is inner sun.
	 *
	 * @return the innerSun
	 */
	public boolean isInnerSun() {
		return innerSun;
	}

	/**
	 * Sets the inner sun.
	 *
	 * @param innerSun the innerSun to set
	 */
	public void setInnerSun(boolean innerSun) {
		this.innerSun = innerSun;
	}

	/**
	 * Checks if is dry period.
	 *
	 * @return the dryPeriod
	 */
	public boolean isDryPeriod() {
		return dryPeriod;
	}

	/**
	 * Sets the dry period.
	 *
	 * @param dryPeriod the dryPeriod to set
	 */
	public void setDryPeriod(boolean dryPeriod) {
		this.dryPeriod = dryPeriod;
	}

	/**
	 * Checks if is optimal pressure.
	 *
	 * @return the optimalPressure
	 */
	public boolean isOptimalPressure() {
		return optimalPressure;
	}

	/**
	 * Sets the optimal pressure.
	 *
	 * @param optimalPressure the optimalPressure to set
	 */
	public void setOptimalPressure(boolean optimalPressure) {
		this.optimalPressure = optimalPressure;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "SystemDay [id=" + id + ", feringi=" + feringi + ", vulcano=" + vulcano + ", betasoide=" + betasoide
				+ ", perimeter=" + perimeter + ", day=" + day + ", line=" + line + ", rainPeriod=" + rainPeriod
				+ ", innerSun=" + innerSun + ", dryPeriod=" + dryPeriod + ", optimalPressure=" + optimalPressure + "]";
	}

	

}
