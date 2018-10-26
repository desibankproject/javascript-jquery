package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class StudentsServlet
 */
@WebServlet("/students")
public class StudentsServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ProfileVO> profileVOs=ProfileDao.findProfiles();
		response.setContentType("application/json");
		//profileVOs =convert this java object into JSON
		 //1. Convert object to JSON string
        Gson gson = new Gson();
        String json = gson.toJson(profileVOs);
        response.getWriter().println(json);
	}

}
