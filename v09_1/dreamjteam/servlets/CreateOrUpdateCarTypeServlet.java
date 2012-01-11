package ru.dreamjteam.servlets;

import ru.dreamjteam.beans.TaxiBeanEmulator;
import ru.dreamjteam.entity.CarTypeVO;

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


public class CreateOrUpdateCarTypeServlet extends CarTypeServlet {        
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String xmlString = (String) req.getSession().getAttribute("xmlString");
                List<CarTypeVO> carTypes = null;
		try {
                        carTypes = TaxiBeanEmulator.parseXML(xmlString);
                        if (req.getParameter("update")!=null)
                        {
                            if (req.getParameter("update").equals("true"))
                                TaxiBeanEmulator.createOrUpdateCarTypes(carTypes, true);
                            if (req.getParameter("update").equals("false"))
                                TaxiBeanEmulator.createOrUpdateCarTypes(carTypes, false);
                        }
                        else TaxiBeanEmulator.createCarTypes(carTypes);
			resp.sendRedirect(req.getContextPath() + "/ViewCarTypeList");
		} catch (DuplicateKeyException e) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/importCarType.jsp");
			String error = "exist";
			req.setAttribute("error", error);
			req.setAttribute("xmlString", xmlString);
			requestDispatcher.forward(req, resp);
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (CreateException e) {
			throw new ServletException(e);
		} catch (FinderException e) {
			throw new ServletException(e);
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
