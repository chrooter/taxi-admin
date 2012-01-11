package ru.dreamjteam.entity;

import javax.ejb.EJBLocalObject;
import javax.ejb.FinderException;
import javax.naming.NamingException;
import java.sql.Timestamp;


public interface OrderEntityBeanLocal extends EJBLocalObject {
	public Integer getId();
	public void setId(Integer id);
	public Integer getCost();
	public void setCost(Integer cost);
	public Integer getDistance();
	public void setDistance(Integer distInfact);
	public Integer getPassengers();
	public void setPassengers(Integer passengers);
	public Timestamp getTimeDone();
	public void setTimeDone(Timestamp timeDone);
	public Timestamp getTimeOrd();
	public void setTimeOrd(Timestamp timeOrd);
	public String getStatus();
        public void setStatus(String status);
	public Integer getStartPoint();
	public void setStartPoint(Integer startPoint);
	public Integer getCarId();
	public void setCarId(Integer carId);
	public String getPhone();
	public void setPhone(String phone);

	OrderVO getOrderVO(Boolean withDependences, Boolean isExport) throws FinderException, NamingException;

	void setOrderVO(OrderVO value);
}

