package ru.dreamjteam.servlets;

import change.Change;
import ru.dreamjteam.beans.TaxiBeanEmulator;
import ru.dreamjteam.entity.CarVO;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.FinderException;
import javax.jms.JMSException;
import ru.dreamjteam.beans.BeanProvider;
import ru.dreamjteam.entity.CarEntityBeanLocal;
import ru.dreamjteam.entity.CarEntityBeanLocalHome;


public class CreateCarServlet extends CarServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createCar.jsp");
		putCarTypesIn2req(req);
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final CarVO car = getCar(req);
		final List<String> errors = validate(car);
		putCarTypesIn2req(req);
		if (!errors.isEmpty()) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createCar.jsp");
			req.setAttribute("errors", errors);
			req.setAttribute("car", car);
			requestDispatcher.forward(req, resp);
			return;
		}
		try {
			TaxiBeanEmulator.createCar(car);
                        CarEntityBeanLocalHome clh = BeanProvider.getCarHome();
                        CarEntityBeanLocal cl;
                        cl = clh.findByLast();
                        java.util.Date now = new java.util.Date();
                        SendToJMS.sendJMSMessageToTaxiQueue(new Change(0,"Car",new Timestamp(now.getTime()),"Car with id "+cl.getId()+" created",1));
			resp.sendRedirect(req.getContextPath() + "/ViewCarList");
		} catch (DuplicateKeyException e) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createCar.jsp");
			errors.add(e.getLocalizedMessage());
			req.setAttribute("errors", errors);
			req.setAttribute("car", car);
			requestDispatcher.forward(req, resp);
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (CreateException e) {
			throw new ServletException(e);
		} catch (FinderException e) {
                        throw new ServletException(e);
                } catch (JMSException e) {

                }
	}
}
