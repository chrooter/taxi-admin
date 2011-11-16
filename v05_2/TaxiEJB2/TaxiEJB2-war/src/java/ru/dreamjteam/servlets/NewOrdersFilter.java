package ru.dreamjteam.servlets;

import ru.dreamjteam.beans.TaxiBeanEmulator;
import ru.dreamjteam.entity.CarVO;
import ru.dreamjteam.entity.OrderVO;

import javax.ejb.FinderException;
import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class NewOrdersFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		try {
			final List<CarVO> cars = TaxiBeanEmulator.getCars();
			final List<OrderVO> orders = new LinkedList<OrderVO>();
			for (CarVO car : cars)
				orders.addAll(car.getCurrentOrderVOs());
			req.setAttribute("newOrders", orders);
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (FinderException e) {
			throw new ServletException(e);
		}
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {
	}

}
