package ru.dreamjteam.servlets;

import ru.dreamjteam.entity.CarTypeVO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;


public class CarTypeServlet extends HttpServlet {
    
        List<String> result;
    
	protected CarTypeVO getCarType(HttpServletRequest request) {
                result = new LinkedList<String>();
                Integer costPerKm = 0;
                Integer capacity = 0;
            
		final String strId = request.getParameter("id");
		final int id = strId == null || strId.trim().isEmpty() ? 0 : Integer.valueOf(strId);
                
		final String strCostperkm = request.getParameter("costperkm");
                if (!strCostperkm.matches("[1-9][0-9]*")) 
                    result.add("Значение в поле \"Стоимость за км\" должно быть положительным целым числом");
                else costPerKm = Integer.valueOf(strCostperkm);
                
		final String name = request.getParameter("name");
                if (name.trim().isEmpty())
                    result.add("Введите название типа");
                
		final String strCapacity = request.getParameter("seatcap");
                if (!strCapacity.matches("[1-9][0-9]*")) 
                    result.add("Значение в поле \"Вместимость\" должно быть положительным целым числом");
                else capacity = Integer.valueOf(strCapacity);
                
		
		return new CarTypeVO(id, capacity, costPerKm, name);
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
