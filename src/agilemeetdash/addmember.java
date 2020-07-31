package agilemeetdash;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addmember")
public class addmember extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		
		HttpSession session = req.getSession();
		String email = req.getParameter("memberemail");
		String channelname = (String) session.getAttribute("channelname");
		System.out.println(channelname);
		
		try {
			Connection conn = databaseconn.initializeDatabase();
			Statement stmt= conn.createStatement();
		    
			PreparedStatement st = conn.prepareStatement("insert into channelmember values(?, ?)"); 
			st.setString(1, channelname);
			st.setString(2,  email);
			st.executeUpdate(); 
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		req.getRequestDispatcher("adminpage").forward(req, res);
		
	}

}
