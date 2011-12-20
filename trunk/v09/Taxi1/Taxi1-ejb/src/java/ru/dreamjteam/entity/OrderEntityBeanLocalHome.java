package ru.dreamjteam.entity;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.util.Collection;


public interface OrderEntityBeanLocalHome extends EJBLocalHome {
	OrderEntityBeanLocal findByPrimaryKey(Integer key) throws FinderException;
	Collection findByCar(Integer carId) throws FinderException;
        Collection findByStatus(String status) throws FinderException;
	Collection findAll() throws FinderException;
        Collection findByOrderVO(OrderVO orderVO) throws FinderException;
	Collection findByCarAndStatus(Integer carId, String status) throws FinderException;
	OrderEntityBeanLocal createOrder(Integer startPoint, Integer passengers, String phone) throws CreateException;
}
