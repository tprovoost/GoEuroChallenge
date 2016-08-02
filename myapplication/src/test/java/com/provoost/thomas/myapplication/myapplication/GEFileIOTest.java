package com.provoost.thomas.myapplication.myapplication;

import java.util.LinkedList;

import junit.framework.TestCase;

public class GEFileIOTest extends TestCase {

	LinkedList<GECityData> data;
	public static final String FILE_PATH = "C:\\test.csv";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		data = new LinkedList<GECityData>();
		for (int i = 1; i < 5; ++i) {
			GECityData city = new GECityData();
			city.setData(i, "Test" + i, "TestType" + i, 50d + i, 40d + i);
			data.add(city);
		}
	}

	public void testWriteData() throws Exception {
		GEFileIO.writeData(FILE_PATH, data);

		LinkedList<GECityData> readData = GEFileIO.readData(FILE_PATH);
		assertEquals(data.size(), readData.size());
		for (int i = 0; i < readData.size(); ++i) {
			assertTrue(readData.get(i).equals(data.get(i)));
		}
	}

}
