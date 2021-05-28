/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

package com.yahitesh.cowin.helper;

/**
 * @author yaHitesh
 * @since 1.0.0
 */
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.yahitesh.cowin.constant.Constant;
import com.yahitesh.cowin.constant.CowinApiEndPoint;
import com.yahitesh.cowin.model.CenterData.Center;
import com.yahitesh.cowin.model.SessionData.Session;
import com.yahitesh.cowin.model.VaccineInfo;

@Component
public class VaccineServiceHelper {

	public static HttpEntity<String> buildHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add(Constant.USER_AGENT_KEY, Constant.USER_AGENT_VALUE);
		return new HttpEntity<String>(Constant.PARAM_PARAMETERS, headers);
	}

	public URI findByPinUri(String pincode, String date) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(CowinApiEndPoint.FIND_BY_PIN.getUrl())
				.queryParam(Constant.PARAM_PINCODE, pincode).queryParam(Constant.PARAM_DATE, date);
		return builder.build().toUri();
	}

	public URI findByDistrictUri(String pincode, String date) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(CowinApiEndPoint.FIND_BY_DISTRICT.getUrl())
				.queryParam(Constant.PARAM_DISTRICT_ID, pincode).queryParam(Constant.PARAM_DATE, date);
		return builder.build().toUri();
	}

	public URI calendarByDistrictUri(String districtId, String date) {
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromUriString(CowinApiEndPoint.CALENDAR_BY_DISTRICT.getUrl())
				.queryParam(Constant.PARAM_DISTRICT_ID, districtId).queryParam(Constant.PARAM_DATE, date);
		return builder.build().toUri();
	}

	public URI calendarByPinUri(String pincode, String date) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(CowinApiEndPoint.CALENDAR_BY_PIN.getUrl())
				.queryParam(Constant.PARAM_PINCODE, pincode).queryParam(Constant.PARAM_DATE, date);
		return builder.build().toUri();
	}

	public String findAllDistrict(String districtId) {
		return CowinApiEndPoint.FIND_ALL_DISTRICT_BY_ID.getUrl().concat(districtId);
	}

	public List<VaccineInfo> vaccineInfoBySession(List<Session> sessionList) {
		List<VaccineInfo> list = new ArrayList<VaccineInfo>();
		sessionList.stream().filter(s -> s.getAvailable_capacity() > 0).collect(Collectors.toList())
				.forEach(session -> {
					list.add(VaccineInfo.builder().name(session.getName()).address(session.getAddress())
							.stateName(session.getState_name()).districtName(session.getDistrict_name())
							.blockName(session.getBlock_name()).pincode(String.valueOf(session.getPincode()))
							.ageLimit(getAgeLimitMsg(session.getMin_age_limit()))
							.availableDose(String.valueOf(session.getAvailable_capacity())).date(session.getDate())
							.build());
				});
		return list;
	}

	public List<VaccineInfo> vaccineInfoByCenter(List<Center> centerList) {
		List<VaccineInfo> list = new ArrayList<VaccineInfo>();
		centerList.stream().filter(
				s -> !CollectionUtils.isEmpty(s.getSessions()) && s.getSessions().get(0).getAvailable_capacity() > 0)
				.collect(Collectors.toList()).forEach(center -> {
					Session session = center.getSessions().get(0);
					list.add(VaccineInfo.builder().name(center.getName()).address(center.getAddress())
							.stateName(center.getState_name()).districtName(center.getDistrict_name())
							.blockName(center.getBlock_name()).pincode(String.valueOf(center.getPincode()))
							.ageLimit(getAgeLimitMsg(session.getMin_age_limit()))
							.availableDose(String.valueOf(session.getAvailable_capacity())).date(session.getDate())
							.build());
				});
		return list;
	}

	private String getAgeLimitMsg(int minAgeLimit) {
		if (minAgeLimit == 18) {
			return Constant.AGE_18_44;
		} else if (minAgeLimit == 45) {
			return Constant.AGE_45_PLUS;
		} else {
			return String.valueOf(minAgeLimit);
		}
	}

}
