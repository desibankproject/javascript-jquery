package com.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

/**
 * Servlet implementation class ProfileUploadServlet
 */
@WebServlet("/profileUpload")
@MultipartConfig(maxFileSize = 16177215)	// upload file's size up to s
public class ProfileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// gets values of text fields
		String sno = request.getParameter("sno");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String mobile = request.getParameter("mobile");
		//This is special code we have to read image 
		InputStream inputStream = null;	// input stream of the upload file
		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("photo");
		if (filePart != null) {
					// prints out some information for debugging
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());
					// obtains input stream of the upload file
					inputStream = filePart.getInputStream();
		}
		
		Timestamp doe=new Timestamp(new Date().getTime());
		ApplicationMessage applicationMessage=new ApplicationMessage();
		applicationMessage.setStatus("success");
		if("0".equals(sno)){
			String message=ProfileDao.save(name, email, gender, mobile, inputStream, doe);
			applicationMessage.setMessage(message);
		}else{
			String message=ProfileDao.update(Integer.parseInt(sno),name, email, gender, mobile, inputStream);
			applicationMessage.setMessage(message);
		}
		//write code to generate JSON response
		response.setContentType("application/json");
		//profileVOs =convert this java object into JSON
		 //1. Convert object to JSON string
        Gson gson = new Gson();
        String json = gson.toJson(applicationMessage);
        response.getWriter().println(json);
		
	}

}
