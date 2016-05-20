package com.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
 * Servlet implementation class Select
 */
@WebServlet("/SelectServlet")
public class SelectServlet extends HttpServlet {
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
			ResultSet rs =  st.executeQuery("select bookname from books");
			pw.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width,initial-scale=1.0'>");
			pw.println("<style>label{font-family:sans-serif;display:block;padding-left:50px;padding-top: 20px;}label.display{padding-left:150px;width:100px;}span.heading{font-family:cursive;font-style:italic;font-size:30px;padding-left: 80px;padding-bottom: 20px;width:420px;position: absolute;}span{font-size:20px;display:inline-block;width:120px;padding-left:20px;}div.wrapper{background-color: bisque;border-radius: 10px;padding: 20px;border-color: red;border-width: 3px;border-style: solid;width: 450px;height: 200px;margin: auto;position: absolute;top: 0;bottom: 0;left: 0;right: 0;}h2.heading{top: 0;bottom: 0;left: 0;right: 0;}body{background-color: rgba(0,0,0,0,5);}input.btn1{width:120px;border: 0;background-color: #3bab53;height: 30px;font-size: 16px;color: white;}input.btn {border: 0;background-color: #3bab53;width: 80px;height: 30px;font-size: 16px;color: white;}</style></head>");
			pw.println("<body><div class='wrapper'><span class='heading'>Books Present !!! </span><br><br>");
				

			pw.write("<br><br><form  method='post' action='DisplayBooks'>");
			while(rs.next())
			{
				pw.write("<span><input type='checkbox' name='list' value='"+rs.getString(1)+"'>"+rs.getString(1)+"</span>");
				
			}
			pw.write("<br><label class='display'><input class='btn1' type='submit' value='Display Details'></label></form>");

			pw.println("<br><br><form method='get' action='LogoutServlet'>");
			pw.println("<input class='btn' type='submit' value='Logout'></label>");
			pw.println("</form></div></body></html>");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		
	}

}
