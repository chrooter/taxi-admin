package ru.dreamjteam.servlets;

import change.Change;
import ru.dreamjteam.beans.TaxiBeanEmulator;
import ru.dreamjteam.entity.CarVO;

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
import ru.dreamjteam.entity.CarEntityBeanLocal;
import ru.dreamjteam.entity.CarEntityBeanLocalHome;


public class EditCarServlet extends CarServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String id = req.getParameter("id");
		if (id == null || id.trim().isEmpty()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		putCarTypesIn2req(req);
		try {
			final CarVO car = TaxiBeanEmulator.getCar(Integer.valueOf(id));
			req.setAttribute("car", car);
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editCar.jsp");
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
		final CarVO car = getCar(req);
		final List<String> errors = validate(car);
		putCarTypesIn2req(req);
		if (!errors.isEmpty()) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editCar.jsp");
			req.setAttribute("errors", errors);
			req.setAttribute("car", car);
			requestDispatcher.forward(req, resp);
			return;
		}
		try {
                        CarEntityBeanLocalHome clh = BeanProvider.getCarHome();;
                        CarEntityBeanLocal cl;
                        cl = clh.findByPrimaryKey(car.getId());
                        java.util.Date now = new java.util.Date();
                        SendToJMS.sendJMSMessageToTaxiQueue(new Change(0,"Car",new Timestamp(now.getTime()),"In car with id "+ cl.getId()+ ": "+(cl.getColor().equals(car.getColor())?"":" color "+cl.getColor()+" changed for "+car.getColor())+
                                                                                                                                           (cl.getGovNumber().equals(car.getGovNumber())?"":" govNumber "+cl.getGovNumber()+" changed for "+car.getGovNumber())+
                                                                                                                                           (cl.getModel().equals(car.getModel())?"":" model "+cl.getModel()+" changed for "+car.getModel()),2));

			TaxiBeanEmulator.updateCar(car);
                        

			resp.sendRedirect(req.getContextPath() + "/ViewCarList");
		} catch (DuplicateKeyException e) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editCar.jsp");
			errors.add(e.getLocalizedMessage());
			req.setAttribute("errors", errors);
			req.setAttribute("car", car);
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
