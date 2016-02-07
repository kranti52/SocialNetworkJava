package com.myapp.login;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myapp.entity.UserEntity;
import com.myapp.repository.UserRepository;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserRepository user_db;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		this.user_db=new UserRepository();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String email=request.getParameter("email");
		String password=request.getParameter("password");

		//System.out.println(email);
		//System.out.println(password);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email=request.getParameter("email_login");
		System.out.println(email);
		String password=request.getParameter("password_login");
		HttpSession session = request.getSession();
		session.setAttribute("user", email);
		session.setMaxInactiveInterval(-1);
		Cookie SocialApp = new Cookie("user", email);
		SocialApp.setMaxAge(60*60*24*30);
		response.addCookie(SocialApp);
		ArrayList<UserEntity> entity=this.user_db.findUserByEmail(email);
		for(UserEntity data:entity)
		{
			if((data.getProfile_done()).equals("1")){
				response.sendRedirect("profile");
			}
			else {
				RequestDispatcher profile=request.getRequestDispatcher("Profile/profile.jsp");
				profile.forward(request, response);
			}

		}
		//response.sendRedirect("Profile/profile.jsp");
	}

}
