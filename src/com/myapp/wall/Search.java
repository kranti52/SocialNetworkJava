package com.myapp.wall;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.myapp.entity.ProfileEntity;
import com.myapp.repository.ProfileRepository;

/**
 * Servlet implementation class Search
 */
//@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ProfileRepository repository;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
		super();
		repository=new ProfileRepository();
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

		String value=request.getParameter("search");
		System.out.println(value);
		// TODO Auto-generated method stub
		ArrayList<ProfileEntity> search_data=repository.search(value);
		for(ProfileEntity entity:search_data) {
			byte[] bytes = IOUtils.toByteArray(entity.getPicture());
			String picture=new String(Base64.encodeBase64(bytes));
			entity.setOccupation(picture);
		}
		Gson gson = new Gson();
		String jsonRepresentation = gson.toJson(search_data);
		//System.out.println(jsonRepresentation);
		response.getWriter().write(jsonRepresentation);

	}

}
