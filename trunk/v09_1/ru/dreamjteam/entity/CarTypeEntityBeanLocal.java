package ru.dreamjteam.entity;

import javax.ejb.EJBLocalObject;
import javax.ejb.FinderException;
import javax.naming.NamingException;


public interface CarTypeEntityBeanLocal extends EJBLocalObject {
	public Integer getId();
	public void setId(Integer id);
	public String getName();
	public void setName(String name);
	public Integer getCapacity();
	public void setCapacity(Integer capacity);
	public Integer getCostPerKm();
	public void setCostPerKm(Integer cost);
	CarTypeVO getCarTypeVO(Boolean withDependences, Boolean isExport) throws NamingException, FinderException;
	void setCarTypeVO(CarTypeVO value);
}
