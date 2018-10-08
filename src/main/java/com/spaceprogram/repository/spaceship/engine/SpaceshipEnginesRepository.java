/**
 * 
 */
package com.spaceprogram.repository.spaceship.engine;

import org.springframework.data.repository.CrudRepository;

import com.spaceprogram.model.spaceship.engine.IdSpaceshipEngine;
import com.spaceprogram.model.spaceship.engine.SpaceshipEngine;

/**
 * @author GLieutard
 * 
 * SpaceshipEngines Repository
 *
 */
public interface SpaceshipEnginesRepository extends CrudRepository<SpaceshipEngine, IdSpaceshipEngine> {

}
