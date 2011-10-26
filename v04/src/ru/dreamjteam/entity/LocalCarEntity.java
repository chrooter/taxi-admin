package ru.dreamjteam.entity;

import javax.ejb.EJBLocalObject;
import javax.ejb.FinderException;
import javax.naming.NamingException;
import java.util.Collection;

/**
 * @author abolmasov (18.10.2011 18:23:58)
 * @version $Revision: $
 */
public interface LocalCarEntity extends EJBLocalObject {
	public Integer getId();
	public void setId(Integer id);
	public String getGovNumber();
	public void setGovNumber(String govNumber);
	public Integer getRunning();
	public void setRunning(Integer running);
	CarVO getCarVO(Boolean withDependences) throws NamingException, FinderException;
	void setCarVO(CarVO value);
}
