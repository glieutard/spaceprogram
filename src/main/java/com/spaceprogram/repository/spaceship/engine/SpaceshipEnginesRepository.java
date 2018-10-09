/**
 * 
 */
package com.spaceprogram.repository.spaceship.engine;

import org.springframework.data.repository.CrudRepository;

import com.spaceprogram.model.spaceship.engine.IdSpaceshipEngines;
import com.spaceprogram.model.spaceship.engine.SpaceshipEngines;

/**
 * @author GLieutard
 * 
 * SpaceshipEngines Repository
 *
 */
public interface SpaceshipEnginesRepository extends CrudRepository<SpaceshipEngines, IdSpaceshipEngines> {

}
