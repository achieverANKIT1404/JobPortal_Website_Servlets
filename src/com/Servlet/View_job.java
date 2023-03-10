package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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

@WebServlet("/viewjob")
public class View_job extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		request.getRequestDispatcher("navbarAdmin.html").include(request, response);;
		
		
		out.print("<html>");
		out.print("<head>");
		out.print("<title>JobHunt | Job</title>");
		out.print("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css' integrity='sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm' crossorigin='anonymous'>");
		out.print("<link rel = 'stylesheet' href = 'all_components/style.css'>");
		out.print("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css' integrity='sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==' crossorigin='anonymous' referrerpolicy='no-referrer' />");
		out.print("<style>");
		out.print(".container{width:70%;}");
		out.print("h2{color:navy;}");

		out.print("</style>");
		out.print("</head>");
		out.print("<body>");
		
		
		JobDao dao = new JobDao(DBConnect.getConn());
		ArrayList<Jobs> list = dao.getAllJobs();
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userobj") == null){
			
			response.sendRedirect("login.html");
		}
	
		out.print("<h2 class = 'text-center  mt-4'><b>All Jobs</b></h2>");

		for(Jobs j: list){
			
			out.print("<div class = 'container'>");
			out.print("<div class = 'row'>");   
			out.print("<div class = 'col-md-12 mt-4'>");    
			out.print("<class = 'text-center text-primary'>"); 
			out.print("<div class = 'card mt-2'>");        
			out.print("<div class = 'card-body'>");          
			out.print("<div class = 'text-center text-info'>");
			out.print("<i class='fa-solid fa-id-card fa-3x'></i>");            
			out.print("</div>");                 
			out.print("<h5>"+j.getTitle()+"</h5>");                       
			out.print("<p>"+j.getDescription()+"</p>");              
		    out.print("<br>");      
	    	out.print("<div class = 'form-row'>");
			out.print("<div class = 'form-group col-md-3'>");               
			out.print("<input type = 'text' class = 'form-control form-control-sm' value = 'Location: "+j.getLocation()+"' readonly>");          
		    out.print("</div>");     
			out.print("<div class = 'form-group col-md-3'>");
			out.print("<input type = 'text' class = 'form-control form-control-sm' value = 'Category: "+j.getCategory()+"' readonly>");                  
			out.print("</div>");               
			out.print("<div class = 'form-group col-md-3'>");                  
			out.print(" <input type = 'text' class = 'form-control form-control-sm' value = 'Status: "+j.getStatus()+"' readonly>");                
			out.print("</div>");             
			out.print("</div>");              
			                
			out.print("<h6>"+j.getPdate()+"</h6>");        
			out.print("<div class = 'text-center'>");
//			String id=Integer.toString(j.getId());
//			System.out.println("ID = "+j.getId());
			out.print("<a href = 'editjob?id="+j.getId()+"' class = 'btn btn-sm bg-success text-white'>Edit</a>&nbsp;");
			out.print("<a href = 'delete?id="+j.getId()+"' class = 'btn btn-sm bg-danger text-white'>Delete</a>");
			out.print("</div>");
			out.print("</div>");
			out.print("</div>");
			out.print("</div>");
			out.print("</div>");
			out.print("</div>");

		}
	
		
		out.print("</body>");
		out.print("</html>");
		
		out.close();
	}

}
