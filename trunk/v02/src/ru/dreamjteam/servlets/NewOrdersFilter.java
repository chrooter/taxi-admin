package ru.dreamjteam.servlets;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author abolmasov (11.10.2011 16:28:07)
 * @version $Revision$
 */
public class NewOrdersFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
