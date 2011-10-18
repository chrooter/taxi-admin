package ru.dreamjteam.servlets;

import ru.dreamjteam.db.CarDb;
import ru.dreamjteam.db.DbAccessException;
import ru.dreamjteam.db.DuplicateEntityException;
import ru.dreamjteam.xml.binds.Car;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author abolmasov (11.10.2011 10:16:13)
 * @version $Revision$
 */
public class CreateCarServlet extends CarServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createCar.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final Car newCar = getCar(req);
		final List<String> errors = validate(newCar);
		if (!errors.isEmpty()) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createCar.jsp");
			req.setAttribute("errors", errors);
			requestDispatcher.forward(req, resp);
		}
		try {
			CarDb.insert(newCar);
			resp.sendRedirect("/cars.jsp");
		} catch (DuplicateEntityException e) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createCar.jsp");
			errors.add(e.getLocalizedMessage());
			req.setAttribute("errors", errors);
			requestDispatcher.forward(req, resp);
		} catch (DbAccessException e) {
			throw new ServletException(e); 
		}
	}
}
