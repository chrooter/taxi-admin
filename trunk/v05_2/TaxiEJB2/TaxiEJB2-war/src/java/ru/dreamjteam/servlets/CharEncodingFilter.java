package ru.dreamjteam.servlets;

import javax.servlet.*;
import java.io.IOException;


/*         Фильтр преобразует кодировку в запросе в кодировку, указанную в параметре encoding для фильтра
           в файле web.xml
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
