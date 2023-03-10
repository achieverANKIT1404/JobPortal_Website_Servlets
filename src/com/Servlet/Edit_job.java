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

import com.DB.DBConnect;
import com.dao.JobDao;
import com.entity.Jobs;

@WebServlet("/editjob")
public class Edit_job extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.print("<html>");
		out.print("<head>");
		out.print("<title>JobHunt | Admin</title>");
		out.print("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css' integrity='sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm' crossorigin='anonymous'>");
		out.print("<link rel = 'stylesheet' href = 'all_components/style.css'>");
		out.print("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css' integrity='sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==' crossorigin='anonymous' referrerpolicy='no-referrer' />");
		out.print("</head>");
		out.print("<body>");
		
		out.print("<nav class='navbar navbar-expand-lg navbar-dark bg-custom'>");
	    out.print("<a class='navbar-brand' href='#' ><b>JobHunt</b></a>");  
	    out.print("<button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarSupportedContent' aria-controls='navbarSupportedContent' aria-expanded='false' aria-label='Toggle navigation'>");
	    out.print(" <span class='navbar-toggler-icon'></span>");
	    out.print("</button>");
	
	    out.print("<div class='collapse navbar-collapse' id='navbarSupportedContent'>"); 
	    out.print("<ul class='navbar-nav mr-auto'>");
	    out.print("<li class='nav-item active mr-4'>");
	    out.print("<a class='nav-link' href='index.html'><i class='fa-solid fa-house-chimney'></i> Home <span class='sr-only'>(current)</span></a>");
        out.print("</li>");
        
        out.print("<li class='nav-item mr-4'><a class='nav-link' href='addjob'><i class='fa-solid fa-square-plus'></i> Post Job</a></li>");
	     
	    out.print("<li class='nav-item'><a class='nav-link' href='viewjob'><i class='fa-solid fa-eye'></i> View Job</a></li>");
	     
	    out.print("</ul>");
	    out.print(" <form class='form-inline my-2 my-lg-0'>");
	    out.print("<a href = '#' class = 'btn btn-light mr-2'><i class='fas fa-user'></i> Admin</a>");    
	    out.print("<a href = 'logout' class = 'btn btn-light'><i class='fa fa-sign-in-alt'></i> Logout</a>");    
	    out.print(" </form>");
	    out.print("</div>");
	    out.print("</nav>");
		
		out.print("<div class = 'container p-2 mt-5'>");
		out.print("<div class = 'col-md-10 offset-md-1'>"); 
		out.print("<div class = 'card'>");     
		out.print("<div class = 'card-body'>");           
		out.print("<div class = 'text-center text-success'>");         
	    out.print("<i class='fa-solid fa-user-group fa-3x'></i>");  
		out.print("<h3><b>Edit Jobs</b></h3>");             
	    out.print("</div>");         
	    
	    
	    String uid = request.getParameter("id");
	     int  id = Integer.parseInt(uid);
	     System.out.println("data id = "+id);
		 JobDao dao = new JobDao(DBConnect.getConn());
		 Jobs j = dao.getJobById(id);
		 
	     System.out.print("Raju");

		out.print("<form action='update' method='get'>");
		out.print("<input type = 'hidden' name = 'id' value = "+j.getId()+" />");
	    out.print("<div class = 'form-group'>");         
		out.print("<label>Enter Title</label>");          
	    out.print("<input type = 'text' name = 'title' required class = 'form-control' value = "+j.getTitle()+">");           
	    out.print(" </div>");       
		out.print("<div class = 'form-row'>");
	    out.print("<div class = 'form-group col-md-4'>");       
		out.print("  <label>Location</label>");             
	    out.print(" <select name = 'location' class = 'custom-select' id = 'inlineFormCustomSelectPref'>");           
		out.print("<option value = "+j.getLocation()+">"+j.getLocation()+"</option>");             
	    out.print("<option value = 'Nagpur'>Nagpur</option>");
        out.print("<option value = 'Indore'>Indore</option>");
	    out.print("<option value = 'Ahemdabad'>Ahemdabad</option>");
	    out.print("<option value = 'Delhi'>Delhi</option>");
	    out.print("<option value = 'Chennai'>Chennai</option>");
	    out.print("<option value = 'Raipur'>Raipur</option>");
	    out.print("<option value = 'Bhuwneshwar'>Bhuwneshwar</option>");
		out.print("<option value = 'Pune'>Pune</option>");
	    out.print("<option value = 'Banglore'>Banglore</option>");
	    out.print("<option value = 'Amravati'>Amravati</option>");

        out.print("</select>");
        out.print("</div>");
            
	    out.print("<div class = 'form-group col-md-4'>");           
	    out.print("<label>Category</label>");            
	    out.print("<select class = 'custom-select' id = 'inlineFormCustomSelectPref' name = 'category'>");
	    out.print("<option value =  "+j.getCategory()+">"+j.getCategory()+"</option>");             
        out.print("<option value = 'IT'>IT</option>");
        out.print("<option value = 'Developer'>Developer</option>");
        out.print("<option value = 'Banking'>Banking</option>");
        out.print("<option value = 'Engineer'>Engineer</option>");
        out.print("<option value = 'Teacher'>Teacher</option>");

        out.print("</select>");
        out.print("</div>");          	  
		             
		 out.print(" <div class = 'form-group col-md-4'>");           
		 out.print(" <label>Status</label>");           
		 out.print(" <select class = 'form-control' name = 'status'>");            
		 out.print(" <option class = 'Active' value ="+j.getStatus()+">"+j.getStatus()+"</option>");             
		 out.print(" <option class = 'Active' value = 'Active'>Active</option>");             
		 out.print(" <option class = 'Inactive' value = 'Inactive'>Inactive</option>");   
		 out.print("</select>");
	     out.print("</div>");          
	     out.print("</div>");          
		                  
		             
		 out.print(" <div class = 'form-group'>");     
	     out.print("<label>Enter Description</label>");     
		 out.print(" <textarea required rows = '4'  name = 'desc' class = 'form-control'>"+j.getDescription()+"</textarea>");         
	     out.print("</div>");          
		 out.print("<button class = 'btn btn-success'>Update Job</button>");   
		 out.print("</form>");    
	     out.print("</div>");          
	     out.print("</div>");          
	     out.print("</div>");          
	     out.print("</div>");          

		out.print("</body>");
		out.print("</html>");
		
	}

}
