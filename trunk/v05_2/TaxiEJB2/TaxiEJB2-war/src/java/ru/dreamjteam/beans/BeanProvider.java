package ru.dreamjteam.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import ru.dreamjteam.entity.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

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

}