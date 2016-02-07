package com.myapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class ProfileFilter
 */
//@WebFilter("/profile/*")
public class ProfileFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ProfileFilter() {
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

	        if (requestURI.startsWith("/Socialweb/profile/")) {
	    		String toReplace = requestURI.substring(requestURI.indexOf("/profile"), requestURI.lastIndexOf("/") + 1);
	    		String[] splitArray=toReplace.split("/");
	    		String newURI="/profile?user="+splitArray[2];
	            request.getRequestDispatcher(newURI).forward(request, response);
	        } else {
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
