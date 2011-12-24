/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dreamjteam.session;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 *
 * @author Artem
 */
public interface XMLSessionBeanLocalHome extends EJBLocalHome {
    
    ru.dreamjteam.session.XMLSessionBeanLocal create() throws CreateException;
}
