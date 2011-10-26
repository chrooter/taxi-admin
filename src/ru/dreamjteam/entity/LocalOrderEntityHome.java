package ru.dreamjteam.entity;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.util.Collection;

/**
 * @author abolmasov (18.10.2011 18:27:09)
 * @version $Revision: $
 */
public interface LocalOrderEntityHome extends EJBLocalHome {
	LocalOrderEntity findByPrimaryKey(Integer key) throws FinderException;
	Collection findByCar(Integer carId) throws FinderException;
	Collection findAll() throws FinderException;
	Collection findByCarAndCompleted(Integer carId, Boolean completed) throws FinderException;
	LocalOrderEntity createOrder(String addrDep, String addrDest, Integer distAppr, Integer passenger, String phone, Integer carId) throws CreateException;
}
