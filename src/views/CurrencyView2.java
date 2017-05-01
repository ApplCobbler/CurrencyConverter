package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import org.apache.derby.iapi.store.access.ColumnOrdering;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import models.CurrencyList;
import models.Database;

/**
 * @author Group 2
 * 
 *         This class is the main app window This section will primarily be
 *         where Greg works out of.
 * 
 *         Build the window Add the panels Add the input fields Add two dropdown
 *         menus: 1. Initial currency 2. Converted currency - this one will need
 *         to have an "ALL" option as well as the standard currencies. The ALL
 *         option should be the default selection when app loads. Add the submit
 *         button Add the content panel where the results will display centered
 *         in the UI and below the above elements This panel should be dynamic
 *         and will be different depending on if the user selects ALL or selects
 *         a specific currency
 * 
 *         New Line For Project right here.
 *
 *
 *         Test New Line Added
 *
 */
public class CurrencyView2 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	CurrencyList currencyList = new CurrencyList();

	// Set the size of the window
	static final int WIDTH = 1200, HEIGHT = 800;

	// Constructor
	public CurrencyView2() {
		super("Currency Converter (ver 1.0)");
		setSize(WIDTH, HEIGHT);
		
		setLayout(new GridLayout(3,1));
		this.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.white);
		topPanel.add(new TitlePanel1(), BorderLayout.NORTH);
		topPanel.add(new ControlPanel1(), BorderLayout.SOUTH);
		
		add(topPanel, BorderLayout.NORTH);
		add(new CurrencyPanel1(), BorderLayout.CENTER);
		// Add customizable panel

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible (true);
	}
}


class TitlePanel extends JPanel{
	
CurrencyList currencyList = new CurrencyList();
//This will display the Title of the app and the icon along the top
	private static final long serialVersionUID = 1L;
	
	private JLabel title;
	private JLabel titleIcon; 
	private JLabel syncIcon;
	private JLabel syncLabel;
	private JPanel titleFormat = new JPanel(new FlowLayout());
	private JPanel syncFormat = new JPanel(new FlowLayout());
	
	
	public TitlePanel(){
	this.setLayout(new BorderLayout());
	this.setBackground(Color.white);
	this.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
	
	//set up first Panel for displaying on left
	titleIcon = new JLabel(new ImageIcon("images/currencyIcon.png"));
	title = new JLabel("<html><font size=8>Currency Converter</font></html>");
	titleFormat.add(titleIcon);
	titleFormat.add(title);
	titleFormat.setBackground(Color.white);
	
	syncIcon = new JLabel(new ImageIcon("images/syncIcon.png"));
	syncLabel = new JLabel("<html><font size=3>Last Synced: " + currencyList.getLastSyncDate() + "</font></html> ");
	syncFormat.add(syncIcon);
	syncFormat.add(syncLabel);
	syncFormat.add(new JLabel("  "));
	syncFormat.setBackground(Color.white);

	this.add(titleFormat, BorderLayout.WEST);
	this.add(syncFormat, BorderLayout.EAST);
	
	}
	
	
	
}

class CurrencyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	EmptyPanel1 defaultPanel = new EmptyPanel1();
	
	public CurrencyPanel(){
		this.add(defaultPanel);
		//this.setBackground(Color.gray);
	}
	
	

}

class ResultsPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	//For when user presses submit
	public ResultsPanel(){
		
	}

}

class ControlPanel extends JPanel{
	
	CurrencyList currencyList = new CurrencyList();
	private static final long serialVersionUID = 1L;
	//This is where the main inputs and combo boxes are
	
	private  JButton enterButton,clearButton;
	private  JComboBox<String> baseCurrencySelection, outputCurrencySelection;
	private  JTextField inputAmount;
	private JLabel comparisonText; //Will say "and compare with: "
	private JLabel titleBuilder; //Will be used to show "[Input Currency]
	
	
	public ControlPanel(){
		this.setLayout(new FlowLayout());
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		inputAmount = new HintTextField1("Enter currency value");
		inputAmount.setPreferredSize(new Dimension (250, 35));
		baseCurrencySelection = new JComboBox<String>(currencyList.getCurrencyInfoList());
		baseCurrencySelection.setPreferredSize(new Dimension (250, 50));
		
		
		
		comparisonText = new JLabel(" and compare with: ");
		outputCurrencySelection = new JComboBox<String>(currencyList.getCurrencyInfoList());
		outputCurrencySelection.setPreferredSize(new Dimension (250, 50));
	
		enterButton = new JButton(new ImageIcon("images/submitIcon.png"));
		enterButton.setBorderPainted(false);
		
		this.add(inputAmount);
		this.add(baseCurrencySelection);
		this.add(comparisonText);
		this.add(outputCurrencySelection);
		this.add(enterButton);
	}
	
}


class EmptyPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	//For when user opens app and there's nothing selected to display yet.
	
	private JPanel helpPanel = new JPanel(new BorderLayout());
	private JPanel helpTextPanel = new JPanel(new BorderLayout());
	private JPanel helpIconPanel = new JPanel(new BorderLayout());
	private JLabel helpText;
	
	public EmptyPanel(){
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
		this.setBackground(Color.white);	
		helpText = new JLabel("<html><font size=6><p> To get started:<br/>"
				+ "1. Select a currency<br/>"
				+ "2. Enter an initial value (ex: 125.50) <br/>"
				+ "3. Select a currency to compare with, <br/>"
				+ "or select ALL to compare with all supported currencies</p></font></html>");
		
		helpIconPanel.setBackground(Color.white);
		helpIconPanel.add(new JLabel(new ImageIcon("images/helpIcon.png")), BorderLayout.NORTH);
		
		helpTextPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
		helpTextPanel.setBackground(Color.white);
		helpTextPanel.add(helpText);
		
		helpPanel.setBackground(Color.white);
		helpPanel.add(helpIconPanel, BorderLayout.WEST);
		helpPanel.add(helpTextPanel, BorderLayout.EAST);
		this.add(helpPanel, BorderLayout.CENTER);
		
		
		
	}
	
}


class HintTextField extends JTextField implements FocusListener {

	private static final long serialVersionUID = 1L;
	private final String hint;
	  private boolean showingHint;

	  public HintTextField(final String hint) {
	    super(hint);
	    this.hint = hint;
	    this.showingHint = true;
	    super.addFocusListener(this);
	  }

	  @Override
	  public void focusGained(FocusEvent e) {
	    if(this.getText().isEmpty()) {
	      super.setText("");
	      showingHint = false;
	    }
	  }
	  @Override
	  public void focusLost(FocusEvent e) {
	    if(this.getText().isEmpty()) {
	      super.setText(hint);
	      showingHint = true;
	    }
	  }

	  @Override
	  public String getText() {
	    return showingHint ? "" : super.getText();
	  }
	}
