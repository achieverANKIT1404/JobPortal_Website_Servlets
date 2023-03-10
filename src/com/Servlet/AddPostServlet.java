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
import com.dao.JobDao;
import com.entity.Jobs;

@WebServlet("/add_job")
public class AddPostServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try{
			
			String title = request.getParameter("title");
			String location = request.getParameter("location");
			String category = request.getParameter("category");
			String status = request.getParameter("status");
			String desc = request.getParameter("desc");

			Jobs j = new Jobs();
			
			j.setTitle(title);
			j.setDescription(desc);
			j.setLocation(location);
			j.setStatus(status);
			j.setCategory(category);
						
			JobDao dao = new JobDao(DBConnect.getConn());
			boolean f = dao.addJobs(j);
			
			if(f){
				RequestDispatcher rd=request.getRequestDispatcher("addjob");
				rd.include(request, response);
				out.print("<p style = 'color:green; margin-top:-43%; margin-left:33pc; font-size:16px; background-color:limegreen; color:white; width:13pc; border-radius:6px; text-align:center; padding:4px;'>Job Posted Succesfully..</p>");	
			}else{
				
				out.print("<h4  style = 'color:green; margin-top:-43%; margin-left:33pc; font-size:16px; background-color:tomato; color:white; width:13pc; border-radius:6px; text-align:center; padding:4px;''>Something Wrong!</h4>");
				request.getRequestDispatcher("addjob").include(request, response);
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}

}
