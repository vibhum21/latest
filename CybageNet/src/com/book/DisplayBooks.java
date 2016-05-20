package com.book;

import java.awt.print.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DisplayBooks
 */
@WebServlet("/DisplayBooks")
public class DisplayBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession hs = request.getSession();
			if(hs.getAttribute("username")==null)
			{
				response.sendRedirect("Login.html");
			}
			Class.forName("com.mysql.jdbc.Driver");
			try {
				
				 con = DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","root");
				 System.out.println("Connected Successfully");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement st,st1;
		try {
			 String[] bookname;
			BookDAO[] booklist = new BookDAO[100];;
			 BookDAO book;
			 bookname = request.getParameterValues("list");
			 if (bookname != null) 
			   {
			      for (int i = 0; i < bookname.length; i++) 
			      {
			    	  st = con.prepareStatement("select * from books where bookname=?");
			    	  st.setString(1, bookname[i]);
			    	  ResultSet rs = st.executeQuery();
			    	  while(rs.next())
			    	  {  
				    		  book =  new BookDAO(rs.getInt(1), rs.getInt(3), rs.getString(2), rs.getString(4), rs.getString(5));
					    	  booklist[i]=book;
			    	  }
			      }
			   }
			 PrintWriter pw  = response.getWriter();
			 for(BookDAO b: booklist)
			 {
				if(b!=null)
				 pw.println(b+"<br>");
			 }
			
			 response.setContentType("text/html");
			 pw.println("<html><body><form method='get' action='LogoutServlet'>"
		
		+"<input type='submit' value='Logout'>"
				+"</form></body></html>");
			} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}

}
