package ru.dreamjteam.entity;

import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;


public interface CarEntityBeanLocalHome extends EJBLocalHome {
    
        CarEntityBeanLocal findByPrimaryKey(Integer key) throws FinderException;
        CarEntityBeanLocal findByLast() throws FinderException;
	Collection findAll() throws FinderException;
        Collection findSort(String field,String type) throws FinderException;
	Collection findByType(Integer carTypeId) throws FinderException;
        Collection findByCarVO(CarVO carVO) throws FinderException;
	CarEntityBeanLocal findByGovNumber(String govNumber) throws FinderException;
	CarEntityBeanLocal createCar(String govNumber, String color, String model, Integer cartypeId) throws CreateException;
}
