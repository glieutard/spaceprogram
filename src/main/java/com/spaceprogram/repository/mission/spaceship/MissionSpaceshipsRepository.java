/**
 * 
 */
package com.spaceprogram.repository.mission.spaceship;

import org.springframework.data.repository.CrudRepository;

import com.spaceprogram.model.mission.spaceship.IdMissionSpaceships;
import com.spaceprogram.model.mission.spaceship.MissionSpaceships;

/**
 * @author GLieutard
 * 
 * MissionSpaceships Repository
 *
 */
public interface MissionSpaceshipsRepository extends CrudRepository<MissionSpaceships, IdMissionSpaceships> {

}
