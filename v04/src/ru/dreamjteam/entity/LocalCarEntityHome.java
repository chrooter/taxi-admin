package ru.dreamjteam.entity;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.util.Collection;
import java.util.Collections;

/**
 * @author abolmasov (18.10.2011 18:23:58)
 * @version $Revision: $
 */
public interface LocalCarEntityHome extends EJBLocalHome {
	LocalCarEntity findByPrimaryKey(Integer key) throws FinderException;
	Collection findAll() throws FinderException;
	Collection findByType(Integer carTypeId) throws FinderException;
	LocalCarEntity findByGovNumber(String govNumber) throws FinderException;
	LocalCarEntity createCar(String govNumber, Integer running, String model, Integer cartypeId) throws CreateException;
}
