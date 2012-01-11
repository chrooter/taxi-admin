package ru.dreamjteam.servlets;

import ru.dreamjteam.beans.TaxiBeanEmulator;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.ejb.FinderException;
import ru.dreamjteam.entity.CarVO;
import ru.dreamjteam.exceptions.ImportException;


public class CreateOrUpdateCarServlet extends CarTypeServlet {        
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String xmlString = (String) req.getSession().getAttribute("xmlString");
                List<CarVO> cars = null;
		try {

                        cars = TaxiBeanEmulator.parseXMLCars(xmlString);
                        if (req.getParameter("update")!=null)
                        {
                            if (req.getParameter("update").equals("true"))
                                TaxiBeanEmulator.createOrUpdateCars(cars, true);
                            if (req.getParameter("update").equals("false"))
                                TaxiBeanEmulator.createOrUpdateCars(cars, false);
                        }
                        else TaxiBeanEmulator.createCars(cars);
                        
			resp.sendRedirect(req.getContextPath() + "/ViewCarList");
		} catch (DuplicateKeyException e) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/importCar.jsp");
			String error = e.getMessage();
                        
			req.setAttribute("error", error);
                        
			req.setAttribute("xmlString", xmlString);
			requestDispatcher.forward(req, resp);
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (CreateException e) {
			throw new ServletException(e);
		} catch (FinderException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
