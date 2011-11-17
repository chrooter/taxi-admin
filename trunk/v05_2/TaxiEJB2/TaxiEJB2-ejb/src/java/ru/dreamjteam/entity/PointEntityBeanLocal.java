package ru.dreamjteam.entity;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalObject;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;

/**
 * Created by IntelliJ IDEA.
 * User: РЎРµСЂРіРµР№
 * Date: 12.11.11
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public interface PointEntityBeanLocal extends EJBLocalObject {
    public Integer  getId();
    public void     setId(Integer id);
    public String   getAddr();
    public void     setAddr(String addr);
    public Integer  getNextId();
    public void     setNextId(Integer nextId);

    PointVO getPointVO() throws FinderException, NamingException;
    void createChain (PointVO chain) throws CreateException;
    void deteleChain (Integer id) throws FinderException, NamingException, RemoveException;
    PointVO getChain (Integer id) throws FinderException, NamingException;
}
