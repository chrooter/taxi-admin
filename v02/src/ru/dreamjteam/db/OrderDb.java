package ru.dreamjteam.db;

import ru.dreamjteam.xml.binds.orders.*;
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
public class OrderDb {
    public static void insert (Order row) throws DbAccessException {
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "INSERT INTO "
                    + "ORDERS "
                    + "VALUES (ORDERS_SEQ.nextval, "
                    + "to_date(?,'dd-mm-yyyy hh24:mi'), "
                    + "to_date(?,'dd-mm-yyyy hh24:mi'), "
                    + "?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
        
            ps.setString(1, row.getTimeOrd());
            ps.setString(2, row.getTimeDest());
            ps.setString(3, row.getAddrDep());
            ps.setString(4, row.getAddrDest());
            ps.setInt(5, row.getPassengers());
            ps.setString(6, row.getStatus());
            ps.setInt(7, row.getDistAppr());
            ps.setInt(8, row.getDistInfact());
            ps.setInt(9, row.getCost());
                
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
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "DELETE FROM ORDERS WHERE ORDERS.ID_ORDER = ?";
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

    public static void update (Order row) throws DbAccessException {
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();
            
            String query = "UPDATE ORDERS "
                    + "ORD_TIME = to_date(?,'dd-mm-yyyy hh24:mi'), "
                    + "DEST_TIME = to_date(?,'dd-mm-yyyy hh24:mi'), "
                    + "DEP_ADDR = ?, "
                    + "DEST_ADDR = ?, "
                    + "PASSANGER = ?, "
                    + "STATUS = ?, "
                    + "DIST_APPR = ?, "
                    + "DIST_INFACT = ?, "
                    + "COST = ? "
                    + "WHERE ID_ORDER = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
        
            ps.setString(1, row.getTimeOrd());
            ps.setString(2, row.getTimeDest());
            ps.setString(3, row.getAddrDep());
            ps.setString(4, row.getAddrDest());
            ps.setInt(5, row.getPassengers());
            ps.setString(6, row.getStatus());
            ps.setInt(7, row.getDistAppr());
            ps.setInt(8, row.getDistInfact());
            ps.setInt(9, row.getCost());
                
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
        return select("ID_ORDER", null);
    }
    
    public static String select (String orderBy) throws DbAccessException {
        return select(orderBy, null);
    }
    
    public static String select (String orderBy, int id) throws DbAccessException {
        Order ord = new Order();
        ord.setId(id);        
        return select(orderBy, ord);
    }
    
    public static String select (String orderBy, Order ord) throws DbAccessException {
        try {
            int conditions = 1;
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "SELECT "
                    + "ID_ORDER, "
                    + "TO_CHAR(ORD_TIME, 'dd-mm-yyyy hh24:mi') ORD_TIME,"
                    + "TO_CHAR(DEST_TIME, 'dd-mm-yyyy hh24:mi') DEST_TIME,"
                    + "DEP_ADDR,"
                    + "DEST_ADDR,"
                    + "PASSANGER,"
                    + "STATUS,"
                    + "DIST_APPR,"
                    + "DIST_INFACT,"
                    + "COST"
                    + "FROM ORDERS ORDER BY " + orderBy;
            
            if (ord != null) {            
                query += "WHERE ID_ORDER = ID_ORDER";
            
                if (ord.getId() != 0)
                    query += "AND ID_ORDER = ? ";
                if (ord.getTimeOrd() != null)
                    query += "AND ORD_TIME = ? ";
                if (ord.getTimeDest() != null)
                    query += "AND DEST_TIME = ? ";
                if (ord.getAddrDep() != null)
                    query += "AND DEP_ADDR = ? ";
                if (ord.getAddrDest() != null)
                    query += "AND DEST_ADDR = ? ";
                if (ord.getPassengers() != 0)
                    query += "AND PASSANGER = ? ";
                if (ord.getStatus() != null)
                    query += "AND STATUS = ? ";
                if (ord.getDistAppr() != 0)
                    query += "AND DIST_APPR = ? ";
                if (ord.getDistInfact() != 0)
                    query += "AND DIST_INFACT = ? ";
                if (ord.getCost() != 0)
                    query += "AND COST = ? ";
                if (ord.getId() != 0)
                    query += "AND ID_ORDER = ? ";
            }
            query += "ORDER BY " + orderBy;
                        
            PreparedStatement ps = conn.prepareStatement(query);
            
             if (ord.getId() != 0)
                ps.setInt(conditions++, ord.getId());
            if (ord.getTimeOrd() != null)
                ps.setString(conditions++, ord.getTimeOrd());
            if (ord.getTimeDest() != null)
                ps.setString(conditions++, ord.getTimeDest());
            if (ord.getAddrDep() != null)
                ps.setString(conditions++, ord.getAddrDep());
            if (ord.getAddrDest() != null)
                ps.setString(conditions++, ord.getAddrDest());
            if (ord.getPassengers() != 0)
                ps.setInt(conditions++, ord.getPassengers());
            if (ord.getStatus() != null)
                ps.setString(conditions++, ord.getStatus());
            if (ord.getDistAppr() != 0)
                ps.setInt(conditions++, ord.getDistAppr());
            if (ord.getDistInfact() != 0)
                ps.setInt(conditions++,ord.getDistInfact());
            if (ord.getCost() != 0)
                ps.setInt(conditions++, ord.getCost());
                                             
            ResultSet rs = ps.executeQuery();            

            Orders rows = new Orders();
            while (rs.next()) {
                Order row = new Order(); 
                row.setId(rs.getInt("ID_ORDER"));
                row.setTimeOrd(rs.getString("ORD_TIME"));
                row.setTimeDest(rs.getString("DEST_TIME"));
                row.setAddrDep(rs.getString("DEP_ADDR"));
                row.setAddrDest(rs.getString("DEST_ADDR"));
                row.setPassengers(rs.getInt("PASSANGER"));
                row.setStatus(rs.getString("STATUS"));
                row.setDistAppr(rs.getInt("DIST_APPR"));
                row.setDistInfact(rs.getInt("DIST_INFACT"));
                row.setCost(rs.getInt("COST"));
                rows.getOrder().add(row);
            }
            ps.close();
            initContext.close();
            return XMLGenerator.toXML(rows);
        }
        catch (SQLException ex) {
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            throw new DbAccessException(ex);
        }       
        catch (NamingException ex) {
            //Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            throw new DbAccessException(ex);
        }
    } 
    
}
