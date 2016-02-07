package com.myapp.wall;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.repository.ProfileRepository;
import com.myapp.repository.WallRepository;

/**
 * Servlet implementation class Wall
 */
//@WebServlet("/Wall")
public class Wall extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WallRepository wall_db;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Wall() {
		super();
		this.wall_db=new WallRepository();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Welcome on wall by get");
		if(request.getSession().getAttribute("user")==null||request.getSession().getAttribute("user")==""){
			response.sendRedirect("");
		} else {
			response.sendRedirect("profile");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("user")==null||request.getSession().getAttribute("user")==""){
			response.sendRedirect("");
		} else {
			System.out.println("Welcome on wall");
			String from=request.getParameter("from");
			String to=request.getParameter("To");
			String content=request.getParameter("message");
			String personal=request.getParameter("personal");
			/*if (request.getParameter("personal")!=null) {
			personal=request.getParameter("personal");
		}*/
			String message=request.getParameter("message");
			System.out.println(from);
			System.out.println(to);
			System.out.println(message);

			Map<String,String> wall_data=new HashMap<String,String>();
			wall_data.put("status_to", to);
			wall_data.put("status_from", from);
			wall_data.put("content", content);
			wall_data.put("type", personal);

			this.wall_db.createStatus(wall_data);
			//return value;

		}
	}

}
