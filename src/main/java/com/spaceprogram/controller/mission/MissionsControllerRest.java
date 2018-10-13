/**
 * 
 */
package com.spaceprogram.controller.mission;

import java.util.List;
import java.util.function.Predicate;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spaceprogram.controller.mission.spaceships.MissionsSpaceshipsController;
import com.spaceprogram.controller.spaceship.coordinates.SpaceshipsCoordinatesController;
import com.spaceprogram.model.coordinates.Coordinates;
import com.spaceprogram.model.mission.Mission;
import com.spaceprogram.model.spaceship.Spaceship;
import com.spaceprogram.repository.mission.MissionsRepository;
import com.spaceprogram.repository.mission.spaceship.MissionsSpaceshipsRepository;
import com.spaceprogram.repository.spaceship.SpaceshipsRepository;

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
	
	@Autowired
	private SpaceshipsRepository spaceshipsRepository;
	
	@Autowired
	private MissionsSpaceshipsRepository missionSpaceshipsRepository;

	@Autowired	
	private MissionsSpaceshipsController missionsSpaceshipsController;
	
	@Autowired
	private SpaceshipsCoordinatesController spaceshipsCoordinatesController;
	
	/**
	 * Get all missions
	 * 
	 * @Return List<Mission>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.GET)
	@ApiMethod(description = "Get all missions")
	public @ApiResponseObject Iterable<Mission> getMissions(
			@ApiQueryParam @RequestParam(value = "detail", required = false) String detail) {

		// Récupération des missions
		Iterable<Mission> missions = missionsRepository.findAll();
		
		// Récupération des vaisseaux par mission si detail = full
		if (detail.equals("full"))
			for (Mission mission : missions)
				mission.setSpaceships(spaceshipsRepository.findByIdMission(mission.getId()));
		
		// Return missions
		return missions;
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
	public @ApiResponseObject Mission getMission(
			@ApiPathParam @PathVariable("id") Integer id,
			@ApiQueryParam @RequestParam(value = "detail", required = false) String detail) {

		// Récupération de la mission
		Mission mission = missionsRepository.findOne(id);
		
		// Récupération des vaisseaux de la mission si detail = full
		if (detail.equals("full"))
			mission.setSpaceships(spaceshipsRepository.findByIdMission(mission.getId()));
		
		// Return mission
		return mission;
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
		
		// sauvegarde des missions
		Iterable<Mission> savedMissions = missionsRepository.save(missions);

		// Sauvegarde des vaisseaux rattachés par mission
		for (Mission mission : savedMissions) {
			mission.setSpaceships(missionsSpaceshipsController.postByMission(mission));
		}
		
		// Liste en retour
		return savedMissions;
		
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
	@ApiMethod(description = "Put missions")
	public @ApiResponseObject Iterable<Mission> putMissions(@RequestBody(required = true) List<Mission> missions) {

		// Suppression des enregistrement dont l'id est null ou à 0
		Predicate<Mission> missionPredicate = p -> p.getId() == null || p.getId() == 0;
		missions.removeIf(missionPredicate);
		
		// sauvegarde des missions
		Iterable<Mission> savedMissions = missionsRepository.save(missions);

		// Gestion des vaisseaux suivant le statut de mission
		for (Mission mission : savedMissions) {
			mission.setSpaceships(missionsSpaceshipsController.putByMission(mission));
		}
		
		// Liste en retour
		return savedMissions;
		
	}

	/**
	 * Delete missions
	 * 
	 * @param List<Mission>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.DELETE)
	@ApiMethod(description = "Delete missions")
	public @ApiResponseObject void deleteMissions(@RequestBody(required = true) Iterable<Mission> missions) {

		// Suppression des vaisseaux par mission
		for (Mission mission : missions) {
			missionSpaceshipsRepository.deleteByIdMission(mission.getId());
		}
		
		missionsRepository.delete(missions);
	}

	/**
	 * Get spaceships by mission
	 * 
	 * @param
	 * 		id int
	 * 
	 * @Return List<Spaceship>
	 * o
	 */
	@RequestMapping(value = path + "/{id}/spaceships", method = RequestMethod.GET)
	@ApiMethod(description = "Get spaceships by mission")
	public @ApiResponseObject List<Spaceship> getSpaceshipsByMission(
			@ApiPathParam @PathVariable("id") Integer id,
			@ApiQueryParam @RequestParam(value = "detail", required = false) String detail) {

		// List of spaceships by mission
		List<Spaceship> spaceships = spaceshipsRepository.findByIdMission(id);

		// Si detail == full récupération des coordonnnées
		if (detail.equals("full"))
			for (Spaceship spaceship : spaceships)
				spaceship.setCoordinates(spaceshipsCoordinatesController.getBySpaceship(spaceship.getId()));

	    return spaceships;
	}

	/**
	 * Get spaceship coordinates by mission/spaceship
	 * 
	 * @param
	 * 		idMission int
	 * 		idSpaceship
	 * 
	 * @Return Coordiantes
	 * o
	 */
	@RequestMapping(value = path + "/{idMission}/spaceships/{idSpaceship}", method = RequestMethod.GET)
	@ApiMethod(description = "Get spaceships by mission")
	public @ApiResponseObject Coordinates getSpaceshipCoordinatesByMissionSpaceship(
			@ApiPathParam @PathVariable("idMission") Integer idMission,
			@ApiPathParam @PathVariable("idSpaceship") Integer idSpaceship) {

		return spaceshipsCoordinatesController.getBySpaceship(idSpaceship);

	}
	
}
