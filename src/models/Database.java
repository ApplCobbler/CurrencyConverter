
package models;
import java.sql.*;

/**
 * @author richardwainwright
 *
 *This section will be where Jik primarily works out of
 *
 *We will need the code for instantiating the DB. I have no problem with whatever implementation we go 
 *with, but I'll be working heavily with other classes that will interact with the DD.
 *
 *I have included some basic boilerplate code that I cannot confirm is any good but hopefully points us in
 *the right direction. I also added a sql file to the project in the event that you'd prefer to work using 
 *sql scripts and a sql db instead of the java native version. 
 *
 *Java Database boilerplate code provided from: 
 *https://www.tutorialspoint.com/jdbc/jdbc-create-database.htm
 *
 *
 *We are trying to get this shit working
 *Comment 
 */
public class Database {
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/";

	   //  Database credentials
	   static final String USER = "username";
	   static final String PASS = "password";
	   
	   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating database...");
	      stmt = conn.createStatement();
	      
	      String sql = "CREATE DATABASE STUDENTS";
	      stmt.executeUpdate(sql);
	      System.out.println("Database created successfully...");
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}//end main

}
=======
package models;
import java.sql.*;

/**
 * @author richardwainwright
 *Greg testing
 *This section will be where Jik primarily works out of
 *
 *We will need the code for instantiating the DB. I have no problem with whatever implementation we go 
 *with, but I'll be working heavily with other classes that will interact with the DD.
 *
 *I have included some basic boilerplate code that I cannot confirm is any good but hopefully points us in
 *the right direction. I also added a sql file to the project in the event that you'd prefer to work using 
 *sql scripts and a sql db instead of the java native version. 
 *
 *Java Database boilerplate code provided from: 
 *https://www.tutorialspoint.com/jdbc/jdbc-create-database.htm
 *
 */
public class Database {
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/";

	   //  Database credentials
	   static final String USER = "username";
	   static final String PASS = "password";
	   
	   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating database...");
	      stmt = conn.createStatement();
	      
	      String sql = "CREATE DATABASE STUDENTS";
	      stmt.executeUpdate(sql);
	      System.out.println("Database created successfully...");
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}//end main

}
>>>>>>> refs/remotes/origin/master
