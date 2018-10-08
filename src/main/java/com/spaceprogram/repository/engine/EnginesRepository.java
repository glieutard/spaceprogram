/**
 * 
 */
package com.spaceprogram.repository.engine;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spaceprogram.model.engine.Engine;

/**
 * @author GLieutard
 * 
 * Engine Repository
 *
 */
public interface EnginesRepository extends CrudRepository<Engine, Integer> {
	
	/*
	 * find engines by spaceships
	 */
	@Query(value = "select e.* from engine e join spaceship_engine se on(e.id = se.idEngine) where se.idSpaceship = :idSpaceship", 
			nativeQuery = true)
	List<Engine> findByIdSpaceship(
			@Param("idSpaceship") Integer idSpaceship);

}
