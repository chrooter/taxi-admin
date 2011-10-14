package ru.dreamjteam.db;

import ru.dreamjteam.xml.binds.types.*;
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
import ru.dreamjteam.xml.XMLGenerator;

/**
 *
 * @author Senya
 */
public class CarTypeDb {
    
    public static void insert (CarType row) throws DbAccessException {
        
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "INSERT INTO TYPES VALUES (TYPES_SEQ.nextval, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setString(1, row.getName());
            ps.setInt(2, row.getSeatCap());
            ps.setInt(3, row.getMassCap());
            ps.setInt(4, row.getCostPerKm());
                    
            ps.executeUpdate();
            ps.close();
            initContext.close();

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
    
    public static void delete (int id) throws DbAccessException {
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "DELETE FROM TYPES WHERE ID_TYPE = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setInt(1, id);
                    
            ps.executeUpdate();
            ps.close();
            initContext.close();
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
   
    public static String select () throws DbAccessException {
        CarType ct = new CarType();
        ct.setId(0);
        ct.setName(null);
        ct.setMassCap(0);
        ct.setSeatCap(0);
        ct.setCostPerKm(0);        
        
        return select ("TYPE_ID", ct);
    }
    
    public static String select (String orderBy) throws DbAccessException {
        CarType ct = new CarType();
        ct.setId(0);
        ct.setName(null);
        ct.setMassCap(0);
        ct.setSeatCap(0);
        ct.setCostPerKm(0);
        
        return select (orderBy, ct);
    }
    
    public static String select (int id) throws DbAccessException {
        CarType ct = new CarType();
        ct.setId(id);
        ct.setName(null);
        ct.setMassCap(0);
        ct.setSeatCap(0);
        ct.setCostPerKm(0);
        
        return select ("TYPE_ID", ct);
    }
                      
    public static String select (String orderBy, CarType ct) throws DbAccessException {
        try {
            int conditions = 1;
           
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "SELECT * FROM TYPES ";
            
            query += "WHERE TYPE_ID = TYPE_ID ";
            if (ct.getId() != 0) 
                query += "AND TYPE_ID = ? ";
            if (ct.getName() != null) 
                query += "AND NAME LIKE ? ";
            if (ct.getSeatCap() != 0) 
                query += "AND SEATING_CAPASITY = ? ";
            if (ct.getMassCap() != 0) 
                query += "AND CAPASITY = ? ";
            if (ct.getCostPerKm() != 0) 
                query += "AND COST_PER_KM = ? ";
            query += "ORDER BY " + orderBy;

            PreparedStatement ps = conn.prepareStatement(query);

            if (ct.getId() != 0) 
                ps.setInt(conditions++, ct.getId());
            if (ct.getName() != null)
                ps.setString(conditions++, ct.getName());
            if (ct.getSeatCap() != 0)
                ps.setInt(conditions++, ct.getSeatCap());
            if (ct.getMassCap() != 0)
                ps.setInt(conditions++, ct.getMassCap());
            if (ct.getCostPerKm() != 0) 
                ps.setInt(conditions++, ct.getCostPerKm());

            ResultSet rs = ps.executeQuery();
            
            CarTypes rows = new CarTypes();
            while (rs.next())
            {
                CarType row = new CarType();
                row.setId(rs.getInt(1));
                row.setName(rs.getString(2));
                row.setSeatCap(rs.getInt(3));
                row.setMassCap(rs.getInt(4));
                row.setCostPerKm(rs.getInt(5));
                rows.getCarType().add(row);
            }

            rs.close();
            initContext.close();
            return XMLGenerator.toXML(rows);
        
        } catch (NamingException ex) {
 //           Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            throw new DbAccessException(ex);
            //return null;
        } catch (SQLException ex) {
 //           Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            throw new DbAccessException(ex);
            //return null;
        }
    }
 
    public static void update (CarType row) throws DbAccessException {
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "UPDATE TYPES "
                    + "SET NAME = ?, SEATING_CAPASITY = ?, CAPASITY = ?, COST_PER_KM = ? "
                    + "WHERE TYPE_ID = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setString(1, row.getName());
            ps.setInt(2, row.getSeatCap());
            ps.setInt(3, row.getMassCap());
            ps.setInt(4, row.getCostPerKm());
            ps.setInt(5, row.getId());
            
            ResultSet rs = ps.executeQuery();
 
            rs.close();
            initContext.close();

        
        } catch (NamingException ex) {
            throw new DbAccessException(ex);
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            throw new DbAccessException(ex);
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
