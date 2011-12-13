/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 *
 * @author Artem
 */
public interface XMLSessionBeanLocalHome extends EJBLocalHome {
    
    session.XMLSessionBeanLocal create() throws CreateException;
}
