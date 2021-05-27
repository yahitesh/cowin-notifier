package com.yahitesh.endpoint;

public enum CowinEndPoint {
	FIND_ALL_DISTRICT_BY_ID("https://cdn-api.co-vin.in/api/v2/admin/location/districts/"),
	CALENDAR_BY_DISTRICT("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict"),
	CALENDAR_BY_PIN("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin"),
	FIND_BY_PIN("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin"),
	FIND_BY_DISTRICT("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict");

	private String url;

	private CowinEndPoint(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

}
