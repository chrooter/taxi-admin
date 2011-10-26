package ru.dreamjteam.entity;

import javax.ejb.EJBLocalObject;
import javax.ejb.FinderException;
import javax.naming.NamingException;

/**
 * @author abolmasov (18.10.2011 18:27:32)
 * @version $Revision: $
 */
public interface LocalCarTypeEntity extends EJBLocalObject {
	public Integer getId();
	public void setId(Integer id);
	public String getName();
	public void setName(String name);
	public Integer getSeatCapacity();
	public void setSeatCapacity(Integer capacity);
	public Integer getCostPerKm();
	public void setCostPerKm(Integer cost);
	CarTypeVO getCarTypeVO(Boolean withDependences) throws NamingException, FinderException;
	void setCarTypeVO(CarTypeVO value);
}
