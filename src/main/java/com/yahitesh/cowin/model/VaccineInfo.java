/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

package com.yahitesh.cowin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yaHitesh
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreType
@Builder
public class VaccineInfo {

	private String name;
	private String address;
	private String stateName;
	private String districtName;
	private String blockName;
	private String pincode;
	private String ageLimit;
	private String availableDose;
	private String date;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String lineSeparator = System.getProperty("line.separator");
		sb.append(lineSeparator).append("Name : " + name).append(lineSeparator)
				.append("Address : " + address + "," + blockName + "," + districtName + "," + stateName + "," + pincode)
				.append(lineSeparator)
				.append("Info : " + availableDose + " dose available on " + date + " for " + ageLimit)
				.append(lineSeparator).append("-----------------------------");
		return sb.toString();
	}

}
