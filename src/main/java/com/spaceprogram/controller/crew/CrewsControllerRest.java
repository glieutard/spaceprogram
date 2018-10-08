/**
 * 
 */
package com.spaceprogram.controller.crew;

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

import com.spaceprogram.model.crew.Crew;
import com.spaceprogram.repository.crew.CrewsRepository;

/**
 * @author GLieutard
 * 
 * Crews Controller REST
 *
 */
@RestController
@Api(name = "CrewsControllerRest", description = "Crews Controller REST")
public class CrewsControllerRest {
	
	// URL en constante
	final private static String path = "/v1/crews";
	
	// Injection respository
	@Autowired
	private CrewsRepository crewsRepository;
	
	/**
	 * Get all crews
	 * 
	 * @Return List<Crew>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.GET)
	@ApiMethod(description = "Get all crews")
	public @ApiResponseObject Iterable<Crew> getCrews() {

		return crewsRepository.findAll();
	}
	
	/**
	 * Get crew
	 * 
	 * @param
	 * 		id int
	 * 
	 * @Return Crew
	 * 
	 */
	@RequestMapping(value = path + "/{id}", method = RequestMethod.GET)
	@ApiMethod(description = "Get crew")
	public @ApiResponseObject Crew getCrew(@ApiPathParam @PathVariable("id") int id) {

		return crewsRepository.findOne(id);
	}
	
	/**
	 * Post crews
	 * 
	 * @param List<Crew>
	 * 
	 * @Return List<Crew>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.POST)
	@ApiMethod(description = "Post engiens")
	public @ApiResponseObject Iterable<Crew> postCrews(@RequestBody(required = true) Iterable<Crew> crews) {

		return crewsRepository.save(crews);
	}
	
	/**
	 * Put crews
	 * 
	 * @param List<Crew>
	 * 
	 * @Return List<Crew>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.PUT)
	@ApiMethod(description = "Post crews")
	public @ApiResponseObject Iterable<Crew> putCrews(@RequestBody(required = true) Iterable<Crew> crews) {

		return crewsRepository.save(crews);
	}

	/**
	 * Delete crews
	 * 
	 * @param List<Crews>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.DELETE)
	@ApiMethod(description = "Post crews")
	public @ApiResponseObject void deleteCrews(@RequestBody(required = true) Iterable<Crew> crews) {

		crewsRepository.delete(crews);
	}

}
