/*
	Copyright 2011 Open Code Ltd.
    http://www.o-code.ru
 
    $Id$
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
 * @version $Revision$
 */
public class OrderServlet extends HttpServlet {
	protected Order getOrder(HttpServletRequest request) {
		final Order newOrder = new Order();
		final String id = request.getParameter("id");
		newOrder.setId(id == null || id.trim().isEmpty() ? 0 : Integer.valueOf(id));
		newOrder.setAddrDep(request.getParameter("addrdep"));
		newOrder.setAddrDest(request.getParameter("addrdest"));
		final String carid = request.getParameter("carid");
		newOrder.setCarId(carid == null || carid.trim().isEmpty() ? 0 : Integer.valueOf(carid));
		final String cost = request.getParameter("cost");
		newOrder.setCost(cost == null || cost.trim().isEmpty() ? 0 : Integer.valueOf(cost));
		final String distinfact = request.getParameter("distinfact");
		newOrder.setDistInfact(distinfact == null || distinfact.trim().isEmpty() ? 0 : Integer.parseInt(distinfact));
		final String passengers = request.getParameter("passengers");
		newOrder.setPassengers(passengers == null || passengers.trim().isEmpty() ? 0 : Integer.parseInt(passengers));
		newOrder.setStatus(request.getParameter("status"));
		newOrder.setTimeDest(request.getParameter("timedest"));
		newOrder.setTimeOrd(request.getParameter("timeord"));
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
