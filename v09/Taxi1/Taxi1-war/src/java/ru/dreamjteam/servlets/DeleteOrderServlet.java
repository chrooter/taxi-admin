package ru.dreamjteam.servlets;

import ru.dreamjteam.beans.TaxiBeanEmulator;

import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteOrderServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String id = req.getParameter("id");
                final String startpoint = req.getParameter("startpoint");
		if (id != null && !id.trim().isEmpty() && startpoint != null && !startpoint.trim().isEmpty()) {
			try {
                                TaxiBeanEmulator.deleteOrder(Integer.valueOf(id));
                                TaxiBeanEmulator.deleteChain(Integer.valueOf(startpoint));
			} catch (ObjectNotFoundException e) {
				resp.sendRedirect(req.getContextPath() + "/ViewOrderList");
			} catch (FinderException e) {
				throw new ServletException(e);
			} catch (NamingException e) {
				throw new ServletException(e);
			} catch (RemoveException e) {
				throw new ServletException(e);
			}
		}
		resp.sendRedirect(req.getContextPath() + "/ViewOrderList");
	}
}
