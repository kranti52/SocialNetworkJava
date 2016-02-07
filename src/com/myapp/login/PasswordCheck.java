package com.myapp.login;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.entity.UserEntity;
import com.myapp.repository.UserRepository;

/**
 * Servlet implementation class PasswordCheck
 */
//@WebServlet("/PasswordCheck")
public class PasswordCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserRepository db;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PasswordCheck() {
		super();
		this.db=new UserRepository();
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
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		//System.out.println(email);
		//System.out.println(password);
		ArrayList<UserEntity> entity=this.db.findUserByEmailPassword(email, password);
		for (UserEntity dataList:entity) {
			//System.out.println(dataList.getemail());
			//System.out.println(dataList.getPassword());
			boolean email_check=email.equals(dataList.getemail());
			boolean password_check=password.equals(dataList.getPassword());
			//System.out.println(email_check);
			//System.out.println(password_check);
			response.setContentType("text/html;charset=UTF-8");
			if((email_check==true)&&(password_check==true)) {
				response.getWriter().write("true");
			}
			else {
				response.getWriter().write("false");
			}

		}

	}

}
