/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

package com.yahitesh.cowin.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author yaHitesh
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class StateData {

	private List<District> districts;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@Builder
	public static class District {
		private int district_id;
		private String district_name;

	}

}
