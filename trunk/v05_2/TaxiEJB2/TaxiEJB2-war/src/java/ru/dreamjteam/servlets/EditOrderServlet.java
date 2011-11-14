package ru.dreamjteam.servlets;

import ru.dreamjteam.TaxiBeanEmulator;
import ru.dreamjteam.entity.*;

import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author abolmasov (11.10.2011 10:16:13)
 * @version $Revision: 84 $
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
			final OrderVO order = TaxiBeanEmulator.getOrder(Integer.valueOf(id));
            order.setCompleted(Boolean.valueOf(((String) req.getParameter("completed"))));
			req.setAttribute("order", order);
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editOrder.jsp");
			requestDispatcher.forward(req, resp);
		} catch (ObjectNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (FinderException e) {
			throw new ServletException(e);
		} catch (NamingException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final OrderVO order = getOrder(req);
		final List<String> errors = validate(order);
		if (!errors.isEmpty()) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editOrder.jsp");
			req.setAttribute("errors", errors);
            req.setAttribute("order", order);
			requestDispatcher.forward(req, resp);
			return;
		}
		try {
			TaxiBeanEmulator.updateOrder(order);
			resp.sendRedirect(req.getContextPath() + "/ViewOrderList");
		} catch (ObjectNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (FinderException e) {
			throw new ServletException(e);
		} catch (NamingException e) {
			throw new ServletException(e);
		}
	}
}
