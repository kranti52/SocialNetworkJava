package com.myapp.wall;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.repository.WallRepository;

/**
 * Servlet implementation class DeleteStatus
 */
//@WebServlet("/DeleteStatus")
public class DeleteStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static WallRepository db;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteStatus() {
		super();
		db=new WallRepository();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("user")==null||request.getSession().getAttribute("user")==""){
			response.sendRedirect("");
		} else {
			String id=request.getParameter("status");
			System.out.println(id);
			Boolean result=db.deleteStatus(id);
			response.sendRedirect("profile");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
