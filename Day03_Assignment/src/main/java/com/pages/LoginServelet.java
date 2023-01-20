package com.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.UserDALimpl;
import pojo.User;
import utils.DBUtils;




@WebServlet(value="/validate",loadOnStartup = 1)
public class LoginServelet extends HttpServlet {
	
	
	
	UserDALimpl userDao;
	
	@Override
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		
		try(PrintWriter pw= resp.getWriter()){
			pw.write("<h2>Welcome To Login Validation</h2>");
			System.out.println("---inside doPost---"+getClass());
		
		String userName = req.getParameter("txtuser");
		String pwd = req.getParameter("txtpwd");
		
		User userObj= userDao.validateUser(userName, pwd);
		
		if(userObj == null) {
			System.out.println("---invalid---");
			pw.write("<h2>Invalid User  <a href='login.html'>Retry </a> </h2>");
		}
		else {
			System.out.println("---valid User---");
			pw.write("<h2>Valid User </h2>");
			pw.write("Welcome :" + userObj.getName());
			pw.write("<h3>User " + userObj + " </h3>");
			
			resp.sendRedirect("welcome");
		}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		System.out.println("--inside destroy---" + getClass());
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			DBUtils.openConnection();
			userDao = new UserDALimpl();
			
		}
		catch (Exception e) {
			
		}
		
		System.out.println("---inside init---"+getClass());
	}

	
	
}
