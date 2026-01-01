package com.qa.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	
	//read config.properties file using java code...
	
	private static Properties prop = new Properties();
	
	static {
		String fileName = "config.properties";
		InputStream input =  ConfigManager.class.getClassLoader().getResourceAsStream(fileName);
		
		if(input != null) {
			try {
				prop.load(input);
				System.out.println("Config Properties =====>> " + prop);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String get(String key) {
		return prop.getProperty(key).trim();
	}

	public static void set(String key, String value) {
		prop.setProperty(key, value);
	}

}
