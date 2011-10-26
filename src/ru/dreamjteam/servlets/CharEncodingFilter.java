/*
	Copyright 2011 Open Code Ltd.
    http://www.o-code.ru

    $Id: CharEncodingFilter.java 28272 2011-08-05 09:55:42Z abolmasov $
*/
package ru.dreamjteam.servlets;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author abolmasov (20.05.2011 10:03:04)
 * @version $Revision: 28272 $
 *          Фильтр преобразует кодировку в запросе в кодировку, указанную в параметре encoding для фильтра
 *          в файле web.xml
 */
public class CharEncodingFilter implements Filter {
	private String encoding;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.encoding = filterConfig.getInitParameter("encoding");
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
	                     FilterChain chain) throws IOException, ServletException {
		if (encoding != null)
			request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}
}
