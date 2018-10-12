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
 * SpaceshipsEngines Repository
 *
 */
public interface SpaceshipsEnginesRepository extends CrudRepository<SpaceshipEngines, IdSpaceshipEngines> {
	
	/*
	 * Delete by idEngine
	 */
	void deleteByIdEngine(Integer idEngine);

}
