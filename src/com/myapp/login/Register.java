package com.myapp.login;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myapp.Mailer.VerifyMail;
import com.myapp.crypto.EncryptDecrypt;
import com.myapp.entity.UserEntity;
import com.myapp.repository.UserRepository;
/**
 * Servlet implementation class Register
 */
//@WebServlet("Login/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public UserRepository user_db;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		this.user_db=new UserRepository();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("user")!=null){
			response.sendRedirect("profile");
		}
		if(request.getSession().getAttribute("user")==null||request.getSession().getAttribute("user")==""){
			response.sendRedirect("");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("user")!=null){
			response.sendRedirect("profile");
		}
		
		else {
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			//System.out.println(email);
			//System.out.println(password);
			Map<String,String> user_data=new HashMap<String,String>();
			user_data.put("user_email", email);
			user_data.put("password", password);
			//System.out.println(user_data);
			ArrayList<UserEntity> entityList=this.user_db.findUserByEmail(email);
			/*if(exist.equals(true)) {
			//((ServletRequest) response).setAttribute("exist","true");
			System.out.println("already exist is:"+exist);
			response.sendRedirect("Login/login.jsp?exist=true");
		}
		else {*/
			this.user_db.createUser(user_data);
			//response.sendRedirect("Profile/profile.jsp");

			HttpSession session = request.getSession();
			session.setAttribute("user", email);
			session.setMaxInactiveInterval(-1);
			Cookie SocialApp = new Cookie("user", email);
			SocialApp.setMaxAge(60*60*24*30);
			response.addCookie(SocialApp);
			VerifyMail mail=new VerifyMail();
			Boolean bool=mail.sendMail(email);
			//System.out.println(bool);
			RequestDispatcher profile=request.getRequestDispatcher("Profile/profile.jsp");
			profile.forward(request, response);
			//response.sendRedirect("Profile");
		}
		//}
	}

}
