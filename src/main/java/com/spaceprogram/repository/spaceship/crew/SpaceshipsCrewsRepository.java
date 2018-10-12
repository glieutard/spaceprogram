/**
 * 
 */
package com.spaceprogram.repository.spaceship.crew;

import org.springframework.data.repository.CrudRepository;

import com.spaceprogram.model.spaceship.crew.IdSpaceshipCrews;
import com.spaceprogram.model.spaceship.crew.SpaceshipCrews;

/**
 * @author GLieutard
 * 
 * SpaceshipsCrews Repository
 *
 */
public interface SpaceshipsCrewsRepository extends CrudRepository<SpaceshipCrews, IdSpaceshipCrews> {
	
	/*
	 *  delete by idCrew
	 */
	void deleteByIdCrew(Integer idCrew);

}
