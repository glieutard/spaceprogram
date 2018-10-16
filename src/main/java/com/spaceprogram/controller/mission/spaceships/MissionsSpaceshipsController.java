/**
 * 
 */
package com.spaceprogram.controller.mission.spaceships;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spaceprogram.model.mission.Mission;
import com.spaceprogram.model.mission.spaceship.MissionSpaceships;
import com.spaceprogram.model.spaceship.Spaceship;
import com.spaceprogram.repository.mission.spaceship.MissionsSpaceshipsRepository;
import com.spaceprogram.repository.spaceship.SpaceshipsRepository;

/**
 * @author GLieutard
 * 
 * MissionsSpaceships Controller
 *
 */
@Component
public class MissionsSpaceshipsController {

	// Injection respository
	@Autowired
	private SpaceshipsRepository spaceshipsRepository;

	@Autowired
	private MissionsSpaceshipsRepository missionSpaceshipsRepository;

	/**
	 * Post spaceships by mission
	 * 
	 * @param Mission
	 *            mission
	 * 
	 * @Return List<Spaceship>
	 * 
	 */
	public List<Spaceship> postByMission(Mission mission) {

		// Pas de vaisseaux à enregistrer
		if (mission.getSpaceships() == null)
			return null;

		// Suppression des vaisseaux déjà en mission
		Predicate<Spaceship> spaceshipPredicate = p -> spaceshipsRepository.isInMission(p.getId());
		mission.getSpaceships().removeIf(spaceshipPredicate);

		// Création de la liste des vaisseaux par mission à enregistrer
		List<MissionSpaceships> missionSpaceships = new ArrayList<MissionSpaceships>();

		// Gestion des vaisseaux suivant le statut de mission
		switch (mission.getState().getId()) {
		case 1:
			for (Spaceship spaceship : mission.getSpaceships())
				missionSpaceships.add(new MissionSpaceships(mission.getId(), spaceship.getId(), null));
			break;
		case 2:
			for (Spaceship spaceship : mission.getSpaceships())
				missionSpaceships.add(new MissionSpaceships(mission.getId(), spaceship.getId(), new Date()));
			break;
		}
		missionSpaceshipsRepository.save(missionSpaceships);

		// Retourne les vaisseaux
		return spaceshipsRepository.findByIdMission(mission.getId());

	}

	/**
	 * Put spaceships by mission
	 * 
	 * @param Mission
	 *            mission
	 * 
	 * @Return List<Spaceship>
	 * 
	 */
	public List<Spaceship> putByMission(Mission mission) {
		
		// Si liste des vaisseaux à null, on récupère celle enregistrée
		if (mission.getSpaceships() == null)
			mission.setSpaceships(spaceshipsRepository.findByIdMission(mission.getId()));
		
		// Pas de vaisseaux, inutile d'aller plus loin
		if (mission.getSpaceships() == null)
			return null;

		// Suppression des vaisseaux déjà en mission
		Predicate<Spaceship> spaceshipPredicate = p -> spaceshipsRepository.isInAnotherMission(mission.getId(), p.getId());
		mission.getSpaceships().removeIf(spaceshipPredicate);

		// Création de la liste des vaisseaux par mission à enregistrer
		List<MissionSpaceships> missionSpaceships = new ArrayList<MissionSpaceships>();

		// Gestion des vaisseaux suivant le statut de mission
		switch (mission.getState().getId()) {
		// Backlog : Les vaisseaux n'ont pas de date
		case 1:
			missionSpaceshipsRepository.deleteByIdMission(mission.getId());
			for (Spaceship spaceship : mission.getSpaceships())
				missionSpaceships.add(new MissionSpaceships(mission.getId(), spaceship.getId(), null));
			missionSpaceshipsRepository.save(missionSpaceships);
			break;
		// In progress : Les vaisseaux ont une date de sortie
		case 2:
			// Récupération des vaisseaux enregistrés poru la mission
			List<MissionSpaceships> savedMissionSpaceships = missionSpaceshipsRepository
					.findByIdMission(mission.getId());

			// Suppression des éléments communs
			for (MissionSpaceships savedMissionSpaceship : savedMissionSpaceships) {
				// Predicate<Spaceship> spaceshipPredicate = p -> p.getId() == savedMissionSpaceship.getIdSpaceship();
				// mission.getSpaceships().removeIf(spaceshipPredicate);
				mission.getSpaceships().remove(savedMissionSpaceships.indexOf(savedMissionSpaceship));
				savedMissionSpaceships.remove(savedMissionSpaceships.indexOf(savedMissionSpaceship));
			}

			// Suppression des vaisseaux ne figurant plus dans la liste
			missionSpaceshipsRepository.delete(savedMissionSpaceships);

			// Sauvegarde des nouveaux vaisseaux rattachés à la mission
			for (Spaceship spaceship : mission.getSpaceships())
				missionSpaceships.add(new MissionSpaceships(mission.getId(), spaceship.getId(), new Date()));
			missionSpaceshipsRepository.save(missionSpaceships);

			break;
		// Failed et Accomplished : On retire les vaisseaux des missions (retour à la base)
		case 3:
			missionSpaceshipsRepository.deleteByIdMission(mission.getId());
			break;
		case 4:
			missionSpaceshipsRepository.deleteByIdMission(mission.getId());
			break;
		}

		// Retourne les vaisseaux enregistrés
		return spaceshipsRepository.findByIdMission(mission.getId());

	}

}
