/**
 * 
 */
package com.spaceprogram.repository.engine;

import org.springframework.data.repository.CrudRepository;

import com.spaceprogram.model.engine.Engine;

/**
 * @author GLieutard
 * 
 * Engine Repository
 *
 */
public interface EnginesRepository extends CrudRepository<Engine, Integer> {

}
