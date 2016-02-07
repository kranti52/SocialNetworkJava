package com.myapp.message;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.entity.MessageEntity;
import com.myapp.entity.ProfileEntity;
import com.myapp.repository.MessageRepository;

/**
 * Servlet implementation class Message
 */
//@WebServlet("/Message")
public class Message extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static MessageRepository message_db;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Message() {
		super();
		// TODO Auto-generated constructor stub
		message_db=new MessageRepository();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("user")==null||request.getSession().getAttribute("user")==""){
			response.sendRedirect("");
		} else {
			String user=(String)request.getSession().getAttribute("user");
			List<HashMap<String,Object>> list=message_db.getMessages(user);
			request.setAttribute("list",list);
			if(list!=null){
				for(HashMap<String,Object> data:list)
				{
					MessageEntity message=(MessageEntity)data.get("message");
					ProfileEntity sender=(ProfileEntity)data.get("sender");
					System.out.println(message.getContent());
					System.out.println(sender.getFull_name());
				}
			}
			request.getRequestDispatcher("Message/message.jsp").forward(request, response);
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
			String to=request.getParameter("message_to");
			String from=request.getParameter("message_from");
			String content=request.getParameter("message");
			Map<String,String> data=new HashMap<String,String>();
			data.put("to", to);
			data.put("from", from);
			data.put("content", content);

			message_db.createMessage(data);
		}
	}
}
