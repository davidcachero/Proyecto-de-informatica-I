package auxiliar;

import com.squareup.okhttp.*;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by H3ku on 23/11/15. leves cambios del equipo CoffeBreack 09/06/2023
 */
public class ApiRequests {

	OkHttpClient client;
	HashMap<String, String> conResponse;

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	public ApiRequests() {
		client = new OkHttpClient();
	}

	/**
	 * Metodo usado para hacer peticiones GET.
	 * 
	 * @param url URL a la que realizar la peticion.
	 * @return Cuerpo y codigo de la respuesta.
	 * @throws IOException
	 */
	public HashMap<String, String> getRequest(String url) throws IOException {
		conResponse = new HashMap<String, String>();

		Request request = new Request.Builder().url(url).build();

		Response response = client.newCall(request).execute();
		conResponse.put("msg", response.body().string());
		conResponse.put("code", Integer.toString(response.code()));

		return conResponse;
	}

	/**
	 * Metodo usado para hacer peticiones json POST.
	 * 
	 * @param url  URL a la que realizar la peticion.
	 * @param json Contenido de la request en JSON.
	 * @return Cuerpo y codigo de la respuesta.
	 * @throws IOException
	 */
	public HashMap<String, String> postRequest(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();

		Response response = client.newCall(request).execute();
		conResponse = new HashMap<String, String>();
		conResponse.put("msg", response.body().string());
		conResponse.put("code", Integer.toString(response.code()));

		return conResponse;
	}

	/**
	 * Metodo usado para hacer una peticion POST mandando el json por un parametro
	 * llamado json
	 * 
	 * @param url  URL a la que realizar la peticion.
	 * @param json Contenido del parametro json
	 * @return Cuerpo y codigo de la respuesta.
	 * @throws IOException
	 */
	public HashMap<String, String> postRequestWithParams(String url, String json) throws IOException {
		RequestBody formBody = new FormEncodingBuilder().add("json", json).build();
		Request request = new Request.Builder().url(url).post(formBody).build();

		Response response = client.newCall(request).execute();
		conResponse = new HashMap<String, String>();
		conResponse.put("msg", response.body().string());
		conResponse.put("code", Integer.toString(response.code()));

		return conResponse;
	}

	/**
	 * Metodo usado para hacer una peticion PUT 
	 * 
	 * @param url  URL a la que realizar la peticion.
	 * @return Cuerpo y codigo de la respuesta del servidor.
	 * @throws IOException
	 */
	public HashMap<String, String> putRequest(String url) throws IOException {

		Request request = new Request.Builder().url(url).build();

		Response response = client.newCall(request).execute();
		conResponse = new HashMap<String, String>();
		conResponse.put("msg", response.body().string());
		conResponse.put("code", Integer.toString(response.code()));

		return conResponse;

	}
}
