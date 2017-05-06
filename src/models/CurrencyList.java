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
		{ "AUD", "Australian Dollar", "/Australia.png"},
		{ "BGN","Bulgarian Lev", "/Bulgaria.png" },
		{ "BRL", "Brazilian Real","/Brazil.png" },
		{ "CAD", "Canadian Dollar","/Canada.png" },
		{ "CHF" , "Swiss Franc","/Switzerland.png" },
		{ "CNY" , "Chinese Yuan Renminbi" ,"/China.png" },
		{ "CZK", "Czech Koruna","/czech.png" },
		{ "DKK", "Danish Krone","/Denmark.png" },
		{ "EUR", "E.U. Euro", "/European Union.png"},
		{ "GBP", "Pound Sterling","/gbp.png" },
		{ "HKD", "Hong Kong Dollar","/Hong Kong.png" },
		{ "HRK", "Croatian Kuna","/Croatia.png" },
		{ "HUF", "Hungarian Forint","/Hungary.png" },
		{ "IDR", "Indonesian Rupiah","/Indonesia.png" },
		{ "ILS", "Israeli Shekel","/Israel.png" },
		{ "INR", "Indian Rupee","/India.png" },
		{ "JPY", "Japanese Yen","/Japan.png" },
		{ "KRW", "South Korean Won","/southkorea.png" },
		{ "MXN", "Mexican Peso","/Mexico.png" },
		{ "MYR", "Malaysian Ringgit","/Malaysia.png" },
		{ "NOK", "Norwegian Krone","/Norway.png" },
		{ "NZD", "New Zealand Dollar","/newzealand.png" },
		{ "PHP", "Philippine Peso","/Philippines.png" },
		{ "PLN", "Polish Zloty","/Poland.png" },
		{ "RON", "Romanian Leu","/Romania.png" },
		{ "RUB", "Russian Rouble","/russia.png" },
		{ "SEK", "Swedish Krona","/Sweden.png" },
		{ "SGD", "Singapore Dollar","/Singapore.png" },
		{ "THB", "Thai Baht","/Thailand.png" },
		{ "TRY", "Turkish Lira","/Turkey.png" },
		{ "USD", "US Dollar","/usa.png" },
		{ "ZAR", "South African Rand","/southafrica.png" },
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
		return infoArray;
	}
	
	public String[] getCurrencyInfo(int input){

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