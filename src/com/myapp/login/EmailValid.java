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
 * Servlet implementation class EmailValid
 */
//@WebServlet("/EmailValid")
public class EmailValid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserRepository db;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmailValid() {
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
		String email= request.getParameter("email");
		ArrayList<UserEntity> entityList=this.db.findUserByEmail(email);
		for(UserEntity entity: entityList) {
			/*System.out.println(entity.getemail());
			System.out.println(entity.getPassword());
			System.out.println(entity.getVerified());*/
		}
		if(!entityList.isEmpty()) {
			response.getWriter().write("true");
		}

	}

}
