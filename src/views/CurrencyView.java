package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import java.util.Date;
import java.util.List;
import java.math.*;

import java.awt.event.*;
import java.math.BigDecimal;
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
*         menus: 1. Initial currency 2. Converted currency 3. Add the submit
*         button 4. Add the content panel where the results will display centered
*         in the UI and below the above elements This panel should be dynamic
*         and will be different depending on what the user selects
*         a specific currency
*
*/
public class CurrencyView extends JFrame {
     private static final long serialVersionUID = 1L;

     CurrencyList currencyList = new CurrencyList();

     // Set the size of the window
     static final int WIDTH = 1200, HEIGHT = 800;

     // Constructor
     public CurrencyView() {
          super("Currency Converter (ver 1.0)");
          setSize(WIDTH, HEIGHT);

          this.setLayout(new BorderLayout());

          JPanel topPanel = new JPanel(new BorderLayout());
          topPanel.setBackground(Color.white);
          topPanel.add(new TitlePanel(), BorderLayout.NORTH);

          add(topPanel, BorderLayout.NORTH);
          add(new ContentPanel(), BorderLayout.CENTER);
          
          
          JLabel creditsLabel = new JLabel("<html><font size=3><p>" + "Created 2017 for CMSC 495 at UMUC. Created by: Jik Oh, Greg Armstrong, Dennie Carr, "
          		+ "Richard Wainwright <br/>" + "Information for database provided courtesy of fixer.io and European Central Bank"+ "</p></font></html>");
          
          
          JPanel creditsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
          creditsPanel.add(creditsLabel);
          add(creditsPanel, BorderLayout.SOUTH);
          // Add customizable panel

          
          WindowListener exitListener = new WindowAdapter() {

        	    @Override
        	    public void windowClosing(WindowEvent e) {
        	        int confirm = JOptionPane.showOptionDialog(
        	             null, "Close application?", 
        	             "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
        	             JOptionPane.QUESTION_MESSAGE, null, null, null);
        	        if (confirm == 0) {
        	        	
        	        	   try {
							Database.disconnectDB();
						} catch (SQLException e1) {
							System.out.println("Error trying to shutdown DB on close.");
							e1.printStackTrace();
						}
        	        	
        	           System.exit(0);
        	        }
        	    }
        	};
        	
        	this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
     titleIcon = new JLabel(new ImageIcon(new ImageIcon("images/CC_Logo.jpg").getImage().getScaledInstance(100, 90, Image.SCALE_DEFAULT)));
     title = new JLabel("<html><font size=8>Currency Converter</font></html>");
     titleFormat.add(titleIcon);
     titleFormat.add(title);
     titleFormat.setBackground(Color.white);

     syncIcon = new JLabel(new ImageIcon("images/syncIcon.png"));
     syncLabel = new JLabel("<html><font size=3>Last Synced: " + CurrencyList.getLastSyncDate(Database.today) + "</font></html> ");
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

class ControlPanel extends JPanel{

     CurrencyList currencyList = new CurrencyList();
     private static final long serialVersionUID = 1L;
     //This is where the main inputs and combo boxes are

     private  JButton enterButton;
     JComboBox<String> baseCurrencySelection;
     private  JComboBox<String> outputCurrencySelection;
     private  JTextField inputAmount;
     private JLabel comparisonText; //Will say "and compare with: "
     private String inputCountryFlagString;
     private String outputCountryFlagString;

     double inputdouble;
    private  String inputParsed;
    private  String outputParsed;
     double results;

     private JTable historyTable;
     private  JScrollPane pane;
     EmptyPanel defaultPanel;
    public ControlPanel(){
          this.setLayout(new BorderLayout());
          defaultPanel = new EmptyPanel();

          this.setBackground(Color.white);
          this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
          inputAmount = new HintTextField("Enter currency value");
          inputAmount.setPreferredSize(new Dimension (250, 25));
          
          

          baseCurrencySelection = new JComboBox<String>(currencyList.getCurrencyInfoList());
          baseCurrencySelection.setPreferredSize(new Dimension (250, 50));
          comparisonText = new JLabel(" and compare with: ");
          outputCurrencySelection = new JComboBox<String>(currencyList.getCurrencyInfoList());
          outputCurrencySelection.setPreferredSize(new Dimension (250, 50));
          
   
          inputParsed = "";
          inputdouble = 0;

          outputParsed = "";
          results = 0;



          enterButton = new JButton(new ImageIcon("images/submitIcon.png"));
          enterButton.setBorderPainted(false);
          enterButton.addActionListener( new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e) {
                     inputParsed = currencyList.getCurrencyKey(baseCurrencySelection.getSelectedIndex());
                     outputParsed = currencyList.getCurrencyKey(outputCurrencySelection.getSelectedIndex());
                     
              if(inputParsed.equals(outputParsed) == false){
              
                if (inputAmount.getText().isEmpty() == false){
                	
                     inputdouble = Double.parseDouble(inputAmount.getText());
                     System.out.println("if found, amount = " + inputdouble);
                     currencyList.setConversionAmount(inputdouble);

                     try {
                              //Database.connectDB();
                              addResultsDisplay();
                              addTable(); 

                         } catch (Exception e1) {
                              e1.printStackTrace();
                         	}
                     defaultPanel.setVisible(false);
                }else{
                	JOptionPane.showMessageDialog(getParent(), "You must enter your currency value.");
                 
                }
              } else{JOptionPane.showMessageDialog(getParent(), "You must select two different currency types.");
            	  
              }
           }

       });


          JPanel controlPanel = new JPanel(new FlowLayout());
          controlPanel.add(inputAmount);
          controlPanel.add(baseCurrencySelection);
          controlPanel.add(comparisonText);
          controlPanel.add(outputCurrencySelection);
          controlPanel.add(enterButton);
          

          this.add(controlPanel, BorderLayout.NORTH);
          this.add(defaultPanel, BorderLayout.WEST);


     }

     

