/**
 * 
 */
package com.spaceprogram.repository.mission.spaceship;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.spaceprogram.model.mission.spaceship.IdMissionSpaceships;
import com.spaceprogram.model.mission.spaceship.MissionSpaceships;

/**
 * @author GLieutard
 * 
 * MissionsSpaceships Repository
 *
 */
@Transactional
public interface MissionsSpaceshipsRepository extends CrudRepository<MissionSpaceships, IdMissionSpaceships> {

	/**
	 *  Delete by idMission
	 */
//	@Modifying
//	@Query(value = "delete from mission_spaceships where idMission = :idMission", nativeQuery = true)
//	void deleteByIdMission(@Param("idMission") Integer idMission);
	void deleteByIdMission(Integer idMission);
	
	/**
	 *  Find by idMission
	 */
	List<MissionSpaceships> findByIdMission(Integer idMission);

	/**
	 *  Find by idSpaceship
	 */
	MissionSpaceships findByIdSpaceship(Integer idSpaceship);
	
}
