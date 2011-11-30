package ru.dreamjteam.entity;

import javax.ejb.EJBLocalObject;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;

public interface PointEntityBeanLocal extends EJBLocalObject {
    public Integer  getId();
    public void     setId(Integer id);
    public String   getAddress();
    public void     setAddress(String addr);
    public Integer  getNextId();
    public void     setNextId(Integer nextId);

    PointVO getPointVO() throws FinderException, NamingException;
    void setPointVO(PointVO value);
    void deteleChain (Integer id) throws FinderException, NamingException, RemoveException;
}
