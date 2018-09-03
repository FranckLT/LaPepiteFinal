package fr.lapepite.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lapepite.javabean.Utilisateur;

/**
 * Servlet Filter implementation class AdminFilter
 */
public class AdminFilter implements Filter {

	/**
	 * Default constructor. 
	 */
	public AdminFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		Utilisateur utilisateur = null;
		utilisateur = (Utilisateur) httpServletRequest.getSession().getAttribute("utilisateur");

		if (utilisateur!=null) {

			if (utilisateur.isAdmin()) {
				chain.doFilter(request, response);
			} else {
				httpServletResponse.sendRedirect("/LaPepite/home");
			}
		} else {
			httpServletResponse.sendRedirect("/LaPepite/home");
		}



	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
