/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class CarType {
    public static String Insert (List<String> row) {
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "INSERT INTO CAR_TYPE VALUES (CAR_TYPE_SEQ.nextval, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setString(1, row.get(0));
            ps.setInt(2, Integer.parseInt(row.get(1)));
            ps.setInt(3, Integer.parseInt(row.get(2)));
            ps.setInt(4, Integer.parseInt(row.get(3)));
                    
            ps.executeUpdate();
            ps.close();
            initContext.close();
            return "Запись добавлена";
            
        }
        catch (SQLException ex) {
            return "Ошибка (БД)";
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }        
        catch (NamingException ex) {
            return "Something went wrong";
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//------------------------------------------------------------------------------      
    
//------------------------------------------------------------------------------       
    public static String Delete (int id) {
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "DELETE FROM CAR_TYPE WHERE CAR_TYPE.ID_TYPE = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setInt(1, id);
                    
            ps.executeUpdate();
            ps.close();
            initContext.close();
            return "Запись удалена";
        }
        catch (SQLException ex) {
            return "Запрещено. Существуют машины такого типа";
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }        
        catch (NamingException ex) {
            return "Ошибка в коде";
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//------------------------------------------------------------------------------    
   public static List<Object> Select () {
        return Select ("ID_TYPE", null);
    }
//------------------------------------------------------------------------------    
    public static List<Object> Select (String orderBy) {
        return Select (orderBy, null);
    }
//------------------------------------------------------------------------------
    
//------------------------------------------------------------------------------            
    public static List<Object>/*String*/ Select (String orderBy, List<String> find) {
        try {
            int conditions = 1;
            
            List<Object> rows = new ArrayList<Object>();
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "SELECT * FROM CAR_TYPE ";
            // лист 0 - ид, 1 - имя, 2 - посадочные месста, 3 - грузоподьемность, 4 - цена
            
            if (find != null) query += "WHERE ID_TYPE = ID_TYPE ";
            if (!find.get(0).isEmpty()) 
                query += "AND CAR_TYPE.ID_TYPE = ? ";
            if (!find.get(1).isEmpty()) 
                query += "AND CAR_TYPE.NAME LIKE ? ";
            if (!find.get(2).isEmpty()) 
                query += "AND CAR_TYPE.SEATING_CAPASITY = ? ";
            if (!find.get(3).isEmpty()) 
                query += "AND CAR_TYPE.CAPASITY = ? ";
            if (!find.get(4).isEmpty()) 
                query += "AND CAR_TYPE.COST_PER_KM = ? ";
            query += "ORDER BY " + orderBy;

            PreparedStatement ps = conn.prepareStatement(query);

            if (!find.get(0).isEmpty())
                ps.setInt(conditions++, Integer.parseInt(find.get(0)));
            if (!find.get(1).isEmpty()) 
                ps.setString(conditions++, find.get(1));
            if (!find.get(2).isEmpty()) 
                ps.setInt(conditions++, Integer.parseInt(find.get(2)));
            if (!find.get(3).isEmpty()) 
                ps.setInt(conditions++, Integer.parseInt(find.get(3)));
            if (!find.get(4).isEmpty()) 
                ps.setInt(conditions++, Integer.parseInt(find.get(4)));

            ResultSet rs = ps.executeQuery();
        
            while (rs.next()) {
                List<String> row = new ArrayList<String>(); 
                row.add(rs.getRow()+"");  
                row.add(rs.getString("ID_TYPE"));
                row.add(rs.getString("NAME"));
                row.add(rs.getString("SEATING_CAPASITY"));
                row.add(rs.getString("CAPASITY"));
                row.add(rs.getString("COST_PER_KM"));
            rows.add(row);
            }
 
            rs.close();
            initContext.close();
            return rows;
        
        } catch (NamingException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
//------------------------------------------------------------------------------
    
//------------------------------------------------------------------------------
        public static String Update (int id, List<String> row) {
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "UPDATE CAR_TYPE "
                    + "SET NAME = ?, SEATING_CAPASITY = ?, CAPASITY = ?, COST_PER_KM = ? "
                    + "WHERE ID_TYPE = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setString(1, row.get(0));
            ps.setInt(2, Integer.parseInt(row.get(1)));
            ps.setInt(3, Integer.parseInt(row.get(2)));
            ps.setInt(4, Integer.parseInt(row.get(3)));
            ps.setInt(5, id);
            
            ResultSet rs = ps.executeQuery();
 
            rs.close();
            initContext.close();
            return "Запись изменена";
        
        } catch (NamingException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return "Ошибка доступа к базе";
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return "Something went wrong";
        }
    }
}
