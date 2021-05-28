/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

package com.yahitesh.cowin.services;

import com.yahitesh.cowin.model.Email;

/**
 * @author yaHitesh
 * @since 1.0.0
 */
public interface EmailService {
	
	Boolean sendSimpleMessage(Email email);

}
