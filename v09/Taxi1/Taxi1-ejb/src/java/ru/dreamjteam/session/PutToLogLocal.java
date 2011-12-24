/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dreamjteam.session;

import ru.dreamjteam.binds.Logs;
import javax.ejb.EJBLocalObject;
import change.Change;

/**
 *
 * @author диман
 */
public interface PutToLogLocal extends EJBLocalObject {
    void Put(Change message);
    Logs getHistory(String entity);
}
