/**
 * 
 */
package com.spaceprogram.controller.spaceship;

import java.util.List;

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
import com.spaceprogram.model.engine.Engine;
import com.spaceprogram.model.spaceship.Spaceship;
import com.spaceprogram.repository.crew.CrewsRepository;
import com.spaceprogram.repository.engine.EnginesRepository;
import com.spaceprogram.repository.spaceship.SpaceshipsRepository;

/**
 * @author GLieutard
 * 
 * Spaceship Controller REST
 *
 */
@RestController
@Api(name = "SpaceshipsControllerRest", description = "Spacehsips Cotroller Rest")
public class SpaceshipsControllerRest {
	
	// URL en constante
	final private static String path = "/v1/spaceships";
	
	// Injection respository
	@Autowired
	private SpaceshipsRepository spaceshipsRepository;
	
	@Autowired
	private CrewsRepository crewsRepository;
	
	@Autowired
	private EnginesRepository enginesRepository;
	
	/**
	 * Get all spaceships
	 * 
	 * @Return List<Spaceship>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.GET)
	@ApiMethod(description = "Get all spaceships")
	public @ApiResponseObject List<Spaceship> getSpaceships() {

		return spaceshipsRepository.findAll();
	}
	
	/**
	 * Get spaceship
	 * 
	 * @Return Spaceship
	 * 
	 */
	@RequestMapping(value = path + "/{id}", method = RequestMethod.GET)
	@ApiMethod(description = "Get spaceships")
	public @ApiResponseObject Spaceship getSpaceship(@ApiPathParam @PathVariable("id") Integer id) {

		return spaceshipsRepository.findOne(id);
	}
	
	/**
	 * Put spaceships
	 * 
	 * @Return List<Spaceship>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.PUT)
	@ApiMethod(description = "Put spaceships")
	public @ApiResponseObject Iterable<Spaceship> putSpaceships(@RequestBody(required = true) Iterable<Spaceship> spaceships) {

		return spaceshipsRepository.save(spaceships);
	}
	
	/**
	 * Post spaceships
	 * 
	 * @Return List<Spaceship>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.POST)
	@ApiMethod(description = "Post spaceships")
	public @ApiResponseObject Iterable<Spaceship> postSpaceships(@RequestBody(required = true) Iterable<Spaceship> spaceships) {

		return spaceshipsRepository.save(spaceships);
	}
	
	/**
	 * Delete spaceships
	 * 
	 * @Return void
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.DELETE)
	@ApiMethod(description = "Delete spaceships")
	public @ApiResponseObject void deleteSpaceships(@RequestBody(required = true) Iterable<Spaceship> spaceships) {

		spaceshipsRepository.delete(spaceships);
	}
	
	/**
	 * Get crews by spaceship
	 * 
	 * @Return List<Crew>
	 * 
	 */
	@RequestMapping(value = path + "/{id}/crews", method = RequestMethod.GET)
	@ApiMethod(description = "Get crews by spaceships")
	public @ApiResponseObject List<Crew> getCrewsBySpaceship(@ApiPathParam @PathVariable("id") Integer id) {

		return crewsRepository.findByIdSpaceship(id);
	}
	
	/**
	 * Get engines by spaceship
	 * 
	 * @Return List<Engine>
	 * 
	 */
	@RequestMapping(value = path + "/{id}/engines", method = RequestMethod.GET)
	@ApiMethod(description = "Get engines by spaceships")
	public @ApiResponseObject List<Engine> getEnginesBySpaceship(@ApiPathParam @PathVariable("id") Integer id) {

		return enginesRepository.findByIdSpaceship(id);
	}

}
