package ru.dreamjteam.servlets;

import ru.dreamjteam.TaxiBeanEmulator;
import ru.dreamjteam.entity.*;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
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
public class CreateCarServlet extends CarServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createCar.jsp");
		putCarTypesIn2req(req);
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final CarVO car = getCar(req);
		final List<String> errors = validate(car);
		putCarTypesIn2req(req);
		if (!errors.isEmpty()) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createCar.jsp");
			req.setAttribute("errors", errors);
			req.setAttribute("car", car);
			requestDispatcher.forward(req, resp);
			return;
		}
		try {
			TaxiBeanEmulator.createCar(car);
			resp.sendRedirect(req.getContextPath() + "/ViewCarList");
		} catch (DuplicateKeyException e) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createCar.jsp");
			errors.add(e.getLocalizedMessage());
			req.setAttribute("errors", errors);
			req.setAttribute("car", car);
			requestDispatcher.forward(req, resp);
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (CreateException e) {
			throw new ServletException(e);
		}
	}
}
