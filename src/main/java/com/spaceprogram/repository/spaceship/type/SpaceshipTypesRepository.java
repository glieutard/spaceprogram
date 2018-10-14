/**
 * 
 */
package com.spaceprogram.repository.spaceship.type;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spaceprogram.model.spaceship.type.SpaceshipType;

/**
 * @author GLieutard
 * 
 * SpaceshipTypes Repository
 *
 */
public interface SpaceshipTypesRepository extends CrudRepository<SpaceshipType, Integer> {

	/*
	 * Is type used
	 */
	@Query(value = "select case when count(*) > 0 then 1 else 0 end from spaceship where idType = :idType", 
			nativeQuery = true)
	Integer isUsed(@Param("idType") Integer idType);

}
