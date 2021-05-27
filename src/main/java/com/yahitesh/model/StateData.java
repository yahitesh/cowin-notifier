package com.yahitesh.model;

import java.util.List;

public class StateData {

	private List<District> districts;

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	public static class District {
		private int district_id;
		private String district_name;

		public int getDistrict_id() {
			return district_id;
		}

		public void setDistrict_id(int district_id) {
			this.district_id = district_id;
		}

		public String getDistrict_name() {
			return district_name;
		}

		public void setDistrict_name(String district_name) {
			this.district_name = district_name;
		}

		@Override
		public String toString() {
			return "District [district_id=" + district_id + ", district_name=" + district_name + "]";
		}

	}

	@Override
	public String toString() {
		return "StateData [districts=" + districts + "]";
	}

}
