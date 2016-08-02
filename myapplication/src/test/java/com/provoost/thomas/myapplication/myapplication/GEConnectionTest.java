package com.provoost.thomas.myapplication.myapplication;

import junit.framework.TestCase;

public class GEConnectionTest extends TestCase {

	public void testPostRequest() throws Exception {
		GEConnection connection = new GEConnection("Berlin");
		connection.postRequest();
		assertTrue(true);
	}
	
}
