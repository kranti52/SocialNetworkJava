package com.myapp.profile;

import java.io.BufferedInputStream;
import java.io.IOException;



import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

















import org.apache.commons.io.IOUtils;

import com.myapp.entity.FollowEntity;
import com.myapp.entity.FriendRequestEntity;
import com.myapp.entity.ProfileEntity;
import com.myapp.entity.StatusEntity;
import com.myapp.repository.AddFriendFollow;
import com.myapp.repository.ProfileRepository;
import com.myapp.repository.UserRepository;
import com.myapp.repository.WallRepository;





/**
 * Servlet implementation class Profile
 */
//@WebServlet("/Profile")
@MultipartConfig(maxFileSize = 16177215)
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfileRepository profile_db;
	private UserRepository user_db;
	private AddFriendFollow friend_db;
	private WallRepository wall_db;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
		this.profile_db=new ProfileRepository();
		this.user_db=new UserRepository();
		this.friend_db=new AddFriendFollow();
		this.wall_db=new WallRepository();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")==null||request.getSession().getAttribute("user")==""){
			response.sendRedirect("");
		}
		else {
			if(request.getParameter("redirect")==null)
			{
				ProfileEntity entity=this.profile_db.findProfileByEmail((String)request.getSession().getAttribute("user"));
				request.setAttribute("entity",entity);
				String session_user=(String)request.getSession().getAttribute("user");
				FriendRequestEntity friend_entity=this.friend_db.getFriendRequestDetails(session_user,entity.getUser_email());
				FollowEntity follow_entity=this.friend_db.getFollowerDetails(session_user,entity.getUser_email());
				List<HashMap<String,Object>> status_entity=this.wall_db.getStatusDetails(entity.getUser_email());
				Map<String,String> friend_map=new HashMap<String,String>();
				friend_map.put("friend1", session_user);
				friend_map.put("friend2", entity.getUser_email());
				List<HashMap<String,Object>> request_entity=this.friend_db.getFriendRequest(session_user);
				Boolean isFriend=this.friend_db.getFriend(friend_map);
				System.out.println(session_user);
				if(isFriend){
					request.setAttribute("isFriend",true);
				}
				else {

				}
				for(HashMap<String,Object> map:status_entity)
				{
					StatusEntity status=(StatusEntity)map.get("status");
					ProfileEntity sender=(ProfileEntity)map.get("sender");
				}
				request.setAttribute("entity",entity);
				request.setAttribute("friend_entity",friend_entity);
				request.setAttribute("request_entity",request_entity);
				request.setAttribute("follow_entity",follow_entity);
				request.setAttribute("status_entity",status_entity);
				RequestDispatcher profile=request.getRequestDispatcher("Wall/wall.jsp");
				profile.forward(request, response);

			}
			else{
				try {
					/*String pathInfo = request.getPathInfo();
				String path = request.getRequestURI().substring(request.getContextPath().length());
				System.out.println(pathInfo);
				String[] parts=pathInfo.split("/");
				String part2=parts[1];
				System.out.println(part2);*/
					String user=request.getParameter("user");
					ProfileEntity entity=this.profile_db.findProfileByUsername(user);
					//System.out.println(request.getSession().getAttribute("user"));
					//System.out.println(entity);
					if(entity.getUser_email()!=null){
						String session_user=(String)request.getSession().getAttribute("user");
						//System.out.println("erhjvjhvhherer");
						FriendRequestEntity friend_entity=this.friend_db.getFriendRequestDetails(session_user,entity.getUser_email());
						FollowEntity follow_entity=this.friend_db.getFollowerDetails(session_user,entity.getUser_email());
						List<HashMap<String,Object>> status_entity=this.wall_db.getStatusDetails(entity.getUser_email());
						Map<String,String> friend_map=new HashMap<String,String>();
						friend_map.put("friend1", session_user);
						friend_map.put("friend2", entity.getUser_email());
						Boolean isFriend=this.friend_db.getFriend(friend_map);
						System.out.println(isFriend);
						if(isFriend){
							request.setAttribute("isFriend",true);
						}
						else {

						}
						request.setAttribute("entity",entity);
						request.setAttribute("friend_entity",friend_entity);
						request.setAttribute("follow_entity",follow_entity);
						request.setAttribute("status_entity",status_entity);
						//String pathInfo = request.getPathInfo();
						//System.out.println(path);
						//request.setAttribute("pathinfo",pathInfo);
						//Filter filter.doFilter(request, response);
						request.getRequestDispatcher("Wall/wall.jsp").forward(request,response);
					} else {

						///System.out.println("ererer");
						//ProfileEntity profile_entity=this.profile_db.findProfileByUsername(user);
						//entity=profile_entity;
						//request.setAttribute("entity",entity);
						response.sendRedirect("Wall");
						//request.getRequestDispatcher("Wall/wall.jsp").forward(request,response);
					}



				}catch(Exception e) {
					e.printStackTrace();
				}

			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("user")==null||request.getSession().getAttribute("user")==""){
			response.sendRedirect("");
		}
		else {
			if(!("".equals(request.getParameter("full_name")))) {
				try {
					//String profile_pic=request.getParameter("profile_pic");

					String full_name=request.getParameter("full_name");
					String username=request.getParameter("username");
					String dob=request.getParameter("dob");
					String occupation=request.getParameter("occupation");
					String home_town=request.getParameter("home_town");
					String current_city=request.getParameter("current_city");
					String experience=request.getParameter("experience");
					String interest=request.getParameter("interest");
					InputStream inputStream = null; // input stream of the upload file

					// obtains the upload file part in this multipart request
					Part filePart = request.getPart("profile_pic");
					if (filePart != null) {
						// prints out some information for debugging

						// obtains input stream of the upload file
						inputStream = filePart.getInputStream();
						//System.out.println(inputStream);
					}
					HttpSession session=request.getSession();
					String email=(String)session.getAttribute("user");
					System.out.println(session.getAttribute("user"));
					Map<String,String> profile_data=new HashMap<String,String>();
					profile_data.put("user_email", email);
					profile_data.put("username", username);
					profile_data.put("full_name", full_name);
					profile_data.put("dob", dob);
					profile_data.put("occupation",occupation);
					profile_data.put("hometown", home_town);
					profile_data.put("current_city", current_city);
					profile_data.put("interest", interest);
					profile_data.put("experience", experience);
					this.profile_db.createProfile(profile_data,inputStream);
					//System.out.println(profile_pic);
					this.user_db.updateProfile(email);

					ProfileEntity entity=this.profile_db.findProfileByEmail(email);
					//System.out.println(entity.getPicture());
					//InputStream picture=(InputStream)entity.getPicture();
					//byte[] bytes = IOUtils.toByteArray(picture);
					//String s = new String(bytes,StandardCharsets.UTF_8); // Or any encoding.
					//BufferedInputStream decoded_picture = new BufferedInputStream(picture);
					//response.sendRedirect("Profile/profile.jsp");
					//System.out.println(s);
					//response.sendRedirect("Wall/wall.jsp");
					request.setAttribute("entity",entity);
					//String pathInfo = request.getPathInfo();
					//System.out.println(pathInfo);
					//request.setAttribute("pathinfo",pathInfo);
					//System.out.println(entity.getUsername());
					RequestDispatcher profile=request.getRequestDispatcher("Wall/wall.jsp");
					profile.forward(request, response);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}	
			else {
				ProfileEntity entity=this.profile_db.findProfileByEmail((String)request.getSession().getAttribute("user"));
				request.setAttribute("entity",entity);
				RequestDispatcher profile=request.getRequestDispatcher("Wall/wall.jsp");
				profile.forward(request, response);
			}
		}
	}
}
