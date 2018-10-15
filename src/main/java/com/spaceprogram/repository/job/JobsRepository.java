/**
 * 
 */
package com.spaceprogram.repository.job;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spaceprogram.model.job.Job;

/**
 * @author GLieutard
 * 
 * Jobs Repository
 *
 */
public interface JobsRepository extends CrudRepository<Job, Integer> {
	
	/**
	 * Is job used
	 */
	@Query(value = "select cast(case when count(*) > 0 then 1 else 0 end as bit) from crew where idJob = :idJob", 
			nativeQuery = true)
	Boolean isUsed(@Param("idJob") Integer idJob);
	
	/**
	 * Count by id
	 */
	Long countById(Integer id);

}
