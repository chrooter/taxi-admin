package ru.dreamjteam.xml;

import ru.dreamjteam.xml.binds.cars.Car;
import ru.dreamjteam.xml.binds.cars.Cars;
import ru.dreamjteam.xml.binds.orders.Order;
import ru.dreamjteam.xml.binds.orders.Orders;
import ru.dreamjteam.xml.binds.types.CarType;
import ru.dreamjteam.xml.binds.types.CarTypes;

import java.sql.*;

//TODO: класс все-еще кому то нужен или это deprecated?
public class ObjectGenerator {
	private static String sqlGetOrders = "SELECT * FROM orders";
	private static String sqlGetCars = "SELECT * FROM cars";
	private static String sqlGetTypes = "SELECT * FROM types";

	public String generateListOrders(Connection con) {
		Orders ot = new Orders();
		try {
			PreparedStatement st = con.prepareStatement(sqlGetOrders);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Order sot = new Order();
				sot.setId(rs.getInt(1));
				sot.setTimeOrd(rs.getDate(2).toString());
				sot.setTimeDest(rs.getDate(3).toString());
				sot.setAddrDep(rs.getString(4));
				sot.setAddrDest(rs.getString(5));
				sot.setPassengers(rs.getInt(6));
				sot.setStatus(rs.getString(7));
				sot.setDistAppr(rs.getInt(8));
				sot.setDistInfact(rs.getInt(9));
				sot.setCost(rs.getInt(10));
				ot.getOrder().add(sot);
			}
			return XMLGenerator.toXML(ot);
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public String generateListCars(Connection con) {
		Cars ct = new Cars();
		try {
			PreparedStatement st = con.prepareStatement(sqlGetCars);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Car sct = new Car();
				sct.setId(rs.getInt(1));
				sct.setCarTypeId(rs.getInt(2));
				sct.setCarModel(rs.getString(3));
				sct.setGovNumber(rs.getString(4));
				sct.setRunning(rs.getInt(5));
				ct.getCar().add(sct);
			}
			return XMLGenerator.toXML(ct);
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}


	public String generateListTypes(Connection con) {
		CarTypes tt = new CarTypes();
		try {
			PreparedStatement st = con.prepareStatement(sqlGetTypes);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				CarType stt = new CarType();
				stt.setId(rs.getInt(1));
				stt.setName(rs.getString(2));
				stt.setSeatCap(rs.getInt(3));
				stt.setMassCap(rs.getInt(4));
				stt.setCostPerKm(rs.getInt(5));
				tt.getCarType().add(stt);
			}
			return XMLGenerator.toXML(tt);
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
