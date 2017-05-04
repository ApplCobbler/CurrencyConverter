package views;

import java.awt.*;
import javax.swing.*;
import org.apache.derby.iapi.store.access.ColumnOrdering;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

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
		topPanel.add(new TitlePanel(), BorderLayout.NORTH);
	//	topPanel.add(new ControlPanel(), BorderLayout.SOUTH);
		
		add(topPanel, BorderLayout.NORTH);
		add(new ContentPanel(), BorderLayout.CENTER);
		
		WindowListener exitListener = new WindowAdapter() {

		    @Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "Are You Sure to Close Application?", 
		             "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		        	try {
						Database.disconnectDB();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		           System.exit(0);
		        }
		    }
		};
		this.addWindowListener(exitListener);
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

class ContentPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	EmptyPanel defaultPanel = new EmptyPanel();
	ControlPanel controlPanel = new ControlPanel();
	
	public ContentPanel(){
		this.add(controlPanel);
		//this.add(defaultPanel);
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
	JComboBox<String> baseCurrencySelection;
	private  JComboBox<String> outputCurrencySelection;
	private  JTextField inputAmount;
	private JLabel comparisonText; //Will say "and compare with: "
	private JLabel titleBuilder; //Will be used to show "[Input Currency]
	private JLabel singleResults;
	
	private ImageIcon[] comboList;
	
	double inputdouble;
	String input;
	String output;
	double results;
	
	private JTable resultsTable;
	EmptyPanel defaultPanel;
	
	public ControlPanel(){
		this.setLayout(new BorderLayout());
		defaultPanel = new EmptyPanel();
		
	//	this.setLayout(new FlowLayout());
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		inputAmount = new HintTextField("Enter currency value");
		inputAmount.setPreferredSize(new Dimension (250, 25));
		
		
//		JLabel label = new JLabel("hi");
//		label.setIcon(new ImageIcon("images/australia.png"));
//		
//		comboList = new ImageIcon[currencyList.currencyInfo.length];
//		for (int i = 0; i < currencyList.currencyInfo.length; i++){
//			comboList[i] = new ImageIcon(currencyList.currencyInfo[i][2]);
//            if (comboList[i] != null) {
//                comboList[i].setDescription(currencyList.getCurrencyToDisplay(i));
//            }
//		}
//		
//		Object[] labels = new Object[currencyList.currencyInfo.length];
//		labels[0] = label;
//	
//		baseCurrencySelection = new JComboBox<Object>(labels);
		
		baseCurrencySelection = new JComboBox<String>(currencyList.getCurrencyInfoList());
		baseCurrencySelection.setPreferredSize(new Dimension (250, 50));
		
		input = "";
		inputdouble = 0;
		output = "";
		results = 0;
		
		singleResults = new JLabel("<html><font size=5><p> Conversion Results: <br/>"
				+ inputdouble + " in " + input + " to " + output + " = " + results +  "</p></font></html>");
		singleResults.setVisible(false);
		
		
		comparisonText = new JLabel(" and compare with: ");
		outputCurrencySelection = new JComboBox<String>(currencyList.getCurrencyInfoList());
		outputCurrencySelection.setPreferredSize(new Dimension (250, 50));
	
		enterButton = new JButton(new ImageIcon("images/submitIcon.png"));
		enterButton.setBorderPainted(false);
		enterButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
                System.out.println("button pressed");
                //Get the Selected Currency from the combo box
                int indexSelected = baseCurrencySelection.getSelectedIndex();
                currencyList.setSelected(currencyList.currencyInfo[indexSelected][0]);

                //Get the entered amount from the input field

                if (inputAmount.getText() != null){
                     inputdouble = Double.parseDouble(inputAmount.getText());
                     System.out.println("if found, amount = " + inputdouble);
                     currencyList.setConversionAmount(inputdouble);
                     input = baseCurrencySelection.getSelectedItem().toString();
                     output = outputCurrencySelection.getSelectedItem().toString();
                     
                     System.out.println(currencyList.getCurrencyKey(baseCurrencySelection.getSelectedIndex()));
                     System.out.println(currencyList.getCurrencyKey(outputCurrencySelection.getSelectedIndex()));
                   
                     try {
						//Database.connectDB();
                    	 
						results = Database.getOneRate(currencyList.getCurrencyKey(baseCurrencySelection.getSelectedIndex()), 
								 currencyList.getCurrencyKey(outputCurrencySelection.getSelectedIndex()), Database.today) * inputdouble;
						//Database.disconnectDB();
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
                     
                     defaultPanel.setVisible(false);
                     
                     singleResults.setText("<html><font size=5><p> Conversion Results: <br/>"
				+ inputdouble + " " + input + " to " + output + ": " + results +  "</p></font></html>");
                     
                     
                     singleResults.repaint();
                     singleResults.setVisible(true);
                    

                }else{
                     System.out.println("Invalid entry");
                     singleResults.setVisible(false);
                }


           }
				
			
       });
		
		
		
		JPanel controlPanel = new JPanel(new FlowLayout());
		controlPanel.add(inputAmount);
		controlPanel.add(baseCurrencySelection);
		controlPanel.add(comparisonText);
		controlPanel.add(outputCurrencySelection);
		controlPanel.add(enterButton);
		
		
//		this.add(inputAmount);
//		this.add(baseCurrencySelection);
//		this.add(comparisonText);
//		this.add(outputCurrencySelection);
//		this.add(enterButton);
		
		this.add(controlPanel, BorderLayout.NORTH);
		this.add(singleResults);
		this.add(defaultPanel, BorderLayout.WEST);
		
		
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
		helpText = new JLabel("<html><font size=5><p> To get started:<br/>"
				+ "1. Enter a currency value (ex: 125.50)<br/>"
				+ "2. Enter the base currency type (ex. USD) <br/>"
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

