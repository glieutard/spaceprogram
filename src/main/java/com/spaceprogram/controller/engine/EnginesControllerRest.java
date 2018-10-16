/**
 * 
 */
package com.spaceprogram.controller.engine;

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

import com.spaceprogram.model.engine.Engine;
import com.spaceprogram.repository.engine.EnginesRepository;

/**
 * @author GLieutard
 * 
 * Engine Controlelr REST
 *
 */
@RestController
@Api(name = "EnginesControllerRest", description = "Engine Controller Rest")
public class EnginesControllerRest {
	
	// URL en constante
	final private static String path = "/v1/engines";
	
	// Injection respository
	@Autowired
	private EnginesRepository enginesRepository;
	
	/**
	 * Get all engins
	 * 
	 * @Return List<Engine>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.GET)
	@ApiMethod(description = "Get all engines")
	public @ApiResponseObject Iterable<Engine> getEngines() {

		return enginesRepository.findAll();
	}
	
	/**
	 * Get engine
	 * 
	 * @param
	 * 		id int
	 * 
	 * @Return Engine
	 * 
	 */
	@RequestMapping(value = path + "/{id}", method = RequestMethod.GET)
	@ApiMethod(description = "Get engine")
	public @ApiResponseObject Engine getEngine(@ApiPathParam @PathVariable("id") Integer id) {

		return enginesRepository.findOne(id);
	}
	
	/**
	 * Post engines
	 * 
	 * @param List<Engine>
	 * 
	 * @Return List<Engien>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.POST)
	@ApiMethod(description = "Post engiens")
	public @ApiResponseObject Iterable<Engine> postEngines(@RequestBody(required = true) List<Engine> engines) {

		// Suppression des enregistrement dont l'id n'est pas null
		Predicate<Engine> enginePredicate = p -> p.getId() != null;
		engines.removeIf(enginePredicate);

		// Suppression des enregistrements dont il manque une information
		Predicate<Engine> engineInfoPredicate = p -> p.getName() == null || p.getName().equals("")
				|| p.getHorsePower() == null
				|| p.getWeight() == null || p.getWeight().equals(0);
		engines.removeIf(engineInfoPredicate);
		
		return enginesRepository.save(engines);
	}
	
	/**
	 * Put engines
	 * 
	 * @param List<Engine>
	 * 
	 * @Return List<Engine>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.PUT)
	@ApiMethod(description = "Put engines")
	public @ApiResponseObject Iterable<Engine> putEngines(@RequestBody(required = true) List<Engine> engines) {

		// Suppression des enregistrement dont l'id n'existe pas
		Predicate<Engine> enginePredicate = p -> enginesRepository.countById(p.getId()) != 1;
		engines.removeIf(enginePredicate);

		// Suppression des enregistrements dont il manque une information
		Predicate<Engine> engineInfoPredicate = p -> p.getName() == null || p.getName().equals("")
				|| p.getHorsePower() == null
				|| p.getWeight() == null || p.getWeight().equals(0);
		engines.removeIf(engineInfoPredicate);
		
		return enginesRepository.save(engines);
	}

	/**
	 * Delete engines
	 * 
	 * @param List<Engine>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.DELETE)
	@ApiMethod(description = "Delete engines")
	public @ApiResponseObject void deleteEngines(@RequestBody(required = true) List<Engine> engines) {

		// Retrait des moteurs utilisés && dont l'id n'existe pas
		Predicate<Engine> enginePredicate = p -> enginesRepository.isUsed(p.getId()) || enginesRepository.countById(p.getId()) != 1;
		engines.removeIf(enginePredicate);
		
		enginesRepository.delete(engines);
	}
	
}
