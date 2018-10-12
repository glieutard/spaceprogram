/**
 * 
 */
package com.spaceprogram.repository.mission.spaceship;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spaceprogram.model.mission.spaceship.IdMissionSpaceships;
import com.spaceprogram.model.mission.spaceship.MissionSpaceships;

/**
 * @author GLieutard
 * 
 * MissionsSpaceships Repository
 *
 */
public interface MissionsSpaceshipsRepository extends CrudRepository<MissionSpaceships, IdMissionSpaceships> {

	/*
	 *  Delete by idMission
	 */
	void deleteByIdMission(Integer idMission);
	
	/*
	 *  Find by idMission
	 */
	List<MissionSpaceships> findByIdMission(Integer idMission);
	
}
