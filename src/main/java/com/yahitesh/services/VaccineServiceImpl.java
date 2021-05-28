/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

package com.yahitesh.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yahitesh.helper.VaccineServiceHelper;
import com.yahitesh.model.CenterData;
import com.yahitesh.model.SessionData;
import com.yahitesh.model.StateData;
import com.yahitesh.model.StateData.District;
import com.yahitesh.model.VaccineInfo;

/**
 * @author yaHitesh
 * @since 1.0.0
 */
@Service
public class VaccineServiceImpl implements VaccineService {

	@Autowired
	private VaccineServiceHelper vaccineServiceHelper;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<VaccineInfo> calendarByDistrict(String districtId, String date) {
		ResponseEntity<CenterData> res = restTemplate.exchange(
				vaccineServiceHelper.calendarByDistrictUri(districtId, date), HttpMethod.GET,
				VaccineServiceHelper.buildHttpEntity(), CenterData.class);
		List<VaccineInfo> list = vaccineServiceHelper.vaccineInfoByCenter(res.getBody().getCenters());
		return list;
	}

	@Override
	public List<VaccineInfo> calendarByPin(String pincode, String date) {
		ResponseEntity<CenterData> res = restTemplate.exchange(vaccineServiceHelper.calendarByPinUri(pincode, date),
				HttpMethod.GET, VaccineServiceHelper.buildHttpEntity(), CenterData.class);
		System.out.println(res.getBody());
		List<VaccineInfo> list = vaccineServiceHelper.vaccineInfoByCenter(res.getBody().getCenters());
		return list;
	}

	@Override
	public List<VaccineInfo> findByDistrict(String districtId, String date) {
		ResponseEntity<SessionData> res = restTemplate.exchange(
				vaccineServiceHelper.findByDistrictUri(districtId, date), HttpMethod.GET,
				VaccineServiceHelper.buildHttpEntity(), SessionData.class);
		List<VaccineInfo> list = vaccineServiceHelper.vaccineInfoBySession(res.getBody().getSessions());
		return list;
	}

	@Override
	public List<VaccineInfo> findByPin(String pincode, String date) {
		ResponseEntity<SessionData> res = restTemplate.exchange(vaccineServiceHelper.findByPinUri(pincode, date),
				HttpMethod.GET, VaccineServiceHelper.buildHttpEntity(), SessionData.class);
		List<VaccineInfo> list = vaccineServiceHelper.vaccineInfoBySession(res.getBody().getSessions());
		return list;
	}

	@Override
	public List<District> findAllDistrict(String districtId) {
		ResponseEntity<StateData> res = restTemplate.exchange(vaccineServiceHelper.findAllDistrict(districtId),
				HttpMethod.GET, VaccineServiceHelper.buildHttpEntity(), StateData.class);
		return res.getBody().getDistricts();
	}

}
