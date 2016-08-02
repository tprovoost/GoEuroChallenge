package com.provoost.thomas.myapplication.myapplication;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import junit.framework.TestCase;

public class GECityDataTest extends TestCase {

	public String jsonData;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		jsonData = new GEConnection("Berlin").postRequest();	

	}

	public void testPopulate() throws Exception {
		GECityData data = new GECityData();

		JsonElement root = new JsonParser().parse(jsonData);
		JsonObject city = root.getAsJsonArray().get(0).getAsJsonObject();
		data.populateFields(city);

		assertEquals(376217, data.getId());
		assertEquals("Berlin", data.getName());
		assertEquals("location", data.getType());
		assertEquals(52.52437, data.getLatitude());
		assertEquals(13.41053, data.getLongitude());
	}

	public void testEquals() throws Exception {
		GECityData data1 = new GECityData();
		GECityData data2 = new GECityData();
		
		JsonElement root = new JsonParser().parse(jsonData);
		JsonObject city = root.getAsJsonArray().get(0).getAsJsonObject();
		
		data1.populateFields(city);
		data2.populateFields(city);
		
		assertTrue(data1.equals(data2));
		assertFalse(data1.equals(null));
		
		data2.setName(null);
		assertFalse(data1.equals(data2));
		
		data1.setName(null);
		assertTrue(data1.equals(data2));
	}

}
