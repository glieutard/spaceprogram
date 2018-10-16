/**
 * 
 */
package com.spaceprogram.repository.module.type;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spaceprogram.model.module.type.ModuleType;

/**
 * @author GLieutard
 * 
 * ModuleTypes Repository
 *
 */
@Transactional
public interface ModuleTypesRepository extends CrudRepository<ModuleType, Integer> {

	/*
	 * Is type used
	 */
	@Query(value = "select cast(case when count(*) > 0 then 1 else 0 end as bit) from module where idType = :idType", 
			nativeQuery = true)
	Boolean isUsed(@Param("idType") Integer idType);

	/**
	 * Count by id
	 */
	Long countById(Integer id);

}
