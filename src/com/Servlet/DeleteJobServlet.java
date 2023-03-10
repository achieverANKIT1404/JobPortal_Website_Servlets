package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.DBConnect;
import com.dao.JobDao;

@WebServlet("/delete")
public class DeleteJobServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
	try{
		
	    int id = Integer.parseInt(request.getParameter("id"));
	    
	    JobDao dao = new JobDao(DBConnect.getConn());
	    boolean f = dao.deleteJobs(id);
		
		if(f){
			RequestDispatcher rd=request.getRequestDispatcher("viewjob");
			rd.include(request, response);
			out.print("<p style = 'color:green; margin-top:-43%; margin-left:33pc; font-size:16px; background-color:tomato; color:white; width:13pc; border-radius:6px; text-align:center; padding:4px;'>Job Deleted Succesfully..</p>");	
		}else{
			
			out.print("<h4  style = 'color:green; margin-top:-43%; margin-left:33pc; font-size:16px; background-color:tomato; color:white; width:13pc; border-radius:6px; text-align:center; padding:4px;''>Something Wrong!</h4>");
			request.getRequestDispatcher("viewjob").include(request, response);
		}
	}catch (Exception e) {
		
		e.printStackTrace();
	}
	}

}
