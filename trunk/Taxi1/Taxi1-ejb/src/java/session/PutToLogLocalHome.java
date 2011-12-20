/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 *
 * @author диман
 */
public interface PutToLogLocalHome extends EJBLocalHome {
    
    session.PutToLogLocal create()  throws CreateException;

}
