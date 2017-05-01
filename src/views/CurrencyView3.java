package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;

import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.util.DoubleProperties;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

import models.CurrencyList;
import models.Database;
import models.Parser;

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
public class CurrencyView3 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	CurrencyList cList = new CurrencyList();
	public double enteredAmount = 0;
	public String selectedCurrency = "";


	// Set the size of the window
	static final int WIDTH = 1200, HEIGHT = 800;

	// Constructor
	public CurrencyView3() {
		super("Currency Converter (ver 1.0)");
		
		setSize(WIDTH, HEIGHT);
		
		setLayout(new GridLayout(3,1));
		this.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.white);
		topPanel.add(new TitlePanel1(), BorderLayout.NORTH);
		topPanel.add(new ControlPanel1(cList), BorderLayout.SOUTH);
		
		add(topPanel, BorderLayout.NORTH);
		add(new CurrencyPanel1(cList), BorderLayout.CENTER);
		// Add customizable panel

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible (true);
	}
	
	void setSelectedCurrency(String input){
		this.selectedCurrency = input;
	}
	void setSelectedAmount (double input){
		this.enteredAmount = input;
	}
	
}


class TitlePanel1 extends JPanel{
	
CurrencyList currencyList = new CurrencyList();
//This will display the Title of the app and the icon along the top
	private static final long serialVersionUID = 1L;
	private JLabel title;
	private JLabel titleIcon; 
	private JLabel syncIcon;
	private JLabel syncLabel;
	private JPanel titleFormat = new JPanel(new FlowLayout());
	private JPanel syncFormat = new JPanel(new FlowLayout());
	
	public TitlePanel1(){
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


class CurrencyPanel1 extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public CurrencyPanel1(CurrencyList clist){
		//ResultsPanel1 resultsPanel1 = new ResultsPanel1(clist);
		EmptyPanel defaultPanel = new EmptyPanel();
		//this.add(resultsPanel1);
		this.add(defaultPanel);
	}

}

class ControlPanel1 extends JPanel{
	
	CurrencyList currencyList;
	private static final long serialVersionUID = 1L;
	//This is where the main inputs and combo boxes are
	
	private  JButton enterButton,clearButton;
	private  JComboBox<String> baseCurrencySelection;
	private  JTextField inputAmount;
	
	public ControlPanel1(CurrencyList clist){
		currencyList = clist;
		this.setLayout(new FlowLayout());
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		inputAmount = new HintTextField("Enter currency value");
		inputAmount.setPreferredSize(new Dimension (250, 35));
		
		String[] comboBoxList = new String[currencyList.currencyInfo.length];
		for (int i = 0; i < currencyList.currencyInfo.length; i++){
			String item = currencyList.currencyInfo[i][0] + " (" 
					+ currencyList.currencyInfo[i][1] + ")";
			comboBoxList[i] = item;
		}
		
		baseCurrencySelection = new JComboBox<String>(comboBoxList);
		baseCurrencySelection.setPreferredSize(new Dimension (250, 50));

	
		enterButton = new JButton(new ImageIcon("images/submitIcon.png"));
		enterButton.setBorderPainted(false);
		
		this.add(inputAmount);
		this.add(baseCurrencySelection);
		this.add(enterButton);
		
		EmptyPanel1 empty = new EmptyPanel1();
		this.add(empty);
		ResultsPanel1 results = new ResultsPanel1(currencyList);
		results.setVisible(false);
		this.add(results);
		
		
		enterButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("button pressed");
				//Get the Selected Currency from the combo box
				int indexSelected = baseCurrencySelection.getSelectedIndex();
				currencyList.setSelected(currencyList.currencyInfo[indexSelected][0]);
				
				//Get the entered amount from the input field
				
				double amount = 1;
				if (inputAmount.getText() != null){
					amount = Double.parseDouble(inputAmount.getText());
					System.out.println("if found, amount = " + amount);
					clist.setConversionAmount(amount);
					empty.setVisible(false);
					
					results.repaint();
					results.setVisible(true);
					
				}else{
					System.out.println("Invalid entry");
				}
				
				
			}
		});
		
	}
	
}


class EmptyPanel1 extends JPanel{
	private static final long serialVersionUID = 1L;
	//For when user opens app and there's nothing selected to display yet.
	
