/**
 * 
 */
package com.spaceprogram.controller.job;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spaceprogram.model.job.Job;
import com.spaceprogram.repository.job.JobsRepository;

/**
 * @author GLieutard
 * 
 * Job Controlelr REST
 *
 */
@RestController
@Api(name = "JobsControllerRest", description = "Jobs Cotroller Rest")
public class JobsControllerRest {

	// URL en constante
	final private static String path = "/v1/jobs";
	
	// Injection respository
	@Autowired
	private JobsRepository jobsRepository;
	
	/**
	 * Get all jobs
	 * 
	 * @Return List<Job>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.GET)
	@ApiMethod(description = "Get all jobs")
	public @ApiResponseObject Iterable<Job> getJobs() {

		return jobsRepository.findAll();
	}
	
	/**
	 * Get job
	 * 
	 * @param
	 * 		id int
	 * 
	 * @Return Job
	 * 
	 */
	@RequestMapping(value = path + "/{id}", method = RequestMethod.GET)
	@ApiMethod(description = "Get job")
	public @ApiResponseObject Job getJob(@ApiPathParam @PathVariable("id") int id) {

		return jobsRepository.findOne(id);
	}
	
	/**
	 * Post jobs
	 * 
	 * @param List<Job>
	 * 
	 * @Return List<Job>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.POST)
	@ApiMethod(description = "Post jobs")
	public @ApiResponseObject Iterable<Job> postJobs(@RequestBody(required = true) Iterable<Job> jobs) {

		return jobsRepository.save(jobs);
	}
	
	/**
	 * Put jobs
	 * 
	 * @param List<Job>
	 * 
	 * @Return List<Job>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.PUT)
	@ApiMethod(description = "Post jobs")
	public @ApiResponseObject Iterable<Job> putJobs(@RequestBody(required = true) Iterable<Job> jobs) {

		return jobsRepository.save(jobs);
	}

	/**
	 * Delete jobs
	 * 
	 * @param List<Job>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.DELETE)
	@ApiMethod(description = "Post jobs")
	public @ApiResponseObject void deleteJobs(@RequestBody(required = true) Iterable<Job> jobs) {

		jobsRepository.delete(jobs);
	}
	
}