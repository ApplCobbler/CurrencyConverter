package views;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;


import models.CurrencyList;

import java.io.*;
import java.util.*;


/**
 * @author Group 2
 * 
 *         This class is the main app window This section will primarily be
 *         where Greg works out of.
 * 
 * Build the window
 * Add the panels
 * Add the input fields
 * Add the two dropdowns:
 * 1. Initial currency
 * 2. Converted currency - this one will need to have an "ALL" option as well as the standard currencies. 
 * The ALL option should be the default selection when app loads.
 * Add the submit button
 * Add the content panel where the results will display centered in the UI and below the above elements
 * This panel should be dynamic and will be different depending on if the user selects ALL or selects 
 * a specific currency
=======
 *         Build the window Add the panels Add the input fields Add two dropdown
 *         menus: 1. Initial currency 2. Converted currency - this one will need
 *         to have an "ALL" option as well as the standard currencies. The ALL
 *         option should be the default selection when app loads. Add the submit
 *         button Add the content panel where the results will display centered
 *         in the UI and below the above elements This panel should be dynamic
 *         and will be different depending on if the user selects ALL or selects
 *         a specific currency
 *
 */

public class CurrencyView extends JFrame {
	private static final long serialVersionUID = 1L;

	// Set the size of the window
	static final int WIDTH = 600, HEIGHT = 600;

