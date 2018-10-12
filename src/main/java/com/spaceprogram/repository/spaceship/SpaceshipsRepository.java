/**
 * 
 */
package com.spaceprogram.repository.spaceship;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spaceprogram.model.spaceship.Spaceship;

/**
 * @author GLieutard
 * 
 * Spaceships Repository
 *
 */
public interface SpaceshipsRepository extends CrudRepository<Spaceship, Integer> {
	
	/*
	 * find spaceships by mission
	 */
	@Query(value = "select s.* from spaceships s join mission_spaceships ms on(s.id = ms.idSpaceship) where se.idMission = :idMission", 
			nativeQuery = true)
	List<Spaceship> findByIdMission(
			@Param("idSpaceship") Integer idMission);
	
}
