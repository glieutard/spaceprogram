/**
 * 
 */
package com.spaceprogram.repository.spaceship.type;

import javax.transaction.Transactional;

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
@Transactional
public interface SpaceshipTypesRepository extends CrudRepository<SpaceshipType, Integer> {

	/**
	 * Is type used
	 */
	@Query(value = "select cast(case when count(*) > 0 then 1 else 0 end as bit) from spaceship where idType = :idType", 
			nativeQuery = true)
	Boolean isUsed(@Param("idType") Integer idType);

	/**
	 * Count by id
	 */
	Long countById(Integer id);

}
