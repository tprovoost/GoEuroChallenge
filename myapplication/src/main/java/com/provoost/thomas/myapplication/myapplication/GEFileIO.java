package com.provoost.thomas.myapplication.myapplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;

public class GEFileIO {

	/**
	 * Write the cities' data into a CSV file. Builds a StringBuilder with all
	 * the data in it, then dumps it into the file.
	 * 
	 * @param path
	 *            : path of the file to save the data into
	 * @param data
	 *            : the data saved
	 * @throws IOException
	 */
	public static void writeData(String path, LinkedList<GECityData> data) throws IOException {
		StringBuilder builder = new StringBuilder();
		for (GECityData city : data) {
			builder.append(city.getCSVFormat()).append("\r\n");
		}
		Files.write(Paths.get(path), builder.toString().getBytes(), StandardOpenOption.WRITE,
				StandardOpenOption.CREATE);
	}

	/**
	 * Reads the data from a CSV file. As of now, mostly used for testing.
	 * 
	 * @param path
	 *            : path of the file to read the data from.
	 * @return
	 * @throws IOException
	 */
	public static LinkedList<GECityData> readData(String path) throws IOException {
		LinkedList<GECityData> allCities = new LinkedList<GECityData>();
		List<String> lines = Files.readAllLines(new File(path).toPath());

		for (String s : lines) {
			String[] attributes = s.split(",");
			int i = 0;
			GECityData data = new GECityData();
			data.setId(Integer.valueOf(attributes[i++]));
			data.setName(attributes[i++]);
			data.setType(attributes[i++]);
			data.setLatitude(Double.valueOf(attributes[i++]));
			data.setLongitude(Double.valueOf(attributes[i++]));
			allCities.add(data);
		}
		return allCities;
	}

}
