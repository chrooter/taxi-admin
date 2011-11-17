package ru.dreamjteam.entity;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.util.Collection;
import javax.ejb.RemoveException;

/**
 * Created by IntelliJ IDEA.
 * User: РЎРµСЂРіРµР№
 * Date: 12.11.11
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public interface PointEntityBeanLocalHome extends EJBLocalHome {
    PointEntityBeanLocal findByPrimaryKey(Integer key) throws FinderException;
    Collection findChain(Integer id) throws FinderException;
    PointEntityBeanLocal createPoint (String addr, Integer nextId) throws CreateException;
}
