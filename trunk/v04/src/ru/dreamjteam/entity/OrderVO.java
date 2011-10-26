/*
	Copyright 2011 Open Code Ltd.
    http://www.o-code.ru
 
    $Id: $
*/
package ru.dreamjteam.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author abolmasov (20.10.2011 11:49:19)
 * @version $Revision: $
 */
public class OrderVO implements Serializable {
	private final Integer id;
	private final Integer cost;
	private final String phone;
	private final Integer distance;
	private final Integer passengers;
	private final Timestamp timeDest;
	private final Timestamp timeOrd;
	private final String addrDep;
	private final String addrDest;
	private final Integer carId;
	private Boolean completed;
	private CarVO carVO;

	public OrderVO(Integer id, Integer cost, String phone, Integer distance, Integer passengers, Timestamp timeDest, Timestamp timeOrd, String addrDep, String addrDest, Integer carId, Boolean completed) {
		this.id = id;
		this.cost = cost;
		this.phone = phone;
		this.distance = distance;
		this.passengers = passengers;
		this.timeDest = timeDest;
		this.timeOrd = timeOrd;
		this.addrDep = addrDep;
		this.addrDest = addrDest;
		this.carId = carId;
		this.completed = completed;
	}

	public Integer getId() {
		return id;
	}

	public Integer getCost() {
		return cost;
	}

	public String getPhone() {
		return phone;
	}

	public Integer getDistance() {
		return distance;
	}

	public Integer getPassengers() {
		return passengers;
	}

	public Timestamp getTimeDest() {
		return timeDest;
	}

	public Timestamp getTimeOrd() {
		return timeOrd;
	}

	public String getAddrDep() {
		return addrDep;
	}

	public String getAddrDest() {
		return addrDest;
	}

	public Integer getCarId() {
		return carId;
	}

	public Boolean getCompleted() {
		return completed;
	}

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public CarVO getCar() {
		if (carVO == null)
			throw new IllegalStateException("carVO == null cause lazy initialization");
		return carVO;
	}

	public void setCar(CarVO carVO) {
		this.carVO = carVO;
	}
}
