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
/**
 * Reading value from file options.properties
 * @param optionKey
 * @return getValue
 * @throws IOException
 */
	public Object getOptionValue(String optionKey) throws IOException {

		InputStream input = null;

		input = getClass().getClassLoader().getResourceAsStream("options.properties");
		prop.load(input);
		Object getValue = prop.getProperty(optionKey);

		return getValue;
	}
/**
 * Writing value to file options.properties
 * @param optionKey
 * @param optionValue
 * @throws IOException
 */
	public void addOption(String optionKey, Object optionValue) throws IOException {
		OutputStream output = null;
		output = new FileOutputStream(
				"C:\\Users\\amanraj\\workspace\\assignment-resource-io-master\\src\\main\\resources\\options.properties");
		prop.setProperty(optionKey, optionValue.toString());

		output.close();
	}
}
