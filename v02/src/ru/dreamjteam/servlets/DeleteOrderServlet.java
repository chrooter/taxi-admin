package ru.dreamjteam.servlets;

import ru.dreamjteam.db.CarDb;
import ru.dreamjteam.db.DbAccessException;
import ru.dreamjteam.db.OrderDb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author abolmasov (11.10.2011 10:16:13)
 * @version $Revision$
 */
public class DeleteOrderServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String id = req.getParameter("id");
		if (id != null && !id.trim().isEmpty())
			try {
				OrderDb.delete(Integer.valueOf(id));
			} catch (DbAccessException e) {
				throw new ServletException(e);
			}
		resp.sendRedirect("/orders.jsp");
	}
}
