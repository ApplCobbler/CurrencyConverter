package views;
import models.Database;

/**
 * CMSC 495 - Spring 2017 Professor Dao Group 2: Richard Wainwright, Dennie
 * Carr, Greg Armstrong, Jik Oh
 *
 */
public class Main {
	/**
	 * Instantiate the app and open the main application window.
	 */

	public static void main(String[] args) throws Exception {

		// Run this baby
		CurrencyView foo = new CurrencyView();
		Database.connectDB();	

		foo.setVisible (true);
		Database.updateDB(Database.today);
		Database.updateDB(Database.today, 15);

	}
}
