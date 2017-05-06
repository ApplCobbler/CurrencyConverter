package views;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.CurrencyList;
import models.Database;

/**
 * CMSC 495 - Spring 2017 Professor Dao Group 2: Richard Wainwright, Dennie
 * Carr, Greg Armstrong, Jik Oh
 *
 */
public class Main {


	public static void main(String[] args) throws Exception {

		// Run this baby
		
		LoadingFrame loadingFrame = new LoadingFrame();
	
		Database.connectDB();	
		Database.updateDB(Database.today, 15);	
		
		loadingFrame.setVisible(false);
		CurrencyView foo = new CurrencyView();
		foo.setVisible (true);
	}

}

class LoadingFrame extends JFrame {


    // Set the size of the window
    static final int WIDTH = 700, HEIGHT = 400;

    // Constructor
    public LoadingFrame() {
         super("Currency Converter App: IC2A ver. 1.0");
         setSize(WIDTH, HEIGHT);
         setLayout(new BorderLayout());
         

         JLabel loadingImage = new JLabel(new ImageIcon(new ImageIcon(Main.class.getResource("/CC_Logo.jpg")).getImage().getScaledInstance
       		  (250, 225, Image.SCALE_DEFAULT)));

         JLabel creditsLabel = new JLabel("<html><font size=3><p>" + 
         "Created 2017 for CMSC 495 at UMUC. Created by: Jik Oh, Greg Armstrong, Dennie Carr, "
          + "Richard Wainwright <br/>" + "Information for database provided courtesy of fixer.io and European Central Bank"+
         "</p></font></html>");
    
         JPanel creditsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
         
         creditsPanel.add(creditsLabel);
         
         JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
         JLabel contentLabel = new JLabel("<html><font size=5><p>"+
         "Checking and Populating Database...(this could take a minute)"+
         "</p></font></html>");
         contentPanel.add(contentLabel);
         
         this.add(loadingImage, BorderLayout.NORTH);
         this.add(contentPanel, BorderLayout.CENTER);
         this.add(creditsPanel, BorderLayout.SOUTH);
         this.setVisible(true);
         this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
         
    }
}