package com.book;

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
 * Servlet implementation class AddReview
 */
@WebServlet("/AddReview")
public class AddReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con;
		PreparedStatement pst;
		String rev=request.getParameter("review");
		HttpSession session=request.getSession();
		PrintWriter pw = response.getWriter();
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");

		String uname=(String)session.getAttribute("username");
		//int book_id=(int) session.getAttribute("bid");
		String s = (String) session.getAttribute("bid");
		int bid = Integer.parseInt(s);
		String review=request.getParameter("review");
		String bname = (String) session.getAttribute("bname");
		pst=con.prepareStatement("insert into review values (?,?,?,?) ");
		pst.setInt(1, bid);
		pst.setString(2, bname);
		pst.setString(3, review);
		pst.setString(4, uname);
		pst.executeUpdate();
		pw.println("<div class='wrapper'>");
		pw.println("<label>");
		pw.println("<span class='heading'>Your Review have been Recorded</span><br>");
		pw.println("</label></div>");
		 }
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
