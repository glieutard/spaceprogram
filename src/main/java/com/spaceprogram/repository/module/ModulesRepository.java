/**
 * 
 */
package com.spaceprogram.repository.module;

import java.util.List;

import javax.transaction.Transactional;

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
@Transactional
public interface ModulesRepository extends CrudRepository<Module, Integer> {

	/*
	 * find modules by spaceships
	 */
	@Query(value = "select m.* from module m join spaceship_modules sm on(m.id = sm.idModule) where sm.idSpaceship = :idSpaceship", 
			nativeQuery = true)
	List<Module> findByIdSpaceship(
			@Param("idSpaceship") Integer idSpaceship);

	/*
	 * Is moduke used
	 */
	@Query(value = "select cast(case when count(*) > 0 then 1 else 0 end as bit) from spaceship_modules where idModule = :idModule", 
			nativeQuery = true)
	Boolean isUsed(@Param("idModule") Integer idModule);

	/**
	 * Count by id
	 */
	Long countById(Integer id);

}
