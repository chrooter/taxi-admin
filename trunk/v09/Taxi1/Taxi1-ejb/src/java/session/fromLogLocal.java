/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import binds.Logs;
import javax.ejb.EJBLocalObject;
import change.Change;

/**
 *
 * @author диман
 */
public interface fromLogLocal extends EJBLocalObject {
    Logs getHistory(String entity);
}
