package com.capgemini.parameters;

import java.io.FileReader;
import org.json.simple.parser.JSONParser;

public class JsonReader {
	
	
	public static String getJsonData(String jsonPath, String key) {
		String value;
		JSONParser parser = new JSONParser();
		try {
			FileReader jsonfile = new FileReader(jsonPath);
			Object obj = parser.parse(jsonfile);
			value = (String) obj;
		} catch (Exception e) {
			System.out.println("Error reading JSON file: " + e.getMessage());
			return null;
		}
		
		if(value==null) {
			System.out.println("Key not found in JSON file: "+ key);
			return null;
		}else {
			return value;
		}		
	}
}
