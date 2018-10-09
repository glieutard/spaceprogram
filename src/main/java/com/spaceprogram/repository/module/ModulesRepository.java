/**
 * 
 */
package com.spaceprogram.repository.module;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spaceprogram.model.module.Module;

/**
 * @author GLieutard
 * 
 * Modules Repository
 *
 */
public interface ModulesRepository extends CrudRepository<Module, Integer> {

	/*
	 * find modules by spaceships
	 */
	@Query(value = "select m.* from module m join spaceship_modules sm on(m.id = sm.idModule) where sm.idSpaceship = :idSpaceship", 
			nativeQuery = true)
	List<Module> findByIdSpaceship(
			@Param("idSpaceship") Integer idSpaceship);

}
