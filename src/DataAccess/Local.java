package DataAccess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import auxiliar.I_Data_Access;
import objects.Catalog;
import objects.Currency;



/*
 * Todas los accesos a datos implementan la interfaz de Datos
 */

public class Local implements I_Data_Access{
	
	File FileCatalog; // Fichero de productos
	File FileCurrency; // Fichero de monedero

	
	public HashMap<Integer, Currency> getCurrencyData() {
		
		HashMap<Integer, Currency> actualCurrency = new HashMap<Integer, Currency>();
		FileCurrency = new File("Files/data/currency.txt");

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
		
		HashMap<String, Catalog> actualCatalog = new HashMap<String, Catalog>();
		FileCatalog = new File("Files/data/Catalog.txt");

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(FileCatalog));
			String text = null;
			Catalog dispensador = null;
			String clave = null;

			while ((text = reader.readLine()) != null) {

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


	public boolean saveCurrency(HashMap<Integer, Currency> currency) {

		boolean todoOK = true;
		FileCurrency = new File("Files/data/currency.txt");
		
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
		FileCatalog = new File("Ficheros/datos/dispensadores.txt");
		
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




}