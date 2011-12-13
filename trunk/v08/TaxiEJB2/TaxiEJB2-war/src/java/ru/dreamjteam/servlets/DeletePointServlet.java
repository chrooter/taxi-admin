package ru.dreamjteam.servlets;

import javax.ejb.FinderException;
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


public class DeletePointServlet extends OrderServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                try {
                        final OrderVO order = getOrder(req, 2);
                        final List<PointVO> chain = getChain();
		
			RequestDispatcher requestDispatcher = null;
                        if (req.getParameter("from").equals("create"))
			requestDispatcher = req.getRequestDispatcher("/createOrder.jsp");
                        if (req.getParameter("from").equals("edit"))
			requestDispatcher = req.getRequestDispatcher("/editOrder.jsp");
                                req.setAttribute("order", order);
                                req.setAttribute("chain", chain);
                                requestDispatcher.forward(req, resp);
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
