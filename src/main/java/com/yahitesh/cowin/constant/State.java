/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

package com.yahitesh.cowin.constant;

/**
 * @author yaHitesh
 * @since 1.0.0
 */
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
