/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

package com.yahitesh.model;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author yaHitesh
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreType
@ToString
public class SessionData {

	public List<Session> sessions;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreType
	@ToString
	public static class Session {
		
		private int center_id;
		private String name;
		private String address;
		private String state_name;
		private String district_name;
		private String block_name;
		private int pincode;
		private String from;
		private String to;
		private int lat;
		@JsonProperty("long")
		private int lonng;
		private String fee_type;
		private String session_id;
		private String date;
		private String available_capacity_dose1;
		private String available_capacity_dose2;
		private int available_capacity;
		private String fee;
		private int min_age_limit;
		private String vaccine;
		private List<String> slots;
		
	}

}
