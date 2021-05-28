/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

package com.yahitesh.cowin.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yahitesh.cowin.model.SessionData.Session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author yaHitesh
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CenterData {

	private List<Center> centers;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Center {

		private int center_id;
		private String name;
		private String address;
		private String state_name;
		private String district_name;
		private String block_name;
		private int pincode;
		private int lat;
		@JsonProperty("long")
		private int longg;
		private String from;
		private String to;
		private String fee_type;
		private List<Session> sessions;
	}

}
