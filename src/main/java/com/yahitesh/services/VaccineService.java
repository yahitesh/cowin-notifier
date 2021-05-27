package com.yahitesh.services;

import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yahitesh.helper.VaccineServiceHelper;
import com.yahitesh.model.CenterRoot;
import com.yahitesh.model.SessionRoot;
import com.yahitesh.model.StateData;
import com.yahitesh.model.VaccineInfo;
import com.yahitesh.model.StateData.District;

@Service
public class VaccineService {

	public static void main(String[] args) {
		new VaccineService().findAllDistrict("34");
	}

	public List<VaccineInfo> calendarByDistrict(String districtId, String date) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<CenterRoot> res = rt.exchange(new VaccineServiceHelper().calendarByDistrictUri(districtId, date),
				HttpMethod.GET, VaccineServiceHelper.buildHttpEntity(), CenterRoot.class);
		System.out.println(res.getBody());
		List<VaccineInfo> list = new VaccineServiceHelper().vaccineInfoByCenter(res.getBody().centers);
		return list;
	}

	public List<VaccineInfo> calendarByPin(String pincode, String date) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<CenterRoot> res = rt.exchange(new VaccineServiceHelper().calendarByPinUri(pincode, date),
				HttpMethod.GET, VaccineServiceHelper.buildHttpEntity(), CenterRoot.class);
		System.out.println(res.getBody());
		List<VaccineInfo> list = new VaccineServiceHelper().vaccineInfoByCenter(res.getBody().centers);
		return list;
	}

	public List<VaccineInfo> findByDistrict(String districtId, String date) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<SessionRoot> res = rt.exchange(new VaccineServiceHelper().findByDistrictUri(districtId, date),
				HttpMethod.GET, VaccineServiceHelper.buildHttpEntity(), SessionRoot.class);
		System.out.println(res.getBody());
		List<VaccineInfo> list = new VaccineServiceHelper().vaccineInfoBySession(res.getBody().sessions);
		return list;
	}

	public List<VaccineInfo> findByPin(String pincode, String date) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<SessionRoot> res = rt.exchange(new VaccineServiceHelper().findByPinUri(pincode, date),
				HttpMethod.GET, VaccineServiceHelper.buildHttpEntity(), SessionRoot.class);
		System.out.println(res.getBody());
		List<VaccineInfo> list = new VaccineServiceHelper().vaccineInfoBySession(res.getBody().sessions);
		return list;
	}

	public List<District> findAllDistrict(String districtId) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<StateData> res = rt.exchange(new VaccineServiceHelper().findAllDistrict(districtId),
				HttpMethod.GET, VaccineServiceHelper.buildHttpEntity(), StateData.class);
		return res.getBody().getDistricts();
	}
		
}
