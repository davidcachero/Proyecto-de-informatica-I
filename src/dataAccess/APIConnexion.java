package dataAccess;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import auxiliar.ApiRequests;
import auxiliar.UsageProperties;
import models.Catalog;
import models.Intolerance;
import models.Purchase;

public class APIConnexion {

	private UsageProperties prop;
	private ApiRequests con;

	public APIConnexion() {
		prop = new UsageProperties();
	}

	// machine status
	public HashMap<String, Object> init() {
		HashMap<String, Object> initData = new HashMap<String, Object>();
		String response = "";

		try {
			HashMap<String, String> rawResponse = con.getRequest(prop.inicialURL());

			switch (Integer.parseInt(rawResponse.get("code"))) {

			case 200:
			case 201:
			case 204:
				// Manejar respuesta exitosa
				response = rawResponse.get("msg");
			case 400: // Manejar error de solicitud incorrecta
			case 401: // Error acceso denegado
			case 403: // Error sin acceso al servidor
			case 404: // Error de recurso no encontrado
			case 500: // Error interno del servidor
			default: {
				response = "ERROR CONEXION API: " + rawResponse.get("msg");
				System.out.println(response);
			}}

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
			e1.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return initData;
	}

	private HashMap<String, String> formatConfig(JSONObject respuesta) {
		HashMap<String, String> configList = new HashMap<String, String>();

		JSONArray configData = (JSONArray) respuesta.get("config");

		for (Object data : configData) {
			JSONObject aux = (JSONObject) data;

			configList.put("TIMEOUT", aux.get("timeout").toString());
		}

		return configList;
	}

	private HashMap<Integer, Catalog> formatProducts(JSONObject respuesta) {
		HashMap<Integer, Catalog> prodList = new HashMap<Integer, Catalog>();
		JSONArray rawProducts = (JSONArray) respuesta.get("products");

		for (Object data : rawProducts) {
			JSONObject aux = (JSONObject) data;

			int id = Integer.parseInt(aux.get("id_producto").toString());

			JSONArray rawIntolerances = (JSONArray) aux.get("intolerancias");

			String[] listIntolerance = new String[rawIntolerances.size()];

			int count = 0;
			for (Object intolerance : rawProducts) {
				JSONObject objIntolerance = (JSONObject) intolerance;

				listIntolerance[count] = objIntolerance.get("id_intolerancia").toString();
			}

			prodList.put(id, new Catalog(aux.get("id_producto").toString(), aux.get("nombre").toString(),
					Float.parseFloat(aux.get("precio").toString()), Integer.parseInt(aux.get("cantidad").toString()),
					aux.get("imagen").toString(), listIntolerance));
		}

		return prodList;
	}

	private HashMap<Integer, Intolerance> formatIntolerances(JSONObject respuesta) {
		HashMap<Integer, Intolerance> intoleranceList = new HashMap<Integer, Intolerance>();
		JSONArray rawIntolerancias = (JSONArray) respuesta.get("intolerancias");

		for (Object data : rawIntolerancias) {
			JSONObject aux = (JSONObject) data;

			int id = Integer.parseInt(aux.get("id_intolerancia").toString());

			intoleranceList.put(id, new Intolerance(id, aux.get("nombre").toString(), aux.get("imagen").toString()));
		}

		return intoleranceList;
	}

	// Funciones que se llaman al terminar
	
	public boolean end(String data) {
		JSONObject configList = new JSONObject();

		try {

			// Crear objetos con datos de configuracion
			return toJSONConfig();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean toJSONConfig() throws IOException {
		HashMap<String, String> rawResponse = con.putRequest(prop.endURL());
		String response = "";

		switch (Integer.parseInt(rawResponse.get("code"))) {

		case 200:
		case 201:
		case 204:
			// Manejar respuesta exitosa
			System.out.println("configuracion");
			return true;
		case 400: // Manejar error de solicitud incorrecta
		case 401: // Error acceso denegado
		case 403: // Error sin acceso al servidor
		case 404: // Error de recurso no encontrado
		case 500: // Error interno del servidor
		default: {
			System.out.println("ERROR CONEXION API: " + rawResponse.get("msg"));
			return false;
		}

		}

	}

	// events

	public boolean buy(Purchase buyData) {
		String response = "";

		try {
			HashMap<String, String> rawResponse = con.postRequestWithParams(prop.buyURL(), buyData.generateJSON());

			switch (Integer.parseInt(rawResponse.get("code"))) {

			case 200:
			case 201:
			case 204:
				// Manejar respuesta exitosa
				response = rawResponse.get("msg");
				return true;
			case 400: // Manejar error de solicitud incorrecta
			case 401: // Error acceso denegado
			case 403: // Error sin acceso al servidor
			case 404: // Error de recurso no encontrado
			case 500: // Error interno del servidor
			default: {
				response = "ERROR CONEXION API: " + rawResponse.get("msg");
				System.out.println(response);
				return false;
			}

			}

		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public HashMap<String, String> login(String id) {
		HashMap<String, String> returnData = new HashMap<String, String>();
		String response = "";

		try {
			returnData = con.postRequest(prop.cardOnURL(), id);

			switch (Integer.parseInt(returnData.get("code"))) {

			case 200:
			case 201:
			case 204:
				// Manejar respuesta exitosa
				break;
			case 400: // Manejar error de solicitud incorrecta
			case 401: // Error acceso denegado
			case 403: // Error sin acceso al servidor
			case 404: // Error de recurso no encontrado
			case 500: // Error interno del servidor
			default:
				System.out.println(response);

			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
			returnData.put("code", "ERROR");
			returnData.put("msg", e.getMessage());

		} catch (ProtocolException e) {
			e.printStackTrace();
			returnData.put("code", "ERROR");
			returnData.put("msg", e.getMessage());

		} catch (IOException e) {
			e.printStackTrace();
			returnData.put("code", "ERROR");
			returnData.put("msg", e.getMessage());
		}

		return returnData;
	}

	public HashMap<String, String> logout(HashMap<String, String> sesionData) {
		HashMap<String, String> returnData = new HashMap<String, String>();
		String response = "";
		
		JSONObject sesionJSON = (JSONObject) sesionData;

		try {
			returnData = con.postRequest(prop.cardOutURL(), sesionJSON.toJSONString());

			switch (Integer.parseInt(returnData.get("code"))) {

			case 200:
			case 201:
			case 204:
				// Manejar respuesta exitosa
				break;
			case 400: // Manejar error de solicitud incorrecta
			case 401: // Error acceso denegado
			case 403: // Error sin acceso al servidor
			case 404: // Error de recurso no encontrado
			case 500: // Error interno del servidor
			default:
				System.out.println(response);

			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
			returnData.put("code", "ERROR");
			returnData.put("msg", e.getMessage());

		} catch (ProtocolException e) {
			e.printStackTrace();
			returnData.put("code", "ERROR");
			returnData.put("msg", e.getMessage());

		} catch (IOException e) {
			e.printStackTrace();
			returnData.put("code", "ERROR");
			returnData.put("msg", e.getMessage());
		}

		return returnData;

	}

}
