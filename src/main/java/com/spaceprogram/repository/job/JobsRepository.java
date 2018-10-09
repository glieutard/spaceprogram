/**
 * 
 */
package com.spaceprogram.repository.job;

import org.springframework.data.repository.CrudRepository;

import com.spaceprogram.model.job.Job;

/**
 * @author GLieutard
 * 
 * Jobs Repository
 *
 */
public interface JobsRepository extends CrudRepository<Job, Integer> {

}
