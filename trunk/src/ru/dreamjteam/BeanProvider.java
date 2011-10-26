package ru.dreamjteam;

import ru.dreamjteam.entity.LocalCarEntityHome;
import ru.dreamjteam.entity.LocalCarTypeEntityHome;
import ru.dreamjteam.entity.LocalOrderEntityHome;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

public class BeanProvider {
	public static LocalCarEntityHome getCarHome() throws NamingException {
		InitialContext ic = new InitialContext();
        return (LocalCarEntityHome) ic.lookup("ejb/LocalCarEntityEJB");
	}

	public static LocalCarTypeEntityHome getCarTypeHome() throws NamingException {
		InitialContext ic = new InitialContext();
		return (LocalCarTypeEntityHome) ic.lookup("ejb/LocalCarTypeEntityEJB");
	}

	public static LocalOrderEntityHome getOrderHome() throws NamingException {
		InitialContext ic = new InitialContext();
		return (LocalOrderEntityHome) ic.lookup("ejb/LocalOrderEntityEJB");
	}
}