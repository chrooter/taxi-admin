package ru.dreamjteam.servlets;

import ru.dreamjteam.BeanProvider;
import ru.dreamjteam.TaxiBeanEmulator;
import ru.dreamjteam.entity.CarVO;
import ru.dreamjteam.entity.LocalCarEntity;
import ru.dreamjteam.entity.LocalCarEntityHome;

import javax.ejb.FinderException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ViewCarListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			final List<CarVO> cars = TaxiBeanEmulator.getCars();
			request.setAttribute("cars", cars);
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (FinderException e) {
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("cars.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
