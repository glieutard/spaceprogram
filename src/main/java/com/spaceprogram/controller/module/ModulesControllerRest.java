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
import com.spaceprogram.repository.module.type.ModuleTypesRepository;

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
	
	@Autowired
	private ModuleTypesRepository moduleTypesRepository;
	
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

		// Suppression des enregistrements dont il manque une information
		Predicate<Module> moduleInfoPredicate = p -> p.getName() == null || p.getName().equals("")
				|| p.getType() == null || p.getType().getId() == null || moduleTypesRepository.countById(p.getType().getId()) != 1;
		modules.removeIf(moduleInfoPredicate);
		
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

		// Suppression des enregistrement dont l'id n'existe pas
		Predicate<Module> modulePredicate = p -> modulesRepository.countById(p.getId()) != 1;
		modules.removeIf(modulePredicate);

		// Suppression des enregistrements dont il manque une information
		Predicate<Module> moduleInfoPredicate = p -> p.getName() == null || p.getName().equals("")
				|| p.getType() == null || p.getType().getId() == null || moduleTypesRepository.countById(p.getType().getId()) != 1;
		modules.removeIf(moduleInfoPredicate);
		
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

		// Retrait des jobs utilisés et dont l'id n'existe pas
		Predicate<Module> modulePredicate = p -> modulesRepository.isUsed(p.getId()) || modulesRepository.countById(p.getId()) != 1;
		modules.removeIf(modulePredicate);
		
		modulesRepository.delete(modules);
	}

}
