package agilemeetdash;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

@WebServlet("/trackerpage")
public class trackerpage extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		HttpSession session = req.getSession();
		//ArrayList<String> channellist = new ArrayList<String>();
		//String haschannel = "no" ;
		JSONObject json = new JSONObject();
		JSONObject jsonnotes = new JSONObject();
		ArrayList<String> noteslist = new ArrayList<String>();
		Set<String> uniqtaskid  = new HashSet<String>(); 
		ArrayList<String> tasklist = new ArrayList<String>();
		String channelname = req.getParameter("channelname");
		
			long startTime = System.nanoTime();
			try {
				Connection conn = databaseconn.initializeDatabase();

			    Statement stmt1= conn.createStatement();
				String sql1 = "SELECT * from assignedtask where channelname = '"  +channelname + "'";
			    ResultSet rs1 = stmt1.executeQuery(sql1);

			    while(rs1.next()) {
			    		json.put("taskid", rs1.getString(3));
			    		json.put("channelname", rs1.getString(1));
			    		json.put("duedate", rs1.getString(4));
			    		json.put("status", rs1.getString(5));
			    		json.put("memberemail", rs1.getString(2));
			    		//uniqtaskid.add(rs1.getString(3));
			    		tasklist.add(json.toString());
			    	}
			   
			    	Statement stmt2= conn.createStatement();
					String sql2 =  "SELECT * from capturednotes where channelname = '" + channelname + "'";
					System.out.println(sql2);
				    ResultSet rs2 = stmt2.executeQuery(sql2);

				    while(rs2.next()) {
				    	jsonnotes.put("noteid", rs2.getString(1));
				    	jsonnotes.put("channelname", rs2.getString(2));
				    	jsonnotes.put("title", rs2.getString(3));
				    	jsonnotes.put("adminemail", rs2.getString(4));
				    	jsonnotes.put("datetime", rs2.getString(5));
				    	jsonnotes.put("msgvalue", rs2.getString(6));
				    	noteslist.add((String)jsonnotes.toString());
				    	}
			    }
			catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				req.setAttribute("tasklist", tasklist);
				req.setAttribute("noteslist", noteslist);
				session.setAttribute("exists", "true");
				long elapsedTime = System.nanoTime() - startTime;
			     
		        System.out.println("Total time to execute above code is (in millis) : "+ elapsedTime/1000000);
				req.getRequestDispatcher("html/tracker.jsp").forward(req, res);
	}
}
