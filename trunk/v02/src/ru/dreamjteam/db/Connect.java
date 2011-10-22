/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dreamjteam.db;
import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.SQLException;
import javax.naming.NamingException;
/**
 *
 * @author диман
 */
public class Connect {
    private static InitialContext ctx = null;
    private static DataSource ds = null;

    public static Connection GetConnect() throws DbAccessException,SQLException,NamingException{
        try {
            if (ctx == null) {
                ctx = new InitialContext();
                ds = (DataSource)ctx.lookup("taxi");
            }
            return ds.getConnection();
        } catch (NamingException ex) {
            throw new DbAccessException(ex);
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            throw new DbAccessException(ex);
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
