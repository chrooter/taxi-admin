/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;
import java.sql.Connection;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * Методы этого класса возвращают вектор типа структур таблицы,
 * котрые в jsp используются для заполнения навигационных страниц и страниц редактирования, создания
 * нужно их изменить, чтобы возвращались вектор элементов XML
 */
public class Query {
    private static Connection conn = null;

    public static Vector<Object> GetVector(String tableName) throws Exception {
        conn = Connect.GetConnect();
        Vector<Object> vec = new Vector<Object>();
        PreparedStatement pstmt = conn.prepareStatement(
                            "SELECT * FROM "+tableName);
        ResultSet rs = pstmt.executeQuery();
        if (tableName.equals("Orders"))
        {
          Request req;
          while (rs.next()) {
            req = new Request();
            req.order_id = rs.getInt("order_id");
            req.time_ord = rs.getDate("time_ord");
            req.time_dest = rs.getDate("time_dest");
            req.addr_dep = rs.getString("addr_dep");
            req.addr_dest = rs.getString("addr_dest");
            req.passenger = rs.getInt("passenger");
            req.status = rs.getInt("status");
            req.dist = rs.getInt("dist_infact");
            req.cost = rs.getInt("cost");
            vec.add(req);
          }
          
        }
        else if (tableName.equals("Types"))
        {
            Type t;
            while(rs.next()) {
                t = new Type();
                t.type_id = rs.getInt("type_id");
                t.seating_capacity = rs.getInt("seating_capacity");
                t.capacity = rs.getInt("capacity");
                t.name = rs.getString("name");
                t.cost_per_km = rs.getInt("cost_per_km");
                vec.add(t);
            }
        }
        else if (tableName.equals("Cars"))
        {
            Car c;
            while(rs.next()) {
                c = new Car();
                c.car_id = rs.getInt("car_id");
                c.gov_number = rs.getString("gov_number");
                c.ref_type = rs.getInt("ref_type");
                c.running = rs.getInt("running");
                vec.add(c);
            }
        }
        conn.close();
        return vec;
    }

    public static Object GetEditItem(String tableName, String idName,int id) throws Exception {
        conn = Connect.GetConnect();
        Object item = new Object();
        PreparedStatement pstmt = conn.prepareStatement(
                            "SELECT * FROM "+tableName +" Where " +idName+" = ?");
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (tableName.equals("Orders"))
        {
          Request req;
          rs.next();
            req = new Request();
            req.order_id = rs.getInt("order_id");
            req.time_ord = rs.getDate("time_ord");
            req.time_dest = rs.getDate("time_dest");
            req.addr_dep = rs.getString("addr_dep");
            req.addr_dest = rs.getString("addr_dest");
            req.passenger = rs.getInt("passenger");
            req.status = rs.getInt("status");
            req.dist = rs.getInt("dist_infact");
            req.cost = rs.getInt("cost");
            item = req;
        }
        else if (tableName.equals("Types"))
        {
            Type t;
            rs.next();
                t = new Type();
                t.type_id = rs.getInt("type_id");
                t.seating_capacity = rs.getInt("seating_capacity");
                t.capacity = rs.getInt("capacity");
                t.name = rs.getString("name");
                t.cost_per_km = rs.getInt("cost_per_km");
                item = t;
        }
        else if (tableName.equals("Cars"))
        {
            Car c;
          rs.next();
                c = new Car();
                c.car_id = rs.getInt("car_id");
                c.gov_number = rs.getString("gov_number");
                c.ref_type = rs.getInt("ref_type");
                c.running = rs.getInt("running");
                item = c;
        }
        conn.close();
        return item;
    }

    public static Vector<String> GetRefId() throws Exception {
        conn = Connect.GetConnect();
        Vector<String> ides = new Vector<String>();
        PreparedStatement pstmt = conn.prepareStatement(
                            "SELECT * FROM Types");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            ides.add(rs.getString("type_id"));
        }
        conn.close();
        return ides;
    }
}
