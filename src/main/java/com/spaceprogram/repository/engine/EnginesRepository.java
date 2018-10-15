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
 * Engines Repository
 *
 */
public interface EnginesRepository extends CrudRepository<Engine, Integer> {
	
	/*
	 * find engines by spaceships
	 */
	@Query(value = "select e.* from engine e join spaceship_engines se on(e.id = se.idEngine) where se.idSpaceship = :idSpaceship", 
			nativeQuery = true)
	List<Engine> findByIdSpaceship(
			@Param("idSpaceship") Integer idSpaceship);
	
	/*
	 * Is engine used
	 */
	@Query(value = "select cast(case when count(*) > 0 then 1 else 0 end as bit) from spaceship_engines where idEngine = :idEngine", 
			nativeQuery = true)
	Boolean isUsed(@Param("idEngine") Integer idEngine);

	/**
	 * Count by id
	 */
	Long countById(Integer id);

}
