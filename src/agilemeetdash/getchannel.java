package agilemeetdash;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/getchannel")
public class getchannel extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		String channelname = req.getParameter("channelname");
		HttpSession session = req.getSession();
		String useremail = (String) session.getAttribute("email");
		boolean isadmin = false;
		try {
			Connection conn = databaseconn.initializeDatabase();
			Statement stmt= conn.createStatement();
			String sql = "SELECT * from channeldetail where channelname = '"  + channelname + "'";
		    ResultSet rs = stmt.executeQuery(sql);

		    while(rs.next()) {
		    		if(rs.getString(2).equals(useremail)) {
		    			isadmin = true;
		    		}
		    	}
		    req.setAttribute("isadmin", isadmin);
		    req.setAttribute("channelname", channelname);
		    session.setAttribute("channelname" , channelname);
		    //System.out.println(channelname);
		    req.getRequestDispatcher("html/channel.jsp").forward(req, res);
		    
		 }	
		catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
