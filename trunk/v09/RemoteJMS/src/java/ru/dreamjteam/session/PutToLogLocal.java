/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dreamjteam.session;
import change.Change;
import javax.ejb.EJBLocalObject;
import ru.dreamjteam.binds.*;
/**
 *
 * @author диман
 */
public interface PutToLogLocal extends EJBLocalObject {
    void Put(Change message);
    Logs getHistory(String entity);
}
