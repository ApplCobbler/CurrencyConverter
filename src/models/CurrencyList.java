package models;

import java.util.*;

//This model handles setting up the currency list and getting names and descriptions

public class CurrencyList {
	
	int sizeOfList;
    HashMap<String, String> currencyMap = new HashMap<String, String>();
    String[] keyArray;
    String[] valueArray;
    
	public CurrencyList(){
	    currencyMap.put( "AUD" , "Australian Dollar");
	    currencyMap.put( "BGN" , "Bulgarian Lev");
	    currencyMap.put( "BRL" , "Brazilian Real");
	    currencyMap.put( "CAD" , "Canadian Dollar");
	    currencyMap.put( "CHF" , "Swiss Franc");
	    currencyMap.put( "CNY" , "Chinese Yuan Renminbi");
	    currencyMap.put( "CZK" , "Czech Koruna");
	    currencyMap.put( "DKK" , "Danish Krone");
	    currencyMap.put( "GBP" , "Pound Sterling");
	    currencyMap.put( "HKD" , "Hong Kong Dollar");
	    currencyMap.put( "HRK" , "Croatian Kuna");
	    currencyMap.put( "HUF" , "Hungarian Forint");
	    currencyMap.put( "IDR" , "Indonesian Rupiah");
	    currencyMap.put( "ILS" , "Israeli Shekel");
	    currencyMap.put( "INR" , "Indian Rupee");
	    currencyMap.put( "JPY" , "Japanese Yen");
	    currencyMap.put( "KRW" , "South Korean Won");
	    currencyMap.put( "MXN" , "Mexican Peso");
	    currencyMap.put( "MYR" , "Malaysian Ringgit");
	    currencyMap.put( "NOK" , "Norwegian Krone");
	    currencyMap.put( "NZD" , "New Zealand Dollar");
	    currencyMap.put( "PHP" , "Philippine Peso");
	    currencyMap.put( "PLN" , "Polish Zloty");
	    currencyMap.put( "RON" , "Romanian Leu");
	    currencyMap.put( "RUB" , "Russian Rouble");
	    currencyMap.put( "SEK" , "Swedish Krona");
	    currencyMap.put( "SGD" , "Singapore Dollar");
	    currencyMap.put( "THB" , "Thai Baht");
	    currencyMap.put( "TRY" , "Turkish Lira");
	    currencyMap.put( "USD" , "US Dollar");
	    currencyMap.put( "ZAR" , "South African Rand");
	    
	    //Build the array for reference to names and descriptions
	    keyArray = (String[])currencyMap.keySet().toArray();
	    valueArray = (String[]) currencyMap.values().toArray();
	    sizeOfList = keyArray.length;
	}
	
	
	public String[] getCurrencyInitialsList(){
		return keyArray;
	}
	public String[] getCurrencyDescList(){
		return valueArray;
	}
 
	public String getCurrencyToDisplay(int input){
		String value = currencyMap.get(keyArray[input]);
		String builder = keyArray[input] + " (" + value + ") "; 
		return builder;
	}
	
	
}
