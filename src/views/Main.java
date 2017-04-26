package views;

import java.sql.SQLException;
import models.Database;
import models.Parser;

/**
 * CMSC 495 - Spring 2017
 * Professor Dao
 * Group 2: Richard Wainwright, Dennie Carr, Greg Armstrong, Jik Oh
 *
 *Dennie's comment
 */
public class Main {

	//New Main Line Code

	/**
	 * Instantiate the app and open the main application window.
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		//Run this baby

		CurrencyView foo = new CurrencyView();
		foo.setVisible(true);
		Parser parser = new Parser();
		parser.showMeStream();
		// Connects to the DB
		Database.connectDB();
		// Drop the main table to test checkTable() and createTable()
		Database.dropTable("main");
		if (Database.checkTable() != true){
			Database.createTable();
		}
		// Disconnect from and shut down the DB
		Database.disconnectDB();
	}

}
