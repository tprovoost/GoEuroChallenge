package com.provoost.thomas.myapplication.myapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GEConnection {

	private String cityName;
	private static final String API_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";

	public GEConnection(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * Sends the request on the server and outputs the result. This method uses
	 * the {@link HttpURLConnection} API of Java. If no answer could be found, a
	 * Error Code 200 is sent.
	 * 
	 * @return
	 * @throws IOException
	 */
	public String postRequest() throws IOException {
		String request = API_URL + cityName;

		URL url = new URL(request);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		StringBuilder output = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			output.append(line);
		}

		conn.disconnect();

		return output.toString();
	}

}