     void addTable(){ 
    	 
    	 //If there's already a pane showing, remove it and prepare to replace
    	 if(pane != null){
    		 this.remove(pane);
    		 this.revalidate();
    	 }
    	 //Define model and set properties for display
         HistoryTableModel historyTableModel = new HistoryTableModel();
         historyTableModel.updateTable(inputParsed, outputParsed, CurrencyList.HISTORY, inputdouble);

         historyTable = new JTable(historyTableModel); 
         historyTable.setRowHeight(30);
         historyTable.setRowHeight(0, 50);
         historyTable.setGridColor(Color.LIGHT_GRAY);
         historyTable.setShowHorizontalLines(true);
         historyTable.setShowVerticalLines(false);
         historyTable.setFocusable(false);
         historyTable.setRowSelectionAllowed(false);

         
         for(int i = 0; i < 3; i++){
         historyTable.setDefaultRenderer(historyTable.getColumnClass(i), new HistoryCellRenderer());
         }
         
         //Add to pane and then add pane to the parent panel
         pane = new JScrollPane();
         pane.setViewportView(historyTable);
         historyTable.repaint();

    	 this.add(pane, BorderLayout.SOUTH);
     }
     
     void addResultsDisplay(){
    	 //Format and display the submitted query
    	 
    	 
    	JPanel resultsDisplay = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
    	
    	inputCountryFlagString = currencyList.getSelectedFlag(baseCurrencySelection.getSelectedIndex());
        outputCountryFlagString = currencyList.getSelectedFlag(outputCurrencySelection.getSelectedIndex());
       
        JLabel inputAmountDisplay = new JLabel("<html><font size=8><p>" + inputdouble + "</p></font></html>");
        JLabel inputCountryFlag = new JLabel(new ImageIcon(inputCountryFlagString));
        JLabel inputBaseDisplay = new JLabel("<html><font size=8><p>" + currencyList.getCurrencyKey(baseCurrencySelection.getSelectedIndex())+ "</p></font></html>");
        JLabel toDisplay = new JLabel("<html><font size=8><p>" + " to " + "</p></font></html>");
        JLabel outputCountryFlag = new JLabel(new ImageIcon(outputCountryFlagString));
        JLabel outputBaseDisplay = new JLabel("<html><font size=8><p>" + currencyList.getCurrencyKey(outputCurrencySelection.getSelectedIndex()) + "</p></font></html>");
      
      
      
      resultsDisplay.add(inputAmountDisplay);
      resultsDisplay.add(inputCountryFlag);
      resultsDisplay.add(inputBaseDisplay);
      resultsDisplay.add(toDisplay);
      resultsDisplay.add(outputCountryFlag);
      resultsDisplay.add(outputBaseDisplay);
     
    this.add(resultsDisplay, BorderLayout.CENTER);

     }
}

class HistoryTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private String[] colNames = {"Date", "Exchanged Rate", "Change From Yesterday"};
	private Object[][] historyData = new Object[CurrencyList.HISTORY][3];
	
	
	public void updateTable(String inputString, String outputString, int days, double amount){
    	 List<Double> returnedRates = null;
         
    	 try {
			Database.updateDBWithHistory(inputString, days);
		} catch (Exception e1) {
			System.out.println("failed to update history");
			e1.printStackTrace();
			
		}
    	 
    	 try {
        	 System.out.println("Input: " + inputString + ", Output: "+ outputString + ", Days: "+ days);
			returnedRates = Database.historicRates(inputString, outputString, days);
			System.out.println(returnedRates);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
         
         //Set the dates for each back date
         for (int i = 0; i < returnedRates.size(); i++){
 			  if(i == 0){
 				 historyData[i][0] = CurrencyList.getLastSyncDate(Database.today);
 			  }else{
 				historyData[i][0] = CurrencyList.getLastSyncDate(Database.subtractDays(Database.today, i)); 
 			  }
         }
         
         //Set the exchange information for the given date
         for (int i = 0; i< returnedRates.size(); i++){
        	 double rate = returnedRates.get(i);
     		BigDecimal shortenedRate = new BigDecimal(rate);
    		shortenedRate = shortenedRate.round(new MathContext(5));
    		double rounded = shortenedRate.doubleValue();
    		
        	 historyData[i][1] = rounded * amount;
         }
         
         //Set the delta information for the given date. This will be the change from the previous day 
         //so last date won't have change
         for (int i = 0; i< returnedRates.size()-1; i++){
            double rate = (Double)historyData[i][1] - (Double)historyData[i+1][1];
     		BigDecimal shortenedRate = new BigDecimal(rate);
    		shortenedRate = shortenedRate.round(new MathContext(5));
    		double rounded = shortenedRate.doubleValue();
        	 
        	 
        	 historyData[i][2] = rounded;
         }
         	this.fireTableDataChanged();
          //	return historyData;    	 
     }
	
	
    @Override
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }

	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return historyData.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return historyData[rowIndex][columnIndex];
	}
	
	@Override
	public String getColumnName(int columnIndex){
		return colNames[columnIndex];
	}
	
	
	@Override
	public void setValueAt(Object value, int row, int col) {
	      historyData[row][col] = value;
	      fireTableCellUpdated(row, col);

	    }
}

class HistoryCellRenderer extends DefaultTableCellRenderer{
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
	   JLabel parent = (JLabel) super.getTableCellRendererComponent(table, 
	    	      value, isSelected, hasFocus, row, column);
	    	     if(row == 0) {
	    	    	// parent.setFont(parent.getFont().deriveFont(Font.BOLD));
	    	    	 parent.setBackground(Color.LIGHT_GRAY);
	    	    	 parent.setFont(new Font("Arial", Font.BOLD, 32));
	    	     }
	    	     else{
	    	    	 parent.setBackground(Color.WHITE);
	    	    	 parent.setFont(new Font("Arial", Font.PLAIN, 16));
	    	     }
	    	     
	    	     if(column == 2){
	    	    	 double parsed = 0;
	    	    	 try {
	    	    		 parsed = Double.parseDouble(value.toString());
	    	    	 }catch (NullPointerException d){
	    	    		 parsed = 0;
	    	    	 }
	    	    	 
	    	    	 if(parsed > 0.0){
	    	    		 parent.setForeground(Color.green);
	    	    	 }
	    	    	if (parsed < 0.0){
	    	    		parent.setForeground(Color.red);
	    	    	}
	    	    	 
	    	     }else{
	    	    	 parent.setForeground(Color.BLACK);
	    	     }
	    	     
	    	     //parent.repaint();
	    	     
	    	     return parent;
		
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
         
         addKeyListener(new KeyAdapter() {
             public void keyTyped(KeyEvent e) {
                 char ch = e.getKeyChar();

                 if (!isNumber(ch) && !isValidSignal(ch) && !validatePoint(ch)  && ch != '\b') {
                     e.consume();
                 }
             }
         });

     }

     private boolean isNumber(char ch){
         return ch >= '0' && ch <= '9';
     }

     private boolean isValidSignal(char ch){
         if( (getText() == null || "".equals(getText().trim()) ) && ch == '-'){
             return true;
         }

         return false;
     }

     private boolean validatePoint(char ch){
         if(ch != '.'){
             return false;
         }

         if(getText() == null || "".equals(getText().trim())){
             setText("0.");
             return false;
         }else if("-".equals(getText())){
             setText("-0.");
         }

         return true;
     
         
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



