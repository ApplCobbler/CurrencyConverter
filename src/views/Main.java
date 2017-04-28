package views;

import models.Database;

/**
 * CMSC 495 - Spring 2017 Professor Dao Group 2: Richard Wainwright, Dennie
 * Carr, Greg Armstrong, Jik Oh
 *
 * Dennie's comment
 */
public class Main {

	// New Main Line Code

	/**
	 * Instantiate the app and open the main application window.
	 */
	public static void main(String[] args) throws Exception {


		// Run this baby

		CurrencyView foo = new CurrencyView();
		foo.setVisible(true);

		// Start database and check for/create tables
		Database.connectDB();
		// Update database for all currencies with a set date.
		Database.updateDB(Database.today);
		// Query rates for all currencies on base currency "USD" for today.
		Database.queryAllRates("USD", Database.today);
		// Prints out the names of currencies and rates
		System.out.println(Database.getAllCurrencies());
		System.out.println(Database.getAllRates());
		// Get a rate for the specific base and target currencies for today
		System.out.println(Database.getOneRate("USD", "KRW", Database.today));
		// Disconnect and shutdown DB
		Database.disconnectDB();

	}

}
