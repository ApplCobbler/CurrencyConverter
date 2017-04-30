package models;

//parser provided by https://github.com/stleary/JSON-java

import org.json.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.json.simple.JSONArray;
import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class Parser {
	  private static String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }
	  
	  public void convertBetween(String base, String output, double amount){
		  JSONParser parser = new JSONParser();
		  
		  String newURL = "http://api.fixer.io/latest" + "?base=" + base;
		  
		  try{
		  InputStream is = new URL(newURL).openStream();
		  BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		  String newInput = readAll(rd);
		  Object obj = parser.parse(newInput);
		  JSONObject jsonObject = (JSONObject) obj; 
		  
		  
		  Map<String, Double> map = (Map<String, Double>) jsonObject.get("rates"); 
		  double exchangeValue = amount * map.get(output);  
		  System.out.println("exchange of " + amount + " between " 
				  			+ base + " and " + output + " is: " + exchangeValue);
		 
		  } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
  
		  
	  
	  
	  public void showMeStream(){
		  JSONParser parser = new JSONParser();
		  try{
		  InputStream is = new URL("http://api.fixer.io/latest").openStream();
		  
		  BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		  String newInput = readAll(rd);
		  Object obj = parser.parse(newInput);
		  JSONObject jsonObject = (JSONObject) obj;
		  
		  
		 Map<String, Double> map = (Map<String, Double>) jsonObject.get("rates");
		 
		 System.out.println(map.get("USD"));
		 
		  
		//  System.out.println(jsonObject);
		  } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }

}
