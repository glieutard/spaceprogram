/**
 * 
 */
package com.spaceprogram.controller.crew;

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

import com.spaceprogram.model.crew.Crew;
import com.spaceprogram.repository.crew.CrewsRepository;
import com.spaceprogram.repository.spaceship.crew.SpaceshipsCrewsRepository;

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
	
	@Autowired
	private SpaceshipsCrewsRepository spaceshipsCrewsRepository;
	
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
	public @ApiResponseObject Crew getCrew(@ApiPathParam @PathVariable("id") Integer id) {

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
	public @ApiResponseObject Iterable<Crew> postCrews(@RequestBody(required = true) List<Crew> crews) {

		// Suppression des enregistrement dont l'id n'est pas null
		Predicate<Crew> crewPredicate = p -> p.getId() != null;
		crews.removeIf(crewPredicate);
		
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
	public @ApiResponseObject Iterable<Crew> putCrews(@RequestBody(required = true) List<Crew> crews) {

		// Suppression des enregistrement dont l'id est null ou à 0
		Predicate<Crew> crewPredicate = p -> p.getId() == null || p.getId() == 0;
		crews.removeIf(crewPredicate);
		
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
	public @ApiResponseObject void deleteCrews(@RequestBody(required = true) List<Crew> crews) {
		
		// Retrait des membres d'équipage dont les vaisseaux sont en mission
		Predicate<Crew> crewPredicate = p -> crewsRepository.isInMission(p.getId());
		crews.removeIf(crewPredicate);
		
		// Suppression des rattachements aux vaisseaux
		for (Crew crew : crews)
			spaceshipsCrewsRepository.deleteByIdCrew(crew.getId());

		crewsRepository.delete(crews);
	}

}
