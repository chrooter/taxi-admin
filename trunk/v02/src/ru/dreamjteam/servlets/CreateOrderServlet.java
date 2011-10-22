package ru.dreamjteam.servlets;

import ru.dreamjteam.db.CarTypeDb;
import ru.dreamjteam.db.DbAccessException;
import ru.dreamjteam.db.DuplicateEntityException;
import ru.dreamjteam.db.OrderDb;
import ru.dreamjteam.xml.binds.CarType;
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
 * @version $Revision: 84 $
 */
public class CreateOrderServlet extends OrderServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createOrder.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final Order order = getOrder(req);
		final List<String> errors = validate(order);
		if (!errors.isEmpty()) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createOrder.jsp");
			req.setAttribute("errors", errors);
			requestDispatcher.forward(req, resp);
		}
		try {
			OrderDb.insert(order);
			resp.sendRedirect(req.getContextPath()+"/ViewOrderListServlet");
		} catch (DuplicateEntityException e) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createOrder.jsp");
			errors.add(e.getLocalizedMessage());
			req.setAttribute("errors", errors);
			requestDispatcher.forward(req, resp);
		} catch (DbAccessException e) {
			throw new ServletException(e);
		}
	}
}
