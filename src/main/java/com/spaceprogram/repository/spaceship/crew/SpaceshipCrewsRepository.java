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
 * SpaceshipCrews Repository
 *
 */
public interface SpaceshipCrewsRepository extends CrudRepository<SpaceshipCrews, IdSpaceshipCrews> {

}
