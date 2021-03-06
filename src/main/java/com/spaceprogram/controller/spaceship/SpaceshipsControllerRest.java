/**
 * 
 */
package com.spaceprogram.controller.spaceship;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
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

import com.spaceprogram.model.crew.Crew;
import com.spaceprogram.model.engine.Engine;
import com.spaceprogram.model.module.Module;
import com.spaceprogram.model.spaceship.Spaceship;
import com.spaceprogram.repository.crew.CrewsRepository;
import com.spaceprogram.repository.engine.EnginesRepository;
import com.spaceprogram.repository.module.ModulesRepository;
import com.spaceprogram.repository.spaceship.SpaceshipsRepository;
import com.spaceprogram.repository.spaceship.type.SpaceshipTypesRepository;

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

	// Entity Manager for audit
	@PersistenceContext
	private EntityManager entityManager;
	
	// Injection respository
	@Autowired
	private SpaceshipsRepository spaceshipsRepository;
	
	@Autowired
	private SpaceshipTypesRepository spaceshipTypesRepository;
	
	@Autowired
	private CrewsRepository crewsRepository;
	
	@Autowired
	private EnginesRepository enginesRepository;
	
	@Autowired
	private ModulesRepository modulesRepository;
	
	/**
	 * Get all spaceships
	 * 
	 * @Return List<Spaceship>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.GET)
	@ApiMethod(description = "Get all spaceships")
	public @ApiResponseObject Iterable<Spaceship> getSpaceships(
			@ApiQueryParam @RequestParam(value = "detail", required = false) String detail) {

		// Récupération des vaisseaux
		Iterable<Spaceship> spaceships = spaceshipsRepository.findAll();
		
		// Pour éviter le null pointer
		if (detail == null)
			detail = new String();
		
		// Si NON option détail à full, on ne renvoit pas l'équipage, les moteurs et les modules
		if (!detail.equals("full")) {
			for (Spaceship spaceship : spaceships) {
				spaceship.setCrews(null);
				spaceship.setEngines(null);
				spaceship.setModules(null);
			}
		}
		
		// Retour des vaisseaux
		return spaceships;
	}
	
	/**
	 * Get spaceship
	 * 
	 * @Return Spaceship
	 * 
	 */
	@RequestMapping(value = path + "/{id}", method = RequestMethod.GET)
	@ApiMethod(description = "Get spaceships")
	public @ApiResponseObject Spaceship getSpaceship(
			@ApiPathParam @PathVariable("id") Integer id,
			@ApiQueryParam @RequestParam(value = "detail", required = false) String detail) {

		// Récupération du vaisseau
		Spaceship spaceship = spaceshipsRepository.findOne(id);

		// Pour éviter le null pointer
		if (detail == null)
			detail = new String();
		
		// Si NON option détail à full, on ne renvoit pas l'équipage, les moteurs et les modules
		if (!detail.equals("full") && spaceship != null) {
			spaceship.setCrews(null);
			spaceship.setEngines(null);
			spaceship.setModules(null);
		}

		// Retour des vaisseaux
		return spaceship;
	}
	
	/**
	 * Put spaceships
	 * 
	 * @Return List<Spaceship>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.PUT)
	@ApiMethod(description = "Put spaceships")
	public @ApiResponseObject Iterable<Spaceship> putSpaceships(@RequestBody(required = true) List<Spaceship> spaceships) {

		// Suppression des enregistrement dont l'id n'existe pas
		Predicate<Spaceship> spaceshipPredicateId = p -> p.getId() == null || spaceshipsRepository.countById(p.getId()) != 1;
		spaceships.removeIf(spaceshipPredicateId);
		
		// Liste des membres d'équipages à enregistrer pour supprimer les éventuels doublons envoyés
		List<Crew> crews = new ArrayList<Crew>();

		// Test des membres d'équipage, des moteurs et des modules
		for (Spaceship spaceship : spaceships) {

			// On retire tous les membres d'équipage avec un id à null ou inexistant,
			// qu'il est déjà sur un autre vaisseau ou qu'il a été intégré dans un autre vaisseau à sauvegardé
			if (spaceship.getCrews() != null) {
				Predicate<Crew> crewPredicate = p -> p.getId() == null || crewsRepository.countById(p.getId()) != 1
						|| crewsRepository.isInAnotherSpaceship(spaceship.getId(), p.getId()) || crews.contains(p);
				spaceship.getCrews().removeIf(crewPredicate);

				// Insertion des membres d'équipage dans la liste pour contrôle
				crews.addAll(spaceship.getCrews());
			}

			// On retire tous les membres d'équipage avec un id à null ou inexistant
			if (spaceship.getEngines() != null) {
				Predicate<Engine> enginePredicate = p -> p.getId() == null
						|| enginesRepository.countById(p.getId()) != 1;
				spaceship.getEngines().removeIf(enginePredicate);
			}

			// On retire tous les membres d'équipage avec un id à null ou inexistant
			if (spaceship.getModules() != null) {
				Predicate<Module> modulePredicate = p -> p.getId() == null
						|| modulesRepository.countById(p.getId()) != 1;
				spaceship.getModules().removeIf(modulePredicate);
			}

		}

		// Suppression des enregistrement si moins de deux moteurs
		Predicate<Spaceship> spaceshipEnginePredicate = p -> p.getEngines() != null && p.getEngines().size() < 2;
		spaceships.removeIf(spaceshipEnginePredicate);

		// Suppression des enregistrements dont il manque une information
		Predicate<Spaceship> spaceshipInfoPredicate = p -> p.getName() == null || p.getName().equals("") 
				|| p.getType() == null || spaceshipTypesRepository.countById(p.getType().getId()) != 1;
		spaceships.removeIf(spaceshipInfoPredicate);
		
		// Récupération des listes passées à null
		for (Spaceship spaceship : spaceships) {
			
			// Récupération de l'équipage si à null
			if (spaceship.getCrews() == null)
				spaceship.setCrews(this.getCrewsBySpaceship(spaceship.getId()));

			// Récupération des moteurs si à null
			if (spaceship.getEngines() == null)
				spaceship.setEngines(this.getEnginesBySpaceship(spaceship.getId()));

			// Récupération des modules si à null
			if (spaceship.getModules() == null)
				spaceship.setModules(this.getModulesBySpaceship(spaceship.getId()));
			
		}
		
		// Sauvegarde & retour
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
	public @ApiResponseObject Iterable<Spaceship> postSpaceships(@RequestBody(required = true) List<Spaceship> spaceships) {

		// Suppression des enregistrement dont l'id n'est pas null
		Predicate<Spaceship> spaceshipPredicate = p -> p.getId() != null;
		spaceships.removeIf(spaceshipPredicate);

		// Liste des membres d'équipages à enregistrer pour supprimer les éventuels doublons envoyés
		List<Crew> crews = new ArrayList<Crew>();

		// Test des membres d'équipage, des moteurs et des modules
		for (Spaceship spaceship : spaceships) {

			// On retire tous les membres d'équipage avec un id à null ou inexistant,
			// qu'il est déjà sur un autre vaisseau ou qu'il a été intégré dans un autre vaisseau à sauvegardé
			if (spaceship.getCrews() != null) {
				Predicate<Crew> crewPredicate = p -> p.getId() == null || crewsRepository.countById(p.getId()) != 1
						|| crewsRepository.isInASpaceship(p.getId()) || crews.contains(p);
				spaceship.getCrews().removeIf(crewPredicate);

				// Insertion des membres d'équipage dans la liste pour contrôle
				crews.addAll(spaceship.getCrews());
			}

			// On retire tous les membres d'équipage avec un id à null ou inexistant
			if (spaceship.getEngines() != null) {
				Predicate<Engine> enginePredicate = p -> p.getId() == null
						|| enginesRepository.countById(p.getId()) != 1;
				spaceship.getEngines().removeIf(enginePredicate);
			}

			// On retire tous les membres d'équipage avec un id à null ou inexistant
			if (spaceship.getModules() != null) {
				Predicate<Module> modulePredicate = p -> p.getId() == null
						|| modulesRepository.countById(p.getId()) != 1;
				spaceship.getModules().removeIf(modulePredicate);
			}

		}

		// Suppression des enregistrement si moins de deux moteurs
		Predicate<Spaceship> spaceshipPredicateEngine = p -> p.getEngines() == null || p.getEngines().size() < 2;
		spaceships.removeIf(spaceshipPredicateEngine);

		// Suppression des enregistrements dont il manque une information
		Predicate<Spaceship> spaceshipInfoPredicate = p -> p.getName() == null || p.getName().equals("") 
				|| p.getType() == null || spaceshipTypesRepository.countById(p.getType().getId()) != 1;
		spaceships.removeIf(spaceshipInfoPredicate);
		
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
	public @ApiResponseObject void deleteSpaceships(@RequestBody(required = true) List<Spaceship> spaceships) {

		// Suppression des enregistrement dont l'id n'existe pas
		Predicate<Spaceship> spaceshipPredicateId = p -> spaceshipsRepository.countById(p.getId()) != 1;
		spaceships.removeIf(spaceshipPredicateId);

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
	
	/**
	 * Get modules by spaceship
	 * 
	 * @Return List<Module>
	 * 
	 */
	@RequestMapping(value = path + "/{id}/modules", method = RequestMethod.GET)
	@ApiMethod(description = "Get modules by spaceships")
	public @ApiResponseObject List<Module> getModulesBySpaceship(@ApiPathParam @PathVariable("id") Integer id) {

		return modulesRepository.findByIdSpaceship(id);
	}

	/**
	 * Get spaceship history
	 * 
	 * @param
	 * 		id int
	 * 
	 * @Return List<Spaceship>
	 * o
	 */
	@RequestMapping(value = path + "/{id}/history", method = RequestMethod.GET)
	@ApiMethod(description = "Get spaceship history")
	public @ApiResponseObject List<Spaceship> getSpaceshipHistory(@ApiPathParam @PathVariable("id") Integer id) {

		// Create the Audit Reader. It uses the EntityManager, which will be opened when
	    // starting the new Transation and closed when the Transaction finishes.
	    AuditReader reader = AuditReaderFactory.get(entityManager);

	    // Create the query
	    AuditQuery q = reader.createQuery().forRevisionsOfEntity(Spaceship.class, true, false);
	    q.add(AuditEntity.id().eq(id));
	    
	    // Search the results
	    @SuppressWarnings("unchecked")
		List<Spaceship> audit = q.getResultList();
	    
	    return audit;
	}
	
}
