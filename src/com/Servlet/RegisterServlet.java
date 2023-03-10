package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.UserDao;
import com.entity.User;

@WebServlet("/add_user")
public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try{
			
			String name = request.getParameter("name");
			String qua = request.getParameter("qua");
			String email = request.getParameter("email");
			String ps = request.getParameter("ps");
			
			UserDao dao = new UserDao(DBConnect.getConn());
			
			User u = new User(name,email,ps,qua,"user");
			boolean f = dao.addUser(u);
			
			if(f){
				
				RequestDispatcher rd=request.getRequestDispatcher("signup.html");
				rd.include(request, response);
				out.print("<p style = 'color:green; margin-top:-48%; margin-left:33pc; font-size:16px; background-color:limegreen; color:white; width:13pc; border-radius:6px; text-align:center; padding:4px;'>Registered Succesfully..</p>");	
			}else{
				
				out.print("<h4  style = 'color:green; margin-top:-43%; margin-left:33pc; font-size:16px; background-color:tomato; color:white; width:13pc; border-radius:6px; text-align:center; padding:4px;''>Something Wrong!</h4>");
				request.getRequestDispatcher("signup.html").include(request, response);
			}
		}catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
