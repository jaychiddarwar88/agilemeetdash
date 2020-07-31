package agilemeetdash;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sendgrid.*;


@WebServlet("/assigntask")
public class ajaxassigntask extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		
		String taskid = req.getParameter("noteid");
		String channelname = req.getParameter("channelname");
		String newdate = req.getParameter("newdate");
		String memberemail = req.getParameter("memberemail");
		
//		System.out.println(taskid);
//		System.out.println(channelname);
//		System.out.println(newdate);
//		System.out.println(memberemail);
		PrintWriter out = res.getWriter();
		
		try {
			Connection conn = databaseconn.initializeDatabase();
			Statement stmt= conn.createStatement();
			
			PreparedStatement st = conn.prepareStatement("insert into assignedtask values(?, ?, ?, ?)"); 
			st.setString(1, channelname );
			st.setString(2, memberemail);
			st.setString(3, taskid);
			st.setString(4, newdate);
			st.executeUpdate(); 
			
			out.println("assigned to : " + memberemail);
			emailhandler.sendmail(memberemail );
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
}