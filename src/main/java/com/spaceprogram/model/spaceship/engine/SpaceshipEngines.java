/**
 * 
 */
package com.spaceprogram.model.spaceship.engine;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * @author GLieutard
 * 
 * Entity SpaceshipEngines
 *
 */
@Entity
@Table(name = "spaceship_engines")
@IdClass(IdSpaceshipEngines.class)
@ApiObject(name = "SpaceshipEngines", description = "Entity SpaceshipEngines", show = false)
public class SpaceshipEngines implements Serializable {

	/**
	 * Id de version
	 */
	private static final long serialVersionUID = 7181059178708601030L;

	/**
	 * Id Spaceship
	 */
	@Id
	@ApiObjectField(description = "Id Spaceship")
	private Integer idSpaceship;
	
	/**
	 * Id Engine
	 */
	@Id
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
