/**
 * 
 */
package com.spaceprogram.controller.spaceship.coordinates;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.spaceprogram.model.coordinates.Coordinates;
import com.spaceprogram.model.spaceship.Spaceship;

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
	private Integer ratio;
	
	/**
	 * Get coordinates by spaceship
	 * 
	 * @param Spaceship
	 *            spaceship
	 * 
	 * @Return Coordinates
	 * 
	 */
	public Coordinates getBySpaceship(Spaceship spaceship) {

		// Retourne les coordonnées
		return new Coordinates();

	}

}
