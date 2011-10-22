/*
	Copyright 2011 Open Code Ltd.
    http://www.o-code.ru
 
    $Id: CarServlet.java 84 2011-10-18 07:58:24Z graf.abolmasov@gmail.com $
*/
package ru.dreamjteam.servlets;

import ru.dreamjteam.xml.binds.Car;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * @author abolmasov (18.10.2011 10:33:55)
 * @version $Revision: 84 $
 */
public class CarServlet extends HttpServlet {
	protected Car getCar(HttpServletRequest req) {
		final Car newCar = new Car();
		final String id = req.getParameter("id");
		newCar.setId(id == null || id.trim().isEmpty() ? 0 : Integer.valueOf(id));
		newCar.setCarModel(req.getParameter("model"));
		newCar.setGovNumber(req.getParameter("gov_number"));
		newCar.setCarTypeId(Integer.valueOf(req.getParameter("ref_type")));
		newCar.setRunning(Integer.valueOf(req.getParameter("running")));
		return newCar;
	}

	protected List<String> validate(final Car car) {
		List<String> result = new LinkedList<String>();
		if (car.getCarTypeId() == 0)
			result.add("Не выбран тип машины");
		if (car.getGovNumber().trim().isEmpty())
			result.add("Введите гос. номер");
		return result;
	}
}
