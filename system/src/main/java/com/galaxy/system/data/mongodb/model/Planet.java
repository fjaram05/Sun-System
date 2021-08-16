package com.galaxy.system.data.mongodb.model;

/**
 * The Class Planet.
 */
public class Planet {
	
	/** The pos x. */
	private double posx = 0d;
	
	/** The pos y. */
	private double posy = 0d;

	/**
	 * Gets the posx.
	 *
	 * @return the posx
	 */
	public double getPosx() {
		return posx;
	}

	/**
	 * Sets the posx.
	 *
	 * @param posx the new posx
	 */
	public void setPosx(double posx) {
		this.posx = posx;
	}

	/**
	 * Gets the posy.
	 *
	 * @return the posy
	 */
	public double getPosy() {
		return posy;
	}

	/**
	 * Sets the posy.
	 *
	 * @param posy the new posy
	 */
	public void setPosy(double posy) {
		this.posy = posy;
	}

	/**
	 * Instantiates a new planeta.
	 *
	 * @param posx the posx
	 * @param posy the posy
	 */
	public Planet(double posx, double posy) {
		super();
		this.posx = posx;
		this.posy = posy;
	}

	@Override
	public String toString() {
		return "Planeta [posx=" + posx + ", posy=" + posy + "]";
	}

}
