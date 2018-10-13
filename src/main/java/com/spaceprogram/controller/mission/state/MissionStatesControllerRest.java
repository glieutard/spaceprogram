/**
 * 
 */
package com.spaceprogram.controller.mission.state;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spaceprogram.model.mission.state.MissionState;
import com.spaceprogram.repository.mission.state.MissionStatesRepository;

/**
 * @author GLieutard
 * 
 * MissionStates Controller REST
 *
 */
@RestController
@Api(name = "MissionStatesControllerRest", description = "Mission States Controller REST")
public class MissionStatesControllerRest {

	// URL en constante
	final private static String path = "/v1/missionStates";
	
	// Injection respository
	@Autowired
	private MissionStatesRepository missionStatesRepository;

	/**
	 * Get all missionStates
	 * 
	 * @Return List<MissionState>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.GET)
	@ApiMethod(description = "Get all missionStates")
	public @ApiResponseObject Iterable<MissionState> getMissionStates() {

		return missionStatesRepository.findAll();
	}
	
	/**
	 * Get missionState
	 * 
	 * @param
	 * 		id int
	 * 
	 * @Return MissionState
	 * 
	 */
	@RequestMapping(value = path + "/{id}", method = RequestMethod.GET)
	@ApiMethod(description = "Get missionState")
	public @ApiResponseObject MissionState getMissionState(@ApiPathParam @PathVariable("id") Integer id) {

		return missionStatesRepository.findOne(id);
	}
	
//	/**
//	 * Post missionStates
//	 * 
//	 * @param List<MissionState>
//	 * 
//	 * @Return List<MissionState>
//	 * 
//	 */
//	@RequestMapping(value = path, method = RequestMethod.POST)
//	@ApiMethod(description = "Post missionStates")
//	public @ApiResponseObject Iterable<MissionState> postMissionStates(@RequestBody(required = true) List<MissionState> missionStates) {
//
//		// Suppression des enregistrement dont l'id n'est pas null
//		Predicate<MissionState> missionStatePredicate = p -> p.getId() != null;
//		missionStates.removeIf(missionStatePredicate);
//		
//		return missionStatesRepository.save(missionStates);
//	}
//	
//	/**
//	 * Put missionStates
//	 * 
//	 * @param List<MissionState>
//	 * 
//	 * @Return List<MissionState>
//	 * 
//	 */
//	@RequestMapping(value = path, method = RequestMethod.PUT)
//	@ApiMethod(description = "Put missionStates")
//	public @ApiResponseObject Iterable<MissionState> putMissionStates(@RequestBody(required = true) List<MissionState> missionStates) {
//
//		// Suppression des enregistrement dont l'id est null ou à 0
//		Predicate<MissionState> missionStatePredicate = p -> p.getId() == null || p.getId() == 0;
//		missionStates.removeIf(missionStatePredicate);
//		
//		return missionStatesRepository.save(missionStates);
//	}
//
//	/**
//	 * Delete missionStates
//	 * 
//	 * @param List<MissionState>
//	 * 
//	 */
//	@RequestMapping(value = path, method = RequestMethod.DELETE)
//	@ApiMethod(description = "Delete missionStates")
//	public @ApiResponseObject void deleteMissionStates(@RequestBody(required = true) Iterable<MissionState> missionStates) {
//
//		missionStatesRepository.delete(missionStates);
//	}
	
}