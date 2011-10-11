package ru.dreamjteam.servlets;

import ru.dreamjteam.xml.binds.cars.Car;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author abolmasov (11.10.2011 10:16:13)
 * @version $Revision$
 */
public class ViewCarListServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Car> cars = new ArrayList<Car>(3);

		Car newCar = new Car();
		newCar.setCarTypeId(0);
		newCar.setGovNumber("й 123 цу 163");
		newCar.setRunning(156);
		cars.add(newCar);

		newCar = new Car();
		newCar.setCarTypeId(0);
		newCar.setGovNumber("к 456 ен 163");
		newCar.setRunning(156987);
		cars.add(newCar);

		newCar = new Car();
		newCar.setCarTypeId(0);
		newCar.setGovNumber("г 789 шщ 63");
		newCar.setRunning(24156);
		cars.add(newCar);

		req.setAttribute("cars", cars);
		req.getRequestDispatcher("/cars.jsp").forward(req, resp);
	}
}
