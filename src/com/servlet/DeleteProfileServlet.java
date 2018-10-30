package com.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
@WebServlet("/delete-profile")
public class DeleteProfileServlet extends HttpServlet {
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException ,java.io.IOException {
				String sno= req.getParameter("sno");
				String status=ProfileDao.deleteProfileBySno(Integer.parseInt(sno));
				
				//Sending JSON data as response back to client 
				//write code to generate JSON response
				ApplicationMessage applicationMessage=new ApplicationMessage();
				applicationMessage.setStatus(status);
				applicationMessage.setMessage("hey! your profiel data has been successfully! from the database!");
				resp.setContentType("application/json");
				//profileVOs =convert this java object into JSON
				 //1. Convert object to JSON string
		        Gson gson = new Gson();
		        String json = gson.toJson(applicationMessage);
		        resp.getWriter().println(json);
		}
		
}
