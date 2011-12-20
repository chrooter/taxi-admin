package ru.dreamjteam.servlets;

import java.io.File;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ImportCarServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            StringWriter sw = new StringWriter();
            String xmlString = null;
        try {
            DiskFileItemFactory  fileItemFactory = new DiskFileItemFactory ();
            ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
            List items = uploadHandler.parseRequest(request);
            Iterator itr = items.iterator();
            while(itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                
                String realPath = getServletConfig().getServletContext().getRealPath("/WEB-INF/classes/ru/dreamjteam/xsl/Cars.xsl");
                
                File styleSheet = new File(realPath);
                StreamSource styleSource = new StreamSource(styleSheet);
                Transformer t = TransformerFactory.newInstance().newTransformer(styleSource);
                xmlString = item.getString("UTF-8");
                t.transform(new StreamSource(new StringReader(xmlString)), new StreamResult(sw));
            }
            
            request.setAttribute("xmlString", xmlString);
            request.setAttribute("output", sw.toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("importCar.jsp");
            dispatcher.forward(request, response);
            
            } catch (FileUploadException e) {
                    throw new ServletException(e);
            } catch (TransformerException e) {
                    throw new ServletException(e);
            } 
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
