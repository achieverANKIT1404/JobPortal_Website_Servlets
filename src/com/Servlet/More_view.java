package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.JobDao;
import com.entity.Jobs;

@WebServlet("/moreview")
public class More_view extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		  
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				
				HttpSession session = request.getSession(false);

				out.print("<html>");
				out.print("<head>");
				out.print("<title>JobHunt | Jobs</title>");
				out.print("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css' integrity='sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm' crossorigin='anonymous'>");
				out.print("<link rel = 'stylesheet' href = 'all_components/style.css'>");
				out.print("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css' integrity='sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==' crossorigin='anonymous' referrerpolicy='no-referrer' />");
				
				out.print("<style>");
				out.print(".container{width:70%;}");
				out.print("h2{color:navy;}");

				out.print("</style>");
				out.print("</head>");
				out.print("<body style = 'background-color:#f0f1f2;'>");
				
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
		        
		        out.print("<li class='nav-item'><a class='nav-link' href='home'><i class='fa-solid fa-eye'></i> View All Jobs</a></li>");
			     
		 	   
			    out.print("</ul>"); 
			    out.print("<form class='form-inline my-2 my-lg-0'>"); 
			    
				String name = (String) session.getAttribute("name");

			    out.print(" <a href = '#' class = 'btn btn-light mr-2'><i class='fa fa-user'></i> "+name+"</a>"); 
			    out.print("<a href = 'logout' class = 'btn btn-light'><i class='fa fa-sign-in-alt'></i> Logout</a>");    
			    out.print("</form>"); 
			    out.print("</div>");
			    out.print("</nav>");
			    
			    out.print("<div class = 'container'>");
			    out.print("<div class = 'row'>");
			    out.print("<div class = 'col-md-12'>");
				out.print("<h2 class = 'text-center  mt-4'><b>All Jobs</b></h2>");

			    
			    String loc = request.getParameter("loc");
			    String cat = request.getParameter("cat");
			    
			    JobDao dao = new JobDao(DBConnect.getConn());
			    ArrayList<Jobs> list = null;
			    
			    if("lo".equals(loc) && "ca".equals(cat)){
			    	
			    	list = new ArrayList<Jobs>();
			    }
			    else if("lo".equals(loc) || "ca".equals(cat)){
			    	
			    	list = dao.getJobORLocAndCat(cat, loc);
			    }
			    else{
			    	
			    	list = dao.getJobANDLocAndCate(cat, loc);
			    }
			    
			    if(list.isEmpty()){
			    	
			    	out.print("<h4 class = 'text-center text-danger'>Job Not Available</h4>");
			    }
			    
			    if(list!=null){
			    	
			    	for(Jobs j : list){
			    		
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
			       		out.print("<input type = 'text' class = 'form-control form-control-sm' value = 'Location: "+j.getLocation()+"'  readonly>");          
			       	    out.print("</div>");     
			       		out.print("<div class = 'form-group col-md-3'>");
			       		out.print("<input type = 'text' class = 'form-control form-control-sm' value = 'Category: "+j.getCategory()+"'  readonly>");                  
			       		out.print("</div>");                         
			       		out.print("</div>");              
			       		                
			       		out.print("<h6>Publish Date : "+j.getPdate().toString()+"</h6>");        
			       		out.print("<div class = 'text-center'>");
			       		out.print("<a href = 'https://www.linkedin.com/' class = 'btn btn-sm bg-success text-white'>Apply Now</a>");
			       		out.print("</div>");
			       		out.print("</div>");
			       		out.print("</div>");

			    	}
			    }
			    else{
			    	
			    	out.print("<h2 class = 'text-center text-danger mt-8' >Job Not Available</h2>");
			    }
			    out.print("</div>");
	       		out.print("</div>");
	       		out.print("</div>");
			    
			    out.print("</body>");
				out.print("</html>");
	}

}
