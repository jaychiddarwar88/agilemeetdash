package agilemeetdash;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

@WebServlet("/login")
public class loginuser extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		boolean loginres = false;
		String dbpass = "";
		String username = "" ;
		HttpSession session = req.getSession();
		ArrayList<String> channellist = new ArrayList<String>();
		String haschannel = "no" ;
		
		try {
			Connection conn = databaseconn.initializeDatabase();
			Statement stmt= conn.createStatement();
			String sql = "SELECT * from userdetail " ;
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    
		    while(rs.next()) {
		    	if( rs.getString(3).equals(req.getParameter("email")) && rs.getString(4).equals(req.getParameter("password") )) {
		    		username = rs.getString(2);
		    		loginres = true;
		    	}
		    }
			
		    if(loginres) {
		    	Statement stmt1= conn.createStatement();
		    	String sql1 = "SELECT * from channelmember where memberemail = '"  + req.getParameter("email") + "'";
			    ResultSet rs1 = stmt1.executeQuery(sql1);

			    while(rs1.next()) {
			    		channellist.add(rs1.getString(1));
			    		haschannel = "yes" ;
			    	}
			    req.setAttribute("haschannel", haschannel);
			    req.setAttribute("channellist", channellist);
		    }
		    
		    
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//HttpSession session = req.getSession();
		
		if(loginres) {
			session.setAttribute("email", req.getParameter("email"));
			session.setAttribute("username", username);
			session.setAttribute("exists", "f");
			req.getRequestDispatcher("html/dash.jsp").forward(req, res);
			
			
		}
		else {
			req.getRequestDispatcher("html/index.jsp").forward(req, res);
		}
		
	}

}
