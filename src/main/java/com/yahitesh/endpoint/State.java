package com.yahitesh.endpoint;

public enum State {

	RAJASTHAN("29", "Rajasthan"), 
	HARYANA("12", "Haryana"), 
	DELHI("9", "Delhi"), 
	UTTAR_PRADESH("34", "Uttar Pradesh");

	private String id;
	private String name;

	private State(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
