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
                
                //В качестве примера вытащим из БД все записи из таблицы ORDERS
                //Генерируем XML'ку в виде потока байт "b" (а не файла!)
                ObjectGenerator og = new ObjectGenerator();
                ByteArrayOutputStream b = og.generateListOrders(con);
                
                //Затем парсим XML, вызывая метод парсера с параметром "b"
                //Возвращаемое значение - объект типа OrdersType, содержащий List<SingleOrderType>
                XMLParser xp = new XMLParser();
                OrdersType ot = new OrdersType();
                ot = (OrdersType) xp.parseXML(b, ot.getClass().getName());

                //Чтобы извлечь лист из полученного объекта, вызываем ot.getSingleOrder() и дальше работаем с листом как хотим
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
