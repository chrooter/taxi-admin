package ru.dreamjteam.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author Senya
 */

public class ExecutionDb {

    private static Connection getConnection() throws NamingException, SQLException {
	Context initContext = new InitialContext();
	Context envContext  = (Context)initContext.lookup("java:/comp/env");
	DataSource ds = (DataSource)envContext.lookup("sampdb");
	return ds.getConnection();
    }
    
    public static void insert (int carId, int orderId) throws DbAccessException {
        try {
            Connection conn = getConnection();

            String query = "INSERT INTO ORD_CAR VALUES (?, ?)";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, carId);
            ps.setInt(2, orderId);
                
            ps.executeUpdate();
            ps.close();
            conn.close();
            
        } catch (NamingException ex) {
            throw new DbAccessException(ex);
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            throw new DbAccessException(ex);
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }  
    
    
    public static void delete (int carId, int orderId ) throws DbAccessException {
        try {
            Connection conn = getConnection();

            String query = "DELETE FROM ORD_CAR "
                    + "WHERE REF_CAR = ? AND REF_ORDER = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setInt(1, carId);
            ps.setInt(2, orderId);
                    
            ps.executeUpdate();
            conn.close();
            ps.close();

        }
        catch (SQLException ex) {
            throw new DbAccessException(ex);    
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }        
        catch (NamingException ex) {
            throw new DbAccessException(ex);
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public static void update (int getCarId, int getOrderId, int setCarId, int setOrderId) throws DbAccessException {
        try {
            Connection conn = getConnection();

            String query = "UPDATE ORD_CAR "
                    + "SET REF_CAR = ?, REF_ORDER = ? "
                    + "WHERE REF_CAR = ? AND REF_ORDER = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setInt(1, getCarId);
            ps.setInt(2, getOrderId);
            ps.setInt(3, setCarId);
            ps.setInt(4, setOrderId);
            
            ResultSet rs = ps.executeQuery();
 
            rs.close();
            conn.close();
        
        } catch (NamingException ex) {
            throw new DbAccessException(ex);
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            throw new DbAccessException(ex);
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
