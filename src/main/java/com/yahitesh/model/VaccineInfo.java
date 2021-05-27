package com.yahitesh.model;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
				.append(lineSeparator).append(availableDose + " available on " + date + " for " + ageLimit)
				.append("-----------------------------");
		return sb.toString();
	}

}
