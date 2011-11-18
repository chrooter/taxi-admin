package ru.dreamjteam.servlets;

import ru.dreamjteam.entity.CarTypeVO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;


public class CarTypeServlet extends HttpServlet {
	protected CarTypeVO getCarType(HttpServletRequest request) {
		final String strId = request.getParameter("id");
		final int id = strId == null || strId.trim().isEmpty() ? 0 : Integer.valueOf(strId);
		final String costperkm = request.getParameter("costperkm");
		final Integer costPerKm = costperkm == null || costperkm.trim().isEmpty() ? 0 : Integer.valueOf(costperkm);
		final String name = request.getParameter("name");
		final String strCapacity = request.getParameter("seatcap");
		final Integer capacity = strCapacity == null ||strCapacity.trim().isEmpty() ? 0 : Integer.valueOf(strCapacity);
		return new CarTypeVO(id, capacity, costPerKm, name);
	}

	protected List<String> validate(final CarTypeVO carType) {
		List<String> result = new LinkedList<String>();
                if (carType.getName().trim().isEmpty())
			result.add("Введите название типа");
                if (carType.getCapacity() <= 0)
			result.add("Введите вместимость больше нуля");
		if (carType.getCostPerKm() <= 0)
			result.add("Введите стоимость за км больше нуля");
		return result;
	}
}
