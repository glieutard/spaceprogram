/**
 * 
 */
package com.spaceprogram.controller.spaceship.type;

import java.util.List;
import java.util.function.Predicate;

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
	 * Get all spaceshipTypes
	 * 
	 * @Return List<SpaceshipType>
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
	public @ApiResponseObject Iterable<SpaceshipType> postSpaceshipTypes(@RequestBody(required = true) List<SpaceshipType> spaceshipTypes) {

		// Suppression des enregistrement dont l'id n'est pas null
		Predicate<SpaceshipType> spaceshipTypePredicate = p -> p.getId() != null;
		spaceshipTypes.removeIf(spaceshipTypePredicate);
		
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
	@ApiMethod(description = "Put spaceshipTypes")
	public @ApiResponseObject Iterable<SpaceshipType> putSpaceshipTypes(@RequestBody(required = true) List<SpaceshipType> spaceshipTypes) {

		// Suppression des enregistrement dont l'id est null ou à 0
		Predicate<SpaceshipType> spaceshipTypePredicate = p -> spaceshipTypesRepository.countById(p.getId()) != 1;
		spaceshipTypes.removeIf(spaceshipTypePredicate);
		
		return spaceshipTypesRepository.save(spaceshipTypes);
	}

	/**
	 * Delete spaceshipTypes
	 * 
	 * @param List<SpaceshipType>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.DELETE)
	@ApiMethod(description = "Delete spaceshipTypes")
	public @ApiResponseObject void deleteSpaceshipTypes(@RequestBody(required = true) List<SpaceshipType> spaceshipTypes) {

		// Retrait des tyoes utilisés
		Predicate<SpaceshipType> spaceshipTypePredicate = p -> spaceshipTypesRepository.isUsed(p.getId()) 
				|| spaceshipTypesRepository.countById(p.getId()) != 1;
		spaceshipTypes.removeIf(spaceshipTypePredicate);
		
		spaceshipTypesRepository.delete(spaceshipTypes);
	}
	
}
