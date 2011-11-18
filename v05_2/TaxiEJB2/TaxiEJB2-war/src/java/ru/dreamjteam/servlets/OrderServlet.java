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
	protected OrderVO getOrder(HttpServletRequest request) {
		final String strId = request.getParameter("id");
		final int id = strId == null || strId.trim().isEmpty() ? 0 : Integer.valueOf(strId);
		final String strStartPoint = request.getParameter("startpoint");
                final int startPoint = strStartPoint == null || strId.trim().isEmpty() ? 0 : Integer.valueOf(strStartPoint);
		final String strCarId = request.getParameter("carid");
		final int carId = strCarId == null || strCarId.trim().isEmpty() ? 0 : Integer.valueOf(strCarId);
		final String status = request.getParameter("status");
		final String strCost = request.getParameter("cost");
		final int cost = strCost == null || strCost.trim().isEmpty() ? 0 : Integer.valueOf(strCost);
		final String strDistance = request.getParameter("distance");
		final int distance = strDistance == null || strDistance.trim().isEmpty() ? 0 : Integer.parseInt(strDistance);
		final String strPassengers = request.getParameter("passengers");
		final int passengers = strPassengers == null || strPassengers.trim().isEmpty() ? 0 : Integer.parseInt(strPassengers);
		final String phone = request.getParameter("phone");

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");

		final String timedest = request.getParameter("timedest");
		Timestamp timeDone;
		try {
			timeDone = timedest == null || timedest.trim().isEmpty() ? null : new Timestamp(dateFormatter.parse(timedest).getTime());
		} catch (ParseException e) {
			throw new IllegalStateException(e);
		}
		final String timeord = request.getParameter("timeord");
		Timestamp timeOrd;
		try {
			timeOrd = timeord == null || timeord.trim().isEmpty() ? null : new Timestamp(dateFormatter.parse(timeord).getTime());
		} catch (ParseException e) {
			throw new IllegalStateException(e);
		}
		return new OrderVO(id, cost, phone, distance, passengers, timeDone, timeOrd, startPoint, carId, status);
	}

	protected List<String> validate(OrderVO order) {
		List<String> result = new LinkedList<String>();
		/*if (order.getAddrDep() == null || order.getAddrDep().trim().isEmpty())
			result.add("Введите адрес отправления");
		if (order.getAddrDest() == null || order.getAddrDest().trim().isEmpty())
			result.add("Введите адрес прибытия");*/
		if (order.getPhone() == null || order.getPhone().trim().isEmpty())
			result.add("Введите телефон клиента");
        /*if (!order.getCompleted())
            return result;*/
        if (order.getDistance() <= 0)
			result.add("Введите дальность поездки");
        if (order.getCost() <= 0)
			result.add("Введите стоимость поездки");
		return result;
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
}
