/*
	Copyright 2011 Open Code Ltd.
    http://www.o-code.ru
 
    $Id: CarTypeServlet.java 84 2011-10-18 07:58:24Z graf.abolmasov@gmail.com $
*/
package ru.dreamjteam.servlets;

import ru.dreamjteam.entity.CarTypeVO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * @author abolmasov (18.10.2011 11:12:08)
 * @version $Revision: 84 $
 */
public class CarTypeServlet extends HttpServlet {
	protected CarTypeVO getCarType(HttpServletRequest request) {
		final String strId = request.getParameter("id");
		final int id = strId == null || strId.trim().isEmpty() ? 0 : Integer.valueOf(strId);
		final String costperkm = request.getParameter("costperkm");
		final Integer costPerKm = costperkm == null || costperkm.trim().isEmpty() ? 0 : Integer.valueOf(costperkm);
		final String name = request.getParameter("name");
		final String seatcap = request.getParameter("seatcap");
		final Integer seatCapacity = seatcap == null ||seatcap.trim().isEmpty() ? 0 : Integer.valueOf(seatcap);
		return new CarTypeVO(id, seatCapacity, costPerKm, name);
	}

	protected List<String> validate(final CarTypeVO carType) {
		List<String> result = new LinkedList<String>();
		if (carType.getCostPerKm() <= 0)
			result.add("Введите стоимость за км больше нуля");
		if (carType.getName() == null || carType.getName().trim().isEmpty())
			result.add("Введите название типа");
		return result;
	}
}
