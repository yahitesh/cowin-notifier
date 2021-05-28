/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

package com.yahitesh.cowin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yahitesh.cowin.model.Notification;

/**
 * @author yaHitesh
 * @since 1.0.0
 */
@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

	@Query("select n from Notification n where n.notify = :notifyFlag order by id")
	List<Notification> findByNotify(@Param("notifyFlag") String notifyFlag);

	Notification findByEmail(String email);

	List<Notification> findAll();
}
