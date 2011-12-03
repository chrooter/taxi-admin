package ru.dreamjteam.servlets;

import javax.ejb.FinderException;
import ru.dreamjteam.beans.TaxiBeanEmulator;
import ru.dreamjteam.entity.OrderVO;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.ejb.RemoveException;
import ru.dreamjteam.entity.PointVO;


public class CreateOrderServlet extends OrderServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createOrder.jsp");
                List<PointVO> chain = getEmptyChain();
                req.setAttribute("chain", chain);
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                try {
                        final OrderVO order = getOrder(req, 0);
                        final List<String> errors = getErrors();
                        final List<PointVO> chain = getChain();
                        if (!errors.isEmpty()) {
                                final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createOrder.jsp");
                                req.setAttribute("errors", errors);
                                req.setAttribute("order", order);
                                req.setAttribute("chain", chain);
                                clearErrors();
                                requestDispatcher.forward(req, resp);
                                return;
                        }
		
			TaxiBeanEmulator.createOrder(order);
			resp.sendRedirect(req.getContextPath() + "/ViewOrderList");
		} catch (FinderException e) {
                        throw new ServletException(e);
                } catch (NamingException e) {
			throw new ServletException(e);
		} catch (CreateException e) {
			throw new ServletException(e);
		} catch (RemoveException e) {
			throw new ServletException(e);
		}
	}
}
