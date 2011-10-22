package ru.dreamjteam.servlets;

import java.io.IOException;
import ru.dreamjteam.xml.XMLParser;
import ru.dreamjteam.db.OrderDb;
import ru.dreamjteam.xml.binds.Orders;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author диман
 */
public class ViewOrderListServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Orders orders;
        try {
            String orderBy = request.getParameter("orderBy");
            if (orderBy == null) {
                orders = XMLParser.parseXML(OrderDb.select(), Orders.class);

            } else {
                orders = XMLParser.parseXML(OrderDb.select(request.getParameter("orderBy")),Orders.class);
            }
            request.setAttribute("orders", orders);
            RequestDispatcher dispatcher = request.getRequestDispatcher("navigationOrder.jsp");
            dispatcher.forward(request, response);
           
        } catch (Exception e) {
             RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
             dispatcher.forward(request, response);
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
