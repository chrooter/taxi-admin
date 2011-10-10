package taxixml;

import java.sql.*;
import generated.*;
import generated2.*;
import generated3.*;
import java.io.ByteArrayOutputStream;

public class ObjectGenerator {
    
    XMLGenerator xg = new XMLGenerator();
       
    private static String sqlGetOrders = "SELECT * FROM orders";
    private static String sqlGetCars = "SELECT * FROM cars";
    private static String sqlGetTypes = "SELECT * FROM types";
    
    public ByteArrayOutputStream generateListOrders(Connection con)
    {
        OrdersType ot = new OrdersType();
        try {
            PreparedStatement st = con.prepareStatement(sqlGetOrders);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                SingleOrderType sot = new SingleOrderType();
                sot.setOrderId(rs.getInt(1));
                sot.setTimeOrd(rs.getDate(2).toString());
                sot.setTimeDest(rs.getDate(3).toString());
                sot.setAddrDep(rs.getString(4));
                sot.setAddrDest(rs.getString(5));
                sot.setPassenger(rs.getInt(6));
                sot.setStatus(rs.getInt(7));
                sot.setDistAppr(rs.getInt(8));
                sot.setDistInfact(rs.getInt(9));
                sot.setCost(rs.getInt(10));
                ot.getSingleOrder().add(sot);
            }
            return xg.generateXMLOrders(ot);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void generateListCars(Connection con)
    {
        CarsType ct = new CarsType();
        try {
            PreparedStatement st = con.prepareStatement(sqlGetCars);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                SingleCarType sct = new SingleCarType();
                sct.setCarId(rs.getInt(1));
                sct.setRefType(rs.getInt(2));
                sct.setGovNumber(rs.getString(3));
                sct.setRunning(rs.getInt(4));
                ct.getSingleCar().add(sct);
            }
            xg.generateXMLCars(ct);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    
    public void generateListTypes(Connection con)
    {
        TypesType tt = new TypesType();
        try {
            PreparedStatement st = con.prepareStatement(sqlGetTypes);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                SingleTypeType stt = new SingleTypeType();
                stt.setTypeId(rs.getInt(1));
                stt.setName(rs.getString(2));
                stt.setSeatingCapacity(rs.getInt(3));
                stt.setCapacity(rs.getInt(4));
                stt.setCostPerKm(rs.getInt(5));
                tt.getSingleType().add(stt);
            }
            xg.generateXMLTypes(tt);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
}
