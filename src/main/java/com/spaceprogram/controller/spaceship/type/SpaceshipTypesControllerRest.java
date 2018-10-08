/**
 * 
 */
package com.spaceprogram.controller.spaceship.type;

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

import com.spaceprogram.model.spaceship.type.SpaceshipType;
import com.spaceprogram.repository.spaceship.type.SpaceshipTypesRepository;

/**
 * @author GLieutard
 * 
 * SpaceshipTypes Controller Rest
 *
 */
@RestController
@Api(name = "SpaceshipTypesControllerRest", description = "SpaceshipTypes Controller Rest")
public class SpaceshipTypesControllerRest {

	// URL en constante
	final private static String path = "/v1/spaceshipTypes";
	
	// Injection respository
	@Autowired
	private SpaceshipTypesRepository spaceshipTypesRepository;
	
	/**
	 * Get all spceshipTypes
	 * 
	 * @Return List<SpceshipType>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.GET)
	@ApiMethod(description = "Get all spceshipTypess")
	public @ApiResponseObject Iterable<SpaceshipType> getSpaceshipTypes() {

		return spaceshipTypesRepository.findAll();
	}
	
	/**
	 * Get spaceshipType
	 * 
	 * @param
	 * 		id int
	 * 
	 * @Return SpaceshipType
	 * 
	 */
	@RequestMapping(value = path + "/{id}", method = RequestMethod.GET)
	@ApiMethod(description = "Get spaceshipType")
	public @ApiResponseObject SpaceshipType getSpaceshipType(@ApiPathParam @PathVariable("id") Integer id) {

		return spaceshipTypesRepository.findOne(id);
	}
	
	/**
	 * Post spaceshipTypes
	 * 
	 * @param List<SpaceshipType>
	 * 
	 * @Return List<SpaceshipType>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.POST)
	@ApiMethod(description = "Post spaceshipTypes")
	public @ApiResponseObject Iterable<SpaceshipType> postSpaceshipTypes(@RequestBody(required = true) Iterable<SpaceshipType> spaceshipTypes) {

		return spaceshipTypesRepository.save(spaceshipTypes);
	}
	
	/**
	 * Put spaceshipTypes
	 * 
	 * @param List<SpaceshipType>
	 * 
	 * @Return List<SpaceshipType>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.PUT)
	@ApiMethod(description = "Post spaceshipTypes")
	public @ApiResponseObject Iterable<SpaceshipType> putSpaceshipTypes(@RequestBody(required = true) Iterable<SpaceshipType> spaceshipTypes) {

		return spaceshipTypesRepository.save(spaceshipTypes);
	}

	/**
	 * Delete spaceshipTypes
	 * 
	 * @param List<SpaceshipType>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.DELETE)
	@ApiMethod(description = "Post spaceshipTypes")
	public @ApiResponseObject void deleteSpaceshipTypes(@RequestBody(required = true) Iterable<SpaceshipType> spaceshipTypes) {

		spaceshipTypesRepository.delete(spaceshipTypes);
	}
	
}
