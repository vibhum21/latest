package com.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession hs = request.getSession();
		String name=(String) hs.getAttribute("username");
		//System.out.println(name);
@SuppressWarnings("unchecked")
HashMap<String, Date> map=(HashMap<String, Date>) hs.getAttribute("map");
		
		Iterator<Map.Entry<String, Date>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
		    Map.Entry<String, Date> entry = entries.next();
		    if(name.equals(entry.getKey()))
		    {
		    	entries.remove();
		    }
		    hs.setAttribute("map", map);
		    /*while (entries.hasNext()) {
		    System.out.println("Name = " + entry.getKey() + ", Login Time = " + entry.getValue());
		    }*/hs.invalidate();
		 RequestDispatcher rd=request.getRequestDispatcher("Login.html");  
		 rd.forward(request, response);  
		
		
	}
	}
	}
