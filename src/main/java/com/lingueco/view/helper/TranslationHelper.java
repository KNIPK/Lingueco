package com.lingueco.view.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranslationHelper {
	
	public static String getTranslation(String name, String language) {
		
		Logger logger = LoggerFactory.getLogger(TranslationHelper.class);
		
		if(language == null) {
			language = TranslationHelper.getSystemLanguage();
		}
		String propertiesFileName = language + ".properties";
		
		Properties properties = new Properties();
		InputStream inputStream = null;
		
		try {
			inputStream = TranslationHelper.class.getClassLoader().getResourceAsStream("translations/" + propertiesFileName);
			
			if(inputStream != null) {
				properties.load(inputStream);
			} else {
				logger.info("Cannot load translation files.");
			}
			inputStream.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		String translation = properties.getProperty(name);
		properties.clear();
		
		return (translation != null) ? translation : name;
	}
	
	public static String getSystemLanguage() {
		//TODO system language is based on the user configuration
		return "pl";
	}
	
}
