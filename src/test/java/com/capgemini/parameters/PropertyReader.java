package com.capgemini.parameters;

import java.io.FileReader;
import java.util.Properties;

public class PropertyReader {
	
	static String value;
	
	public static String getPropertyData(String propertyfilePath, String key) {
		
		try {
			FileReader propsfile = new FileReader(propertyfilePath);
			Properties properties = new Properties();
			properties.load(propsfile);
			value = properties.getProperty(key);
		} catch (Exception e) {
			System.out.println("Error reading properties file: " + e.getMessage());
			return null;
		}
		
		if(value==null) {
			System.out.println("Key not found in property file: "+ key);
			return null;
		}else {
			return value;
		}		
		
	}
	
}
