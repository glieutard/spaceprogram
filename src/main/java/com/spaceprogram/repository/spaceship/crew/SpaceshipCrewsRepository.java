/**
 * 
 */
package com.spaceprogram.repository.spaceship.crew;

import org.springframework.data.repository.CrudRepository;

import com.spaceprogram.model.spaceship.crew.IdSpaceshipCrew;
import com.spaceprogram.model.spaceship.crew.SpaceshipCrew;

/**
 * @author MSI Gaming
 *
 */
public interface SpaceshipCrewsRepository extends CrudRepository<SpaceshipCrew, IdSpaceshipCrew> {

}
