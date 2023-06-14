package auxiliar;

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
		propList = new HashMap<String, String>();

	}

	// Config  - get values
	public String inicialURL() {
		try {
			configInput = new FileInputStream("propiedades.properties");
			config.load(configInput);

			configInput = new FileInputStream("conexionApi.properties");
			configUrl.load(configInput);

			url = config.getProperty("host")
					+ configUrl.getProperty("start").replace("{id_maquina}", config.getProperty("config"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	public String endURL() {
		try {
			configInput = new FileInputStream("propiedades.properties");
			config.load(configInput);

			configInput = new FileInputStream("conexionApi.properties");
			configUrl.load(configInput);

			url = config.getProperty("host")
					+ configUrl.getProperty("turnoff").replace("{id_maquina}", config.getProperty("config"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	public String buyURL() {

		try {
			configInput = new FileInputStream("propiedades.properties");
			config.load(configInput);

			configInput = new FileInputStream("conexionApi.properties");
			configUrl.load(configInput);

			url = config.getProperty("host")
					+ configUrl.getProperty("buy").replace("{id_maquina}", config.getProperty("config"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	public String cardOnURL() {

		try {
			configInput = new FileInputStream("propiedades.properties");
			config.load(configInput);

			configInput = new FileInputStream("conexionApi.properties");
			configUrl.load(configInput);

			url = config.getProperty("host")
					+ configUrl.getProperty("cardon").replace("{id_maquina}", config.getProperty("config"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	public String cardOutURL() {

		try {
			configInput = new FileInputStream("propiedades.properties");
			config.load(configInput);

			configInput = new FileInputStream("conexionApi.properties");
			configUrl.load(configInput);

			url = config.getProperty("host")
					+ configUrl.getProperty("cardoff").replace("{id_maquina}", config.getProperty("config"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	// config - setValues
	
	public boolean setTimeOut(String num) {
		try {
			
			configOutput = new FileOutputStream("propiedades.properties");
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
			configInput = new FileInputStream("conexionFiles.properties");
			configUrl.load(configInput);

			url = config.getProperty(type.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	
}



