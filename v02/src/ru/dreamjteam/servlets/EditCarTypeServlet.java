package ru.dreamjteam.servlets;

import ru.dreamjteam.db.*;
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
public class EditCarTypeServlet extends CarTypeServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String id = req.getParameter("id");
		if (id == null || id.trim().isEmpty()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		try {
			final String xml = CarTypeDb.select(Integer.valueOf(id));
			CarTypes carTypes = XMLParser.parseXML(xml, CarTypes.class);
			req.setAttribute("carType", carTypes.getCarType().get(0));
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editType.jsp");
			requestDispatcher.forward(req, resp);
		} catch (EntityNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (DbAccessException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final CarType carType = getCarType(req);
		final List<String> errors = validate(carType);
		if (!errors.isEmpty()) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editType.jsp");
			req.setAttribute("errors", errors);
			requestDispatcher.forward(req, resp);
		}
		try {
			CarTypeDb.update(carType);
			resp.sendRedirect(req.getContextPath()+"/ViewCarTypeListServlet");
		} catch (DuplicateEntityException e) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editType.jsp");
			errors.add(e.getLocalizedMessage());
			req.setAttribute("errors", errors);
			requestDispatcher.forward(req, resp);
		} catch (EntityNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}catch (DbAccessException e) {
			throw new ServletException(e);
		}
	}
}
