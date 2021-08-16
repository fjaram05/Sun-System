package com.galaxy.system.services;

import java.time.LocalDate;

import com.galaxy.system.data.mongodb.model.SystemDay;
import com.galaxy.system.entity.ClimateCondition;
import com.galaxy.system.exception.SystemNotFoundException;

/**
 * The Interface SystemServicesInterface.
 */
public interface SystemServicesInterface {
	
	/**
	 * Consultation day.
	 *
	 * @param diaConsulta the dia consulta
	 * @return the system day
	 * @throws SystemNotFoundException the system not found exception
	 */
	public SystemDay consultationDay(LocalDate diaConsulta) throws SystemNotFoundException;
	
	/**
	 * Consultation number day.
	 *
	 * @param day the day
	 * @return the climate condition
	 * @throws SystemNotFoundException the system not found exception
	 */
	public ClimateCondition consultationNumberDay(Integer day) throws SystemNotFoundException;
	
	/**
	 * Batch calculation days.
	 *
	 * @param fechaIni the fecha ini
	 * @param fechaFin the fecha fin
	 * @throws SystemNotFoundException the system not found exception
	 */
	public void batchCalculationDays(LocalDate fechaIni, LocalDate fechaFin) throws SystemNotFoundException;
	
	/**
	 * Period data.
	 *
	 * @return the climate condition
	 * @throws SystemNotFoundException the system not found exception
	 */
	public ClimateCondition periodData() throws SystemNotFoundException;
	
	/**
	 * Gets the optimal conditions.
	 *
	 * @return the optimal conditions
	 * @throws SystemNotFoundException the system not found exception
	 */
	public ClimateCondition getOptimalConditions() throws SystemNotFoundException;
	
	/**
	 * Gets the dry period.
	 *
	 * @return the dry period
	 * @throws SystemNotFoundException the system not found exception
	 */
	public ClimateCondition getDryPeriod() throws SystemNotFoundException;
	
	/**
	 * Gets the rain period.
	 *
	 * @return the rain period
	 * @throws SystemNotFoundException the system not found exception
	 */
	public ClimateCondition getRainPeriod() throws SystemNotFoundException;
	
	/**
	 * Gets the maximum rain day.
	 *
	 * @return the maximum rain day
	 * @throws SystemNotFoundException the system not found exception
	 */
	public ClimateCondition getMaximumRainDay() throws SystemNotFoundException;

}
