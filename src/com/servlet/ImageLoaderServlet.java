package com.servlet;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/image-loader")
public class ImageLoaderServlet extends HttpServlet {
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException ,java.io.IOException {
				String sno= req.getParameter("sno");
				byte[] image=ProfileDao.findImageBySno(Integer.parseInt(sno));
				//writing image into the http response
				resp.setContentType("image/png");
				ServletOutputStream sos=resp.getOutputStream();
				if(image!=null){
					sos.write(image);
					sos.flush();
				}
				sos.close();
		}
		
}
