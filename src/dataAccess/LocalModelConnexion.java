package dataAccess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import auxiliar.I_Data_Access;
import models.Catalog;
import models.Currency;
import models.Usuario;



/*
 * Todas los accesos a datos implementan la interfaz de Datos
 */

public class LocalModelConnexion implements I_Data_Access{

	
	private String usersAddress;
	private String catalogAddress;
	private String currencyAddress;
	private String filesCurrecytypes;
	
	public LocalModelConnexion() {
		usersAddress = "Files/data/Users.txt";
		catalogAddress = "Files/data/Catalog.txt";
		currencyAddress = "Files/data/Currency.txt";
		filesCurrecytypes = "Files/struct/CurrencyTypes.txt";
	}

	// data files
	
	public HashMap<Integer, Currency> getCurrencyData() {
		System.out.println("1");
		
		HashMap<Integer, Currency> actualCurrency = new HashMap<Integer, Currency>();
		File FileCurrency = new File(currencyAddress);

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(FileCurrency));
			String text = null;
			Currency currency = null;
			int id = 0;

			while ((text = reader.readLine()) != null) {

				String[] splitData = text.split(";");
				id = Integer.parseInt(splitData[1]);
				currency = new Currency(
						splitData[0],
						id,
						Integer.parseInt(splitData[2])
					);
				
				actualCurrency.put(id, currency);

			}
			
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
		System.out.println("2");
		
		HashMap<String, Catalog> actualCatalog = new HashMap<String, Catalog>();
		File FileCatalog = new File(catalogAddress);

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(FileCatalog));
			String text = null;
			Catalog dispensador = null;
			String clave = null;

			while ((text = reader.readLine()) != null) {
				System.out.println("leyendo archivo.........");

				String[] splitData = text.split(";");
				clave = splitData[0].toString();
				dispensador = new Catalog(
						clave, 
						splitData[1].toString(),
						Integer.parseInt(splitData[2]),
						Integer.parseInt(splitData[3])
					);
				
				actualCatalog.put(clave, dispensador);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return actualCatalog;
		
	}
	
	public HashMap<String, Usuario> getUsersData() {
		HashMap<String, Usuario> actualUsers = new HashMap<String, Usuario>();
		File FileUsers = new File(usersAddress);

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(FileUsers));
			String text = null;
			Usuario users = null;
			String clave = null;

			while ((text = reader.readLine()) != null) {
				System.out.println("leyendo archivo.........");

				String[] splitData = text.split(";");
				clave = splitData[0].toString();
				users = new Usuario(
						clave, 
						Float.parseFloat(splitData[1])
					);
				
				actualUsers.put(clave, users);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return actualUsers;
	}


	public boolean saveCurrency(HashMap<Integer, Currency> currency) {

		boolean todoOK = true;
		File FileCurrency = new File(currencyAddress);
		
		try {
			PrintWriter pw = new PrintWriter(FileCurrency);

			for(Integer key : currency.keySet()) {
				Currency value = currency.get(key);
				 pw.println(value.getName() + ";" + key + ";" + value.getAmount());
			}
			
			pw.close();
			
		}	catch (FileNotFoundException e1) {
			e1.printStackTrace();
			
		}	catch (Exception e) {
			return false;
		}
		

		return todoOK;

	}

	public boolean saveCatalog(HashMap<String, Catalog> catalog) {

		boolean todoOK = true;
		PrintWriter pw = null;
		File FileCatalog = new File(catalogAddress);
		
		try {
			pw = new PrintWriter(FileCatalog);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			for(String key : catalog.keySet()) {
				Catalog value=catalog.get(key);
				 pw.println(key+";"+value.getName()+";"+value.getprice() +";"+value.getAmount());
			}
		} catch (Exception e) {
			return false;
		}
		

		return todoOK;
	}

	public boolean saveUser(HashMap<String, Usuario> users) {

		boolean todoOK = true;
		PrintWriter pw = null;
		File FileUsers = new File(usersAddress);
		
		try {
			pw = new PrintWriter(FileUsers);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			for(String key : users.keySet()) {
				Usuario value=users.get(key);
				 pw.println(key+";"+value.getSaldo());
			}
		} catch (Exception e) {
			return false;
		}
		

		return todoOK;
	}


	// struct files
	
	public String[] getCurrencyTypes() {
		
		String[] types;
		File FileCurrecyTypes = new File(filesCurrecytypes);

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(FileCurrecyTypes));
	
			types = reader.readLine().split(";");

			reader.close();
			
			return types;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new String[0];
	}




}