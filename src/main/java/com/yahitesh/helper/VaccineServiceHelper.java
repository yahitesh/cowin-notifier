package com.yahitesh.helper;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.yahitesh.endpoint.CowinEndPoint;
import com.yahitesh.model.Center;
import com.yahitesh.model.Session;
import com.yahitesh.model.VaccineInfo;

public class VaccineServiceHelper {

	public static HttpEntity<String> buildHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		return new HttpEntity<String>("parameters", headers);
	}

	public URI findByPinUri(String pincode, String date) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(CowinEndPoint.FIND_BY_PIN.getUrl())
				.queryParam("pincode", pincode).queryParam("date", date);
		return builder.build().toUri();
	}

	public URI findByDistrictUri(String pincode, String date) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(CowinEndPoint.FIND_BY_DISTRICT.getUrl())
				.queryParam("district_id", pincode).queryParam("date", date);
		return builder.build().toUri();
	}

	public URI calendarByDistrictUri(String districtId, String date) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(CowinEndPoint.CALENDAR_BY_DISTRICT.getUrl())
				.queryParam("district_id", districtId).queryParam("date", date);
		return builder.build().toUri();
	}

	public URI calendarByPinUri(String pincode, String date) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(CowinEndPoint.CALENDAR_BY_PIN.getUrl())
				.queryParam("pincode", pincode).queryParam("date", date);
		return builder.build().toUri();
	}

	public List<VaccineInfo> vaccineInfoBySession(List<Session> sessionList) {
		List<VaccineInfo> list = new ArrayList<VaccineInfo>();
		List<Session> sessionsFilterList = sessionList.stream().filter(s -> s.available_capacity > 0)
				.collect(Collectors.toList());
		for (Session session : sessionsFilterList) {
			VaccineInfo vaccineInfo = new VaccineInfo();
			vaccineInfo.name = session.name;
			vaccineInfo.address = session.address;
			vaccineInfo.stateName = session.state_name;
			vaccineInfo.districtName = session.district_name;
			vaccineInfo.blockName = session.block_name;
			vaccineInfo.pincode = String.valueOf(session.pincode);
			if (session.min_age_limit == 18) {
				vaccineInfo.ageLimit = "18 Plus";
			} else if (session.min_age_limit == 45) {
				vaccineInfo.ageLimit = "45 Plus";
			} else {
				vaccineInfo.ageLimit = String.valueOf(session.min_age_limit);
			}

			vaccineInfo.availableDose = String.valueOf(session.available_capacity);
			list.add(vaccineInfo);
		}
		System.out.println("list::" + list);
		return list;
	}

	public List<VaccineInfo> vaccineInfoByCenter(List<Center> centerList) {
		List<VaccineInfo> list = new ArrayList<VaccineInfo>();
		List<Center> centerFilterList = centerList.stream()
				.filter(s -> !CollectionUtils.isEmpty(s.sessions) && s.sessions.get(0).available_capacity > 0)
				.collect(Collectors.toList());
		for (Center center : centerFilterList) {
			VaccineInfo vaccineInfo = new VaccineInfo();
			vaccineInfo.name = center.name;
			vaccineInfo.address = center.address;
			vaccineInfo.stateName = center.state_name;
			vaccineInfo.districtName = center.district_name;
			vaccineInfo.blockName = center.block_name;
			vaccineInfo.pincode = String.valueOf(center.pincode);
			Session session = center.sessions.get(0);
			if (session.min_age_limit == 18) {
				vaccineInfo.ageLimit = "18 Plus";
			} else if (session.min_age_limit == 45) {
				vaccineInfo.ageLimit = "45 Plus";
			} else {
				vaccineInfo.ageLimit = String.valueOf(session.min_age_limit);
			}

			vaccineInfo.availableDose = String.valueOf(session.available_capacity);
			list.add(vaccineInfo);
		}
		System.out.println("list::" + list);
		return list;
	}

}
