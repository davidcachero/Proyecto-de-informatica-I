package auxiliar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Properties;


public class UsageProperties {

	Properties config;
	Properties configUrl;

	InputStream configInput;
	OutputStream configOutput;

	HashMap<String, String> propList;
	String url;

	public UsageProperties() {
		config = new Properties();
		configUrl = new Properties();
		propList = new HashMap<String, String>();

	}

	// Config  - get values
	public String getPath() {
		try {
			configInput = new FileInputStream(new File("Files/config/propiedades.properties"));
			config.load(configInput);

			configInput = new FileInputStream(new File("Files/config/conexionApi.properties"));
			configUrl.load(configInput);

			url = config.getProperty("SERVER_PATH")
					+ configUrl.getProperty("PATH").replace("{version}", config.getProperty("API_VERSION"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	
	public String getId() {
		try {
			configInput = new FileInputStream(new File("Files/config/propiedades.properties"));
			config.load(configInput);

			url = config.getProperty("ID");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	// config - setValues
	
	public boolean setTimeOut(String num) {
		try {
			
			configOutput = new FileOutputStream(new File("Files/config/propiedades.properties"));
			config.put("TIMEOUT", num);
			config.store(configOutput, "This is a sample properties file");
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	// files - get values

	public String conexionFiles(ConnectionFiles type) {

		try {
			configInput = new FileInputStream(new File("Files/config/conexionFiles.properties"));
			config.load(configInput);

			System.out.println(type.toString());
			url = config.getProperty(type.toString());
			System.out.println(url);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	public int getTimeOut() {
		try {
			configInput = new FileInputStream(new File("Files/config/propiedades.properties"));
			config.load(configInput);

			int time = Integer.parseInt((String) config.getProperty("TIMEOUT"));

			return time;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public String getUser() {
		try {
			configInput = new FileInputStream(new File("Files/config/propiedades.properties"));
			config.load(configInput);

			url = config.getProperty("SERVER_PATH");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	
}



