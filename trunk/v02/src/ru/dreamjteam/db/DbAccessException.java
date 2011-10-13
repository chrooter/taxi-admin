package ru.dreamjteam.db;

import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Senya
 */
class DbAccessException extends Exception {

    public DbAccessException(SQLException ex) {
    }

    public DbAccessException(NamingException ex) {
        
    }
    
}
