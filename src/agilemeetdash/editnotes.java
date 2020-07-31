package agilemeetdash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editnotes")
public class editnotes extends HttpServlet{
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		int noteid = Integer.parseInt(req.getParameter("noteid"));
		String newtitle = (String) req.getParameter("newtitle");
		
		
		//  to be removed
		System.out.println(noteid);
	    System.out.println(newtitle);
	    
	    String sql = "UPDATE capturednotes SET title = '" + newtitle + "'  WHERE noteid = " + noteid ;
	    
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
		
		req.getRequestDispatcher("/adminpage").forward(req, res);
		
	}
	

}
