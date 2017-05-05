package models;

import java.util.*;
import java.text.SimpleDateFormat;

//This model handles setting up the currency list and getting names and descriptions

public class CurrencyList {
	int sizeOfList = 31;
	public String selectedCurrency;
	public double enteredAmount;
	public static final int HISTORY = 15;
	
    public HashMap<String, String> currencyMap = new HashMap<String, String>();
    public  String[] keyArray;
    public  String[] valueArray;
    public String[] infoArray;
    
    public static String[][] currencyInfo = new String[][]{
		{ "AUD", "Australian Dollar", "images/Australia.png"},
		{ "BGN","Bulgarian Lev", "images/Bulgaria.png" },
		{ "BRL", "Brazilian Real","images/Brazil.png" },
		{ "CAD", "Canadian Dollar","images/Canada.png" },
		{ "CHF" , "Swiss Franc","images/Switzerland.png" },
		{ "CNY" , "Chinese Yuan Renminbi" ,"images/China.png" },
		{ "CZK", "Czech Koruna","images/czech.png" },
		{ "DKK", "Danish Krone","images/Denmark.png" },
		{ "EUR", "E.U. Euro", "images/European Union.png"},
		{ "GBP", "Pound Sterling","images/gbp.png" },
		{ "HKD", "Hong Kong Dollar","images/Hong Kong.png" },
		{ "HRK", "Croatian Kuna","images/Croatia.png" },
		{ "HUF", "Hungarian Forint","images/Hungary.png" },
		{ "IDR", "Indonesian Rupiah","images/Indonesia.png" },
		{ "ILS", "Israeli Shekel","images/Israel.png" },
		{ "INR", "Indian Rupee","images/India.png" },
		{ "JPY", "Japanese Yen","images/Japan.png" },
		{ "KRW", "South Korean Won","images/southkorea.png" },
		{ "MXN", "Mexican Peso","images/Mexico.png" },
		{ "MYR", "Malaysian Ringgit","images/Malaysia.png" },
		{ "NOK", "Norwegian Krone","images/Norway.png" },
		{ "NZD", "New Zealand Dollar","images/newzealand.png" },
		{ "PHP", "Philippine Peso","images/Philippines.png" },
		{ "PLN", "Polish Zloty","images/Poland.png" },
		{ "RON", "Romanian Leu","images/Romania.png" },
		{ "RUB", "Russian Rouble","images/russia.png" },
		{ "SEK", "Swedish Krona","images/Sweden.png" },
		{ "SGD", "Singapore Dollar","images/Singapore.png" },
		{ "THB", "Thai Baht","images/Thailand.png" },
		{ "TRY", "Turkish Lira","images/Turkey.png" },
		{ "USD", "US Dollar","images/usa.png" },
		{ "ZAR", "South African Rand","images/southafrica.png" },
	};
    //Format for object array will be: Row = {Initials, Description, Flag}
    
    
    
	public CurrencyList(){
		this.enteredAmount = 1;
		
	    keyArray = new String[currencyInfo.length];
	    valueArray = new String[currencyInfo.length];
	    infoArray = new String[currencyInfo.length];
	    
	    
	    //Build the array for reference to names and descriptions
//	    keyArray = new String[currencyMap.size()];
//	    valueArray = new String[currencyMap.size()];
//	    infoArray = new String[currencyMap.size()];
	    for (int i = 0; i < currencyInfo.length; i++) {
	        keyArray[i] = currencyInfo[i][0];
	        valueArray[i] = currencyInfo[i][1];
	        infoArray[i] = currencyInfo[i][0] +" (" + currencyInfo[i][1] + ")";
	    }
	}
	
	public CurrencyList(String selected){
		this.selectedCurrency = selected;
		
	    //Build the array for reference to names and descriptions
	    keyArray = new String[currencyMap.size()];
	    valueArray = new String[currencyMap.size()];
	    infoArray = new String[currencyMap.size()];
	    int index = 0;
	    for (Map.Entry<String, String> mapEntry : currencyMap.entrySet()) {
	        keyArray[index] = mapEntry.getKey();
	        valueArray[index] = mapEntry.getValue();
	        infoArray[index] = mapEntry.getKey() +" (" + mapEntry.getValue() + ")";
	        index++;
	    }
	}
	
	
	public String[] getCurrencyDescList(){
		String[] list = new String[sizeOfList];
		
		for (int i = 0; i < sizeOfList; i++){
		list[i] = currencyInfo[i][0] + " (" +currencyInfo[i][1] +")";
		}
		return list;
	}
	
	public String[] getCurrencyInfoList(){
		//System.out.println(infoArray.length);
		return infoArray;
	}
	
	public String[] getCurrencyInfo(int input){
		//System.out.println(infoArray.length);
		return currencyInfo[input];
	}
 
	public String getCurrencyToDisplay(int input){
		String builder = keyArray[input] + " (" + valueArray[input] + ") "; 
		return builder;
	}
	
	
	public String getCurrencyKey(int input){
		return keyArray[input];
	}
	
	
	public static String getLastSyncDate(Date inputDate){
        Date today = inputDate;
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = formatter.format(today);
		return formattedDate;
	}
	
	public String getSelectedFlag(int input){
		return currencyInfo[input][2];
	}
	
	public void setSelected(String cur){
		this.selectedCurrency = cur;
	}
	public String getSelectedString(){
		return this.selectedCurrency;
	}
	
	public void setConversionAmount(double in){
		this.enteredAmount = in;
	}
	public double getConversionAmount(){
		return this.enteredAmount;
	}
}