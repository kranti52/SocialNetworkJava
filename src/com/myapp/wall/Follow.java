package com.myapp.wall;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.repository.AddFriendFollow;

/**
 * Servlet implementation class Follow
 */
@WebServlet("/Follow")
public class Follow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AddFriendFollow follow_db;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Follow() {
		super();
		// TODO Auto-generated constructor stub
		this.follow_db=new AddFriendFollow();
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
		
		String follower=request.getParameter("follower");
		String following=request.getParameter("following");
		Map<String,String> follow_data=new HashMap<String,String>();
		follow_data.put("follower",follower);
		follow_data.put("following", following);
		Boolean result=this.follow_db.createFollower(follow_data);
		if(result==true){
			response.getWriter().write("true");
		}
		else{
			response.getWriter().write("false");
		}
	}

}
