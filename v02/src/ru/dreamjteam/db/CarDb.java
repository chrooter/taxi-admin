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
public class CarDb {    
    
    private static Connection getConnection() throws NamingException, SQLException {
	Context initContext = new InitialContext();
	Context envContext  = (Context)initContext.lookup("java:/comp/env");
	DataSource ds = (DataSource)envContext.lookup("sampdb");
	return ds.getConnection();
    }
    
    
    public static void insert (Car row) throws DbAccessException {
        try {
	        //TODO: КОПИПАСТа detected! (1)
	        final Connection conn = Connect.GetConnect();

	        String query = "INSERT INTO CARS VALUES (CARS_SEQ.nextval, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, row.getCarTypeId());       // тип
            ps.setString(2, row.getGovNumber());    // номер
            ps.setString(3, row.getCarModel());     // модель
            ps.setInt(4, row.getRunning());         // пробег
                
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

    public static void delete (int id) throws DbAccessException {
        try {
	    Connection conn = getConnection();

            String query = "DELETE FROM CARS WHERE CAR_ID = ?";
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
        Car cr = new Car();
        return select ("CAR_ID", null);
    }
    
    public static String select (String orderBy) throws DbAccessException {
        return select (orderBy, null);
    }
        
    public static String select (int id) throws DbAccessException {
        Car cr = new Car();
        cr.setId(id);
        return select ("CAR_ID", cr);
    }
        
    public static String select (String orderBy, Car cr) throws DbAccessException {
        try {
            int conditions = 1;

            Connection conn = getConnection();

            String query = "SELECT * FROM CARS ";
            if (cr != null) {
                query += "WHERE CAR_ID = CAR_ID ";
                if (cr.getId() != null)
                    query += "AND CAR_ID = ? ";
                if (cr.getCarTypeId() != null) 
                    query += "AND REF_TYPE = ? ";
                if (cr.getGovNumber() != null)
                    query += "AND GOV_NUMBER LIKE ? ";
                if (cr.getCarModel() != null)
                    query += "AND MODEL LIKE ? ";
                if (cr.getRunning() != null) 
                    query += "AND RUNNING = ? ";
            }
            query += "ORDER BY " + orderBy;

            PreparedStatement ps = conn.prepareStatement(query);
        
            if (cr != null) {
                if (cr.getId() != null)
                    ps.setInt(conditions++, cr.getId());
                if (cr.getCarTypeId() != null) 
                    ps.setInt(conditions++, cr.getCarTypeId());
                if (cr.getGovNumber() != null)  
                    ps.setString(conditions++, "%"+cr.getGovNumber()+"%");
                if (cr.getCarModel() != null) 
                    ps.setString(conditions++, "%"+cr.getCarModel()+"%");
                if (cr.getRunning() != null) 
                    ps.setInt(conditions++, cr.getRunning());     
            }
            
            ResultSet rs = ps.executeQuery();
            Cars rows = new Cars();
            while (rs.next()) {
                Car row = new Car();
                row.setId(rs.getInt("CAR_ID"));
                row.setCarTypeId(rs.getInt("REF_TYPE"));
                row.setGovNumber(rs.getString("GOV_NUMBER"));
                row.setCarModel(rs.getString("MODEL"));
                row.setRunning(rs.getInt("RUNNING"));
                row.setCarType(new CarType());
                row.setOrders(new Orders());
                rows.getCar().add(row);
            }
 
            ps.close();
            conn.close();
            return XMLGenerator.toXML(rows);
        
        } catch (NamingException ex) {
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            throw new DbAccessException(ex);
        } catch (SQLException ex) {
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            throw new DbAccessException(ex);
        }
    }
    
    public static void update (Car row) throws DbAccessException {
        try {
	        //TODO: КОПИПАСТа detected! (3)
	        Connection conn = Connect.GetConnect();

            String query = "UPDATE CARS "
                    + "SET REF_TYPE = ?, GOV_NUMBER = ?, MODEL = ?, RUNNING = ? "
                    + "WHERE CAR_ID = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
        
            ps.setInt(1, row.getCarTypeId());       // тип
            ps.setString(2, row.getGovNumber());    // номер
            ps.setString(3, row.getCarModel());     // модель
            ps.setInt(4, row.getRunning());         // пробег
            ps.setInt(5, row.getId());              // ид
                
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
 }
