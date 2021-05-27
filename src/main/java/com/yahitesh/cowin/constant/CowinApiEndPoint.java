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
public enum CowinApiEndPoint {
	FIND_ALL_DISTRICT_BY_ID("https://cdn-api.co-vin.in/api/v2/admin/location/districts/"),
	CALENDAR_BY_DISTRICT("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict"),
	CALENDAR_BY_PIN("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin"),
	FIND_BY_PIN("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin"),
	FIND_BY_DISTRICT("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict");

	private String url;

	private CowinApiEndPoint(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

}
