package com.myapp.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.repository.UserRepository;

/**
 * Servlet implementation class SavePassword
 */
//@WebServlet("/SavePassword")
public class SavePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserRepository db;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SavePassword() {
		super();
		db=new UserRepository();
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
		
			String email=request.getParameter("email_reset");
			String password=request.getParameter("password1");
			System.out.println(password);
			System.out.println(email);
			db.updatePassword(email, password);
			response.sendRedirect("");
		
	}

}
