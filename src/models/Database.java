package models;

import java.sql.*;

/**
 * @@Comment from JIK
 * If you want to test this class, you have to copy Derby.jar to your library
 * and set classpath. If you can improve the codes below, please feel free to
 * correct and inform me. 
 * 
 */
public class Database {

	// JDBC driver name and database URL
	static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	// Connection URL
	static final String DB_URL = "jdbc:derby:IC2ADB;create=true";
	// Shutdown URL
	static final String SD_URL = "jdbc:derby:;shutdown=true";

	static Connection conn;
	static Statement stmt;
	static PreparedStatement pstmt;
	static ResultSet rs;

	/**
	 * This method establishes database connection
	 */
	public static Connection connectDB() throws SQLException, ClassNotFoundException {
		try {
			// Initializing Apache Derby driver
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(DB_URL);
			conn.setAutoCommit(false);
			System.out.println("Connecting to the database");
			// Check if the connection is made. If connection is good, current timestamp will be printed.
			if (conn != null) {
				try {
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select CURRENT_timestamp from sysibm.sysdummy1");
					while (rs.next()) {
						String confirmMessage = rs.getString(1);
						System.out.println(confirmMessage);
					}
					System.out.println("If you see the current time stamp, connection is established!");
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	// Check if MAIN table exists in the database. 
	public static boolean checkTable() throws SQLException {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from main");
			return true;
		} // If there is no MAIN table, returns false
		catch (SQLException ex) {
		}
		return false;
	}

	// Create the MAIN table
	public static void createTable() throws SQLException {
		String mainTable = "create table MAIN ("
				+ "base varchar(3) NOT NULL, "
				+ "date date NOT NULL, "
				+ "AUD double NOT NULL, "
				+ "BGN double NOT NULL, "
				+ "BRL double NOT NULL, "
				+ "CAD double NOT NULL, "
				+ "CHF double NOT NULL, "
				+ "CNY double NOT NULL, "
				+ "CZK double NOT NULL, "
				+ "DKK double NOT NULL, "
				+ "GBP double NOT NULL, "
				+ "HKD double NOT NULL, "
				+ "HRK double NOT NULL, "
				+ "HUF double NOT NULL, "
				+ "IDR double NOT NULL, "
				+ "ILR double NOT NULL, "
				+ "INR double NOT NULL, "
				+ "JPY double NOT NULL, "
				+ "KRW double NOT NULL, "
				+ "MXN double NOT NULL, "
				+ "MYR double NOT NULL, "
				+ "NOK double NOT NULL, "
				+ "NZD double NOT NULL, "
				+ "PHP double NOT NULL, "
				+ "PLN double NOT NULL, "
				+ "RON double NOT NULL, "
				+ "RUB double NOT NULL, "
				+ "SEK double NOT NULL, "
				+ "SGD double NOT NULL, "
				+ "THB double NOT NULL, "
				+ "TRY double NOT NULL, "
				+ "USD double NOT NULL, "
				+ "ZAR double NOT NULL)";                
		try {
			stmt = conn.createStatement();
			stmt.execute(mainTable);
			System.out.println("Main table created.");
			conn.commit();
		}
		catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Update the database

	public static void updateDB(Date date){

	}
	// Drop a table
	public static void dropTable(String tbName){
		String dropTB = "drop table " + tbName;
		try {
			stmt = conn.createStatement();
			stmt.execute(dropTB);
			System.out.println("Table " + tbName + " has been dropped.");
			conn.commit();
		}
		catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Disconnects from the DB
	public static void disconnectDB() throws SQLException {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
				DriverManager.getConnection(SD_URL);
			}           
			System.out.println("Database disconnected.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}