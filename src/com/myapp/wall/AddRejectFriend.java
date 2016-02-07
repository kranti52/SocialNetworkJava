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
 * Servlet implementation class AddRejectFriend
 */
//@WebServlet("/AddRejectFriend")
public class AddRejectFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AddFriendFollow db;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddRejectFriend() {
		super();
		// TODO Auto-generated constructor stub
		db=new AddFriendFollow();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("user")==null||request.getSession().getAttribute("user")==""){
			response.sendRedirect("");
		} else {
			String value=request.getParameter("value");
			String from=request.getParameter("from");
			String to=request.getParameter("to");
			if(value.equals("confirm")) {
				Map<String,String> friend_data=new HashMap<String,String>();
				friend_data.put("friend1",to);
				friend_data.put("friend2", from);
				db.AddFriend(friend_data);
				Map<String,String> data=new HashMap<String,String>();
				data.put("request_to",to);
				data.put("request_from", from);
				db.DeleteFriendRequest(data);
			}
			else {
				Map<String,String> data=new HashMap<String,String>();
				data.put("request_to",to);
				data.put("request_from", from);
				db.DeleteFriendRequest(data);

			}
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
