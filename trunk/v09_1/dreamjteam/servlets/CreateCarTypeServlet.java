package ru.dreamjteam.servlets;

import change.Change;
import ru.dreamjteam.beans.TaxiBeanEmulator;
import ru.dreamjteam.entity.CarTypeVO;

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
import ru.dreamjteam.entity.CarTypeEntityBeanLocal;
import ru.dreamjteam.entity.CarTypeEntityBeanLocalHome;


public class CreateCarTypeServlet extends CarTypeServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createCarType.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final CarTypeVO carType = getCarType(req);
		final List<String> errors = getErrors();
		if (!errors.isEmpty()) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createCarType.jsp");
			req.setAttribute("errors", errors);
			req.setAttribute("carType", carType);
                        clearErrors();
			requestDispatcher.forward(req, resp);
			return;
		}
		try {
			TaxiBeanEmulator.createCarType(carType);
                        CarTypeEntityBeanLocalHome ctlh = BeanProvider.getCarTypeHome();
                        CarTypeEntityBeanLocal ctl;
                        ctl = ctlh.findByLast();
                         java.util.Date now = new java.util.Date();
                        SendToJMS.sendJMSMessageToTaxiQueue(new Change(0,"CarType",new Timestamp(now.getTime()),"CarType with id "+ctl.getId()+" created",1));
			resp.sendRedirect(req.getContextPath() + "/ViewCarTypeList");
		} catch (DuplicateKeyException e) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createCarType.jsp");
			errors.add(e.getLocalizedMessage());
			req.setAttribute("errors", errors);
			req.setAttribute("carType", carType);
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
