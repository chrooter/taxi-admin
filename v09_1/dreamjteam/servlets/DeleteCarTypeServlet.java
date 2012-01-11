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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import javax.jms.JMSException;
import javax.servlet.RequestDispatcher;


public class DeleteCarTypeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String id = req.getParameter("id");
		if (id != null && !id.trim().isEmpty()) {
			try {
				TaxiBeanEmulator.deleteCarType(Integer.valueOf(id));
                                java.util.Date now = new java.util.Date();
                                SendToJMS.sendJMSMessageToTaxiQueue(new Change(0,"CarType",new Timestamp(now.getTime()),"CarType with id "+id+" deleted",3));
			} catch (ObjectNotFoundException e) {
				resp.sendRedirect(req.getContextPath() + "/ViewOrderList");
                                return;
			} catch (SQLIntegrityConstraintViolationException e) {
                                final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/ViewCarTypeList");
                                String error = e.getLocalizedMessage();
                                req.setAttribute("error", error);
                                requestDispatcher.forward(req, resp);
                                return;
                        } catch (FinderException e) {
				throw new ServletException(e);
			} catch (NamingException e) {
				throw new ServletException(e);
			} catch (RemoveException e) {
				throw new ServletException(e);
			} catch (JMSException e) {

                }
		}
		resp.sendRedirect(req.getContextPath() + "/ViewCarTypeList");
                
	}
}
