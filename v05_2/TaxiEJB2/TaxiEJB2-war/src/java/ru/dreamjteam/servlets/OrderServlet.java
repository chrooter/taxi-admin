package ru.dreamjteam.servlets;

import ru.dreamjteam.beans.TaxiBeanEmulator;
import ru.dreamjteam.entity.CarVO;
import ru.dreamjteam.entity.OrderVO;

import javax.ejb.FinderException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;


public class OrderServlet extends HttpServlet {
    
        List<String> result;
    
	protected OrderVO getOrder(HttpServletRequest request) {
                result = new LinkedList<String>();
                int passengers = 0;
                
		final String strId = request.getParameter("id");
		final int id = strId == null || strId.trim().isEmpty() ? 0 : Integer.valueOf(strId);
                
		final String strStartPoint = request.getParameter("startpoint");
                final int startPoint = strStartPoint == null || strStartPoint.trim().isEmpty() ? 0 : Integer.valueOf(strStartPoint);
                if (strStartPoint.trim().isEmpty())
                    result.add("Введите адрес отправления");
                
                final String phone = request.getParameter("phone");
                if (phone.trim().isEmpty())
                    result.add("Введите номер телефона");
                
                final String strPassengers = request.getParameter("passengers");
                if (!strPassengers.matches("[1-9][0-9]*")) 
                    result.add("Значение в поле \"Число пассажиров\" должно быть положительным целым числом");
                else passengers = Integer.valueOf(strPassengers);
                
		final String strCarId = request.getParameter("carid");
		final int carId = strCarId == null || strCarId.trim().isEmpty() ? 0 : Integer.valueOf(strCarId);
                
		final String status = request.getParameter("status");
                
		final String strCost = request.getParameter("cost");
		final int cost = strCost == null || strCost.trim().isEmpty() ? 0 : Integer.valueOf(strCost);
                
		final String strDistance = request.getParameter("distance");
		final int distance = strDistance == null || strDistance.trim().isEmpty() ? 0 : Integer.parseInt(strDistance);
                
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");

		final String strTimeDone = request.getParameter("strTimeDone");
		Timestamp timeDone;
		try {
			timeDone = strTimeDone == null || strTimeDone.trim().isEmpty() ? null : new Timestamp(dateFormatter.parse(strTimeDone).getTime());
		} catch (ParseException e) {
			throw new IllegalStateException(e);
		}
                
		final String strTimeOrd = request.getParameter("strTimeOrd");
		Timestamp timeOrd;
		try {
			timeOrd = strTimeOrd == null || strTimeOrd.trim().isEmpty() ? null : new Timestamp(dateFormatter.parse(strTimeOrd).getTime());
		} catch (ParseException e) {
			throw new IllegalStateException(e);
		}
		return new OrderVO(id, cost, phone, distance, passengers, timeDone, timeOrd, startPoint, carId, status);
	}

	protected void putCarIn2Req(HttpServletRequest req) throws ServletException {
		try {
			final List<CarVO> cars = TaxiBeanEmulator.getCars();
			req.setAttribute("cars", cars);
		} catch (FinderException e) {
			throw new ServletException(e);
		} catch (NamingException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		putCarIn2Req(req);
		super.service(req, resp);
	}
        
        protected List<String> getErrors()
        {
            return result;
        }
        
        protected void clearErrors()
        {
            result = null;
        }
}
