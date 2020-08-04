package agilemeetdash;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

@WebServlet("/adminpage")
public class getadminpage extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		String channelname = req.getParameter("channelname");
		System.out.println(channelname);
		ArrayList<String> noteslist = new ArrayList<String>();
		JSONObject json = new JSONObject();
		JSONObject json1 = new JSONObject();
		JSONObject jsonlist = new JSONObject();
		ArrayList<String> memberlist = new ArrayList<String>();
		ArrayList<String> tasklist = new ArrayList<String>();
		HttpSession session = req.getSession() ;
		try {
			Connection conn = databaseconn.initializeDatabase();
			Statement stmt= conn.createStatement();
			String sql = "SELECT * from capturednotes where channelname = '" + channelname  +"'" ;
		    ResultSet rs = stmt.executeQuery(sql);
		    String adminemail  = "" ;
		    while(rs.next()) {
		    	json.put("nodeid", rs.getString(1));
		    	json.put("channelname", rs.getString(2));
		    	json.put("title", rs.getString(3));
		    	json.put("adminemail", rs.getString(4));
		    	json.put("datetime", rs.getString(5));
		    	json.put("msgvalue", rs.getString(6));
		    	adminemail = rs.getString(4);
		    	jsonlist.put((String)json.toString(), "");
		    	
		    	noteslist.add((String)json.toString());
		    }
		    rs.close();
		    stmt.close();
		    
		    Statement stmt2= conn.createStatement();
		    String sql2 = "SELECT * from channelmember where channelname = '" + channelname  +"'" ;
		    ResultSet rs2 = stmt2.executeQuery(sql2);
		    while(rs2.next()) {
		    	
		    	memberlist.add(rs2.getString(2));
		    }
		    	
		    
		    	// to add admin email to assign
		    	memberlist.remove(adminemail);
		    	
		    	
		    	Statement stmt1= conn.createStatement();
				String sql1 = "SELECT * from assignedtask where channelname = '"  + channelname + "'";
			    ResultSet rs1 = stmt1.executeQuery(sql1);

			    while(rs1.next()) {
				    	json1.put("taskid", rs1.getString(3));
				    	json1.put("channelname", rs1.getString(1));
				    	json1.put("duedate", rs1.getString(4));
				    	json1.put("status", rs1.getString(5));
			    		tasklist.add(json1.toString());
			    	}
		    	
//		    	System.out.println(noteslist.toString());
//		    	System.out.println(memberlist.toString());
		    	
			    req.setAttribute("tasklist", tasklist);
		    	req.setAttribute("channelname" , channelname);
			    req.setAttribute("noteslist", noteslist);
			    req.setAttribute("memberlist",  memberlist);
			    req.setAttribute("jsonlist", jsonlist);
		    }
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		req.getRequestDispatcher("html/adminpage.jsp").forward(req, res);
		
	}
}
