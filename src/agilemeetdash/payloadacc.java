package agilemeetdash;

import java.io.BufferedReader;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.util.*;  
import java.util.Enumeration;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import jdk.nashorn.internal.parser.JSONParser;

@WebServlet("/payload")
public class payloadacc extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("api working");
		
		JSONObject obj = new JSONObject();
		
		PrintWriter out = response.getWriter();
		//String authorization = request.getHeader("authorization");
		
//		String message = request.getHeader("text");
//		System.out.println(message);

		StringBuilder buffer = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        buffer.append(line);
	    }
	    String data = buffer.toString();
	    System.out.println(data);
	    
	    JSONObject json;
	    String message ="" ;
		try {
			json = new JSONObject(data);
			message = json.getString("text");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
		
//		Enumeration headerNames = request.getHeaderNames();
//		while (headerNames.hasMoreElements()) {
//            String key = (String) headerNames.nextElement();
//            String value = request.getHeader(key);
//            if(key.equals("authorization"))
//        }
		System.out.println(message);
		String splitter = "</at>";
		String[] parts = message.split(splitter);
		String part1 = parts[0];
		part1 = part1.replace("<at>", "");
		String part2 = parts[1];
		part1 = part1.trim();
		part2 = part2.trim();
	    
		try {
			obj.put("type", "message");
			obj.put("text", "jay chiddarwar : your message : "  + part1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// next code from here
		try {
			Connection conn = databaseconn.initializeDatabase();
			Statement stmt= conn.createStatement();
			
//			String sql = "SELECT MAX(id) from userdetail";
//		    ResultSet rs = stmt.executeQuery(sql);
			
//		    while(rs.next()) {
//		    	id = rs.getInt(1);
//		    }
//		    
			PreparedStatement st = conn.prepareStatement("insert into notestodash values(?, ?)"); 
			
			st.setString(1, part1);
			st.setString(2, part2);
			
			st.executeUpdate(); 
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(obj);
		
	}
}
