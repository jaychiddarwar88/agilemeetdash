package agilemeetdash;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createuser")
public class createuser extends HttpServlet {
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		int id = 0;
		
		try {
			Connection conn = databaseconn.initializeDatabase();
			Statement stmt= conn.createStatement();
			String sql = "SELECT MAX(id) from userdetail";
		    ResultSet rs = stmt.executeQuery(sql);
		    while(rs.next()) {
		    	id = rs.getInt(1);
		    }
		    
			PreparedStatement st = conn.prepareStatement("insert into userdetail values(?, ?, ?, ?)"); 
			st.setInt(1, id+1);
			st.setString(2, req.getParameter("name"));
			st.setString(3, req.getParameter("email"));
			st.setString(4, req.getParameter("password"));
			st.executeUpdate(); 
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		PrintWriter out =res.getWriter();
//		out.println("hello working");
		
		res.sendRedirect("/agilemeetdash/");
	}
}
