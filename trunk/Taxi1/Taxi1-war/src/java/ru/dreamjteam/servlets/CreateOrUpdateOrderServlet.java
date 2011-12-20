/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dreamjteam.servlets;

import ru.dreamjteam.beans.TaxiBeanEmulator;
import ru.dreamjteam.entity.OrderVO;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.ejb.FinderException;


/**
 *
 * @author Артём
 */
public class CreateOrUpdateOrderServlet extends OrderServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
           String xmlString = (String) req.getSession().getAttribute("xmlString");
                List<OrderVO> orders = null;
		try {
                        orders = TaxiBeanEmulator.parseXMLOrders(xmlString);
                        if (req.getParameter("update")!=null)
                        {
                            if (req.getParameter("update").equals("true"))
                                TaxiBeanEmulator.createOrUpdateOrders(orders, true);
                            if (req.getParameter("update").equals("false"))
                                TaxiBeanEmulator.createOrUpdateOrders(orders, false);
                        }
                        else TaxiBeanEmulator.createOrders(orders);
			resp.sendRedirect(req.getContextPath() + "/ViewOrderList");
		} catch (DuplicateKeyException e) {
			final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/importOrder.jsp");
			String error = e.getLocalizedMessage();
			req.setAttribute("error", error);
			req.setAttribute("xmlString", xmlString);
			requestDispatcher.forward(req, resp);
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (CreateException e) {
			throw new ServletException(e);
		} catch (FinderException e) {
			throw new ServletException(e);
		} catch (Exception e) {
			throw new ServletException(e);
		}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
