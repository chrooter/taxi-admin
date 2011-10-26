package ru.dreamjteam.entity;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.util.Collection;

/**
 * @author abolmasov (18.10.2011 18:27:32)
 * @version $Revision: $
 */
public interface LocalCarTypeEntityHome extends EJBLocalHome {
	LocalCarTypeEntity findByPrimaryKey(Integer key) throws FinderException;
	LocalCarTypeEntity findByName(String name) throws FinderException;
	Collection findAll() throws FinderException;
	LocalCarTypeEntity createCarType(String name, Integer costPerKm, Integer seatCapacity) throws CreateException;
}
