package agilemeetdash;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reviewtask")
public class reviewtask extends HttpServlet{
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		String taskid = req.getParameter("taskid");
		
		String sql = "UPDATE assignedtask SET status = 'review'  WHERE task = '" + taskid + "'" ;
			    
			    try {
					Connection conn = databaseconn.initializeDatabase();
					Statement stmt= conn.createStatement();
					//String sql = "SELECT * from capturednotes where channelname = '" + channelname  +"'" ;
					stmt.executeUpdate(sql);
				    
				    }
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				PrintWriter out = res.getWriter();
				out.println("working" );
		
	}
}
