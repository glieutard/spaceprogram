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
	
	/**
	 * find spaceships by mission
	 */
	@Query(value = "select s.* from spaceship s join mission_spaceships ms on(s.id = ms.idSpaceship) where ms.idMission = :idMission", 
			nativeQuery = true)
	List<Spaceship> findByIdMission(
			@Param("idMission") Integer idMission);

	/**
	 * Is spaceship in mission
	 */
	@Query(value = "select cast(case when count(*) > 0 then 1 else 0 end as bit) from mission_spaceships where idSpaceship = :idSpaceship", 
			nativeQuery = true)
	Boolean isInMission(@Param("idSpaceship") Integer idSpaceship);

	/**
	 * Is spaceship in another mission
	 */
	@Query(value = "select cast(case when count(*) > 0 then 1 else 0 end as bit) from mission_spaceships"
			+ " where idMission = :idMission and idSpaceship = :idSpaceship", 
			nativeQuery = true)
	Boolean isInAnotherMission(
			@Param("idMission") Integer idMission,
			@Param("idSpaceship") Integer idSpaceship);
	
	/**
	 * Find mass by spaceship
	 */
	@Query(value = "select s.weight + se.totalweight from spaceship"
			+ " join (select idSpaceship, sum(weight) totalweight from spaceship_engines) se on (s.id = se.idSpaceship)"
			+ " where s.id = :idSpaceship", 
			nativeQuery = true)
	Integer findMassByIdSpaceship(@Param("idSpaceship") Integer idSpaceship);
	
	/**
	 * Find power by spaceship
	 */
	@Query(value = "select sum(horsePower) power from spaceship_engines where idSpaceship = :idSpaceship", 
			nativeQuery = true)
	Integer findPowerByIdSpaceship(@Param("idSpaceship") Integer idSpaceship);

	/**
	 * Count by id
	 */
	Long countById(Integer id);

}
