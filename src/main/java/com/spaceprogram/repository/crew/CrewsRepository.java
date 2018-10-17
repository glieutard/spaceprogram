/**
 * 
 */
package com.spaceprogram.repository.crew;

import java.util.List;

import javax.transaction.Transactional;

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
@Transactional
public interface CrewsRepository extends CrudRepository<Crew, Integer> {

	/*
	 * find crews by spaceships
	 */
	@Query(value = "select c.* from crew c join spaceship_crews sc on(c.id = sc.idCrew) where sc.idSpaceship = :idSpaceship", 
			nativeQuery = true)
	List<Crew> findByIdSpaceship(
			@Param("idSpaceship") Integer idSpaceship);
	
	/*
	 * is crew in mission
	 */
	@Query(value = "select cast(case when count(*) > 0 then 1 else 0 end as bit) from mission_spaceships ms"
			+ " join spaceship s on (s.id = ms.idSpaceship)"
			+ " join spaceship_crews sc on (s.id = sc.idSpaceship)"
			+ " where sc.idCrew = :idCrew", nativeQuery = true)
	Boolean isInMission(@Param("idCrew") Integer idCrew);

	/*
	 * is crew in another spaceship
	 */
	@Query(value = "select cast(case when count(*) > 0 then 1 else 0 end as bit) from spaceship_crews sc"
			+ " where sc.idCrew = :idCrew and sc.idSpaceship <> :idSpaceship", nativeQuery = true)
	Boolean isInAnotherSpaceship(
			@Param("idSpaceship") Integer idSpaceship, 
			@Param("idCrew") Integer idCrew);

	/*
	 * is crew in a spaceship
	 */
	@Query(value = "select cast(case when count(*) > 0 then 1 else 0 end as bit) from spaceship_crews sc"
			+ " where sc.idCrew = :idCrew", nativeQuery = true)
	Boolean isInASpaceship(@Param("idCrew") Integer idCrew);

	/**
	 * Count by id
	 */
	Long countById(Integer id);

}
