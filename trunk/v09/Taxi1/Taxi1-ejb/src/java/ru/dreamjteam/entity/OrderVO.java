package ru.dreamjteam.entity;

import java.io.Serializable;
import java.sql.Timestamp;


public class OrderVO implements Serializable {
	private final Integer id;
	private final Integer cost;
	private final String phone;
	private final Integer distance;
	private final Integer passengers;
	private final Timestamp timeDone;
	private final Timestamp timeOrd;
	private final Integer startPoint;
	private final Integer carId;
	private String status;
	private CarVO carVO;
        private PointVO pointVO;

	public OrderVO(Integer id, Integer cost, String phone, Integer distance, Integer passengers, Timestamp timeDone, Timestamp timeOrd, Integer startPoint, Integer carId, String status) {
		this.id = id;
		this.cost = cost;
		this.phone = phone;
		this.distance = distance;
		this.passengers = passengers;
		this.timeDone = timeDone;
		this.timeOrd = timeOrd;
		this.startPoint = startPoint;
		this.carId = carId;
		this.status = status;
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

	public Timestamp getTimeDone() {
		return timeDone;
	}

	public Timestamp getTimeOrd() {
		return timeOrd;
	}

	public Integer getStartPoint() {
		return startPoint;
	}

	public Integer getCarId() {
		return carId;
	}

	public String getStatus() {
		return status;
	}

        public void setStatus(String status) {
            this.status = status;
        }

        public CarVO getCar() {
		return carVO;
	}

	public void setCar(CarVO carVO) {
		this.carVO = carVO;
	}
        
        public PointVO getPoint() {
		return pointVO;
	}
        
        public void setPoint(PointVO pointVO) {
		this.pointVO = pointVO;
	}
}
