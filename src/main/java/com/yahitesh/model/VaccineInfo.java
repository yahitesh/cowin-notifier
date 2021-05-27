/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

package com.yahitesh.model;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

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
@JsonIgnoreType
@ToString
public class VaccineInfo {
	public String name;
	public String address;
	public String stateName;
	public String districtName;
	public String blockName;
	public String pincode;
	public String ageLimit;
	public String availableDose;
	public String date;

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
