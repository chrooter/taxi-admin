package ru.dreamjteam.servlets;

import change.Change;
import javax.ejb.CreateException;
import ru.dreamjteam.beans.TaxiBeanEmulator;
import ru.dreamjteam.entity.OrderVO;

import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.RemoveException;
import javax.jms.JMSException;
import ru.dreamjteam.beans.BeanProvider;
import ru.dreamjteam.entity.OrderEntityBeanLocal;
import ru.dreamjteam.entity.OrderEntityBeanLocalHome;
import ru.dreamjteam.entity.PointVO;


public class EditOrderServlet extends OrderServlet {
		@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String id = req.getParameter("id");

		if (id == null || id.trim().isEmpty()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		try {
                        OrderEntityBeanLocalHome olh = BeanProvider.getOrderHome();
                        OrderEntityBeanLocal ol;
			final OrderVO order = TaxiBeanEmulator.getOrder(Integer.valueOf(id));
                        final List<PointVO> chain = TaxiBeanEmulator.getChain(order.getStartPoint());
                        final String status = req.getParameter("status");
                        if (status != null)
                        {
                            order.setStatus(status);
                            if (status.equals("canceled"))
                            {
                                ol = olh.findByPrimaryKey(order.getId());
                                java.util.Date now = new java.util.Date();
                                SendToJMS.sendJMSMessageToTaxiQueue(new Change(0,"Order",new Timestamp(now.getTime()),"In order with id "+ol.getId()+ ": status "+ol.getStatus()+" changed for "+order.getStatus(),2));
                                TaxiBeanEmulator.updateOrder(order);
                                
                                resp.sendRedirect(req.getContextPath() + "/ViewOrderList");
                                return;
                            }
                        }
                        req.setAttribute("order", order);
                        req.setAttribute("chain", chain);
                        final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editOrder.jsp");
			requestDispatcher.forward(req, resp);
		} catch (ObjectNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (FinderException e) {
			throw new ServletException(e);
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (JMSException e) {
                        throw new EJBException(e);
                } 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                try {
                        OrderEntityBeanLocalHome olh = BeanProvider.getOrderHome();
                        OrderEntityBeanLocal ol;
                        final OrderVO order = getOrder(req, 0);
                        final List<String> errors = getErrors();
                        final List<PointVO> chain = getChain();
                        if (!errors.isEmpty()) {
                                final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editOrder.jsp");
                                req.setAttribute("errors", errors);
                                req.setAttribute("order", order);
                                req.setAttribute("chain", chain);
                                clearErrors();
                                requestDispatcher.forward(req, resp);
                                return;
                        }
		        ol = olh.findByPrimaryKey(order.getId());
                        java.util.Date now = new java.util.Date();
                        SendToJMS.sendJMSMessageToTaxiQueue(new Change(0,"Order",new Timestamp(now.getTime()),"In order with id "+ol.getId()+": changed all fields",2));
			TaxiBeanEmulator.updateOrder(order);
                        
			resp.sendRedirect(req.getContextPath() + "/ViewOrderList");
		} catch (CreateException e) {
                        throw new ServletException(e);
                } catch (ObjectNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (FinderException e) {
			throw new ServletException(e);
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (RemoveException e) {
			throw new ServletException(e);
		} catch (JMSException e) {
                        throw new EJBException(e);
                }
	}
}
