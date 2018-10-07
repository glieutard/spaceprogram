/**
 * 
 */
package com.spaceprogram.repository.spaceship;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spaceprogram.model.spaceship.Spaceship;

/**
 * @author GLieutard
 *
 */
public interface SpaceshipsRepository extends CrudRepository<Spaceship, Integer> {
	
	// find all
	List<Spaceship> findAll();

}
