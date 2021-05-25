package com.yahitesh.model;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreType
@ToString
public class Session {
	public int center_id;
	public String name;
	public String address;
	public String state_name;
	public String district_name;
	public String block_name;
	public int pincode;
	public String from;
	public String to;
	public int lat;
	@JsonProperty("long")
	public int lonng;
	public String fee_type;
    public String session_id;
    public String date;
    public String available_capacity_dose1;
    public String available_capacity_dose2;
    public int available_capacity;
    public String fee;
    public int min_age_limit;
    public String vaccine;
    public List<String> slots;
}
