/**
 * 
 */
package com.galaxy.system.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.galaxy.system.data.mongodb.model.SystemDay;
import com.galaxy.system.data.mongodb.model.Planet;
import com.galaxy.system.entity.ClimateCondition;
import com.galaxy.system.exception.SystemNotFoundException;

/**
 * The Class SystemUtil.
 *
 * @author ajaramillo
 */
public class SystemUtil {
	
	/** The Constant SPEEDFERENGI. */
	public static final int SPEEDFERENGI = 1;
	
	/** The Constant SPEEDVULCANO. */
	public static final int SPEEDVULCANO = -5;
	
	/** The Constant SPEEDBETASOIDE. */
	public static final int SPEEDBETASOIDE = 3;
	
	/** The Constant DISTANCEFERENGI. */
	public static final int DISTANCEFERENGI = 500;
	
	/** The Constant DISTANCEVULCANO. */
	public static final int DISTANCEVULCANO = 1000;
	
	/** The Constant DISTANCEBETASOIDE. */
	public static final int DISTANCEBETASOIDE = 2000;
	
	/**
	 * Instantiates a new system util.
	 */
	private SystemUtil() {
	    throw new IllegalStateException("Utility class");
	  }
	
	/**
	 * Gets the conditions.
	 *
	 * @param systemDay the system day
	 * @param climateCondition the climate condition
	 * @return the conditions
	 */
	public static ClimateCondition getConditions(SystemDay systemDay, ClimateCondition climateCondition) {
		if (systemDay.isOptimalPressure()) {
			climateCondition.setOptimalPressure(climateCondition.getOptimalPressure()+1);
		}
		if (systemDay.isRainPeriod()) {
			climateCondition.setRainPeriod(climateCondition.getRainPeriod()+1);
			if (systemDay.getPerimeter() > climateCondition.getPerimeter()) {
				climateCondition.setPerimeter(systemDay.getPerimeter());
				climateCondition.setMaximumRainDay(systemDay.getDay());
			}
		}
		if (systemDay.isLine()) {
			climateCondition.setDryPeriod(climateCondition.getDryPeriod()+1);
		}
		return climateCondition;
	}

	/**
	 * Validation date.
	 *
	 * @param InitialDate the initial date
	 * @param FinalDate the final date
	 * @throws SystemNotFoundException the system not found exception
	 */
	public static void validationDate(LocalDate InitialDate, LocalDate FinalDate) throws SystemNotFoundException {
		if(ChronoUnit.YEARS.between(InitialDate, FinalDate) > 20) {
			throw new SystemNotFoundException("Batch for calculating dates greater than 10 years");
		}
		if (InitialDate.compareTo(FinalDate) >= 0) {
			throw new SystemNotFoundException("Initial date greater or equals than the Final");
		}
	}
	
	
	/**
	 * Calculate day.
	 *
	 * @param day the day
	 * @param initialDay the initial day
	 * @param tFerengui the t ferengui
	 * @param tVulcano the t vulcano
	 * @param tBetasoide the t betasoide
	 * @return the system day
	 */
	public static SystemDay calculateDay(int day, LocalDate initialDay, double tFerengui, double tVulcano, double tBetasoide) {
		SystemDay systemDay = new SystemDay();
		systemDay.setDay(initialDay.plusDays(day));
		double radianesF = getRadians(getAngle(SPEEDFERENGI, day%tFerengui));
		double radianesV = getRadians(getAngle(SPEEDVULCANO, day%tVulcano));
		double radianesB = getRadians(getAngle(SPEEDBETASOIDE, day%tBetasoide));
		systemDay.setFeringi(new Planet(getPositionX(radianesF, DISTANCEFERENGI), getPositionY(radianesF, DISTANCEFERENGI)));
		systemDay.setVulcano(new Planet(getPositionX(radianesV, DISTANCEVULCANO), getPositionY(radianesV, DISTANCEVULCANO)));
		systemDay.setBetasoide(new Planet(getPositionX(radianesB, DISTANCEBETASOIDE), getPositionY(radianesB, DISTANCEBETASOIDE)));
		systemDay.setLine(isLine(systemDay.getFeringi().getPosy(), systemDay.getVulcano().getPosy(), systemDay.getBetasoide().getPosy(), systemDay.getFeringi().getPosx(), systemDay.getVulcano().getPosx(), systemDay.getBetasoide().getPosx()));
		if(systemDay.isLine()) {
			systemDay.setDryPeriod(isLine(0, systemDay.getVulcano().getPosy(), systemDay.getBetasoide().getPosy(), 0, systemDay.getVulcano().getPosx(),  systemDay.getBetasoide().getPosx()));
			if(!systemDay.isDryPeriod()) {
				systemDay.setOptimalPressure(true);
			}
		} else {
			systemDay.setRainPeriod(true);
			double distancia = getDistance(systemDay.getFeringi().getPosy(), systemDay.getVulcano().getPosy(), systemDay.getFeringi().getPosx(), systemDay.getVulcano().getPosx());
			distancia += getDistance(systemDay.getBetasoide().getPosy(), systemDay.getVulcano().getPosy(),  systemDay.getBetasoide().getPosx(), systemDay.getVulcano().getPosx());
			distancia += getDistance(systemDay.getFeringi().getPosy(), systemDay.getBetasoide().getPosy(), systemDay.getFeringi().getPosx(),  systemDay.getBetasoide().getPosx());
			systemDay.setPerimeter(distancia);
			systemDay.setInnerSun(isInside(0, systemDay.getFeringi().getPosx(), systemDay.getVulcano().getPosx(),  systemDay.getBetasoide().getPosx(), 0, systemDay.getFeringi().getPosy(), systemDay.getVulcano().getPosy(), systemDay.getBetasoide().getPosy()));
		}
		
		return systemDay;
	}
	
