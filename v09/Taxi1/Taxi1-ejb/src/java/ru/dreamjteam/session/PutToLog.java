/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dreamjteam.session;

import ru.dreamjteam.binds.Log;
import ru.dreamjteam.binds.Logs;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import change.Change;
/**
 *
 * @author диман
 */
public class PutToLog implements SessionBean {
    
    private SessionContext context;
    private static DataSource dataSource;
    private static Connection conn;
    
    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">;

    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise bean, Web services)
    // TODO Add business methods or web service operations

    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(SessionContext aContext) {
        context = aContext;
        try {
			final InitialContext ic = new InitialContext();
			dataSource = getTaxi();
		} catch (Exception e) {
			throw new EJBException(e);
		}
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
        
    }
    
    // </editor-fold>;

    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
    public void ejbCreate() {
        // TODO implement ejbCreate if necessary, acquire resources
        // This method has access to the JNDI context so resource aquisition
        // spanning all methods can be performed here such as home interfaces
        // and data sources.
    }
    
    public void Put(Change message) {
        PreparedStatement st = null;
        try {
			oConnection();
			st = conn.prepareStatement("INSERT INTO LOGS (ID, ENTITY, DATEOFCHANGE, PRIORITY, CHANGE)" +
					" VALUES (logs_seq.nextval, ?, ?, ?, ?)");
			st.setString(1, message.getEntity());
			st.setTimestamp(2, message.getDateOfChange());
			st.setInt(3, message.getPriority());
			st.setString(4, message.getChange());
			if (st.executeUpdate() != 1)
				throw new CreateException();
		} catch (SQLException e) {
			throw new EJBException(e);
		} catch (CreateException e) {
                        throw new EJBException(e);
                } finally {
			cConnection(st);
		}
    }
    public static Logs getHistory(String entity) {
        PreparedStatement st = null;
        Logs logs = null;
		try {
			oConnection();
                        logs = new Logs();
			st = conn.prepareStatement("SELECT * FROM LOGS WHERE ENTITY = ?");
			st.setString(1, entity);
			ResultSet rs = st.executeQuery();
			while (rs.next())
				logs.setLog(new Log(rs.getInt(1),rs.getString(2),rs.getTimestamp(3).toString(),rs.getInt(4),rs.getString(5)));
			return logs;
		} catch (SQLException e) {
			throw new EJBException(e);
		} catch (Exception e) {
                        throw new EJBException(e);
                } finally {
			cConnection(st);
		}
    }

    private static DataSource getTaxi() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/taxi");
    }

     private static void oConnection(){
                try{
                    Locale.setDefault(Locale.ENGLISH);
                    dataSource = getTaxi();
                    conn = dataSource.getConnection();
                }catch(Exception ex){
                    throw new EJBException("Unable to connect to database. " + ex.getMessage());
                }
            }

	private static void cConnection(Statement st) {
		try {
			if (st != null)
				st.close();
		} catch (SQLException ex) {
			throw new EJBException("Unable to connect to database. " + ex.getMessage());
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException ex) {
			throw new EJBException("Unable to connect to database. " + ex.getMessage());
		}
	}

    
}
