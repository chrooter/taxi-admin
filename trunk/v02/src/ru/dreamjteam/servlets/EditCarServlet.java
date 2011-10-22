package ru.dreamjteam.servlets;

import ru.dreamjteam.db.CarDb;
import ru.dreamjteam.db.DbAccessException;
import ru.dreamjteam.db.DuplicateEntityException;
import ru.dreamjteam.db.EntityNotFoundException;
import ru.dreamjteam.xml.XMLParser;
import ru.dreamjteam.xml.binds.*;

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
		try {
			final String xml = CarDb.select(Integer.valueOf(id));
			final Cars cars = XMLParser.parseXML(xml, Cars.class);
			req.setAttribute("car", cars.getCar().get(0));
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editCar.jsp");
			requestDispatcher.forward(req, resp);
		} catch (EntityNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (DbAccessException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final Car car = getCar(req);
		final List<String> errors = validate(car);
		if (!errors.isEmpty()) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editCar.jsp");
			req.setAttribute("errors", errors);
			requestDispatcher.forward(req, resp);
		}
		try {
			CarDb.update(car);
			resp.sendRedirect(req.getContextPath()+"/ViewCarListServlet");
		} catch (DuplicateEntityException e) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editCar.jsp");
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
