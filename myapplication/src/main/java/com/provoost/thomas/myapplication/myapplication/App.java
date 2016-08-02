package com.provoost.thomas.myapplication.myapplication;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	if (args.length > 0)
    		System.out.println("This program needs a city name to run.");
        
    	GEConnection connection = new GEConnection(args[0]);
    	try {
			String jsonData = connection.postRequest();
			LinkedList<GECityData> cities = GECityData.getCitiesFromJSon(jsonData);
			GEFileIO.writeData(args[0] + ".csv", cities);			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    	System.out.println( "Hello World!" );
    }
}
