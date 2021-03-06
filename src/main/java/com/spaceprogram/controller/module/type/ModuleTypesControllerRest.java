/**
 * 
 */
package com.spaceprogram.controller.module.type;

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

import com.spaceprogram.model.module.type.ModuleType;
import com.spaceprogram.repository.module.type.ModuleTypesRepository;

/**
 * @author GLieutard
 * 
 * ModuleTypes Controller Rest
 *
 */
@RestController
@Api(name = "ModuleTypesControllerRest", description = "ModuleTypes Controller Rest")
public class ModuleTypesControllerRest {

	// URL en constante
	final private static String path = "/v1/moduleTypes";
	
	// Injection respository
	@Autowired
	private ModuleTypesRepository moduleTypesRepository;
	
	/**
	 * Get all moduleTypes
	 * 
	 * @Return List<ModuleType>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.GET)
	@ApiMethod(description = "Get all spceshipTypess")
	public @ApiResponseObject Iterable<ModuleType> getModuleTypes() {

		return moduleTypesRepository.findAll();
	}
	
	/**
	 * Get moduleType
	 * 
	 * @param
	 * 		id int
	 * 
	 * @Return ModuleType
	 * 
	 */
	@RequestMapping(value = path + "/{id}", method = RequestMethod.GET)
	@ApiMethod(description = "Get moduleType")
	public @ApiResponseObject ModuleType getModuleType(@ApiPathParam @PathVariable("id") Integer id) {

		return moduleTypesRepository.findOne(id);
	}
	
	/**
	 * Post moduleTypes
	 * 
	 * @param List<ModuleType>
	 * 
	 * @Return List<ModuleType>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.POST)
	@ApiMethod(description = "Post moduleTypes")
	public @ApiResponseObject Iterable<ModuleType> postModuleTypes(@RequestBody(required = true) List<ModuleType> moduleTypes) {

		// Suppression des enregistrement dont l'id n'est pas null
		Predicate<ModuleType> moduleTypePredicate = p -> p.getId() != null;
		moduleTypes.removeIf(moduleTypePredicate);

		// Suppression des enregistrements dont il manque une information
		Predicate<ModuleType> moduleTypeInfoPredicate = p -> p.getName() == null || p.getName().equals("");
		moduleTypes.removeIf(moduleTypeInfoPredicate);
		
		return moduleTypesRepository.save(moduleTypes);
	}
	
	/**
	 * Put moduleTypes
	 * 
	 * @param List<ModuleType>
	 * 
	 * @Return List<ModuleType>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.PUT)
	@ApiMethod(description = "Put moduleTypes")
	public @ApiResponseObject Iterable<ModuleType> putModuleTypes(@RequestBody(required = true) List<ModuleType> moduleTypes) {

		// Suppression des enregistrement dont l'id n'existe pas
		Predicate<ModuleType> moduleTypePredicate = p -> moduleTypesRepository.countById(p.getId()) != 1;
		moduleTypes.removeIf(moduleTypePredicate);

		// Suppression des enregistrements dont il manque une information
		Predicate<ModuleType> moduleTypeInfoPredicate = p -> p.getName() == null || p.getName().equals("");
		moduleTypes.removeIf(moduleTypeInfoPredicate);
		
		return moduleTypesRepository.save(moduleTypes);
	}

	/**
	 * Delete moduleTypes
	 * 
	 * @param List<ModuleType>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.DELETE)
	@ApiMethod(description = "Delete moduleTypes")
	public @ApiResponseObject void deleteModuleTypes(@RequestBody(required = true) List<ModuleType> moduleTypes) {

		// Retrait des types utilisés et dont l'id n'existe pas
		Predicate<ModuleType> moduleTypePredicate = p -> moduleTypesRepository.isUsed(p.getId()) || moduleTypesRepository.countById(p.getId()) != 1;
		moduleTypes.removeIf(moduleTypePredicate);
		
		moduleTypesRepository.delete(moduleTypes);
	}
	
}
