package dataAccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import auxiliar.ApiRequests;
import auxiliar.UsageProperties;
import models.Catalog;
import models.Intolerance;

public class APIConnexion {

	private UsageProperties prop;
	private ApiRequests con;

	public APIConnexion() {
		prop = new UsageProperties();
	}


	// machine status
	public void init() {
		HashMap<String, Object> initData = new HashMap<String, Object>();

		
		try {
			HashMap<String, String> rawResponse = con.getRequest(prop.inicialURL());
			String response = rawResponse.get("msg");

			switch (Integer.parseInt(rawResponse.get("code"))) {
				case 299: {
					System.out.println("ERROR API: " + response);
				}
				default:
					System.out.println("ERROR CONEXION API: " + response);
				}
			
			System.out.println(response); // Traza para pruebas
			
			JSONObject respuesta = (JSONObject) JSONValue.parse(response);
			
			
			// Crear objetos con datos de configuracion
			HashMap<String, String> configList = formatConfig(respuesta);
			
			// Crear objetos con los datos de productos
			HashMap<Integer, Catalog> prodList = formatProducts(respuesta);
			
			// Crear objetos con los datos de las intolerancias
			HashMap<Integer, Intolerance> intoleranceList = formatIntolerances(respuesta);

			
			initData.put("CONF", configList);
			initData.put("PROD", prodList);
			initData.put("INTO", intoleranceList);

		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private HashMap<Integer, Catalog> formatProducts(JSONObject respuesta) {
		HashMap<Integer, Catalog> prodList = new HashMap<Integer, Catalog>();
		JSONArray rawProducts = (JSONArray) respuesta.get("products");

		for (Object data : rawProducts) {
			JSONObject aux = (JSONObject) data;
			

			int id = Integer.parseInt( aux.get("id_producto").toString());
			
			JSONArray rawIntolerances = (JSONArray) aux.get("intolerancias");

			String[] listIntolerance = new String[rawIntolerances.size()];
			
			int count = 0;
			for (Object intolerance : rawProducts) {
				JSONObject objIntolerance = (JSONObject) intolerance;
				
				listIntolerance[count] = objIntolerance.get("id_intolerancia").toString();
			}
			
			prodList.put(
				id
				, new Catalog(
					aux.get("id_producto").toString()
					, aux.get("nombre").toString()
					, Float.parseFloat(aux.get("precio").toString())
					, Integer.parseInt(aux.get("cantidad").toString())
					, aux.get("imagen").toString()
					, listIntolerance
			));
		}
		
		return prodList;
	}

	private HashMap<Integer, Intolerance> formatIntolerances (JSONObject respuesta){
		HashMap<Integer, Intolerance> intoleranceList = new HashMap<Integer, Intolerance>();
		JSONArray rawIntolerancias = (JSONArray) respuesta.get("intolerancias");

		for (Object data : rawIntolerancias) {
			JSONObject aux = (JSONObject) data;

			int id = Integer.parseInt( aux.get("id_intolerancia").toString());
			
			intoleranceList.put(
				id
				, new Intolerance(
						id
						, aux.get("nombre").toString()
						, aux.get("imagen").toString()
					)
			);
		}
		
		return intoleranceList;
	}
	
	private HashMap<String, String> formatConfig (JSONObject respuesta){
		HashMap<String, String> configList = new HashMap<String, String>();
		
		JSONArray configData = (JSONArray) respuesta.get("config");
		
		for (Object data : configData) {
			JSONObject aux = (JSONObject) data;

			configList.put("TIMEOUT", aux.get("timeout").toString());
		}
		
		return configList;
	}
	
	
	// Funciones que se llaman al terminar
	public void end() {

	}

	// events

	public void buy() {

	}

	public void login() {

	}

	public void logout() {

	}

}
