package ru.dreamjteam.servlets;

import ru.dreamjteam.beans.TaxiBeanEmulator;
import ru.dreamjteam.entity.*;

import javax.ejb.FinderException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class XMLCarTypeServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			final List<CarTypeVO> carTypes = TaxiBeanEmulator.getCarTypes();
			request.setAttribute("carTypes", carTypes);
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (FinderException e) {
			throw new ServletException(e);
		}
                
		RequestDispatcher dispatcher = request.getRequestDispatcher("exportCarType.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
