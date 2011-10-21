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
import ru.dreamjteam.xml.XMLGenerator;
import ru.dreamjteam.xml.binds.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Senya
 */
public class CarReportDb {

    public static String select () throws DbAccessException {
        return select("CAR_ID", null);
    }
    
    public static String selectSt (String orderBy, String status) throws DbAccessException {
        Car cr = new Car();
        cr.setOrders(new Orders());
        cr.getOrders().getOrder().add(new Order());
        cr.getOrders().getOrder().get(0).setStatus(status);
        return select(orderBy, cr);
    }

    public static String select (int carId) throws DbAccessException {
        Car cr = new Car();
        cr.setId(carId);
        return select("CAR_ID", cr);
    }


    public static String select (String orderBy, Car find) throws DbAccessException {
       try {
            int i, id, conditions = 1;
            Integer position;
            Car row;

            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("sampdb");
            Connection conn = ds.getConnection();

            String query = "SELECT CAR_ID, ORDER_ID, MODEL, SEATING_CAPACITY, "
                    + "GOV_NUMBER, RUNNING, STATUS, REF_TYPE "
                    + "FROM CAR "
                    + "LEFT JOIN TYPES ON TYPE_ID = REF_TYPE "
                    + "LEFT JOIN ORD_CAR ON REF_CAR = CAR_ID "
                    + "LEFT JOIN ORDERS ON REF_ORDER = ORDER_ID ";

            if (find != null) {
                query += "WHERE CAR_ID = CAR_ID ";

                if (find.getId() != null)
                    query += "AND CAR_ID = ? ";
                if (find.getCarModel() != null)
                    query += "AND MODEL LIKE ? ";
                if ((find.getCarType() != null)
                  &&(find.getCarType().getSeatCap() != null))
                    query += "AND SEATING_CAPACITY = ? ";
                if (find.getGovNumber() != null)
                    query += "AND GOV_NUMBER LIKE ? ";
                if (find.getRunning() != null)
                    query += "AND RUNNING = ? ";
                if (find.getCarTypeId() != null)
                    query += "AND REF_TYPE = ? ";
                if ((find.getOrders() != null)
                 && (find.getOrders().getOrder().get(0).getStatus() != null))
                    query += "AND STATUS = ? ";
            }
            query += "ORDER BY " + orderBy;

            PreparedStatement ps = conn.prepareStatement(query);

            if (find != null) {
                if (find.getId() != null)
                    ps.setInt(conditions++, find.getId());
                if (find.getCarModel() != null)
                    ps.setString(conditions++, find.getCarModel());
                if ((find.getCarType() != null)
                  &&(find.getCarType().getSeatCap() != null))
                    ps.setInt(conditions++, find.getCarType().getSeatCap());
                if (find.getGovNumber() != null)
                    ps.setString(conditions++, find.getGovNumber());
                if (find.getRunning() != null)
                    ps.setInt(conditions++, find.getRunning());
                if (find.getCarTypeId() != null)
                    ps.setInt(conditions++, find.getCarTypeId());
                if ((find.getOrders() != null)
                 && (find.getOrders().getOrder().get(0).getStatus() != null))
                    ps.setString(conditions++, find.getOrders().getOrder().get(0).getStatus());
            }

            
            Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
            
            
            ResultSet rs = ps.executeQuery();
            Cars rows = new Cars();
            while (rs.next()) {
                id = rs.getInt("CAR_ID");
                position = mp.get(id);
                if (position == null) {
                    row = new Car();
                    row.setOrders(new Orders());
                    row.getOrders().getOrder().add(new Order());
                    row.setCarType(new CarType());
                    row.setId(id);
                    row.getOrders().getOrder().get(0).setId(rs.getInt("ORDER_ID"));
                    row.getOrders().getOrder().get(0).setCar(new Car());
                    row.setCarModel(rs.getString("MODEL"));
                    row.setGovNumber(rs.getString("GOV_NUMBER"));
                    row.getCarType().setSeatCap(rs.getInt("SEATING_CAPACITY"));
                    row.setRunning(rs.getInt("RUNNING"));
                    row.getOrders().getOrder().get(0).setStatus(rs.getString("STATUS"));
                    row.getCarType().setId(rs.getInt("REF_TYPE"));
                    mp.put(id, rows.getCar().size());  // при добавлении новой машины, запоминаем её ИД и номер строки в сете
                    rows.getCar().add(row);                    
                } else {
                    row = rows.getCar().get(position.intValue());
                    row.getOrders().getOrder().add(new Order());
                    i = row.getOrders().getOrder().size() - 1;
                    row.getOrders().getOrder().get(i).setId(rs.getInt("ORDER_ID"));
                    row.getOrders().getOrder().get(i).setCar(new Car());
                    row.getOrders().getOrder().get(i).setStatus(rs.getString("STATUS"));                    
                }
                
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
  

}
