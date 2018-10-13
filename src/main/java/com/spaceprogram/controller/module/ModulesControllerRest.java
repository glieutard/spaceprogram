/**
 * 
 */
package com.spaceprogram.controller.module;

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

import com.spaceprogram.model.module.Module;
import com.spaceprogram.repository.module.ModulesRepository;

/**
 * @author GLieutard
 * 
 * Modules Controller REST
 *
 */
@RestController
@Api(name = "ModulesControllerRest", description = "Modules Controller REST")
public class ModulesControllerRest {

	// URL en constante
	final private static String path = "/v1/modules";
	
	// Injection respository
	@Autowired
	private ModulesRepository modulesRepository;
	
	/**
	 * Get all modules
	 * 
	 * @Return List<Module>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.GET)
	@ApiMethod(description = "Get all modules")
	public @ApiResponseObject Iterable<Module> getModules() {

		return modulesRepository.findAll();
	}
	
	/**
	 * Get module
	 * 
	 * @param
	 * 		id int
	 * 
	 * @Return Module
	 * 
	 */
	@RequestMapping(value = path + "/{id}", method = RequestMethod.GET)
	@ApiMethod(description = "Get module")
	public @ApiResponseObject Module getModule(@ApiPathParam @PathVariable("id") Integer id) {

		return modulesRepository.findOne(id);
	}
	
	/**
	 * Post modules
	 * 
	 * @param List<Module>
	 * 
	 * @Return List<Module>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.POST)
	@ApiMethod(description = "Post engiens")
	public @ApiResponseObject Iterable<Module> postModules(@RequestBody(required = true) List<Module> modules) {

		// Suppression des enregistrement dont l'id n'est pas null
		Predicate<Module> modulePredicate = p -> p.getId() != null;
		modules.removeIf(modulePredicate);
		
		return modulesRepository.save(modules);
	}
	
	/**
	 * Put modules
	 * 
	 * @param List<Module>
	 * 
	 * @Return List<Module>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.PUT)
	@ApiMethod(description = "Put modules")
	public @ApiResponseObject Iterable<Module> putModules(@RequestBody(required = true) List<Module> modules) {

		// Suppression des enregistrement dont l'id est null ou à 0
		Predicate<Module> modulePredicate = p -> p.getId() == null || p.getId() == 0;
		modules.removeIf(modulePredicate);
		
		return modulesRepository.save(modules);
	}

	/**
	 * Delete modules
	 * 
	 * @param List<Modules>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.DELETE)
	@ApiMethod(description = "Delete modules")
	public @ApiResponseObject void deleteModules(@RequestBody(required = true) List<Module> modules) {

		// Retrait des jobs utilisés
		Predicate<Module> modulePredicate = p -> modulesRepository.isUsed(p.getId());
		modules.removeIf(modulePredicate);
		
		modulesRepository.delete(modules);
	}

}
