package ru.dreamjteam.servlets;

import change.Change;
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
import java.sql.Timestamp;
import javax.jms.JMSException;


public class DeleteOrderServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String id = req.getParameter("id");
                final String startpoint = req.getParameter("startpoint");
		if (id != null && !id.trim().isEmpty() && startpoint != null && !startpoint.trim().isEmpty()) {
			try {
                                TaxiBeanEmulator.deleteOrder(Integer.valueOf(id));
                                java.util.Date now = new java.util.Date();
                                SendToJMS.sendJMSMessageToTaxiQueue(new Change(0,"Order",new Timestamp(now.getTime()),"Order with id "+id+" deleted",3));
                                TaxiBeanEmulator.deleteChain(Integer.valueOf(startpoint));
			} catch (ObjectNotFoundException e) {
				resp.sendRedirect(req.getContextPath() + "/ViewOrderList");
			} catch (FinderException e) {
				throw new ServletException(e);
			} catch (NamingException e) {
				throw new ServletException(e);
			} catch (RemoveException e) {
				throw new ServletException(e);
			} catch (JMSException e) {

                }
		}
		resp.sendRedirect(req.getContextPath() + "/ViewOrderList");
	}
}
