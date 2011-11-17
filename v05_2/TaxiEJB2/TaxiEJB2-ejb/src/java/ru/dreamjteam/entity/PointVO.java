package ru.dreamjteam.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: РЎРµСЂРіРµР№
 * Date: 16.11.11
 * Time: 0:07
 * To change this template use File | Settings | File Templates.
 */
public class PointVO implements Serializable{
    private final Integer id;
    private final String address;
    private final Integer nextId;
    private List<PointVO> pointVOs;

	public PointVO(Integer id, String address, Integer nextId) {
		this.id = id;
		this.address = address;
        this.nextId = nextId;
	}

	public Integer getId() {
		return id;
	}

	public String getAddress() {
		return address;
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
