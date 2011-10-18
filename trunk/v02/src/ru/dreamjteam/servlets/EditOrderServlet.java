package ru.dreamjteam.servlets;

import ru.dreamjteam.db.*;
import ru.dreamjteam.xml.XMLParser;
import ru.dreamjteam.xml.binds.Car;
import ru.dreamjteam.xml.binds.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author abolmasov (11.10.2011 10:16:13)
 * @version $Revision$
 */
public class EditOrderServlet extends OrderServlet {
		@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String id = req.getParameter("id");
		if (id == null || id.trim().isEmpty()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		try {
			final String xml = OrderDb.select(Integer.valueOf(id));
			final Order order = XMLParser.parseXML(xml, Order.class);
			req.setAttribute("order", order);
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editOrder.jsp");
			requestDispatcher.forward(req, resp);
		} catch (EntityNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (DbAccessException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final Order order = getOrder(req);
		final List<String> errors = validate(order);
		if (!errors.isEmpty()) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editOrder.jsp");
			req.setAttribute("errors", errors);
			requestDispatcher.forward(req, resp);
		}
		try {
			OrderDb.update(order);
			resp.sendRedirect("/orders.jsp");
		} catch (DuplicateEntityException e) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editOrder.jsp");
			errors.add(e.getLocalizedMessage());
			req.setAttribute("errors", errors);
			requestDispatcher.forward(req, resp);
		} catch (EntityNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (DbAccessException e) {
			throw new ServletException(e);
		}
	}
}
