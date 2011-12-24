package ru.dreamjteam.servlets;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
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
import ru.dreamjteam.entity.PointVO;


public class OrderServlet extends HttpServlet {
    
        List<String> errors;
        LinkedList<PointVO> chain;
    
	protected OrderVO getOrder(HttpServletRequest request, int checker) throws CreateException, NamingException, FinderException, RemoveException {
                errors = new LinkedList<String>();
                chain = new LinkedList<PointVO>();
                int passengers = 0;
                int distance = 0;
                Timestamp timeDone = null;
                
		final String strId = request.getParameter("id");
		final int id = strId == null || strId.trim().isEmpty() ? 0 : Integer.valueOf(strId);
                
                final String strStartPointId = request.getParameter("startpointid");
		int startPointId = strStartPointId == null || strStartPointId.trim().isEmpty() ? 0 : Integer.valueOf(strStartPointId);
                
                int i=1;
                String point = request.getParameter("point"+i);
                while (point!=null)
                {
                    if (point.trim().isEmpty() && checker==0)
                        errors.add("Введите адрес " + i);
                    chain.add(new PointVO(0, point, 0));
                    i++;
                    point = request.getParameter("point"+i);
                }
                
                if (checker==1)
                    chain.add(new PointVO(0, "", 0));
                if (checker==2)
                    chain.removeLast();
                
                final String phone = request.getParameter("phone");
                if (phone.trim().isEmpty() && checker==0)
                    errors.add("Введите номер телефона");
                
                final String strPassengers = request.getParameter("passengers");
                if (!strPassengers.matches("[1-9][0-9]*")) 
                    errors.add("Значение в поле \"Число пассажиров\" должно быть положительным целым числом");
                else passengers = Integer.valueOf(strPassengers);
                
                final String status = request.getParameter("status");
                
		final String strCarId = request.getParameter("carid");
                final int carId = strCarId == null || strCarId.trim().isEmpty() ? 0 : Integer.valueOf(strCarId);
		if (status!=null && checker==0)
                    if (!status.equals("new") && carId == 0)
                        errors.add("Выберите автомобиль");
                
                final String strDistance = request.getParameter("distance");
                if (status!=null)
                    if (status.equals("done") && !strDistance.matches("[1-9][0-9]*"))
                        errors.add("Значение в поле \"Расстояние поездки\" должно быть положительным целым числом");
                    else distance = Integer.parseInt(strDistance);
                
		final String strCost = request.getParameter("cost");
		int cost = strCost == null || strCost.trim().isEmpty() ? 0 : Integer.valueOf(strCost);
                if (status!=null)
                    if (status.equals("done") && strDistance.matches("[1-9][0-9]*"))
                    {
                        final String strCarTypeId = request.getParameter("carTypeId");
                        int carTypeId = strCarTypeId == null || strCarTypeId.trim().isEmpty() ? 0 : Integer.valueOf(strCarTypeId);
                        int costPerKm = TaxiBeanEmulator.getCostPerKm(carTypeId);
                        cost = distance*costPerKm;
                    }
                        
                
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");

		final String strTimeOrd = request.getParameter("timeord");
		Timestamp timeOrd;
		try {
			timeOrd = strTimeOrd == null || strTimeOrd.trim().isEmpty() ? null : new Timestamp(dateFormatter.parse(strTimeOrd).getTime());
		} catch (ParseException e) {
			throw new IllegalStateException(e);
		}
                
		final String strTimeDone = request.getParameter("timedone");
                if (!strTimeDone.trim().isEmpty())
                    try {
                            timeDone = new Timestamp(dateFormatter.parse(strTimeDone).getTime());
                    } catch (ParseException e) {
                            throw new IllegalStateException(e);
                    }
                
                if (status!=null)
                    if (status.equals("done") && strTimeDone.trim().isEmpty())
                {
                    timeDone = new Timestamp((new java.util.Date()).getTime());
                }
                
                if (errors.isEmpty() && checker==0)
                    if (startPointId==0) startPointId=TaxiBeanEmulator.createChain(chain);
                    else TaxiBeanEmulator.updateChain(startPointId, chain);
                
		return new OrderVO(id, cost, phone, distance, passengers, timeDone, timeOrd, startPointId, carId, status);
	}

	protected void putCarIn2Req(HttpServletRequest req) throws ServletException {
		try {
			final List<CarVO> cars = TaxiBeanEmulator.getCars(null,null);
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
            return errors;
        }
        
        protected void clearErrors()
        {
            errors = null;
        }
        
        protected List<PointVO> getChain()
        {
            return chain;
        }
        
        protected List<PointVO> getEmptyChain()
        {
                final List<PointVO> chain = new LinkedList<PointVO>();
                chain.add(new PointVO(0, "", 0));
                chain.add(new PointVO(0, "", 0));
                return chain;
        }
}
