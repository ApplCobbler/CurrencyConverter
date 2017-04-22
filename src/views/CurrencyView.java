package views;
import java.awt.*;
import javax.swing.*;


/**
 * @author Group 2
 * 
 * This class is the main app window
 * This section will primarily be where Greg works out of.
 * 
 * Build the window
 * Add the panels
 * Add the input fields
 * Add two dropdown menus:
 * 1. Initial currency
 * 2. Converted currency - this one will need to have an "ALL" option as well as the standard currencies. 
 * The ALL option should be the default selection when app loads.
 * Add the submit button
 * Add the content panel where the results will display centered in the UI and below the above elements
 * This panel should be dynamic and will be different depending on if the user selects ALL or selects 
 * a specific currency
 * 
 * New Line For Project right here.
 *
 *
 *Test New Line Added
 *
 */
public class CurrencyView extends JFrame{
	private static final long serialVersionUID = 1L;
	
	//Set the size of the window
	static final int WIDTH = 600, HEIGHT = 600;
	
	//Constructor
	public CurrencyView(){
	super("Currency Converter (ver 1.0)");
	setSize(WIDTH, HEIGHT);
	
	//Add customizable panel
	add(new CurrencyPanel());
	
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
}
	class CurrencyPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		//Create main views, lay them out accordingly
		//Add in all buttons within this panel
		
	}

