package com.provoost.thomas.myapplication.myapplication;

import java.util.LinkedList;
import java.util.Objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GECityData {

	private int id;
	private String name;
	private String type;
	private double latitude;
	private double longitude;

	private static final String CITY_ID = "_id";
	private static final String CITY_NAME = "name";
	private static final String CITY_TYPE = "type";
	private static final String CITY_GEO = "geo_position";
	private static final String CITY_LATITUDE = "latitude";
	private static final String CITY_LONGITUDE = "longitude";

	public GECityData() {
	}

	/**
	 * Populates all fields of the city with a JSON object.
	 * 
	 * @param city
	 */
	public void populateFields(JsonObject city) {
		if (city.has(CITY_ID)) {
			id = city.get(CITY_ID).getAsInt();
		}

		if (city.has(CITY_NAME)) {
			name = city.get(CITY_NAME).getAsString();
		}

		if (city.has(CITY_TYPE)) {
			type = city.get(CITY_TYPE).getAsString();
		}

		if (city.has(CITY_GEO)) {
			JsonObject geoPosition = city.get(CITY_GEO).getAsJsonObject();
			if (geoPosition.has(CITY_LATITUDE) && geoPosition.has(CITY_LONGITUDE)) {
				latitude = geoPosition.get(CITY_LATITUDE).getAsDouble();
				longitude = geoPosition.get(CITY_LONGITUDE).getAsDouble();
			}
		}
	}

	/**
	 * Get a {@link LinkedList} of all the cities contained in the JSON result
	 * of the request.
	 * 
	 * @param requestResult
	 * @return
	 */
	public static LinkedList<GECityData> getCitiesFromJSon(String requestResult) {
		LinkedList<GECityData> cities = new LinkedList<GECityData>();
		JsonArray root = new JsonParser().parse(requestResult).getAsJsonArray();
		for (int i = 0; i < root.size(); ++i) {
			JsonObject o = root.get(i).getAsJsonObject();
			GECityData data = new GECityData();
			data.populateFields(o);
			cities.add(data);
		}
		return cities;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setData(int id, String name, String type, double longitude, double latitude) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	/**
	 * Get the formatted string for CSV.
	 * 
	 * @return
	 */
	public String getCSVFormat() {
		return id + "," + name + "," + type + "," + latitude + "," + longitude;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj))
			return true;
		if (!(obj instanceof GECityData))
			return false;
		GECityData city = (GECityData) obj;
		if (id != city.id)
			return false;
		if (!(Objects.equals(name, city.name)))
			return false;
		if (!(Objects.equals(type, city.type)))
			return false;
		if (latitude != city.latitude)
			return false;
		if (longitude != city.longitude)
			return false;
		return true;
	}

}
