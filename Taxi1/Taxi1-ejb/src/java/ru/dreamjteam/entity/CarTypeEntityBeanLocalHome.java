package ru.dreamjteam.entity;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.util.Collection;


public interface CarTypeEntityBeanLocalHome extends EJBLocalHome {
	CarTypeEntityBeanLocal findByPrimaryKey(Integer key) throws FinderException;
	CarTypeEntityBeanLocal findByName(String name) throws FinderException;
	Collection findAll() throws FinderException;
        Collection findByCarType(CarTypeVO carTypeVO) throws FinderException;
	CarTypeEntityBeanLocal createCarType(String name, Integer costPerKm, Integer capacity) throws CreateException;
}
