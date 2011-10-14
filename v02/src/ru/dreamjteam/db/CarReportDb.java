package ru.dreamjteam.db;

import ru.dreamjteam.xml.binds.carReports.*;
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
public class CarReportDb {
        
    public static String select () throws DbAccessException {
        return select("CAR_ID", null);
    }
    
    public static String selectSt (String orderBy, String status) throws DbAccessException {
        CarReport cr = new CarReport();
        cr.setStatus(status);
        return select(orderBy, cr);
    }
    
    public static String select (int carId) throws DbAccessException {
        CarReport cr = new CarReport();
        cr.setIdCar(carId);
        return select("CAR_ID", cr);
    }
       
    
    public static String select (String orderBy, CarReport find) throws DbAccessException {
        try {            
            int conditions = 1;
            
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "SELECT CAR_ID, ORDER_ID, MODEL, SEATING_CAPACITY, "
                    + "GOV_NUMBER, RUNNING, STATUS "
                    + "FROM CAR "
                    + "LEFT JOIN TYPES ON TYPE_ID = REF_TYPE "
                    + "LEFT JOIN ORD_CAR ON REF_CAR = CAR_ID "
                    + "LEFT JOIN ORDERS ON REF_ORDER = ORDER_ID";
            
            if (find != null) { 
                query += "WHERE CAR_ID = CAR_ID ";
            
                if (find.getIdCar() != 0) 
                    query += "AND CAR_ID = ? ";
                if (find.getCarModel() != null) 
                    query += "AND MODEL LIKE ? ";
                if (find.getSeatCap() != 0) 
                    query += "AND SEATING_CAPACITY = ? ";
                if (find.getGovNumber() != null)
                    query += "AND GOV_NUMBER LIKE ? ";
                if (find.getRunning() != 0) 
                    query += "AND RUNNING = ?";
            }
            query += "ORDER BY " + orderBy;

            PreparedStatement ps = conn.prepareStatement(query);
        
            if (find != null) { 
                if (find.getIdCar() != 0) 
                    ps.setInt(conditions++, find.getIdCar());
                if (find.getCarModel() != null) 
                    ps.setString(conditions++, find.getCarModel());
                if (find.getSeatCap() != 0) 
                    ps.setInt(conditions++, find.getSeatCap());
                if (find.getGovNumber() != null)
                    ps.setString(conditions++, find.getGovNumber());
                if (find.getRunning() != 0) 
                    ps.setInt(conditions++, find.getRunning());
            }           

            ResultSet rs = ps.executeQuery();
            CarReports rows = new CarReports();
            while (rs.next()) {
                CarReport row = new CarReport();    
                row.setIdCar(rs.getInt("CAR_ID"));
                row.setIdOrder(rs.getInt("ORDER_ID"));
                row.setCarModel(rs.getString("MODEL"));
                row.setGovNumber(rs.getString("GOV_NUMBER"));
                row.setSeatCap(rs.getInt("SEATING_CAPASITY"));
                row.setRunning(rs.getInt("RUNNING"));
                row.setStatus(rs.getString("STATUS"));
                rows.getCarReport().add(row);
            }
 
            ps.close();
            initContext.close();
            return XMLGenerator.toXML(rows);
        
        } catch (NamingException ex) {
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            throw new DbAccessException(ex);
        } catch (SQLException ex) {
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            throw new DbAccessException(ex);
        }
    }
  

}
