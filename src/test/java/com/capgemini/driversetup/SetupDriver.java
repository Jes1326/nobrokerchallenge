package com.capgemini.driversetup;
 
import java.util.HashMap;
import java.util.Map;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
 
public class SetupDriver {
	public static WebDriver getDriver(String browserName) {
		WebDriver driver = null;
		if(driver == null) {
			switch(browserName.toLowerCase()) {
			case "chrome":
				ChromeOptions chrome_options = new ChromeOptions();
				Map<String,Object> chromeprefs = new HashMap<>();
				chromeprefs.put("profile.default_content_settings_popups", 0);
				chromeprefs.put("profile.default_content_settings_values.notifications", 2);
				chromeprefs.put("profile.default_content_settings_values.geolocation", 2);
				chrome_options.setExperimentalOption("prefs", chromeprefs);
				driver = new ChromeDriver(chrome_options);
				break;
			case "edge":
				EdgeOptions edge_options = new EdgeOptions();
				Map<String,Object> edgeprefs = new HashMap<String, Object>();
				edgeprefs.put("profile.default_content_settings_popups", 0);
				edgeprefs.put("profile.default_content_settings_values.notifications", 2);
				edgeprefs.put("profile.default_content_settings_values.geolocation", 2);
				edge_options.setExperimentalOption("prefs", edgeprefs);
				driver = new EdgeDriver(edge_options);
				break;
				
			}
		}
		return driver;
	}
}