	/**
	 * Gets the full turn.
	 *
	 * @param rate the rate
	 * @return the full turn
	 */
	public static double getFullTurn(double rate) {
		return Math.abs(2/(rate/180));
	}

	/**
	 * Gets the radians.
	 *
	 * @param radius the radius
	 * @return the radians
	 */
	public static double getRadians(double radius) {
		return radius * Math.PI / 180;
	}

	/**
	 * Gets the position X.
	 *
	 * @param angle the angle
	 * @param radius the radius
	 * @return the position X
	 */
	public static double getPositionX(double angle, int radius) {
		return radius * Math.cos(angle);
	}

	/**
	 * Gets the position Y.
	 *
	 * @param angle the angle
	 * @param radius the radius
	 * @return the position Y
	 */
	public static double getPositionY(double angle, int radius) {
		return radius * Math.sin(angle);
	}

	/**
	 * Gets the angle.
	 *
	 * @param velocity the velocity
	 * @param time the time
	 * @return the angle
	 */
	public static double getAngle(double velocity, double time) {
		return velocity * time;
	}

	/**
	 * Gets the slope.
	 *
	 * @param y1 the y 1
	 * @param y2 the y 2
	 * @param x1 the x 1
	 * @param x2 the x 2
	 * @return the slope
	 */
	public static double getSlope(double y1, double y2, double x1, double x2) {
		return (y2 - y1) / (x2 - x1);
	}

	/**
	 * Checks if is line.
	 *
	 * @param y the y
	 * @param y1 the y 1
	 * @param y2 the y 2
	 * @param x the x
	 * @param x1 the x 1
	 * @param x2 the x 2
	 * @return true, if is line
	 */
	public static boolean isLine(double y, double y1, double y2, double x, double x1, double x2) {
		boolean isLinear = false;
		double puntoY = getSlope(y1, y2, x1, x2) * (x - x1) + y1;
		isLinear = (y == puntoY);
		return isLinear;
	}

	/**
	 * Gets the distance.
	 *
	 * @param y1 the y 1
	 * @param y2 the y 2
	 * @param x1 the x 1
	 * @param x2 the x 2
	 * @return the distance
	 */
	public static double getDistance(double y1, double y2, double x1, double x2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

	
	/**
	 * Checks if is inside.
	 *
	 * @param x the x
	 * @param x1 the x 1
	 * @param x2 the x 2
	 * @param x3 the x 3
	 * @param y the y
	 * @param y1 the y 1
	 * @param y2 the y 2
	 * @param y3 the y 3
	 * @return true, if is inside
	 */
	public static boolean isInside(double x, double x1, double x2, double x3, double y, double y1, double y2, double y3) {
		double dx = x2 - x1;
		double dy = y2 - y1;
		
		if (y == y3) {
			y3 += 0.0001d;
		}
		
		double ex = x3 - x1;
		double ey = y3 - y1;
		
		double w1 = (ex * (y1 - y) + ey * ( x - x1)) / (dx * ey - dy * ex); 
		double w2 = (y - y1 - w1 * dy)/ ey;
		
		return ( w1 >= 0 && w2 >= 0 && (w1 + w2) <= 1);
	}

}
