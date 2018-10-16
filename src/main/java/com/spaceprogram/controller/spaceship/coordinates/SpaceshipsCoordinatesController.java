/**
 * 
 */
package com.spaceprogram.controller.spaceship.coordinates;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.spaceprogram.controller.mission.MissionsControllerRest;
import com.spaceprogram.model.coordinates.Coordinates;
import com.spaceprogram.model.mission.Mission;
import com.spaceprogram.model.mission.spaceship.MissionSpaceships;
import com.spaceprogram.repository.mission.spaceship.MissionsSpaceshipsRepository;
import com.spaceprogram.repository.spaceship.SpaceshipsRepository;

/**
 * @author GLieutard
 * 
 * SpaceshipsCoordinates Controller
 *
 */
@Component
public class SpaceshipsCoordinatesController {

	// variables .properties
	@Value("${speed.ratio}")
	private double ratio;

	// Injection Repository (& Controller)
	@Autowired
	private MissionsSpaceshipsRepository missionsSpaceshipsRepository;
	
	@Autowired
	private MissionsControllerRest missionsControllerRest;
	
	@Autowired
	private SpaceshipsRepository spaceshipsRepository;
	
	/**
	 * Get coordinates by spaceship
	 * 
	 * @param Integer
	 *            id
	 * 
	 * @Return Coordinates
	 * 
	 */
	public Coordinates getBySpaceship(Integer id) {
		
		// Récupération de son entrée dans la mission
		MissionSpaceships missionSpaceship = missionsSpaceshipsRepository.findByIdSpaceship(id);
		
		// Si pas de mission en cours, retour à null
		if (missionSpaceship == null)
			return null;

		// Récupération des informations de mission
		Mission mission = missionsControllerRest.getMission(missionSpaceship.getIdMission(), null);

		// Déclaration des coordonnées qui seront retournées
		// Initialisation des coordonnées avec celles de la base de la mission
		Coordinates coordinates = mission.getBaseCoordinates();
		
		// Si date d'entrée dans la mission à null, le vaisseau est encore à la base
		if (missionSpaceship.getDate() == null)
			return coordinates;
		
		// Vérification que le vaisseau a un pilote
		if (!spaceshipsRepository.hasAPilote(id))
			return coordinates;
		
		// Récupération de la différence en secondes
		long difference = (new Date().getTime() - missionSpaceship.getDate().getTime()) / 1000;
		
		// Différences de coordonnées entre la base et la cible
		double distanceX = mission.getBaseCoordinates().getX().doubleValue() - mission.getTargetCoordinates().getX().doubleValue();
		double distanceY = mission.getBaseCoordinates().getY().doubleValue() - mission.getTargetCoordinates().getY().doubleValue();
		double distanceZ = mission.getBaseCoordinates().getZ().doubleValue() - mission.getTargetCoordinates().getZ().doubleValue();
		double distanceTotal = Math.abs(distanceX) + Math.abs(distanceY) + Math.abs(distanceZ);
		
		// Récupération de la masse totale du vaisseau
		int mass = spaceshipsRepository.findMassByIdSpaceship(id);
		
		// Récupération de la puissance du vaisseau
		Integer power = spaceshipsRepository.findPowerByIdSpaceship(id);
		
		// Distance parcourue potentielle
		double distanceTraveled  = (double)power / (double)Math.pow((double)mass, 2.0) / 1000.0 * ratio * (double)difference;
		
		// Si Cible atteinte, on reste à la cible
		if (distanceTraveled * Math.abs(distanceX) / distanceTotal > Math.abs(distanceX)) {
			coordinates = mission.getTargetCoordinates();
			return coordinates;
		}
		
		// Calcul des nouvelles coordonnées
		coordinates.setX(new BigDecimal(coordinates.getX().doubleValue() + distanceTraveled * Math.abs(distanceX) / distanceTotal));
		coordinates.setY(new BigDecimal(coordinates.getY().doubleValue() + distanceTraveled * Math.abs(distanceY) / distanceTotal));
		coordinates.setZ(new BigDecimal(coordinates.getZ().doubleValue() + distanceTraveled * Math.abs(distanceZ) / distanceTotal));
		
		// Retourne les coordonnées
		return coordinates;

	}

}
