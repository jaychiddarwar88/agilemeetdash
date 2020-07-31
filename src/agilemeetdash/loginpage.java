package agilemeetdash;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/")
public class loginpage extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)  throws IOException, ServletException{
		
//		res.sendRedirect("index.html");
		HttpSession session = req.getSession();
		ArrayList<String> channellist = new ArrayList<String>();
		String haschannel = "no" ;
		if(session.getAttribute("email") == null) {
			req.getRequestDispatcher("html/index.jsp").forward(req, res);
		}
		else {
			try {
				Connection conn = databaseconn.initializeDatabase();
				Statement stmt= conn.createStatement();
				String sql = "SELECT * from channelmember where memberemail = '"  + session.getAttribute("email") + "'";
			    ResultSet rs = stmt.executeQuery(sql);

			    while(rs.next()) {
			    		channellist.add(rs.getString(1));
			    		haschannel = "yes" ;
			    	}
			    req.setAttribute("haschannel", haschannel);
			    req.setAttribute("channellist", channellist);
			    
			    }
			
				
			catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session.setAttribute("exists", "true");
				req.getRequestDispatcher("html/dash.jsp").forward(req, res);
		}
		
	}
}
