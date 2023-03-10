package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
				
		session.removeAttribute("userobj");
		
		request.getRequestDispatcher("login.html").include(request, response);
		out.print("<p style = ' margin-top:-34%; margin-left:32pc; font-size:16px; background-color:tomato; color:white; width:17pc; border-radius:6px; text-align:center; padding:4px;'>Logout Succesfully!</p>");	

	}

}
