
package models;
// ## Initialization section ##
import java.sql.*;
import java.io.*;

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
 *This is Jik's comment.
 *For the local database, I am using Apache Derby, which is free and can be embedded to the application easily.
 *How to setup and install the environment:
 *https://builds.apache.org/job/Derby-docs/lastSuccessfulBuild/artifact/trunk/out/getstart/index.html
 *
 * *Comment 
 */
public class Database {	
	public static void main(String[] args) {

		// Variables
		// Define the driver to use
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		// The database Name
		String dbName="CurrencyDB";
		// Define the derby connection URL to use
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";


		Connection conn = null;
		Statement stmt;		
		String createMain = "CREATE TABLE MAIN "
				+ "(ID INT NOT NULL CONSTRAINT PK_MAIN_ID PRIMARY KEY, "
				+ "ENTRY_DATE DATE NOT NULL, "
				+ "BASE VARCHAR(3) NOT NULL, "
				+ "AUD DOUBLE(5,5) NOT NULL, "
				+ "BGN DOUBLE(5,5) NOT NULL, "
				+ "BRL DOUBLE(5,5) NOT NULL, "
				+ "CAD DOUBLE(5,5) NOT NULL, "
				+ "CHF DOUBLE(5,5) NOT NULL, "
				+ "CNY DOUBLE(5,5) NOT NULL, "
				+ "CZK DOUBLE(5,5) NOT NULL, "
				+ "DKK DOUBLE(5,5) NOT NULL, "
				+ "GBP DOUBLE(5,5) NOT NULL) ";

		// JDBC code sections
		// Beginning of primary DB access section
		// ## BOOT DATABASE SECTION ##
		try{
			// Create (for the first time) and connect to the database.
			// The driver is loaded automatically.
			conn = DriverManager.getConnection(connectionURL);
			System.out.println("Connected to database " + dbName);

			// ## INITIAL SQL SECTION ##
			// Create a statement to issue simple commands.

			stmt = conn.createStatement();
			// Call utility method to check if table exists. 
			// Create the table if needed
			if (! Chk4Table(conn))
			{
				System.out.println ("....Creating new Main table");
				stmt.execute(createMain);
			}
			stmt.close();
			conn.close();
			System.out.println("Connection Closed");

			// ## DATABASE SHUTDOWN SECTION ##
			if (driver.equals("org.apache.derby.jdbc.EmbeddedDriver")) {
				boolean gotSQLExc = false;
				try {
					DriverManager.getConnection("jdbc:derby:;shutdown=true");
				} catch (SQLException se) {
					if (se.getSQLState().equals("XJ015")) {
						gotSQLExc = true;
					}
				}
				if (!gotSQLExc) {
					System.out.println("Database did not shut down normally");
				} else {
					System.out.println("Database shut down normally");
				}
			}

		}
		catch (Throwable e)  {   
			/*       Catch all exceptions and pass them to 
			 *       the Throwable.printStackTrace method  */
			System.out.println(" . . . exception thrown:");
			e.printStackTrace(System.out);
		}

	}


	// Checking for the main table (initial startup) 
	public static boolean Chk4Table (Connection conTst ) throws SQLException {
		try {
			Statement s = conTst.createStatement();
			s.execute("SELECT * FROM main;");
		}  catch (SQLException sqle) {
			String theError = (sqle).getSQLState();
			// If table exists will get -  WARNING 02000: No row was found 
			if (theError.equals("42X05"))   // Table does not exist
			{  return false;
			}  else if (theError.equals("42X14") || theError.equals("42821"))  {
				System.out.println("Chk4Table: Incorrect table definition");
				throw sqle;   
			} else { 
				System.out.println("Chk4Table: Unhandled SQLException");
				throw sqle; 
			}
		}
		//  If table exists, then returns true.
		return true;
	} 


}




