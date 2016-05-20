package com.book;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class WelcomeAdmin
 */
@WebServlet("/WelcomeAdmin")
public class WelcomeAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs =  request.getSession();
		hs.setAttribute("uname", request.getParameter("uname"));
		RequestDispatcher rd=request.getRequestDispatcher("WelcomeAdmin.html");  
		  rd.forward(request, response);  
		
		
	}

	
}
