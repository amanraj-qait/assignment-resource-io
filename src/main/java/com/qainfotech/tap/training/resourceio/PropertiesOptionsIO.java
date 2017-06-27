package com.qainfotech.tap.training.resourceio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO {

	Properties prop = new Properties();

	public Object getOptionValue(String optionKey) throws IOException {

		InputStream input = null;

		input = getClass().getClassLoader().getResourceAsStream("options.properties");
		prop.load(input);
		Object a = prop.getProperty(optionKey);

		return a;
	}

	public void addOption(String optionKey, Object optionValue) throws IOException {
		OutputStream output = null;
		output = new FileOutputStream(
				"C:\\Users\\amanraj\\workspace\\assignment-resource-io-master\\src\\main\\resources\\options.properties");
		prop.setProperty(optionKey, optionValue.toString());

		output.close();
	}
}