	private JPanel helpPanel = new JPanel(new BorderLayout());
	private JPanel helpTextPanel = new JPanel(new BorderLayout());
	private JPanel helpIconPanel = new JPanel(new BorderLayout());
	private JLabel helpText;
	
	public EmptyPanel1(){
		
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


class ResultsPanel1 extends JPanel{
	private static final long serialVersionUID = 1L;
	
	CurrencyList currencyList;
	//For when user presses submit
	 Object[][] data;
	 
	public ResultsPanel1(CurrencyList clist){
		currencyList = clist;
		Parser parser = new Parser();
		
		String[] columns = new String[] {
				
	            "Flag", "ID", "Name", "Exchanged Result"
	        };
		
	    data = new Object[currencyList.keyArray.length][4];
	    this.updateTable();
//	    
//	        for (int currencyRow = 0; currencyRow < currencyList.keyArray.length; currencyRow++){
//	        	for (int rowContent = 0; rowContent < 4; rowContent++){
//	        		if(rowContent == 0){
//	        			data[currencyRow][rowContent] = new ImageIcon(currencyList.currencyInfo[currencyRow][2]);
//	        		}
//	        		if(rowContent == 1){
//	        			data[currencyRow][rowContent] = currencyList.currencyInfo[currencyRow][0];
//	        		}
//	        		if(rowContent == 2){
//	        			data[currencyRow][rowContent] = currencyList.currencyInfo[currencyRow][1];
//	        		}
//	        		if (rowContent == 3){
//	        			System.out.println("Row Content 3");
//	        			String exchangeResult = "";
//						try {
//							exchangeResult = parser.convertBetween(currencyList.currencyInfo[currencyRow][0], 
//									currencyList.selectedCurrency, currencyList.enteredAmount);
//							System.out.println("Succeeded in updating exchange result");
//						} catch (SQLException e) {
//							System.out.println("Problem with SQL");
//							e.printStackTrace();
//						}
//	        			data[currencyRow][rowContent] = exchangeResult;
//	        		}
//	        	}
//	        }
	        
	        System.out.println(data);
	    
	        
	        DefaultTableModel model = new DefaultTableModel(data, columns);
	        JTable table = new JTable(model){
	            public Class getColumnClass(int column)
	            {
	                return getValueAt(0, column).getClass();
	            }
	            public boolean isCellEditable(int row, int column) {                
	                return false;   
	            }
	        };
	        table.setRowHeight(35);
	        TableColumn column = null;
	        for (int i = 0; i < columns.length; i++) {
	            column = table.getColumnModel().getColumn(i);
	            if (i == 0) 
	                column.setMaxWidth(50);
	            if (i == 1)
	                column.setMaxWidth(50);
	        }
	         
	        //add the table to the frame
	        this.add(new JScrollPane(table));
		
	} 
	        void updateTable(){
	        	Parser parser = new Parser();
	        	
	        	System.out.println("Key array length on update: " + currencyList.keyArray.length);
	        	
		        for (int currencyRow = 0; currencyRow < currencyList.keyArray.length; currencyRow++){
		        	for (int rowContent = 0; rowContent < 4; rowContent++){
		        		if(rowContent == 0){
		        			data[currencyRow][rowContent] = new ImageIcon(currencyList.currencyInfo[currencyRow][2]);
		        		}
		        		if(rowContent == 1){
		        			data[currencyRow][rowContent] = currencyList.currencyInfo[currencyRow][0];
		        		}
		        		if(rowContent == 2){
		        			data[currencyRow][rowContent] = currencyList.currencyInfo[currencyRow][1];
		        		}
		        		if (rowContent == 3){
		        			System.out.println("Row Content 3");
		        			String exchangeResult = "";
							try {
								exchangeResult = parser.convertBetween(currencyList.currencyInfo[currencyRow][0], 
										currencyList.selectedCurrency, currencyList.enteredAmount);
								System.out.println("Succeeded in updating exchange result");
							} catch (SQLException e) {
								System.out.println("Problem with SQL");
								e.printStackTrace();
							}
		        			data[currencyRow][rowContent] = exchangeResult;
		        		}
		        	}
		        }
		        
		        System.out.println("Updated length " + data.length);
		        
	        }

	
}



class HintTextField1 extends JTextField implements FocusListener {

	private static final long serialVersionUID = 1L;
	private final String hint;
	  private boolean showingHint;

	  public HintTextField1(final String hint) {
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
