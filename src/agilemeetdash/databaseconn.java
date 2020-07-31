package agilemeetdash;

import java.sql.Connection; 

import java.sql.DriverManager; 
import java.sql.SQLException; 
  
// This class can be used to initialize the database connection 
public class databaseconn { 
    protected static Connection initializeDatabase() 
        throws SQLException, ClassNotFoundException 
    { 
        // Initialize all the information regarding 
        // Database Connection 
    	
//    	String dbDriver = "org.postgresql.Driver"; 
//        String dbURL = "jdbc:postgresql://ec2-54-234-28-165.compute-1.amazonaws.com:5432/d8jh2eal24fbin"; 
//        // Database name to access 
//        String dbName = "agilemeeting?serverTimezone=UTC"; 
//        String dbUsername = "myldihbwoffyoo"; 
//        String dbPassword = "07ccc08dea573902b33922fb45bd6e27c030a2070bbe56859672c3ee65757281"; 
//
//        Class.forName(dbDriver); 
//        Connection con = DriverManager.getConnection(dbURL, dbUsername, dbPassword ); 
//        return con; 
    	
    	
        String dbDriver = "com.mysql.jdbc.Driver"; 
        String dbURL = "jdbc:mysql:// localhost:3306/"; 
        // Database name to access 
        String dbName = "agilemeeting?serverTimezone=UTC"; 
        String dbUsername = "root"; 
        String dbPassword = "jay"; 

        Class.forName(dbDriver); 
        Connection con = DriverManager.getConnection(dbURL + dbName, 
                                                     dbUsername,  
                                                     dbPassword); 
        return con; 
    } 
} 