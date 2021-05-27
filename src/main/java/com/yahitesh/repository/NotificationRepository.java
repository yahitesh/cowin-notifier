package com.yahitesh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yahitesh.model.Notification;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long>  {
	
	@Query("select n from Notification n where n.notify = :notifyFlag order by id")
	List<Notification> findAllUnNotify(@Param("notifyFlag") String notifyFlag);
	Notification findByEmail(String email);
	List<Notification> findAll();
}
