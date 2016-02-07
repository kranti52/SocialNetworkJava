package com.myapp.Mailer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.crypto.EncryptDecrypt;
import com.myapp.repository.KeyPairRepository;

/**
 * Servlet implementation class ResetPassword
 */
//@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KeyPairRepository key_db;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResetPassword() {
		super();
		this.key_db=new KeyPairRepository();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String decrypt_mail=null;
		String query=request.getQueryString();
		String[] query_array=query.split("=");
		String encrypt_email=query_array[1];
		String secret_key=this.key_db.getSecretKey(encrypt_email);
		if(secret_key.equals("Already Used")) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Fraud</title>");
			out.println("</head>");
			out.println("<body bgcolor=\"white\">");
			out.println("<H1>This link is already used</H1>");
			out.println("</body>");
			out.println("</html>");
		}
		else{
			//System.out.println(encrypt_email);
			EncryptDecrypt decoder=new EncryptDecrypt();
			try {
				decrypt_mail=decoder.decrypt(encrypt_email,secret_key);
				this.key_db.updateUsedSecretKey(encrypt_email);
				System.out.println(decrypt_mail);
				request.setAttribute("decrypt_email", decrypt_mail);
				request.getRequestDispatcher("Login/forgot.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
