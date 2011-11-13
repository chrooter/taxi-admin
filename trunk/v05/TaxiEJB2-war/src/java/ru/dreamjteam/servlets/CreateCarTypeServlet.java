package ru.dreamjteam.servlets;

import ru.dreamjteam.TaxiBeanEmulator;
import ru.dreamjteam.entity.CarTypeVO;
import ru.dreamjteam.entity.LocalCarTypeEntityHome;

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
public class CreateCarTypeServlet extends CarTypeServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createCarType.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final CarTypeVO carType = getCarType(req);
		final List<String> errors = validate(carType);
		if (!errors.isEmpty()) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createCarType.jsp");
			req.setAttribute("errors", errors);
			req.setAttribute("carType", carType);
			requestDispatcher.forward(req, resp);
			return;
		}
		try {
			TaxiBeanEmulator.createCarType(carType);			
			resp.sendRedirect(req.getContextPath() + "/ViewCarTypeList");
		} catch (DuplicateKeyException e) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createCarType.jsp");
			errors.add(e.getLocalizedMessage());
			req.setAttribute("errors", errors);
			req.setAttribute("carType", carType);
			requestDispatcher.forward(req, resp);
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (CreateException e) {
			throw new ServletException(e);
		}
	}
}
