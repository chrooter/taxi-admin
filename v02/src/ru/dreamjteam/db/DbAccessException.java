/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tdb;

import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Сергей
 */
class DbAccessException extends Exception {

    public DbAccessException(SQLException ex) {
    }

    public DbAccessException(NamingException ex) {
        
    }
    
}
