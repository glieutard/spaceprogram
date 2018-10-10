/**
 * 
 */
package com.spaceprogram.controller.mission;

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

import com.spaceprogram.model.mission.Mission;
import com.spaceprogram.repository.mission.MissionsRepository;

/**
 * @author GLieutard
 * 
 * Missions Controller REST
 *
 */
@RestController
@Api(name = "MissionsControllerRest", description = "Mission Controller REST")
public class MissionsControllerRest {

	// URL en constante
	final private static String path = "/v1/missions";
	
	// Injection respository
	@Autowired
	private MissionsRepository missionsRepository;
	
	/**
	 * Get all missions
	 * 
	 * @Return List<Mission>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.GET)
	@ApiMethod(description = "Get all missions")
	public @ApiResponseObject Iterable<Mission> getMissions() {

		return missionsRepository.findAll();
	}
	
	/**
	 * Get mission
	 * 
	 * @param
	 * 		id int
	 * 
	 * @Return Mission
	 * 
	 */
	@RequestMapping(value = path + "/{id}", method = RequestMethod.GET)
	@ApiMethod(description = "Get mission")
	public @ApiResponseObject Mission getMission(@ApiPathParam @PathVariable("id") Integer id) {

		return missionsRepository.findOne(id);
	}
	
	/**
	 * Post missions
	 * 
	 * @param List<Mission>
	 * 
	 * @Return List<Mission>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.POST)
	@ApiMethod(description = "Post missions")
	public @ApiResponseObject Iterable<Mission> postMissions(@RequestBody(required = true) List<Mission> missions) {

		// Suppression des enregistrement dont l'id n'est pas null
		Predicate<Mission> missionPredicate = p -> p.getId() != null;
		missions.removeIf(missionPredicate);
		
		return missionsRepository.save(missions);
	}
	
	/**
	 * Put missions
	 * 
	 * @param List<Mission>
	 * 
	 * @Return List<Mission>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.PUT)
	@ApiMethod(description = "Post missions")
	public @ApiResponseObject Iterable<Mission> putMissions(@RequestBody(required = true) List<Mission> missions) {

		// Suppression des enregistrement dont l'id est null ou à 0
		Predicate<Mission> missionPredicate = p -> p.getId() == null || p.getId() == 0;
		missions.removeIf(missionPredicate);
		
		return missionsRepository.save(missions);
	}

	/**
	 * Delete missions
	 * 
	 * @param List<Mission>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.DELETE)
	@ApiMethod(description = "Post missions")
	public @ApiResponseObject void deleteMissions(@RequestBody(required = true) Iterable<Mission> missions) {

		missionsRepository.delete(missions);
	}
	
}
