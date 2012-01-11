package ru.dreamjteam.entity;

import javax.ejb.EJBLocalObject;
import javax.ejb.FinderException;
import javax.naming.NamingException;


public interface CarEntityBeanLocal extends EJBLocalObject {
    
        public Integer getId();
	public void setId(Integer id);
	public String getGovNumber();
	public void setGovNumber(String govNumber);
        public String getModel();
	public void setModel(String model);
	public String getColor();
	public void setColor(String color);
	CarVO getCarVO(Boolean withDependences, Boolean isExport) throws NamingException, FinderException;
	void setCarVO(CarVO value);
}
