package ru.dreamjteam.servlets;

import ru.dreamjteam.xml.binds.orders.Order;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * @author abolmasov (11.10.2011 16:28:07)
 * @version $Revision$
 */
public class NewOrdersFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		final ArrayList<Order> orders = new ArrayList<Order>();

		Order order = new Order();
		order.setAddrDep("какой-то адрес отправления будет тут");
		order.setAddrDest("какой-то адрес назначения будет тут");
		order.setPassengers(4);
		order.setId(1);
		order.setStatus("Новый");
		Date now = new Date();
		order.setTimeOrd(now.toString());
		order.setTimeDest(now.toString());
		orders.add(order);

		order = new Order();
		order.setAddrDep("какой-то ДРУГОЙ адрес отправления будет тут");
		order.setAddrDest("какой-то ДРУГОЙ адрес назначения будет тут");
		order.setPassengers(2);
		order.setId(2);
		order.setStatus("Новый");
		now = new Date();
		order.setTimeOrd(now.toString());
		order.setTimeDest(now.toString());
		orders.add(order);

		req.setAttribute("newRequests", orders);
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
