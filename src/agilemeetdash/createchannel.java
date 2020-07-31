package agilemeetdash;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/createchannel")
public class createchannel extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		String channelname = req.getParameter("channelname");
		boolean exists = true;
		HttpSession session = req.getSession();
		try {
			Connection conn = databaseconn.initializeDatabase();
			Statement stmt= conn.createStatement();
			String sql = "SELECT * from channeldetail where channelname = '" + channelname + "'" ;
		    ResultSet rs = stmt.executeQuery(sql);
		    exists = rs.next();
		    
		    if(exists) {
		    	
		    	session.setAttribute("exists", "true");
		    	
		    	req.getRequestDispatcher("/").forward(req, res);
		    	
		    }else {
		    	PreparedStatement st = conn.prepareStatement("insert into channeldetail values(?, ?)"); 
				st.setString(1, channelname);
				st.setString(2,  (String) session.getAttribute("email"));
				st.executeUpdate(); 
				
				PreparedStatement st2 = conn.prepareStatement("insert into channelmember values(?, ? ) " ) ;
				st2.setString(1, channelname);
				st2.setString(2,  (String) session.getAttribute("email"));
				st2.executeUpdate();
				
				session.setAttribute("exists", "false");
				req.getRequestDispatcher("/").forward(req, res);
		    }
		    
		    
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
