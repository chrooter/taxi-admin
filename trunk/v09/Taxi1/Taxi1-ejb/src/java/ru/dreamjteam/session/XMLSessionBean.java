/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dreamjteam.session;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.io.*;
import javax.xml.bind.*;

/**
 *
 * @author Artem
 */
public class XMLSessionBean implements SessionBean {
    
    private SessionContext context;

    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">;
    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise bean, Web services)
    // TODO Add business methods or web service operations
    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(SessionContext aContext) {
        context = aContext;
    }

    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
        
    }

    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
        
    }

    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
        
    }

    // </editor-fold>;
    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
    public void ejbCreate() {
        // TODO implement ejbCreate if necessary, acquire resources
        // This method has access to the JNDI context so resource aquisition
        // spanning all methods can be performed here such as home interfaces
        // and data sources.
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
        public static String toXML(Object ot) {
		try {
			JAXBContext jaxbCtx = JAXBContext.newInstance(ot.getClass().getPackage().getName());
			Marshaller marshaller = jaxbCtx.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter result = new StringWriter();
			marshaller.marshal(ot, result);
			return result.toString();
		}
		catch (JAXBException ex) {
			ex.printStackTrace();
		}
		return "";
	}
    
    public static <T> T parseXML(final String xml, Class<T> classOfT) {
		try {
			final String packageName = classOfT.getPackage().getName();
			JAXBContext jaxbCtx = JAXBContext.newInstance(packageName);
			Unmarshaller um = jaxbCtx.createUnmarshaller();
			StringReader reader = new StringReader(xml);
			JAXBElement<T> ot = (JAXBElement<T>) um.unmarshal(reader);
			return ot.getValue();
		}
		catch (JAXBException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
