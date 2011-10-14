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
import ru.dreamjteam.xml.binds.cars.*;

/**
 *
 * @author Senya
 */
public class CarDb {
       
    public static void insert (Car row) throws DbAccessException {
        try {

	        //TODO: КОПИПАСТа detected! (1)
            Context initContext = new InitialContext();

            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "INSERT INTO CAR VALUES (CAR_SEQ.nextval, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, row.getCarTypeId());       // тип
            ps.setString(2, row.getGovNumber());    // номер
            ps.setString(3, row.getCarModel());     // модель
            ps.setInt(4, row.getRunning());         // пробег
                
            ps.executeUpdate();
            ps.close();

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
	        //TODO: КОПИПАСТа detected! (2)
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "DELETE FROM CAR WHERE CAR.ID_CAR = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
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
    
    public static void update (Car row) throws DbAccessException {
        try {
	        //TODO: КОПИПАСТа detected! (3)
            Context initContext = new InitialContext();

            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "UPDATE CAR "
                    + "SET REF_TYPE = ?, GOV_NUMBER = ?, MODEL = ?, RUNNING = ? "
                    + "WHERE ID_CAR = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
        
            ps.setInt(1, row.getCarTypeId());       // тип
            ps.setString(2, row.getGovNumber());    // номер
            ps.setString(3, row.getCarModel());     // модель
            ps.setInt(4, row.getRunning());         // пробег
            ps.setInt(5, row.getId());              // ид
                
            ps.executeUpdate();
            ps.close();

        } catch (NamingException ex) {
            throw new DbAccessException(ex);
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            throw new DbAccessException(ex);
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }   



    public static String select () throws DbAccessException {
        Car cr = new Car();
        cr.setId(0);
        cr.setCarTypeId(0);
        cr.setGovNumber(null);
        cr.setCarModel(null);
        cr.setRunning(0);
        return select ("ID_CAR", cr);
    }
    
    public static String select (String orderBy) throws DbAccessException {
        Car cr = new Car();
        cr.setId(0);
        cr.setCarTypeId(0);
        cr.setGovNumber(null);
        cr.setCarModel(null);
        cr.setRunning(0);
        return select (orderBy, cr);
    }
    
    public static String select (String orderBy, int id) throws DbAccessException {
        Car cr = new Car();
        cr.setId(id);
        cr.setCarTypeId(0);
        cr.setGovNumber(null);
        cr.setCarModel(null);
        cr.setRunning(0);        
        return select (orderBy, cr);
    }
        
    public static String select (String orderBy, Car cr) throws DbAccessException {
        try {
            int conditions = 1;

            Context initContext = new InitialContext();

            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "SELECT * FROM CAR ";
            query += "WHERE ID_CAR = ID_CAR ";
            if (cr.getId() != 0)
                query += "AND CAR.ID_CAR = ? ";
            if (cr.getCarTypeId() != 0) 
                query += "AND CAR.REF_TYPE = ? ";
	        //TODO: а если гос.номер пустая сторка? смысл будет у выражения?
	        //TODO: такой запрос найдет точное совпадени, что зачастую не нужно.
            if (cr.getGovNumber() != null)
                query += "AND CAR.GOV_NUMBER LIKE ? ";
            if (cr.getCarModel() != null)
            //TODO: аналогично, сделай поиск по части названия, а не точное совпадение
                query += "AND CAR.MODEL LIKE ? ";
            if (cr.getRunning() != 0) 
                query += "AND CAR.RUNNING = ? ";
           
            query += "ORDER BY " + orderBy;

            PreparedStatement ps = conn.prepareStatement(query);
        
            if (cr.getId() != 0)
                ps.setInt(conditions++, cr.getId());
            if (cr.getCarTypeId() != 0) 
                ps.setInt(conditions++, cr.getCarTypeId());
            if (cr.getGovNumber() != null)  
                ps.setString(conditions++, cr.getGovNumber());
            if (cr.getCarModel() != null) 
                ps.setString(conditions++, cr.getCarModel());
            if (cr.getRunning() != 0) 
                ps.setInt(conditions++, cr.getRunning());     

            ResultSet rs = ps.executeQuery();
            Cars rows = new Cars();
            while (rs.next()) {
                Car row = new Car();
                  
                row.setId(rs.getInt("ID"));
                row.setCarTypeId(rs.getInt("REF_TYPE"));
                row.setGovNumber(rs.getString("GOV_NUMBER"));
                row.setCarModel(rs.getString("MODEL"));
                row.setRunning(rs.getInt("RUNNING"));
                rows.getCar().add(row);
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
/*    
    public static Select selectCars (String orderBy, Car find) {
        try {
            int conditions = 1;
            
            Context initContext = new InitialContext();

            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "SELECT "
                + "ID_CAR ID, NAME, GOV_NUMBER,  SEATING_CAPASITY, CAPASITY, "
                + "COST_PER_KM, RUNNING "
                + "FROM CAR_TYPE INNER JOIN CAR ON CAR_TYPE.ID_TYPE = CAR.ID_CAR ";
            
            //0 - ид, 1 - тип, 2 - модель 3 - гос номер, 4 - посадочные места, 5 - груз, 6 - цена - 7 пробег
            if (find != null) query += "WHERE ID_CAR = ID_CAR ";
            if (!find.get(0).isEmpty()) 
                query += "AND CAR_TYPE.ID_CAR = ? ";
            if (!find.get(1).isEmpty()) 
                query += "AND CAR_TYPE.NAME LIKE ? ";
            if (!find.get(2).isEmpty()) 
                query += "AND CAR_TYPE.MODEL LIKE ? ";
            if (!find.get(3).isEmpty()) 
                query += "AND CAR_TYPE.GOV_NUMBER LIKE ? ";
            if (!find.get(4).isEmpty()) 
                query += "AND CAR_TYPE.SEATING_CAPASITY = ? ";
            if (!find.get(5).isEmpty()) 
                query += "AND CAR_TYPE.CAPASITY = ? ";
            if (!find.get(6).isEmpty()) 
                query += "AND CAR_TYPE.COST_PER_KM = ? ";
            if (!find.get(7).isEmpty()) 
                query += "AND CAR_TYPE.COST_PER_KM = ? ";
            query += "ORDER BY " + orderBy;

            PreparedStatement ps = conn.prepareStatement(query);
        
            if (!find.get(0).isEmpty())
                ps.setInt(conditions++, Integer.parseInt(find.get(0)));
            if (!find.get(1).isEmpty()) 
                ps.setString(conditions++, find.get(1));
            if (!find.get(2).isEmpty()) 
                ps.setString(conditions++, find.get(2));
            if (!find.get(3).isEmpty()) 
                ps.setString(conditions++, find.get(3));
            if (!find.get(4).isEmpty()) 
                ps.setInt(conditions++, Integer.parseInt(find.get(4)));
            if (!find.get(5).isEmpty()) 
                ps.setInt(conditions++, Integer.parseInt(find.get(5)));
            if (!find.get(6).isEmpty()) 
                ps.setInt(conditions++, Integer.parseInt(find.get(6)));
            if (!find.get(7).isEmpty()) 
                ps.setInt(conditions++, Integer.parseInt(find.get(7)));            

            ResultSet rs = ps.executeQuery();
            Cars rows = new Cars();
            while (rs.next()) {
                Car row = new Car();
                //List<String> row = new ArrayList<String>(); 
                  
                row.setId(rs.getInt("ID"));
                row.add(rs.getString("NAME"));
                row.add(rs.getString("MODEL"));
                row.add(rs.getString("GOV_NUMBER"));
                row.add(rs.getString("SEATING_CAPASITY"));
                row.add(rs.getString("CAPASITY"));
                row.add(rs.getString("COST_PER_KM"));
                row.add(rs.getString("RUNNING"));
                rows.add(row);
            }
 
            ps.close();
            return rows;
        
        } catch (NamingException ex) {
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
//------------------------------------------------------------------------------    
*/
}
