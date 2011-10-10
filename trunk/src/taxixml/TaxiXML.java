package taxixml;

import generated.*;
import generated2.CarsType;
import java.io.ByteArrayOutputStream;
import java.sql.*;

public class TaxiXML {

private static Connection con;
    
    public static void main(String[] args) {
        try {
            // Название драйвера
                String driverName = "com.mysql.jdbc.Driver"; 
                Class.forName(driverName);
     
                // Создаем подключение к БД
                String serverName = "localhost";
                String mydatabase = "test";
                String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
                String login = "root";
                String pass = "1111";
     
                Connection con = DriverManager.getConnection(url, login, pass);
                
                ObjectGenerator og = new ObjectGenerator();
                ByteArrayOutputStream b = og.generateListOrders(con);
                og.generateListCars(con);
                
                XMLParser xp = new XMLParser();
                OrdersType ot = new OrdersType();
                ot = (OrdersType) xp.parseXML(b, ot.getClass().getName());
                
//                CarsType ct = new CarsType();
//                ct = (CarsType) xp.parseXML("C:\\test2.xml", ct.getClass().getName());
                
                //TypesType tt = new TypesType();
                //tt = (TypesType) xp.parseXML("C:\\test3.xml", tt.getClass().getName());
                
                /*for (int i=0; i<ct.getSingleCar().size(); i++)
                {
                    System.out.println(ct.getSingleCar().get(i).getRunning());
                }*/
                
                for (int i=0; i<ot.getSingleOrder().size(); i++)
                {
                    System.out.println(ot.getSingleOrder().get(i).getTimeDest());
                }
                                
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
