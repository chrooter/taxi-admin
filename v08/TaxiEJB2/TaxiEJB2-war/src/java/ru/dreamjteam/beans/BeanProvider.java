package ru.dreamjteam.beans;

import javax.ejb.CreateException;
import javax.naming.Context;
import ru.dreamjteam.entity.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.XMLSessionBeanLocal;
import session.XMLSessionBeanLocalHome;

public class BeanProvider {
    
	public static CarEntityBeanLocalHome getCarHome() throws NamingException {
		InitialContext ic = new InitialContext();
        return (CarEntityBeanLocalHome) ic.lookup("java:comp/env/CarEntityBean");
	}

	public static CarTypeEntityBeanLocalHome getCarTypeHome() throws NamingException {
		InitialContext ic = new InitialContext();
		return (CarTypeEntityBeanLocalHome) ic.lookup("java:comp/env/CarTypeEntityBean");
	}

	public static OrderEntityBeanLocalHome getOrderHome() throws NamingException {
		InitialContext ic = new InitialContext();
		return (OrderEntityBeanLocalHome) ic.lookup("java:comp/env/OrderEntityBean");
	}
        
        public static PointEntityBeanLocalHome getPointHome() throws NamingException {
		InitialContext ic = new InitialContext();
		return (PointEntityBeanLocalHome) ic.lookup("java:comp/env/PointEntityBean");
	}

        public static XMLSessionBeanLocal getXMLHome() throws NamingException, CreateException {
            Context ic = new InitialContext();
            XMLSessionBeanLocalHome rv = (XMLSessionBeanLocalHome) ic.lookup("java:comp/env/XMLSessionBean");
            return rv.create();
        }

}