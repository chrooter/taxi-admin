package ru.dreamjteam.servlets;

import java.io.ByteArrayInputStream;
import ru.dreamjteam.beans.TaxiBeanEmulator;
import ru.dreamjteam.entity.*;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.ejb.CreateException;
import javax.servlet.ServletOutputStream;

public class ExportOrderServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String filename = "export_orders.xml";
                response.setHeader("Content-Disposition","attachment;filename="+filename);
                String mimetype = getServletConfig().getServletContext().getMimeType( filename );
                response.setContentType( (mimetype != null) ? mimetype : "application/octet-stream" );
                
                List<OrderVO> orders = (List<OrderVO>) request.getSession().getAttribute("orders");
                try {
                        String h = TaxiBeanEmulator.getXMLOrders(orders);

                        InputStream in = new ByteArrayInputStream(h.getBytes("UTF-8"));
                        ServletOutputStream out = response.getOutputStream();
                        byte[] outputByte = new byte[h.length()];

                        int length = 0;
                        while((in!=null) && (length = in.read(outputByte)) != -1)
                        {
                                out.write(outputByte, 0, length);
                        }
                        in.close();
                        out.flush();
                        out.close();
                        
                
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (CreateException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
