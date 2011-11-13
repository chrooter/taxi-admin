package ru.dreamjteam.entity;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.util.Collection;


public interface OrderEntityBeanLocalHome extends EJBLocalHome {
	OrderEntityBeanLocal findByPrimaryKey(Integer key) throws FinderException;
	Collection findByCar(Integer carId) throws FinderException;
	Collection findAll() throws FinderException;
	Collection findByCarAndCompleted(Integer carId, String status) throws FinderException;
	OrderEntityBeanLocal createOrder(Integer startPoint, Integer passengers, String phone) throws CreateException;
}
