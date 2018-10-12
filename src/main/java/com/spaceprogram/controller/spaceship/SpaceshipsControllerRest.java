/**
 * 
 */
package com.spaceprogram.controller.spaceship;

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
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spaceprogram.model.crew.Crew;
import com.spaceprogram.model.engine.Engine;
import com.spaceprogram.model.module.Module;
import com.spaceprogram.model.spaceship.Spaceship;
import com.spaceprogram.repository.crew.CrewsRepository;
import com.spaceprogram.repository.engine.EnginesRepository;
import com.spaceprogram.repository.module.ModulesRepository;
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

	// Entity Manager for audit
	@PersistenceContext
	private EntityManager entityManager;
	
	// Injection respository
	@Autowired
	private SpaceshipsRepository spaceshipsRepository;
	
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
	public @ApiResponseObject Iterable<Spaceship> getSpaceships() {

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
	public @ApiResponseObject Iterable<Spaceship> putSpaceships(@RequestBody(required = true) List<Spaceship> spaceships) {

		// Suppression des enregistrement dont l'id est null ou à 0
		Predicate<Spaceship> spaceshipPredicateId = p -> p.getId() == null || p.getId() == 0;
		spaceships.removeIf(spaceshipPredicateId);

		// Suppression des enregistrement si moins de deux moteurs
		Predicate<Spaceship> spaceshipPredicateEngine = p -> p.getEngines() == null || p.getEngines().size() < 2;
		spaceships.removeIf(spaceshipPredicateEngine);
		
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

		// Suppression des enregistrement si moins de deux moteurs
		Predicate<Spaceship> spaceshipPredicateEngine = p -> p.getEngines() != null && p.getEngines().size() < 2;
		spaceships.removeIf(spaceshipPredicateEngine);
		
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
