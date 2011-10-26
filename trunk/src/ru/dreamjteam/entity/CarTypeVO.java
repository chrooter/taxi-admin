/*
	Copyright 2011 Open Code Ltd.
    http://www.o-code.ru
 
    $Id: $
*/
package ru.dreamjteam.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author abolmasov (20.10.2011 11:00:22)
 * @version $Revision: $
 */
public class CarTypeVO implements Serializable {
	private final Integer id;
	private final Integer seatCapacity;
	private final Integer costPerKm;
	private final String name;
	private List<CarVO> carVOs;

	public CarTypeVO(Integer id, Integer seatCapacity, Integer costPerKm, String name) {
		this.id = id;
		this.seatCapacity = seatCapacity;
		this.costPerKm = costPerKm;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public Integer getSeatCapacity() {
		return seatCapacity;
	}

	public Integer getCostPerKm() {
		return costPerKm;
	}

	public String getName() {
		return name;
	}

	public List<CarVO> getCarVOs() {
		if (carVOs == null)
			throw new IllegalStateException("car == null cause lazy initialization");
		return carVOs;
	}

	public void setCarVOs(List<CarVO> carVOs) {
		this.carVOs = carVOs;
	}
}
