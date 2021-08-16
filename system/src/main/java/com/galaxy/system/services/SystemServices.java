package com.galaxy.system.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxy.system.data.mongodb.model.SystemDay;
import com.galaxy.system.data.mongodb.repository.SystemDayRepository;
import com.galaxy.system.entity.ClimateCondition;
import com.galaxy.system.exception.SystemNotFoundException;
import com.galaxy.system.util.SystemUtil;

/**
 * The Class SystemServices.
 * 
 * @author ajaramillo
 */
@Service("systemServices")
public class SystemServices implements SystemServicesInterface {
	
	/** The Constant logger. */
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SystemServices.class);
	
	/** The Constant ERROR_CONDITIONS. */
	private static final String ERROR_CONDITIONS = "Not found conditions";
	
	/** The system repository. */
	@Autowired
	SystemDayRepository systemRepository;
	
	/**
	 * Consultation day.
	 *
	 * @param date the date
	 * @return the system day
	 * @throws SystemNotFoundException the system not found exception
	 */
	public SystemDay consultationDay(LocalDate date) throws SystemNotFoundException {
		SystemDay systemDay = systemRepository.findByDay(date);
		if(systemDay == null) {
			logger.error("Date Error {}",date);
			throw new SystemNotFoundException("The day is not in the system");
		}
		return systemDay;
	}
	
	/**
	 * Consultation number day.
	 *
	 * @param date the date
	 * @return the system day
	 * @throws SystemNotFoundException the system not found exception
	 */
	public ClimateCondition consultationNumberDay(Integer day) throws SystemNotFoundException {
		if(day == null || day < 0) {
			logger.error("Date Error {}",day);
			throw new SystemNotFoundException("Error number day");
		}
		List<SystemDay> dayList = systemRepository.findAll();
		ClimateCondition climate = new ClimateCondition();
		if(dayList.size() < day) {
			logger.error("Day Error {}",day);
			throw new SystemNotFoundException("The day is not in the system");
		} else {
			SystemDay systemDay = dayList.get(day-1);
			climate.setDay(day);
			if(systemDay.isDryPeriod()) {
				climate.setClimate("Dry");
			} else if (systemDay.isRainPeriod()) {
				climate.setClimate("Rain");
			} else if (systemDay.isOptimalPressure()) {
				climate.setClimate("Optimal Pressure");
			} else {
				climate.setClimate("Normal Day");
			}
		}
		return climate;
	}
	
	/**
	 * Batch calculation days.
	 *
	 * @param InitialDate the initial date
	 * @param FinalDate the final date
	 * @throws SystemNotFoundException the system not found exception
	 */
	public void batchCalculationDays(LocalDate InitialDate, LocalDate FinalDate) throws SystemNotFoundException {
		SystemUtil.validationDate(InitialDate, FinalDate);
		systemRepository.deleteAll();
		Long diasDiferencia = ChronoUnit.DAYS.between(InitialDate, FinalDate);
		IntStream list= IntStream.range(0, diasDiferencia.intValue());
		double tFerengui = SystemUtil.getFullTurn(SystemUtil.SPEEDFERENGI);
		double tVulcano = SystemUtil.getFullTurn(SystemUtil.SPEEDVULCANO);
		double tBetasoide = SystemUtil.getFullTurn(SystemUtil.SPEEDBETASOIDE);
		List<SystemDay> dayList = list.parallel().mapToObj(i -> SystemUtil.calculateDay(i, InitialDate, tFerengui, tVulcano, tBetasoide)).collect(Collectors.toList());
		systemRepository.saveAll(dayList);
	}
	
	/**
	 * Period data.
	 *
	 * @return the climate condition
	 * @throws SystemNotFoundException the system not found exception
	 */
	public ClimateCondition periodData() throws SystemNotFoundException {
		ClimateCondition conditions = new ClimateCondition(0l,0l,0l,0d);
		List<SystemDay> dayList = systemRepository.findAll();
		if(dayList.isEmpty()) {
			throw new SystemNotFoundException(ERROR_CONDITIONS);
		}
		for (SystemDay systemDay : dayList) {
			conditions = SystemUtil.getConditions(systemDay, conditions);
		}
		return conditions;
	}
	
	/**
	 * Gets the optimal conditions.
	 *
	 * @return the optimal conditions
	 * @throws SystemNotFoundException the system not found exception
	 */
	public ClimateCondition getOptimalConditions() throws SystemNotFoundException {
		ClimateCondition conditions = new ClimateCondition();
		List<SystemDay> dayList = systemRepository.findAll();
		if(dayList.isEmpty()) {
			throw new SystemNotFoundException(ERROR_CONDITIONS);
		}
		conditions.setOptimalPressure(dayList.parallelStream().filter(condicion -> condicion.isOptimalPressure()).count());
		return conditions;
	}
	
	
	/**
	 * Gets the dry period.
	 *
	 * @return the dry period
	 * @throws SystemNotFoundException the system not found exception
	 */
	public ClimateCondition getDryPeriod() throws SystemNotFoundException {
		ClimateCondition conditions = new ClimateCondition();
		List<SystemDay> listaDias = systemRepository.findAll();
		if(listaDias.isEmpty()) {
			throw new SystemNotFoundException(ERROR_CONDITIONS);
		}
		conditions.setDryPeriod(listaDias.parallelStream().filter(condicion -> condicion.isDryPeriod()).count());
		return conditions;
	}
	
	/**
	 * Gets the rain period.
	 *
	 * @return the rain period
	 * @throws SystemNotFoundException the system not found exception
	 */
	public ClimateCondition getRainPeriod() throws SystemNotFoundException {
		ClimateCondition conditions = new ClimateCondition();
		List<SystemDay> listaDias = systemRepository.findAll();
		if(listaDias.isEmpty()) {
			throw new SystemNotFoundException(ERROR_CONDITIONS);
		}
		conditions.setRainPeriod(listaDias.parallelStream().filter(condicion -> condicion.isRainPeriod()).count());
		return conditions;
	}
	
	/**
	 * Gets the maximum rain day.
	 *
	 * @return the maximum rain day
	 * @throws SystemNotFoundException the system not found exception
	 */
	public ClimateCondition getMaximumRainDay() throws SystemNotFoundException {
		ClimateCondition conditions = new ClimateCondition();
		List<SystemDay> listaDias = systemRepository.findAll();
		if(listaDias.isEmpty()) {
			throw new SystemNotFoundException(ERROR_CONDITIONS);
		}
		conditions.setMaximumRainDay(listaDias.parallelStream().sorted(Comparator.comparingDouble(SystemDay::getPerimeter).reversed()).collect(Collectors.toList()).get(0).getDay());
		return conditions;
	}

}
