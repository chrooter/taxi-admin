package ru.dreamjteam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import ru.dreamjteam.xml.XMLParser;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.dreamjteam.db.CarTypeDb;
import ru.dreamjteam.xml.binds.CarTypes;


/**
 *
 * @author диман
 */
public class ViewCarTypeListServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        CarTypes types;
        try {
            String orderBy = request.getParameter("orderBy");
            if (orderBy == null) {
                types = XMLParser.parseXML(CarTypeDb.select(),CarTypes.class);

            } else {
                types = XMLParser.parseXML(CarTypeDb.select(request.getParameter("orderBy")),CarTypes.class);
            }
            request.setAttribute("types", types);
            RequestDispatcher dispatcher = request.getRequestDispatcher("navigationType.jsp");
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
