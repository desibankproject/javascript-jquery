package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
@WebServlet("/seach-profile")
public class SearchStudentServlet extends HttpServlet {
	
	/*public static String getBody(HttpServletRequest request) throws IOException {

	    String body = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;

	    try {
	        InputStream inputStream = request.getInputStream();
	        if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            char[] charBuffer = new char[128];
	            int bytesRead = -1;
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                stringBuilder.append(charBuffer, 0, bytesRead);
	            }
	        } else {
	            stringBuilder.append("");
	        }
	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }

	    body = stringBuilder.toString();
	    return body;
	}*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String payloadRequest = request.getReader().lines()
			    .reduce("", (accumulator, actual) -> accumulator + actual);
		System.out.println("(##(#(#(#(");
		System.out.println(payloadRequest);
		 Gson gson = new Gson();
		InputData inputData=gson.fromJson(payloadRequest, InputData.class);
		List<ProfileVO> profileVOs=ProfileDao.findProfilesByEmail(inputData.getSearchText());
		response.setContentType("application/json");
		//profileVOs =convert this java object into JSON
		 //1. Convert object to JSON string
        String json = gson.toJson(profileVOs);
        response.getWriter().println(json);
	}

}
