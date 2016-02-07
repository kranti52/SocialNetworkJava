package com.myapp.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.codec.binary.Base64;

/**
 * Servlet implementation class StreamToJpeg
 */
//@WebServlet("/StreamToJpeg")
public class StreamToJpeg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StreamToJpeg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String stream=request.getParameter("picture");
		InputStream in = IOUtils.toInputStream(stream, "UTF-8");
		byte[] bytes = IOUtils.toByteArray(in);
		System.out.println(bytes);
		OutputStream out=null;
		IOUtils.copy(in,out);
		String picture=new String(Base64.encodeBase64(bytes));
		System.out.println(picture);
		//response.getOutputStream().write(out);
		
	}

}
