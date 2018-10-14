/**
 * 
 */
package com.spaceprogram.repository.module.type;

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
public interface ModuleTypesRepository extends CrudRepository<ModuleType, Integer> {

	/*
	 * Is type used
	 */
	@Query(value = "select case when count(*) > 0 then 1 else 0 end from module where idType = :idType", 
			nativeQuery = true)
	Integer isUsed(@Param("idType") Integer idType);

}
