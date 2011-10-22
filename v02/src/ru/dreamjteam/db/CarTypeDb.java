package ru.dreamjteam.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import ru.dreamjteam.xml.XMLGenerator;
import ru.dreamjteam.xml.binds.*;


/**
 *
 * @author Senya
 */
public class CarTypeDb {
    
    private static Connection getConnection () throws NamingException, SQLException {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            return ds.getConnection();
    }
    
    public static void insert ( CarType row) throws DbAccessException {
        
        try {
            
            Connection conn = Connect.GetConnect();

            String query = "INSERT INTO TYPES VALUES (TYPES_SEQ.nextval, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setString(1, row.getName());
            ps.setInt(2, row.getSeatCap());
            ps.setInt(3, row.getMassCap());
            ps.setInt(4, row.getCostPerKm());
                    
            ps.executeUpdate();
            ps.close();
            conn.close();

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
            Connection conn = getConnection();
            String query = "DELETE FROM TYPES WHERE TYPE_ID = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setInt(1, id);
                    
            ps.executeUpdate();
            ps.close();
            conn.close();
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
        return select ("TYPE_ID", null);
    }
    
    public static String select (String orderBy) throws DbAccessException {
        CarType ct = new CarType();
        return select (orderBy, null);
    }
    
    public static String select (int id) throws DbAccessException {
        CarType ct = new CarType();
        ct.setId(id);
        return select ("TYPE_ID", ct);
    }
                      
   public static String select (String orderBy, CarType ct) throws DbAccessException {
        try {
            int conditions = 1;
            Connection conn = Connect.GetConnect();

            String query = "SELECT * FROM TYPES ";
            
            if (ct != null) {
                query += "WHERE TYPE_ID = TYPE_ID ";
                if (ct.getId() != 0) 
                    query += "AND TYPE_ID = ? ";
                if (ct.getName() != null) 
                    query += "AND NAME LIKE ? ";
                if (ct.getSeatCap() != null)
                    query += "AND SEATING_CAPACITY = ? ";
                if (ct.getMassCap() != null)
                    query += "AND CAPACITY = ? ";
                if (ct.getCostPerKm() != null)
                    query += "AND COST_PER_KM = ? ";
            }    
            query += "ORDER BY " + orderBy;

            PreparedStatement ps = conn.prepareStatement(query);

            if (ct != null) {
                if (ct.getId() != 0) 
                    ps.setInt(conditions++, ct.getId());
                if (ct.getName() != null)
                    ps.setString(conditions++, "%"+ct.getName()+"%");
                if (ct.getSeatCap() != null)
                    ps.setInt(conditions++, ct.getSeatCap());
                if (ct.getMassCap() !=null)
                    ps.setInt(conditions++, ct.getMassCap());
                if (ct.getCostPerKm() != null)
                    ps.setInt(conditions++, ct.getCostPerKm());
            }
            
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
            conn.close();
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
            Connection conn = Connect.GetConnect();

            String query = "UPDATE TYPES "
                    + "SET NAME = ?, SEATING_CAPACITY = ?, CAPACITY = ?, COST_PER_KM = ? "
                    + "WHERE TYPE_ID = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setString(1, row.getName());
            ps.setInt(2, row.getSeatCap());
            ps.setInt(3, row.getMassCap());
            ps.setInt(4, row.getCostPerKm());
            ps.setInt(5, row.getId());
            
            ps.executeUpdate();
            
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
