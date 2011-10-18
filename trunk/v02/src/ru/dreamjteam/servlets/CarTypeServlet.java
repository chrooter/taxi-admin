/*
	Copyright 2011 Open Code Ltd.
    http://www.o-code.ru
 
    $Id$
*/
package ru.dreamjteam.servlets;

import ru.dreamjteam.xml.binds.CarType;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * @author abolmasov (18.10.2011 11:12:08)
 * @version $Revision$
 */
public class CarTypeServlet extends HttpServlet {
	protected CarType getCarType(HttpServletRequest request) {
		final CarType newCarType = new CarType();
		final String id = request.getParameter("id");
		newCarType.setId(id == null || id.trim().isEmpty() ? 0 : Integer.valueOf(id));
		newCarType.setCostPerKm(Integer.valueOf(request.getParameter("costperkm")));
		newCarType.setMassCap(Integer.valueOf(request.getParameter("masscap")));
		newCarType.setSeatCap(Integer.valueOf(request.getParameter("seatcap")));
		return newCarType;
	}

	protected List<String> validate(final CarType carType) {
		List<String> result = new LinkedList<String>();
		if (carType.getCostPerKm() <= 0)
			result.add("Введите стоимость за км больше нуля");
		if (carType.getMassCap() <= 0 || carType.getSeatCap() <= 0)
			result.add("Введите вместимость больше нуля");
		return result;
	}
}
