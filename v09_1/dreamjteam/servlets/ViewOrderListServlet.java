package ru.dreamjteam.servlets;

import ru.dreamjteam.beans.TaxiBeanEmulator;
import ru.dreamjteam.entity.OrderVO;

import javax.ejb.FinderException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewOrderListServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
                    String field = (String)request.getParameter("field");
                    String type = (String)request.getParameter("type");
                     if (type == null)
                           type = "asc";
                     else type = (type.equals("asc") ? "desc": "asc");
                    request.setAttribute("type", type);
			final List<OrderVO> orders = TaxiBeanEmulator.getOrders(field,type,false);
			request.setAttribute("orders", orders);
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (FinderException e) {
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("orders.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
