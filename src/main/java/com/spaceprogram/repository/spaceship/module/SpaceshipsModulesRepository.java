/**
 * 
 */
package com.spaceprogram.repository.spaceship.module;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.spaceprogram.model.spaceship.module.IdSpaceshipModules;
import com.spaceprogram.model.spaceship.module.SpaceshipModules;

/**
 * @author GLieutard
 * 
 * SpaceshipsModules Repository
 *
 */
@Transactional
public interface SpaceshipsModulesRepository extends CrudRepository<SpaceshipModules, IdSpaceshipModules> {
	
	/*
	 * Delete by id module
	 */
	void deleteByIdModule(Integer idModule);

}
