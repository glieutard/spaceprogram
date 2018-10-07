/**
 * 
 */
package com.spaceprogram.controller.spaceship;

import java.util.List;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spaceprogram.model.spaceship.Spaceship;
import com.spaceprogram.repository.spaceship.SpaceshipsRepository;

/**
 * @author MSI Gaming
 *
 */
@RestController
@Api(name = "SpaceshipControllerRest", description = "Spacehsip Cotroller Rest")
public class SpaceshipsControllerRest {
	
	// URL en constante
	final private static String path = "/v1/spaceships";
	
	// Injection respository
	@Autowired
	private SpaceshipsRepository spaceshipsRepository;
	
	/**
	 * Get all spaceships
	 * 
	 * @Return List<Spaceship>
	 * 
	 */
	@RequestMapping(value = path, method = RequestMethod.GET)
	@ApiMethod(description = "Get all spaceships")
	public @ApiResponseObject List<Spaceship> getSpaceships() {

		return spaceshipsRepository.findAll();
	}

}
