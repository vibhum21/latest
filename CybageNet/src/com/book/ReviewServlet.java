package com.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		Statement st;
		try {
			PrintWriter pw  =response.getWriter();
			st = con.createStatement();
			ResultSet rs =  st.executeQuery("select bname from book");
			pw.write("<html><body><form  method='post' action='WriteReview'>");
			while(rs.next())
			{
				pw.write("<input type='radio' name='list' value='"+rs.getString(1)+"'>"+rs.getString(1)+"<br>");
				
			}
			pw.write("<input type='submit' value='Give Review'></form></body></html>");
			pw.println("<form method='get' action='LogoutServlet'>");
			pw.println("<input type='submit' value='Logout'>");
			pw.println("</form>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
