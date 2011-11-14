package ru.dreamjteam.servlets;

import ru.dreamjteam.TaxiBeanEmulator;
import ru.dreamjteam.entity.CarVO;

import javax.ejb.DuplicateKeyException;
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
public class EditCarServlet extends CarServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String id = req.getParameter("id");
		if (id == null || id.trim().isEmpty()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		putCarTypesIn2req(req);
		try {
			final CarVO car = TaxiBeanEmulator.getCar(Integer.valueOf(id));
			req.setAttribute("car", car);
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editCar.jsp");
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
		final CarVO car = getCar(req);
		final List<String> errors = validate(car);
		putCarTypesIn2req(req);
		if (!errors.isEmpty()) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editCar.jsp");
			req.setAttribute("errors", errors);
			req.setAttribute("car", car);
			requestDispatcher.forward(req, resp);
			return;
		}
		try {
			TaxiBeanEmulator.updateCar(car);
			resp.sendRedirect(req.getContextPath() + "/ViewCarList");
		} catch (DuplicateKeyException e) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editCar.jsp");
			errors.add(e.getLocalizedMessage());
			req.setAttribute("errors", errors);
			req.setAttribute("car", car);
			requestDispatcher.forward(req, resp);
		} catch (ObjectNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (FinderException e) {
			throw new ServletException(e);
		} catch (NamingException e) {
			throw new ServletException(e); 
		}
	}
}
