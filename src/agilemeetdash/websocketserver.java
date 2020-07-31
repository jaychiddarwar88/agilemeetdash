package agilemeetdash;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

@ServerEndpoint("/websocket")
public class websocketserver {
	private static Set<Session> clients = 
		    Collections.synchronizedSet(new HashSet<Session>());
		  
		  @OnMessage
		  public void onMessage(String message, Session session) 
		    throws IOException, ClassNotFoundException, SQLException, JSONException {
		    System.out.println(message);
		    
		    JSONObject json = null;
		    String typemsg ="" ;
		    Connection conn = databaseconn.initializeDatabase();
		    json = new JSONObject(message);
			typemsg = json.getString("type");
			
			int lastid = 0;
			
			if ( typemsg.equals("assign")) {
				System.out.println("task is assgined");
				
				Statement stmt= conn.createStatement();
				String sql = "SELECT MAX(noteid) from capturednotes";
			    ResultSet rs = stmt.executeQuery(sql);
			    while(rs.next()) {
			    	lastid = rs.getInt(1);
			    }
			    System.out.println(lastid);
			    
			    lastid = lastid + 1;
				System.out.println(lastid);
			    PreparedStatement st = conn.prepareStatement("insert into capturednotes values(?, ?, ?, ?, ?, ?)"); 
				st.setInt(1, lastid);
				st.setString(2, json.getString("channelname"));
				st.setString(3, json.getString("channelname"));
				st.setString(4, json.getString("msgby"));
				st.setString(5, json.getString("date"));
				st.setString(6, json.getString("text"));
				st.executeUpdate(); 
			}
			
			else {
				synchronized(clients){
				      // Iterate over the connected sessions
				      // and broadcast the received message
				      for(Session client : clients){
				          client.getBasicRemote().sendText(message);
				      }
				    }
			}
		  }
		  
		  @OnOpen
		  public void onOpen (Session session) {
		  // Add session to the connected sessions set
		    clients.add(session);
		  }

		  @OnClose
		  public void onClose (Session session) {
		    // Remove session from the connected sessions set
		    clients.remove(session);
		  }

}
