package ru.dreamjteam.servlets;

import change.Change;
import ru.dreamjteam.beans.TaxiBeanEmulator;
import ru.dreamjteam.entity.CarTypeVO;

import javax.ejb.DuplicateKeyException;
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
import javax.jms.JMSException;
import ru.dreamjteam.beans.BeanProvider;
import ru.dreamjteam.entity.CarTypeEntityBeanLocal;
import ru.dreamjteam.entity.CarTypeEntityBeanLocalHome;


public class EditCarTypeServlet extends CarTypeServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String id = req.getParameter("id");
		if (id == null || id.trim().isEmpty()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		try {
			final CarTypeVO carTypeVO = TaxiBeanEmulator.getCarType(Integer.valueOf(id));
			req.setAttribute("carType", carTypeVO);
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editCarType.jsp");
			requestDispatcher.forward(req, resp);
		} catch (ObjectNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (FinderException e) {
			throw new ServletException(e);
		} catch (NamingException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final CarTypeVO carType = getCarType(req);
		final List<String> errors = getErrors();
		if (!errors.isEmpty()) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editCarType.jsp");
			req.setAttribute("errors", errors);
			req.setAttribute("carType", carType);
                        clearErrors();
			requestDispatcher.forward(req, resp);
			return;
		}
		try {
                        CarTypeEntityBeanLocalHome ctlh = BeanProvider.getCarTypeHome();
                        CarTypeEntityBeanLocal ctl;
                        ctl = ctlh.findByPrimaryKey(carType.getId());
                        java.util.Date now = new java.util.Date();
                        SendToJMS.sendJMSMessageToTaxiQueue(new Change(0,"CarType",new Timestamp(now.getTime()),"In carType with id "+ctl.getId()+": "+(ctl.getCostPerKm().equals(carType.getCostPerKm())?"":" costPerKm "+ctl.getCostPerKm()+" changed for "+carType.getCostPerKm())+
                                                                                                                                                  (ctl.getCapacity().equals(carType.getCapacity())?"":" capacity "+ctl.getCapacity()+" changed for "+carType.getCapacity())+
                                                                                                                                                  (ctl.getName().equals(carType.getName())?"":" name "+ctl.getName()+" changed for "+carType.getName()),2));
			TaxiBeanEmulator.updateCarType(carType);
                        
			resp.sendRedirect(req.getContextPath() + "/ViewCarTypeList");
		} catch (DuplicateKeyException e) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editCarType.jsp");
			errors.add(e.getLocalizedMessage());
			req.setAttribute("errors", errors);
			req.setAttribute("carType", carType);
			requestDispatcher.forward(req, resp);
		} catch (ObjectNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (FinderException e) {
			throw new ServletException(e);
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (JMSException e) {
                        
                }
	}
}
