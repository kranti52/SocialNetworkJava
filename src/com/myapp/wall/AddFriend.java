package com.myapp.wall;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.repository.AddFriendFollow;
import com.myapp.repository.ProfileRepository;

/**
 * Servlet implementation class AddFriend
 */
@WebServlet("/AddFriend")
public class AddFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AddFriendFollow friend_db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriend() {
        super();
        // TODO Auto-generated constructor stub
        this.friend_db=new AddFriendFollow();
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
		if(request.getSession().getAttribute("user")==null||request.getSession().getAttribute("user")==""){
			response.sendRedirect("");
		}
		String to=request.getParameter("to");
		String from=request.getParameter("from");
	       Map<String,String> friend_data=new HashMap<String,String>();
	       friend_data.put("request_to",to);
	       friend_data.put("request_from", from);
	       Boolean result=this.friend_db.sendFriendRequest(friend_data);
	       if(result==true) {
	    	   response.getWriter().write("true"); 
	       }
	       else {
	    	   response.getWriter().write("false"); 
	       }
		
	}

}
