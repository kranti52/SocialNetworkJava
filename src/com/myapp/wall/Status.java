package com.myapp.wall;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.repository.WallRepository;

/**
 * Servlet implementation class Status
 */
@WebServlet("/Status")
public class Status extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private static WallRepository wall_db; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Status() {
        super();
        wall_db=new WallRepository();
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
		String type;
		String to=request.getParameter("to");
		String from=request.getParameter("from");
		String content=request.getParameter("content");
		if(to.equals(from)){
			type="personal";
		}
		else {
			type="other";
		}
		Map<String,String> data=new HashMap<String,String>();
		data.put("status_from",from);
		data.put("status_to",to);
		data.put("type",type);
		data.put("content",content);
		Boolean result=wall_db.createStatus(data);
		if(result==true) {
			response.getWriter().write("true");
		}
		else {
			response.getWriter().write("false");
		}
	}

}
