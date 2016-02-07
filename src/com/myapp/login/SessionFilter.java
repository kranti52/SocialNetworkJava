package com.myapp.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class SessionFilter
 */
//@WebFilter("/SessionFilter")
public class SessionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SessionFilter() {
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
		   HttpServletRequest http_request = (HttpServletRequest) request;
	        String requestURI = http_request.getRequestURI();
	        
	        if (http_request.getSession().getAttribute("user")!=null) {
	        	requestURI=requestURI.replace("/Socialweb/","");
	        	HttpServletResponse http_response=(HttpServletResponse)response;
	        	http_response.sendRedirect(requestURI);
	        	/*String toReplace = requestURI.substring(requestURI.indexOf("/profile"), requestURI.lastIndexOf("/") + 1);
	    		String[] splitArray=toReplace.split("/");
	    		String newURI="/profile?user="+splitArray[2];*/
	            //request.getRequestDispatcher(newURI).forward(request, response);
	        } else {
	        	System.out.println("filter");
	            chain.doFilter(request, response);
	        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
