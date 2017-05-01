package models;
import java.io.*;
import java.sql.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import org.json.JSONObject;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
/* @@Comment from JIK If you want to test this class, you have to copy Derby.jar
 *           to your library and set classpath. If you can improve the codes
 *           below, please feel free to correct and inform me.
 *
 */
public class Database {

	// 1. Class Variables

	// For Database connection and SQL statements
	// JDBC driver name and database URL
	static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	// Connection URL
	static final String DB_URL = "jdbc:derby:IC2ADB;create=true";
	// Shutdown URL
	static final String SD_URL = "jdbc:derby:;shutdown=true";
	private static Connection conn;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

	// For data processing and manipulation
	// Today's date
	public static Date today = Calendar.getInstance().getTime();
	// Specific date
	private static String date;
	// specific base
	private static String base;
	// Currency array
	private static String currency_list;
	// rates array
	private static String rates_list;
	// currency list
	private static final List<String> list = Arrays.asList("AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK",
			"EUR", "GBP", "HKD", "HRK", "HUF", "IDR", "ILS", "INR", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP",
			"PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "USD", "ZAR");
	// Contain currencies and rates for query purpose (Expandable arrays)
	private static List<String> c_query = new ArrayList<>(Arrays.asList());
	private static List<Double> r_query = new ArrayList<>(Arrays.asList());
	// Initialize StringBuilders to create strings
	private static StringBuilder sb_c = new StringBuilder();
	private static StringBuilder sb_r = new StringBuilder();

	// 2. Database
	// Methods for Database connection and shutdown
	// Connects to the Database named IC2ADB
	public static void connectDB() throws Exception {
		try {
			// Initializing Apache Derby driver
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(DB_URL);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			System.out.println("Connected to the database.");
			// Check tables. If tables do not exist, create new tables.
			if (Database.checkTable() != true) {
				Database.createTable();
			}
		} catch (Exception e) {
			System.out.println("Connection cannot be made: " + e);
		}
	}

	// Disconnects from and shutdown the DB
	public static void disconnectDB() throws SQLException {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			System.out.println("Database disconnected.");
			DriverManager.getConnection(SD_URL);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// 3. Table check/creation
	// When the application starts, below 2 methods check for table and
	// if there isn't one, creates 32 tables for each currency.
	// Check for tables in the database.
	private static boolean checkTable() throws SQLException {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from USD");
			System.out.println("Tables already exists.");
			conn.commit();
			return true;
		} // If there is no MAIN table, returns false
		catch (SQLException ex) {
			System.out.println("Table doesn't exist. Creating tables...");
			return false;
		}
	}

	// Create 32 currency tables for each currency
	private static void createTable() throws Exception {
		StringBuilder table = new StringBuilder();
		String tableSQL;

		try {
			stmt = conn.createStatement();
			// Create tables for each currency type
			for (int i = 0; i < 32; i++) {
				table.append("create table " + list.get(i) + " (date date NOT NULL CONSTRAINT " + list.get(i)
						+ "_PK PRIMARY KEY, ");
				for (int j = 0; j < 32; j++) {
					if (i == j) {
						continue;
					}
					table.append(list.get(j) + " double NOT NULL, ");
				}
				// Delete the last comma and space and put )
				table.delete(table.length() - 2, table.length());
				table.append(")");
				// Change to a string
				tableSQL = table.toString();
				System.out.println(tableSQL);
				stmt.execute(tableSQL);
				System.out.println("Table " + list.get(i) + " was created.");
				table.delete(0, table.length());
			}
			conn.commit();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	
	// 4. DB Update.
	// Methods used to update Data to the DB from URL

	// Set a formatted date and base
	private static void setDateBase(Date sdate, String sbase) {
		
		//This should be in MM-DD-YYYY for purposes of displaying on the GUI - RW
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		date = df.format(sdate);
		base = sbase;
	}

	// Reads the content of the URL based on Date and base currency and return
	// as string.
	private static String readURL() throws Exception {
		String urlString = ("http://api.fixer.io/" + date + "?base=" + base);
		URL url = new URL(urlString);
		BufferedReader reader = null;
		try {

			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuilder buffer = new StringBuilder();
			int cp;
			while ((cp = reader.read()) != -1) {
				buffer.append((char) cp);
			}
			return buffer.toString();

		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	// Create lists for currencies and rates to update DB
	private static void createLists() throws Exception {
		// Separate rates JSON Object
		JSONObject json = new JSONObject(readURL());
		JSONObject rates = json.getJSONObject("rates");
		// Put "(" and other columns
		sb_c.append("(date, ");
		sb_r.append("('" + date + "', ");
		Iterator iter = rates.keys();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			sb_c.append(key + ", ");
			Double value = rates.getDouble(key);
			String s_value = value.toString();
			sb_r.append(s_value + ", ");
		}
		// Remove the comma and space and put ")"
		sb_c.delete(sb_c.length() - 2, sb_c.length());
		sb_r.delete(sb_r.length() - 2, sb_r.length());
		sb_c.append(")");
		sb_r.append(")");
		// Save the strings to class variables and reset string builders
		currency_list = sb_c.toString();
		sb_c.delete(0, sb_c.length());
		rates_list = sb_r.toString();
		sb_r.delete(0, sb_r.length());
	}

	// Update rates of all currencies based on a date
	public static void updateDB(Date udate) throws Exception {
		try {
			for (int i = 0; i < 32; i++) {
				setDateBase(udate, list.get(i));
				createLists();
				pstmt = conn
						.prepareStatement("insert into " + list.get(i) + " " + currency_list + " values" + rates_list);
				pstmt.execute();
				System.out.println("Updated table " + list.get(i) + " from the web on " + date + ".");
			}
			conn.commit();
		}
		// If data already exists, then print out the warning message.
		catch (Exception e) {
			System.out.println("Data for " + date + " already exist.");
			conn.commit();
		}
	}

	
	
	// 5. Query
	// Methods to query DB and return data
	//
	// Methods to query database for currency rates on
	// base currency for a certain date (do not return values)
	// *********** This is where we need to work to add more ****************
	// *********** exception list to warn the user **************************.
	public static void queryAllRates(String qbase, Date qdate) throws Exception {

		// Clear the lists first
		c_query.clear();
		r_query.clear();
		try {
			setDateBase(qdate, qbase);
			rs = stmt.executeQuery("select * from " + base + " where date = '" + date + "'");
			ResultSetMetaData meta = rs.getMetaData();
			int count = meta.getColumnCount();
			while (rs.next()) {
				for (int z = 0; z < count; z++) {
					if (z == 0) {
						continue;
					}
					c_query.add(meta.getColumnName(z + 1));
					r_query.add(rs.getDouble(z + 1));
				}
			}
			conn.commit();
		} catch (Exception e) {
			System.out.println(e);
			conn.commit();
		}
	}
	
	// Return a String list of currencies for one base currency (used with
	// queryAllrates)
	public static List<String> getAllCurrencies() {
		return c_query;
	}

	// Return a Double list of currencies for one base currency (used with
	// queryAllrates)
	public static List<Double> getAllRates() {
		return r_query;
	}

	// Return a rate for the specific base currency and target currency on a
	// date
	public static double getOneRate(String gbase, String target, Date gdate) throws SQLException {
		Double rate = null;
		try {
			setDateBase(gdate, gbase);
			rs = stmt.executeQuery("select " + target + " from " + base + " where date = '" + date + "'");
			conn.commit();
			while (rs.next()) {

				rate = rs.getDouble(1);
			}
			conn.commit();
		} catch (Exception e) {

			System.out.println(e);
			conn.commit();
		}
		return rate;
	}



	// For testing purpose
	// Drop a table
	public static void dropTable() throws Exception {
		try {
			for (int i = 0; i < 32; i++) {

				String dropTB = ("drop table " + list.get(i));

				stmt = conn.createStatement();
				stmt.execute(dropTB);
				System.out.println("Table " + list.get(i) + " has been dropped.");
			}
			conn.commit();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