	// Constructor
	public CurrencyView() {
		super("Currency Converter (ver 1.0)");
		setSize(WIDTH, HEIGHT);

		// Add customizable panel
		add(new CurrencyPanel());

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

public class CurrencyView extends JApplet implements ActionListener{
	
	//Integrate the currency list model for adding to the dropdowns.
	CurrencyList currencyList = new CurrencyList();
	
	 JButton bt1,bt2;
	 JComboBox cb1,cb2;
	 JTextField tf1,tf2;
   //hard coded currency names this can be read from API
   // String[] currency={"US Dollar","Indian Rupee","British Pound","Euro","Canadian Dollar","Emirati Driham","Chinese Yaun"}; 
    
    public void init(){
        try{SwingUtilities.invokeAndWait(new Runnable(){
            public void run(){
                makeGUI();
             }
         });
      }catch(Exception exc){System.out.println("Error occured due to"+exc);}
   }
private void makeGUI(){
    GridBagLayout gbag=new GridBagLayout();
    GridBagConstraints gbc=new GridBagConstraints();
    setLayout(gbag);
    cb1=new JComboBox(currencyList.getCurrencyInfoList());
    cb1.setSelectedIndex(0);
    cb2=new JComboBox(currencyList.getCurrencyInfoList());
    cb2.setSelectedIndex(1);
    tf1=new JTextField(10);
    tf2=new JTextField(10);
    bt1=new JButton("Convert");
    bt2=new JButton("Clear");
    JLabel h=new JLabel("Currency Converter");
    JLabel l1=new JLabel("Select the Input Currency:");
    JLabel l2=new JLabel("Enter the amount:  ");
    JLabel l3=new JLabel("Select hte Output Currency:");
    JLabel l4=new JLabel("Converted amount:  ");
     
    gbc.weighty=1.0;
    gbc.gridwidth=GridBagConstraints.REMAINDER;
    gbc.anchor=GridBagConstraints.NORTH;
    gbag.setConstraints(h, gbc);
     
    gbc.anchor=GridBagConstraints.EAST;
     
    gbc.gridwidth=GridBagConstraints.RELATIVE;
    gbag.setConstraints(l1, gbc);
    gbc.gridwidth=GridBagConstraints.REMAINDER;
    gbag.setConstraints(cb1, gbc);
    gbc.gridwidth=GridBagConstraints.RELATIVE;
    gbag.setConstraints(l2, gbc);
    gbc.gridwidth=GridBagConstraints.REMAINDER;
    gbag.setConstraints(tf1, gbc);
    gbc.gridwidth=GridBagConstraints.RELATIVE;
    gbag.setConstraints(l3, gbc);
    gbc.gridwidth=GridBagConstraints.REMAINDER;
    gbag.setConstraints(cb2, gbc);
    gbc.gridwidth=GridBagConstraints.RELATIVE;
    gbag.setConstraints(l4, gbc);
    gbc.gridwidth=GridBagConstraints.REMAINDER;
    gbag.setConstraints(tf2, gbc);
    gbc.anchor=GridBagConstraints.CENTER;
    gbag.setConstraints(bt1, gbc);
    gbc.gridwidth=GridBagConstraints.CENTER;
    gbag.setConstraints(bt2, gbc);
     
    add(h);add(l1);add(cb1);add(l2);add(tf1);add(l3);add(cb2);add(bt1);add(l4);add(tf2);add(bt2);
     
    tf1.addActionListener(this);
    tf2.addActionListener(this);
    bt1.addActionListener(this);
    bt2.addActionListener(this);
    cb1.addActionListener(this);
    cb2.addActionListener(this);
    }
public void actionPerformed(ActionEvent ae){
    double a,b,c=0;
    a=Double.valueOf(tf1.getText());
    try{
        if(cb1.getSelectedIndex()==0 & cb2.getSelectedIndex()==1){
            c= a*60.335456;}
        if(cb1.getSelectedIndex()==0 & cb2.getSelectedIndex()==2){
            c= a*0.595194;}
        if(cb1.getSelectedIndex()==0 & cb2.getSelectedIndex()==3){
            c= a*0.723333;}
        if(cb1.getSelectedIndex()==0 & cb2.getSelectedIndex()==4){
            c= a*1.099742;}
        if(cb1.getSelectedIndex()==0 & cb2.getSelectedIndex()==5){
            c= a*3.672939;}
        if(cb1.getSelectedIndex()==0 & cb2.getSelectedIndex()==6){
            c= a*6.221082;}
        if(cb1.getSelectedIndex()==1 & cb2.getSelectedIndex()==0){
            c= a*0.016574;}
        if(cb1.getSelectedIndex()==1 & cb2.getSelectedIndex()==2){
            c= a*0.009868;}
        if(cb1.getSelectedIndex()==1 & cb2.getSelectedIndex()==3){
            c= a*0.011992;}
        if(cb1.getSelectedIndex()==1 & cb2.getSelectedIndex()==4){
            c= a*0.018234;}
        if(cb1.getSelectedIndex()==1 & cb2.getSelectedIndex()==5){
            c= a*0.060880;}
        if(cb1.getSelectedIndex()==1 & cb2.getSelectedIndex()==6){
            c= a*0.103114;}
        if(cb1.getSelectedIndex()==2 & cb2.getSelectedIndex()==0){
            c= a*1.679949;}
        if(cb1.getSelectedIndex()==2 & cb2.getSelectedIndex()==1){
            c= a*101.251087;}
        if(cb1.getSelectedIndex()==2 & cb2.getSelectedIndex()==3){
            c= a*1.215237;}
        if(cb1.getSelectedIndex()==2 & cb2.getSelectedIndex()==4){
            c= a*1.848254;}
        if(cb1.getSelectedIndex()==2 & cb2.getSelectedIndex()==5){
            c= a*6.170453;}
        if(cb1.getSelectedIndex()==2 & cb2.getSelectedIndex()==6){
            c= a*10.449975;}
        if(cb1.getSelectedIndex()==3 & cb2.getSelectedIndex()==0){
            c= a*1.382656;}
        if(cb1.getSelectedIndex()==3 & cb2.getSelectedIndex()==1){
            c= a*83.332669;}
        if(cb1.getSelectedIndex()==3 & cb2.getSelectedIndex()==2){
            c= a*0.822930;}
        if(cb1.getSelectedIndex()==3 & cb2.getSelectedIndex()==4){
            c= a*1.52083;}
        if(cb1.getSelectedIndex()==3 & cb2.getSelectedIndex()==5){
            c= a*5.078644;}
        if(cb1.getSelectedIndex()==3 & cb2.getSelectedIndex()==6){
            c= a*8.600954;}
        if(cb1.getSelectedIndex()==4 & cb2.getSelectedIndex()==0){
            c= a*0.909156;}
        if(cb1.getSelectedIndex()==4 & cb2.getSelectedIndex()==1){
            c= a*54.794847;}
        if(cb1.getSelectedIndex()==4 & cb2.getSelectedIndex()==2){
            c= a*0.541034;}
        if(cb1.getSelectedIndex()==4 & cb2.getSelectedIndex()==3){
            c= a*0.657569;}
        if(cb1.getSelectedIndex()==4 & cb2.getSelectedIndex()==5){
            c= a*3.339467;}
        if(cb1.getSelectedIndex()==4 & cb2.getSelectedIndex()==6){
            c= a*5.655489;}
        if(cb1.getSelectedIndex()==5 & cb2.getSelectedIndex()==0){
            c= a*0.272260;}
        if(cb1.getSelectedIndex()==5 & cb2.getSelectedIndex()==1){
            c= a*16.409082;}
        if(cb1.getSelectedIndex()==5 & cb2.getSelectedIndex()==2){
            c= a*0.162022;}
        if(cb1.getSelectedIndex()==5 & cb2.getSelectedIndex()==3){
            c= a*0.196942;}
        if(cb1.getSelectedIndex()==5 & cb2.getSelectedIndex()==4){
            c= a*0.299497;}
        if(cb1.getSelectedIndex()==5 & cb2.getSelectedIndex()==6){
            c= a*1.693525;}
        if(cb1.getSelectedIndex()==6 & cb2.getSelectedIndex()==0){
            c= a*0.160762;}
        if(cb1.getSelectedIndex()==6 & cb2.getSelectedIndex()==1){
            c= a*9.689100;}
        if(cb1.getSelectedIndex()==6 & cb2.getSelectedIndex()==2){
            c= a*0.095673;}
        if(cb1.getSelectedIndex()==6 & cb2.getSelectedIndex()==3){
            c= a*0.116292;}
        if(cb1.getSelectedIndex()==6 & cb2.getSelectedIndex()==4){
            c= a*0.176855;}
        if(cb1.getSelectedIndex()==6 & cb2.getSelectedIndex()==5){
            c= a*0.590495;}
        tf2.setText(String.valueOf(c));
        }catch(Exception x){System.out.println("Error");}
    if(ae.getSource()==bt2){
        tf1.setText("0000");
        tf2.setText("0000");}    
   }

}

class CurrencyPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	// Create main views, lay them out accordingly
	// Add in all buttons within this panel


}
}
