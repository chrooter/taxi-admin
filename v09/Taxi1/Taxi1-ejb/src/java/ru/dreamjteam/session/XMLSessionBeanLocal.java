/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dreamjteam.session;

import javax.ejb.EJBLocalObject;

/**
 *
 * @author Artem
 */
public interface XMLSessionBeanLocal extends EJBLocalObject {
    String toXML(Object ot);
    <T> T parseXML(final String xml, Class<T> classOfT);
    
}
