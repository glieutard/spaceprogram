/**
 * 
 */
package com.spaceprogram.model.spaceship.engine;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author GLieutard
 * 
 * Id Entity SpaceshipEngines
 *
 */
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiObject(description = "Id Entity SpaceshipEngines", show = false)
public class IdSpaceshipEngines implements Serializable {

	/**
	 * Id de version
	 */
	private static final long serialVersionUID = 3691567717791099817L;
	
	/**
	 * id Spaceship
	 */
	@ApiObjectField(description = "Id Spaceship")
	private Integer idSpaceship;
	
	/**
	 * Id Engine
	 */
	@ApiObjectField(description = "Id Engine")
	private Integer idEngine;

	/**
	 * @return the idSpaceship
	 */
	public Integer getIdSpaceship() {
		return idSpaceship;
	}

	/**
	 * @param idSpaceship the idSpaceship to set
	 */
	public void setIdSpaceship(Integer idSpaceship) {
		this.idSpaceship = idSpaceship;
	}

	/**
	 * @return the idEngine
	 */
	public Integer getIdEngine() {
		return idEngine;
	}

	/**
	 * @param idEngine the idEngine to set
	 */
	public void setIdEngine(Integer idEngine) {
		this.idEngine = idEngine;
	}

}
