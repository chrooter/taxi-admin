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
import org.xml.sax.SAXException;

public class ImportCarTypeServlet extends HttpServlet {
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
                
                String realPath = getServletConfig().getServletContext().getRealPath("/WEB-INF/classes/ru/dreamjteam/xsl/CarTypes.xsl");
                
                File styleSheet = new File(realPath);
                StreamSource styleSource = new StreamSource(styleSheet);
                Transformer t = TransformerFactory.newInstance().newTransformer(styleSource);
                xmlString = item.getString("UTF-8");
                t.transform(new StreamSource(new StringReader(xmlString)), new StreamResult(sw));
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("importCarType.jsp");
            request.setAttribute("xmlString", xmlString);
            try {
                ValidateXML.CheckXML(xmlString, "D:///schema1.xsd");
            } catch (SAXException sex) {
                
                request.setAttribute("error", "wrongXML");
            }

            request.setAttribute("output", sw.toString());
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
