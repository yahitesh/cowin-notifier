/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

package com.yahitesh.services;

import java.util.List;

import com.yahitesh.model.StateData.District;
import com.yahitesh.model.VaccineInfo;

/**
 * @author yaHitesh
 * @since 1.0.0
 */
public interface VaccineService {
	
	List<VaccineInfo> calendarByDistrict(String districtId, String date);
	
	List<VaccineInfo> calendarByPin(String districtId, String date);
	
	List<VaccineInfo> findByDistrict(String districtId, String date);
	
	List<VaccineInfo> findByPin(String pincode, String date);
	
	List<District> findAllDistrict(String districtId);

}
