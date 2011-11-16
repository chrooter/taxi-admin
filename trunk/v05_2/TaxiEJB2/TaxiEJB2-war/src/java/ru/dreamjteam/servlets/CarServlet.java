package ru.dreamjteam.servlets;

import ru.dreamjteam.beans.TaxiBeanEmulator;
import ru.dreamjteam.entity.CarTypeVO;
import ru.dreamjteam.entity.CarVO;

import javax.ejb.FinderException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;


public class CarServlet extends HttpServlet {
	protected CarVO getCar(HttpServletRequest req) {
		final String strId = req.getParameter("id");
		final int id = strId == null || strId.trim().isEmpty() ? 0 : Integer.valueOf(strId);
		final String govNumber = req.getParameter("govnum");
		final String model = req.getParameter("model");
		final String typeid = req.getParameter("typeid");
		final Integer carTypeId = typeid == null || typeid.trim().isEmpty() ? 0 : Integer.valueOf(typeid);
		final String color = req.getParameter("color");
		return new CarVO(id, model, govNumber, color, carTypeId);
	}

	protected List<String> validate(final CarVO car) {
		List<String> result = new LinkedList<String>();
		if (car.getCarTypeId() == 0)
			result.add("Не выбран тип машины");
		if (car.getGovNumber().trim().isEmpty())
			result.add("Введите гос. номер");
		return result;
	}

	protected void putCarTypesIn2req(HttpServletRequest req) throws ServletException {
		try {
			final List<CarTypeVO> carTypes = TaxiBeanEmulator.getCarTypes();
			req.setAttribute("carTypes", carTypes);
		} catch (FinderException e) {
			throw new ServletException(e);
		} catch (NamingException e) {
			throw new ServletException(e);
		}
	}
}
