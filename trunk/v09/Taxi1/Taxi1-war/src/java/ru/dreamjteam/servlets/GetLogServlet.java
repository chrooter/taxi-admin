package ru.dreamjteam.servlets;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import ru.dreamjteam.beans.TaxiBeanEmulator;
//import session.fromLogLocal;
//import session.fromLogLocalHome;
import session.*;

/**
 *
 * @author диман
 */
public class GetLogServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        StringWriter sw = new StringWriter();
        try {
           String entity = request.getParameter("entity");
           fromLogLocal ptl = lookupfromLogLocal();
           String logXML = TaxiBeanEmulator.getXMLLogs(ptl.getHistory(entity));
           String realPath = getServletConfig().getServletContext().getRealPath("/WEB-INF/classes/ru/dreamjteam/xsl/Logs.xsl");
           File styleSheet = new File(realPath);
           StreamSource styleSource = new StreamSource(styleSheet);
           Transformer t = TransformerFactory.newInstance().newTransformer(styleSource);
           t.transform(new StreamSource(new StringReader(logXML)), new StreamResult(sw));
           request.setAttribute("entity", entity);
           request.setAttribute("output", sw.toString());
           RequestDispatcher dispatcher = request.getRequestDispatcher("log.jsp");
           dispatcher.forward(request, response);
        } catch (NamingException e) {
			throw new ServletException(e);
        } catch (CreateException e) {
			throw new ServletException(e);
        }catch (TransformerException e) {
                    throw new ServletException(e);
        } finally {
            
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

    private fromLogLocal lookupfromLogLocal() {
        try {
            Context c = new InitialContext();
            fromLogLocalHome rv = (fromLogLocalHome) c.lookup("java:comp/env/fromLog");
            return rv.create();
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        } catch (CreateException ce) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ce);
            throw new RuntimeException(ce);
        }
    }


}
