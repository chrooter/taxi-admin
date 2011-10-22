/*
	Copyright 2011 Open Code Ltd.
    http://www.o-code.ru
 
    $Id: OrderServlet.java 84 2011-10-18 07:58:24Z graf.abolmasov@gmail.com $
*/
package ru.dreamjteam.servlets;

import ru.dreamjteam.xml.binds.Car;
import ru.dreamjteam.xml.binds.Order;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.beans.Beans;
import java.util.LinkedList;
import java.util.List;

/**
 * @author abolmasov (18.10.2011 11:24:17)
 * @version $Revision: 84 $
 */
public class OrderServlet extends HttpServlet {
	protected Order getOrder(HttpServletRequest request) {
		final Order newOrder = new Order();
                final String id = request.getParameter("id");
                newOrder.setId(id == null || id.trim().isEmpty() ? 0 : Integer.valueOf(id));
		newOrder.setAddrDep(request.getParameter("ADDR_DEP"));
		newOrder.setAddrDest(request.getParameter("ADDR_DEST"));
		final String carid = request.getParameter("carid");
		newOrder.setCarId(carid == null || carid.trim().isEmpty() ? 0 : Integer.valueOf(carid));
		final String cost = request.getParameter("COST");
		newOrder.setCost(cost == null || cost.trim().isEmpty() ? 0 : Integer.valueOf(cost));
		final String distinfact = request.getParameter("DIST_INFACT");
		newOrder.setDistInfact(distinfact == null || distinfact.trim().isEmpty() ? 0 : Integer.parseInt(distinfact));
		final String passengers = request.getParameter("PASSENGER");
		newOrder.setPassengers(passengers == null || passengers.trim().isEmpty() ? 0 : Integer.parseInt(passengers));
		newOrder.setStatus(request.getParameter("STATUS"));
		newOrder.setTimeDest(request.getParameter("TIME_DEST"));
		newOrder.setTimeOrd(request.getParameter("TIME_ORD"));
		return newOrder;
	}

	protected List<String> validate(Order order) {
		List<String> result = new LinkedList<String>();
		if (order.getAddrDep() == null || order.getAddrDep().trim().isEmpty())
			result.add("Введите адрес отправления");
		if (order.getAddrDest() == null || order.getAddrDest().trim().isEmpty())
			result.add("Введите адрес прибытия");
		return result;
	}
}
