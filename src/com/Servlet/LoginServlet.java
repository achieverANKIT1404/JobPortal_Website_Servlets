package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.UserDao;
import com.entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try{
			
			String name = request.getParameter("name");
			String ps = request.getParameter("password");
			
			User us = new User();
			HttpSession session = request.getSession();
			
			if("achiever12@gmail.com".equals(name) && "achiever#12".equals(ps)){
				
				session.setAttribute("userobj", us);
				us.setRole("admin");
				response.sendRedirect("adminpg");
			}
			else{
				
				UserDao dao = new UserDao(DBConnect.getConn());
				User user = dao.login(name, ps);
				
				if(user!=null){
					
					session.setAttribute("userobj", user);
					session.setAttribute("name", name);
					user.setRole("user");
					request.getRequestDispatcher("home").forward(request, response);
				}else{
					
					request.getRequestDispatcher("login.html").include(request, response);
					out.print("<p style = ' margin-top:-34%; margin-left:32pc; font-size:16px; background-color:tomato; color:white; width:17pc; border-radius:6px; text-align:center; padding:4px;'>Invalid UserName OR Password!</p>");	

				}
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}

}
