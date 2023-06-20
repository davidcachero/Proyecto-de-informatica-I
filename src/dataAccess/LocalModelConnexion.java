package dataAccess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;

import auxiliar.ConnectionFiles;
import auxiliar.I_Data_Access;
import auxiliar.UsageProperties;
import models.Catalog;
import models.Currency;
import models.Intolerance;
import models.Usuario;

/**
 * 
 * @author equipoCoffeeBreak
 * 
 *         Datos guardados en ficheros
 *
 */

public class LocalModelConnexion implements I_Data_Access {

	private UsageProperties prop;

	public LocalModelConnexion() {
		prop = new UsageProperties();
	}

	// data files

	public HashMap<Float, Currency> getCurrencyData() {

		HashMap<Float, Currency> actualCurrency = new HashMap<Float, Currency>();
		File FileCurrency = new File( prop.conexionFiles(ConnectionFiles.CURRENCY) );

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(FileCurrency));
			String text = null;
			Currency currency = null;
			float id = 0;

			while ((text = reader.readLine()) != null) {

				String[] splitData = text.split(";");
				id = Float.parseFloat(splitData[1]);
				currency = new Currency(splitData[0], id, Integer.parseInt(splitData[2]));

				actualCurrency.put(id, currency);

			}

			System.out.println("[PROCESS FILES] datos de Currency descargados");

		} catch (FileNotFoundException e) {
			System.err.println("[ERROR FILES] CONNECTIONS");
			e.printStackTrace();

		} catch (IOException e) {
			System.err.println("[ERROR FILES] DATA");
			e.printStackTrace();
		}

		return actualCurrency;
	}

	public HashMap<String, Catalog> getCatalogData() {

		HashMap<String, Catalog> actualCatalog = new HashMap<String, Catalog>();
		File FileCatalog = new File(prop.conexionFiles(ConnectionFiles.CATALOG));

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(FileCatalog));
			String text = null;
			Catalog catalog = null;
			String clave = null;
			String[] intolerances = null;

			while ((text = reader.readLine()) != null) {

				String[] splitData = text.split(";");
				clave = splitData[0].toString();
				try {
					intolerances = splitData[5].split(":");
				} catch (IndexOutOfBoundsException e) {
					intolerances = new String[0];
				}
				catalog = new Catalog(clave, splitData[1].toString(), Float.parseFloat(splitData[2]),
						Integer.parseInt(splitData[3]), splitData[4], intolerances);

				actualCatalog.put(clave, catalog);
			}

			System.out.println("[PROCESS FILES] datos de Catalog descargados");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return actualCatalog;

	}

	public HashMap<String, Usuario> getUsersData() {
		HashMap<String, Usuario> actualUsers = new HashMap<String, Usuario>();
		File FileUsers = new File(prop.conexionFiles(ConnectionFiles.USERS));

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(FileUsers));
			String text = null;
			Usuario users = null;
			String clave = null;

			while ((text = reader.readLine()) != null) {

				String[] splitData = text.split(";");
				clave = splitData[0].toString();
				users = new Usuario(clave, splitData[1], Float.parseFloat(splitData[2]));

				actualUsers.put(clave, users);
			}

			System.out.println("[PROCESS FILES] datos de Users descargados");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return actualUsers;
	}

	public HashMap<String, Intolerance> getIntoleranceData() {
		HashMap<String, Intolerance> actualUsers = new HashMap<String, Intolerance>();
		File FileUsers = new File(prop.conexionFiles(ConnectionFiles.INTOLERANCES));

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(FileUsers));
			String text = null;
			
			String id;

			while ((text = reader.readLine()) != null) {

				String[] splitData = text.split(";");
				id = splitData[0].toString();
				Intolerance intolerance = new Intolerance(Integer.parseInt(id), splitData[1].toString(), splitData[2].toString());

				actualUsers.put(id, intolerance);
			}

			System.out.println("[PROCESS FILES] datos de Intolerances descargados");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return actualUsers;
	}
	
// Guardar las monedas
	public boolean saveCurrency(HashMap<Float, Currency> currency) {

		boolean todoOK = true;
		File FileCurrency = new File(prop.conexionFiles(ConnectionFiles.CURRENCY));

		try {
			PrintWriter pw = new PrintWriter(FileCurrency);

			for (Float key : currency.keySet()) {
				Currency value = currency.get(key);
				pw.println(value.getName() + ";" + key + ";" + value.getAmount());
			}

			pw.close();

			System.out.println("[PROCESS FILES] datos de Currency guardados");

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();

		} catch (Exception e) {
			return false;
		}

		return todoOK;

	}

// Guardar el catalogo de los productos
	public boolean saveCatalog(HashMap<String, Catalog> catalog) {

		boolean todoOK = true;
		String FileCatalog = prop.conexionFiles(ConnectionFiles.CATALOG);

		try {
			PrintWriter pw = new PrintWriter(new File(FileCatalog));
			
			for (Entry<String, Catalog> entry : catalog.entrySet()) {
				Catalog value = entry.getValue();

				String valueString = value.getKey() + ";" + value.getName() + ";" + value.getprice() + ";" + value.getAmount() + ";" + value.getImage();

				String valueIntolerances = ";";
				for (String idIntolerance : value.getIntolerances()) {
					valueIntolerances += idIntolerance + ":";
				}
				valueIntolerances = valueIntolerances.substring(0, valueIntolerances.length() - 1);

				pw.println(valueString + valueIntolerances);

			}

			pw.close();

			System.out.println("[PROCESS FILES] datos de Catalog guardados");

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return false;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}

		return todoOK;
	}

// Guardar los usuarios registrados
	public boolean saveUser(HashMap<String, Usuario> users) {

		boolean todoOK = true;
		File FileUsers = new File(prop.conexionFiles(ConnectionFiles.USERS));

		try {
			PrintWriter pw = new PrintWriter(FileUsers);

			for (String key : users.keySet()) {
				Usuario value = users.get(key);
				pw.println(key + ";" + value.getName() + ";" + value.getSaldo());
			}

			pw.close();

			System.out.println("[PROCESS FILES] datos de Users guardados");

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			return false;
		}

		return todoOK;
	}

	// Guardar las monedas
	public boolean saveIntolerances(HashMap<String, Intolerance> intolerances) {

		boolean todoOK = true;
		File FileCurrency = new File(prop.conexionFiles(ConnectionFiles.INTOLERANCES));

		try {
			PrintWriter pw = new PrintWriter(FileCurrency);

			for (String key : intolerances.keySet()) {
				Intolerance value = intolerances.get(key);
				pw.println(value.getId() + ";" + value.getNombre() + ";" + value.getImage());
			}

			pw.close();

			System.out.println("[PROCESS FILES] datos de Intolerances guardados");

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();

		} catch (Exception e) {
			return false;
		}

		return todoOK;

	}

	// struct files

	public String[] getCurrencyTypes() {

		String[] types;
		File FileCurrecyTypes = new File(prop.conexionFiles(ConnectionFiles.CURRENCY_TYPE));

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(FileCurrecyTypes));

			types = reader.readLine().split(";");

			reader.close();

			System.out.println("[PROCESS FILES] datos de CurrencyType descargados");

			return types;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new String[0];
	}

	// Config functions

	public void setConfig(HashMap<String, String> config) {
		prop.setTimeOut(config.get("TIMEOUT"));
	}

	public int getTimeOut() {
		// TODO Auto-generated method stub
		return prop.getTimeOut();
	}
}