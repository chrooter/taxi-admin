package ru.dreamjteam.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PointVO implements Serializable{
    private final Integer id;
    private final String address;
    private final Integer nextId;
    private List<PointVO> pointVOs;

	public PointVO(Integer id, String address, Integer nextId) {
		this.id = id;
		this.address = address;
                this.nextId = nextId;
                pointVOs = new ArrayList<PointVO>();
	}

	public Integer getId() {
		return id;
	}
        
	public String getAddress() {
		return address;
	}
        
        public Integer getNextId() {
		return nextId;
	}

    public List<PointVO> getPointVOs() {
		if (pointVOs == null)
			throw new IllegalStateException("points == null cause lazy initialization");
		return pointVOs;
	}
	public void setPointVOs(List<PointVO> pointVOs) {
		this.pointVOs = pointVOs;
	}

}
