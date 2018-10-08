/**
 * 
 */
package com.spaceprogram.repository.crew;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spaceprogram.model.crew.Crew;

/**
 * @author GLieutard
 * 
 * Crews Repository
 *
 */
public interface CrewsRepository extends CrudRepository<Crew, Integer> {

	/*
	 * find crews by spaceships
	 */
	@Query(value = "select c.* from crew c join spaceship_crew sc on(c.id = sc.idCrew) where sc.idSpaceship = :idSpaceship", 
			nativeQuery = true)
	List<Crew> findByIdSpaceship(
			@Param("idSpaceship") Integer idSpaceship);
	
}
