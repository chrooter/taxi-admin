package ru.dreamjteam.entity;

import java.io.Serializable;
import java.util.List;


public class CarVO implements Serializable {
    
        private final Integer id;
        private final String model;
	private final String govNumber;
	private final String color;
	private final Integer carTypeId;
	private CarTypeVO carTypeVO;
	private List<OrderVO> orderVOs;
	private List<OrderVO> currentOrderVOs;

	public List<OrderVO> getCurrentOrderVOs() {
		if (currentOrderVOs == null)
			throw new IllegalStateException("currentOrders == null cause lazy initialization");
		return currentOrderVOs;
	}
        
	public void setCurrentOrderVOs(List<OrderVO> currentOrderVOs) {
		this.currentOrderVOs = currentOrderVOs;
	}
        
	public List<OrderVO> getOrderVOs() {
		if (orderVOs == null)
			throw new IllegalStateException("orders == null cause lazy initialization");
		return orderVOs;
	}
        
	public void setOrderVOs(List<OrderVO> orderVOs) {
		this.orderVOs = orderVOs;
	}
        
	public CarVO(Integer id, String model, String govNumber, String color, Integer carTypeId) {
                this.id = id;
                this.model = model;
		this.govNumber = govNumber;
		this.color = color;
		this.carTypeId = carTypeId;
	}

	public String getGovNumber() {
		return govNumber;
	}

	public String getColor() {
		return color;
	}

	public Integer getId() {
		return id;
	}

	public String getModel() {
		return model;
	}

	public Integer getCarTypeId() {
		return carTypeId;
	}

	public CarTypeVO getCarTypeVO() {
		if (carTypeVO == null)
			throw new IllegalStateException("carTypeVO == null cause lazy initialization");
		return carTypeVO;
	}

	public void setCarTypeVO(CarTypeVO carTypeVO) {
		this.carTypeVO = carTypeVO;
	}

    @Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		return builder.append(model).append(" (").append(govNumber).append(")").toString();
	}
}
